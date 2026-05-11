package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user"); // ログインユーザー
        
        // 1. リクエストパラメータの取得
        String entYearStr = request.getParameter("f1"); // 入学年度
        String classNum = request.getParameter("f2"); // クラス
        String isAttendStr = request.getParameter("f3"); // 在学中フラグ
        
        if (classNum == null) {
            classNum = "0";
        } 
        
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
        
        // 在学中チェックボックスの判定（f3にチェックが入っている場合のみ true）
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

        // --- 【注目】検索条件に応じた学生リストの取得ロジック修正 ---
        if (entYear != 0 && !classNum.equals("0")) {
            // A. 入学年度とクラスの両方が指定されている場合
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
            
        } else if (entYear != 0 && classNum.equals("0")) {
            // B. 入学年度のみ指定されている場合
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
            
        } else if (entYearStr == null && classNum.equals("0") && isAttendStr == null) {
            // C. 【初期表示】（すべてのパラメータが送られてきていないとき）
            // 在学チェックの有無に関わらず、学校の全生徒（在学中も退学中もすべて）を表示する
            // 多くの設計では、第2引数（isAttend）を false にすると全表示、または専用の全取得メソッドを呼び出します。
            // ここでは在学不問とするため、一度DBの全データを取得するロジック（または退学含む全取得）にします。
            students = sDao.filter(teacher.getSchool(), false); 
            
        } else if (entYear == 0 && classNum.equals("0")) {
            // D. 絞り込み条件（年度・クラス）が未指定で「絞込み」ボタンが押された場合
            if (isAttendStr != null) {
                // 「在学中」だけにチェックしてボタンを押したなら、在学中のみ
                students = sDao.filter(teacher.getSchool(), true);
            } else {
                // チェックも何もせずボタンを押したなら、全員表示
                students = sDao.filter(teacher.getSchool(), false);
            }
            
        } else {
            // E. クラスだけ選んで年度を選んでいないエラーパターン
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            // エラー時も全生徒（在学不問）を表示
            students = sDao.filter(teacher.getSchool(), false);
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
        return "student_list.jsp";
    }
}