package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. リクエストパラメータ（科目番号）の取得
        String cd = request.getParameter("cd");

        // 2. ビジネスロジック
        SubjectDao sbDao = new SubjectDao();
        // 科目番号をキーに科目情報を1件取得
        Subject subject = sbDao.get(cd,teacher.getSchool());

        

        // 3. レスポンス設定（JSPへ渡すデータ）
        request.setAttribute("subject", subject);

        // 4. 更新画面（jsp）を表示
        return "subject_update.jsp";
    }
}