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
     * No.10の指示を守りつつ、null対策としてCOALESCEを使用します。
     * これにより、t.class_numがnullならst.class_numを表示します。
     */
    private String baseSql = "select "
            + "st.ent_year, st.no as student_no, st.name as student_name, "
            + "COALESCE(t.class_num, st.class_num) as class_num, " // ここが重要
            + "t.no as test_no, t.point "
            + "from student st "
            + "left join test t on st.no = t.student_no and t.subject_cd = ? and st.school_cd = t.school_cd "
            + "where st.ent_year = ? and st.class_num = ? and st.school_cd = ? "
            + "order by st.no asc, t.no asc";

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(baseSql)) {
            statement.setString(1, subject.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setString(4, school.getCd());
            ResultSet rSet = statement.executeQuery();
            list = postFilter(rSet);
        }
        return list;
    }

    private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        String lastStudentNo = "";
        TestListSubject tls = null;

        while (rSet.next()) {
            String currentStudentNo = rSet.getString("student_no");
            if (!currentStudentNo.equals(lastStudentNo)) {
                tls = new TestListSubject();
                tls.setEntYear(rSet.getInt("ent_year"));
                tls.setStudentNo(currentStudentNo);
                tls.setStudentName(rSet.getString("student_name"));
                // ここでCOALESCEの結果（TEST優先、なければSTUDENT）がセットされる
                tls.setClassNum(rSet.getString("class_num"));
                
                list.add(tls);
                lastStudentNo = currentStudentNo;
            }
            int testNo = rSet.getInt("test_no");
            if (!rSet.wasNull()) {
                tls.putPoint(testNo, rSet.getInt("point"));
            }
        }
        return list;
    }
}