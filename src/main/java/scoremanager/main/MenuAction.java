package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class MenuAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user"); // ログインユーザー
        
        
        if (teacher == null) {
            // テスト用に仮のユーザーを作成
            teacher = new Teacher();
            School school = new School();
            school.setCd("tes"); // データベースにある学校コード
            teacher.setSchool(school);
        }
        
        
        // 1. リクエストパラメータの取得
        String entYearStr = request.getParameter("f1"); // 入学年度
        String classNum = request.getParameter("f2"); // クラス
        String isAttendStr = request.getParameter("f3"); // 在学中フラグ
        
        int entYear = 0;
        boolean isAttend = false;
        List<Student> students = null; // 学生リスト
        LocalDate todaysDate = LocalDate.now(); // 今日の日付
        int year = todaysDate.getYear(); // 現在の年
        StudentDao sDao = new StudentDao(); // 学生DAO
        ClassNumDao cDao = new ClassNumDao(); // クラス番号DAO
        Map<String, String> errors = new HashMap<>(); // エラーメッセージ

        // 2. ビジネスロジック
        // 入学年度が選択されている場合、int型に変換
        if (entYearStr != null && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }
        // 在学中チェックボックスの判定
        if (isAttendStr != null) {
            isAttend = true;
        }

        // クラス番号一覧を取得（プルダウン用）
        List<String> class_num_set = cDao.filter(teacher.getSchool());
        
        // 入学年度リストを生成（プルダウン用：現在の10年前〜現在まで）
        List<Integer> ent_year_set = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            ent_year_set.add(i);
        }

        // 検索条件に応じた学生リストの取得
        if (entYear != 0 && !classNum.equals("0")) {
            // 入学年度とクラスの両方が指定されている場合
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && classNum.equals("0")) {
            // 入学年度のみ指定されている場合
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            // 全指定なし（在学フラグのみ）の場合
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        // 3. レスポンス（JSPへ渡すデータ）の設定
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", isAttendStr);
        request.setAttribute("students", students);
        request.setAttribute("class_num_set", class_num_set);
        request.setAttribute("ent_year_set", ent_year_set);
        request.setAttribute("errors", errors);

        // 4. JSPのパスを返す
        return "menu.jsp";
    }
}