package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends DAO {

    /**
     * 基本となるSQL文
     */
    private String baseSql = "select * from student where school_cd=? ";

    /**
     * 学生番号から学生一人を取得する
     */
    public Student get(String no) throws Exception {
        Student student = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("select * from student where no=?");
            statement.setString(1, no);
            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                student = new Student();
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                student.setAttend(rSet.getBoolean("is_attend"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return student;
    }

    /**
     * ResultSetをStudentのリストに変換する共通メソッド
     */
    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        List<Student> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Student student = new Student();
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                student.setAttend(rSet.getBoolean("is_attend"));
                student.setSchool(school);
                list.add(student);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    /**
     * フィルター：入学年度、クラス、在学状態すべてを指定
     */
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        String condition = "and ent_year=? and class_num=? and is_attend=? ";
        String order = "order by no asc";

        try {
            statement = connection.prepareStatement(baseSql + condition + order);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setBoolean(4, isAttend);
            ResultSet rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return list;
    }

    /**
     * フィルター：入学年度、在学状態を指定
     */
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        String condition = "and ent_year=? and is_attend=? ";
        String order = "order by no asc";

        try {
            statement = connection.prepareStatement(baseSql + condition + order);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setBoolean(3, isAttend);
            ResultSet rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return list;
    }

    /**
     * フィルター：在学状態のみ指定
     */
    public List<Student> filter(School school, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        String condition = "and is_attend=? ";
        String order = "order by no asc";

        try {
            statement = connection.prepareStatement(baseSql + condition + order);
            statement.setString(1, school.getCd());
            statement.setBoolean(2, isAttend);
            ResultSet rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return list;
    }

    /**
     * 学生情報の保存（登録・更新）
     */
    public boolean save(Student student) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存の学生かチェック
            Student existing = get(student.getNo());
            if (existing == null) {
                // 新規登録
                statement = connection.prepareStatement(
                    "insert into student (no, name, ent_year, class_num, is_attend, school_cd) values (?, ?, ?, ?, ?, ?)"
                );
                statement.setString(1, student.getNo());
                statement.setString(2, student.getName());
                statement.setInt(3, student.getEntYear());
                statement.setString(4, student.getClassNum());
                statement.setBoolean(5, student.isAttend());
                statement.setString(6, student.getSchool().getCd());
            } else {
                // 更新
                statement = connection.prepareStatement(
                    "update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?"
                );
                statement.setString(1, student.getName());
                statement.setInt(2, student.getEntYear());
                statement.setString(3, student.getClassNum());
                statement.setBoolean(4, student.isAttend());
                statement.setString(5, student.getNo());
            }
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count > 0;
    }
    /**
     * フィルター：学校のみ指定（全件取得）
     */
    public List<Student> filter(School school) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        String order = "order by no asc";

        try {
            // school_cd のみで検索（在学条件なし）
            statement = connection.prepareStatement(baseSql + order);
            statement.setString(1, school.getCd());
            ResultSet rSet = statement.executeQuery();

            list = postFilter(rSet, school);

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }
}