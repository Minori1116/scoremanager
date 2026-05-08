package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements Serializable {

    private int entYear;
    private String studentNo;
    private String studentName;
    private String classNum;
    private Map<Integer, Integer> points = new HashMap<>();

    public TestListSubject() {
    }

    // --- ゲッター・セッター ---

    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Map<Integer, Integer> getPoints() {
        return points;
    }

    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }

    // --- 個別操作メソッド ---

    /**
     * 指定された回数(key)の点数を取得する。
     * クラス図の戻り値がString型のため、点数が存在しない場合は空文字やハイフンを返す想定。
     */
    public String getPoint(int key) {
        Integer point = points.get(key);
        if (point == null) {
            return "-"; //状況に合わせて変更してください
        }
        return String.valueOf(point);
    }

    /**
     * 指定された回数(key)に対して点数(value)をセットする。
     * (Mapのput操作)
     */
    public void putPoint(int key, int value) {
        this.points.put(key, value);
    }
}