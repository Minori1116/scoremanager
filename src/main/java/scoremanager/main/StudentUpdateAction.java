package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. リクエストパラメータ（学籍番号）の取得
        String no = request.getParameter("no");

        // 2. ビジネスロジック
        StudentDao sDao = new StudentDao();
        // 学籍番号をキーに学生情報を1件取得
        Student student = sDao.get(no);

        // クラス番号一覧を取得（更新画面のプルダウン用）
        ClassNumDao cDao = new ClassNumDao();
        List<String> class_num_set = cDao.filter(teacher.getSchool());

        // 3. レスポンス設定（JSPへ渡すデータ）
        request.setAttribute("student", student);
        request.setAttribute("class_num_set", class_num_set);

        // 4. 更新画面（jsp）を表示
        return "student_update.jsp";
    }
}