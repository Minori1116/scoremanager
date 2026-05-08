package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends DAO {

    /**
     * 基本となるSQL文
     * 学生情報とテストの回数、点数を取得します。
     */
    private String baseSql = "select "
            + "st.ent_year, st.no as student_no, st.name as student_name, st.class_num, "
            + "t.no as test_no, t.point "
            + "from student st "
            + "left join test t on st.no = t.student_no and t.subject_cd = ? "
            + "where st.ent_year = ? and st.class_num = ? and st.school_cd = ? "
            + "order by st.no asc, t.no asc";

    /**
     * 指定された条件で学生別の成績一覧を取得する
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(baseSql)) {
            
            // プレースホルダの設定
            statement.setString(1, subject.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setString(4, school.getCd());
            
            ResultSet rSet = statement.executeQuery();
            list = postFilter(rSet);
        }
        return list;
    }

    /**
     * ResultSetの結果を学生単位で集約してリストに変換する
     */
    private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        String lastStudentNo = ""; // 直前に処理した学生番号
        TestListSubject tls = null;

        while (rSet.next()) {
            String currentStudentNo = rSet.getString("student_no");

            // 新しい学生に切り替わったタイミングでBeanを作成
            if (!currentStudentNo.equals(lastStudentNo)) {
                tls = new TestListSubject();
                tls.setEntYear(rSet.getInt("ent_year"));
                tls.setStudentNo(currentStudentNo);
                tls.setStudentName(rSet.getString("student_name"));
                tls.setClassNum(rSet.getString("class_num"));
                
                list.add(tls);
                lastStudentNo = currentStudentNo;
            }

            // テスト結果が存在する場合（点数がNULLでない場合）、Mapに追加
            // test_noが取得できているか確認（外部結合のためNULLの可能性がある）
            int testNo = rSet.getInt("test_no");
            if (!rSet.wasNull()) {
                tls.putPoint(testNo, rSet.getInt("point"));
            }
        }
        return list;
    }
}