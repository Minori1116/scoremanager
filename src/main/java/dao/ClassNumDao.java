package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends DAO {
    
    /**
     * 特定のクラス情報を取得する処理
     */
    public ClassNum get(String class_num, School school) throws Exception {
        ClassNum classNum = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            // クラス番号と学校コードの両方で検索
            statement = connection.prepareStatement(
                "select * from class_num where class_num=? and school_cd=?"
            );
            statement.setString(1, class_num);
            statement.setString(2, school.getCd());
            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                classNum = new ClassNum();
                classNum.setClass_num(rSet.getString("class_num"));
                classNum.setSchool(school);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return classNum;
    }

    /**
     * 学校に所属するクラス番号の一覧を取得する処理
     */
    public List<String> filter(School school) throws Exception {
        List<String> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(
                "select class_num from class_num where school_cd=? order by class_num asc"
            );
            statement.setString(1, school.getCd());
            rSet = statement.executeQuery();

            while (rSet.next()) {
                list.add(rSet.getString("class_num"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return list;
    }

    /**
     * クラス情報を保存する処理（新規登録・更新の自動切り替え）
     */
    public boolean save(ClassNum classNum) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存のクラスかチェック
            ClassNum existing = get(classNum.getClass_num(), classNum.getSchool());
            
            if (existing == null) {
                // 新規登録
                statement = connection.prepareStatement(
                    "insert into class_num (class_num, school_cd) values (?, ?)"
                );
                statement.setString(1, classNum.getClass_num());
                statement.setString(2, classNum.getSchool().getCd());
            } else {
                // 更新（このメソッドではクラス番号以外の項目がないため、実質的には上書き確認）
                statement = connection.prepareStatement(
                    "update class_num set class_num=? where class_num=? and school_cd=?"
                );
                statement.setString(1, classNum.getClass_num());
                statement.setString(2, classNum.getClass_num());
                statement.setString(3, classNum.getSchool().getCd());
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
     * クラス番号の変更を伴う保存（更新）処理
     */
    public boolean save(ClassNum classNum, String newClassNum) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 主キーである class_num を変更するための SQL
            statement = connection.prepareStatement(
                "update class_num set class_num=? where class_num=? and school_cd=?"
            );
            statement.setString(1, newClassNum); // 新しい番号
            statement.setString(2, classNum.getClass_num()); // 元の番号
            statement.setString(3, classNum.getSchool().getCd());
            
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return count > 0;
    }
}