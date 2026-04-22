package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDao extends DAO {

    /**
     * IDとパスワードに一致する教員を取得する（ログイン認証）
     */
    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            // 教員テーブル(teacher)と学校テーブル(school)を結合して取得
            // school_cdをキーにして学校名なども取得できるようにします
            String sql = "SELECT t.id, t.password, t.name, t.school_cd, s.name AS school_name " +
                         "FROM teacher t " +
                         "JOIN school s ON t.school_cd = s.cd " +
                         "WHERE t.id = ? AND t.password = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, password);

            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                // 教員Beanの生成
                teacher = new Teacher();
                teacher.setId(rSet.getString("id"));
                teacher.setPassword(rSet.getString("password"));
                teacher.setName(rSet.getString("name"));

                // 学校Beanの生成とセット
                School school = new School();
                school.setCd(rSet.getString("school_cd"));
                school.setName(rSet.getString("school_name"));
                teacher.setSchool(school);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return teacher;
    }

    /**
     * IDから教員を一人取得する（必要に応じて使用）
     */
    public Teacher get(String id) throws Exception {
        Teacher teacher = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "SELECT t.id, t.name, t.school_cd, s.name AS school_name " +
                "FROM teacher t JOIN school s ON t.school_cd = s.cd WHERE t.id = ?"
            );
            statement.setString(1, id);
            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                teacher = new Teacher();
                teacher.setId(rSet.getString("id"));
                teacher.setName(rSet.getString("name"));
                
                School school = new School();
                school.setCd(rSet.getString("school_cd"));
                school.setName(rSet.getString("school_name"));
                teacher.setSchool(school);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return teacher;
    }
}