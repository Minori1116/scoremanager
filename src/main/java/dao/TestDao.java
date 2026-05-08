package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends DAO {

    /**
     * 基本となるSQL文
     */
    private String baseSql = "select "
            + "st.ent_year, st.class_num, st.no as student_no, st.name as student_name, "
            + "t.subject_cd, t.no as test_no, t.point "
            + "from test t "
            + "inner join student st on t.student_no = st.no "
            + "inner join subject su on t.subject_cd = su.cd and st.school_cd = su.school_cd ";

    /**
     * 指定された条件に合致する成績情報を1件取得する
     */
    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Test test = null;
        String sql = baseSql + "where t.student_no=? and t.subject_cd=? and st.school_cd=? and t.no=?";
        
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getNo());
            statement.setString(2, subject.getCd());
            statement.setString(3, school.getCd());
            statement.setInt(4, no);

            ResultSet rSet = statement.executeQuery();
            List<Test> list = postFilter(rSet, school);
            if (!list.isEmpty()) {
                test = list.get(0);
            }
        }
        return test;
    }

    /**
     * ResultSetの結果をリストに変換する共通プライベートメソッド
     */
    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        while (rSet.next()) {
            Test test = new Test();
            // Studentの設定
            Student student = new Student();
            student.setNo(rSet.getString("student_no"));
            student.setName(rSet.getString("student_name"));
            student.setEntYear(rSet.getInt("ent_year"));
            student.setClassNum(rSet.getString("class_num"));
            
            // Subjectの設定
            Subject subject = new Subject();
            subject.setCd(rSet.getString("subject_cd"));
            
            test.setStudent(student);
            test.setSubject(subject);
            test.setSchool(school);
            test.setNo(rSet.getInt("test_no"));
            test.setPoint(rSet.getInt("point"));
            list.add(test);
        }
        return list;
    }

    /**
     * 入学年度、クラス、科目、回数を指定して成績リストを取得する
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        String sql = baseSql + "where st.ent_year=? and st.class_num=? and t.subject_cd=? and t.no=? and st.school_cd=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject.getCd());
            statement.setInt(4, num);
            statement.setString(5, school.getCd());

            ResultSet rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        }
        return list;
    }

    /**
     * 成績リストをまとめて保存（トランザクション管理）
     */
    public boolean save(List<Test> list) throws Exception {
        Connection connection = getConnection();
        boolean result = true;
        try {
            // オートコミットをオフにしてトランザクション開始
            connection.setAutoCommit(false);
            
            for (Test test : list) {
                if (!save(test, connection)) {
                    result = false;
                    break;
                }
            }

            if (result) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return result;
    }

    /**
     * 単一の成績情報を保存（内部用）
     */
    private boolean save(Test test, Connection connection) throws SQLException {
        int count = 0;
        String sql = "merge into test (student_no, subject_cd, school_cd, no, point) "
                   + "key (student_no, subject_cd, school_cd, no) values (?, ?, ?, ?, ?)";
        
        // 注: MERGE文が使えない環境の場合は get で存在確認して INSERT or UPDATE に分岐
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, test.getStudent().getNo());
            statement.setString(2, test.getSubject().getCd());
            statement.setString(3, test.getSchool().getCd());
            statement.setInt(4, test.getNo());
            statement.setInt(5, test.getPoint());
            count = statement.executeUpdate();
        }
        return count > 0;
    }
}