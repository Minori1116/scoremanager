package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends DAO {

    /**
     * 基本となるSQL文
     * 科目テーブルと結合して科目名を取得し、特定の学生番号で絞り込みます。
     */
    private String baseSql = "select "
            + "sj.name as subject_name, sj.cd as subject_cd, "
            + "t.no as test_no, t.point "
            + "from test t "
            + "inner join subject sj on t.subject_cd = sj.cd and t.school_cd = sj.school_cd "
            + "where t.student_no = ? "
            + "order by sj.cd asc, t.no asc";

    /**
     * 指定された学生の成績一覧を取得します。
     * @param student 学生情報のBean
     * @return TestListStudentのリスト
     * @throws Exception データベースエラー
     */
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        
        // DAO継承元のgetConnection()を使用して接続
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(baseSql)) {
            
            // SQLの「?」に学生番号をセット
            statement.setString(1, student.getNo());
            
            // SQL実行
            ResultSet rSet = statement.executeQuery();
            
            // ResultSetをBeanのリストに変換
            list = postFilter(rSet);
        }
        return list;
    }

    /**
     * 取得結果（ResultSet）をTestListStudentのリストに詰め替えます。
     * @param rSet SQL実行結果
     * @return 設定済みのTestListStudentリスト
     * @throws Exception データベースエラー
     */
    private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>();

        while (rSet.next()) {
            // 1行につき1つのBeanを作成
            TestListStudent tls = new TestListStudent();
            
            // Beanのセッターに値をセット
            tls.setSubjectName(rSet.getString("subject_name"));
            tls.setSubjectCd(rSet.getString("subject_cd"));
            tls.setNum(rSet.getInt("test_no"));
            tls.setPoint(rSet.getInt("point"));
            
            // リストに追加
            list.add(tls);
        }
        return list;
    }
}