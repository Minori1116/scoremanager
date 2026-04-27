package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. パラメータ（科目コード）の取得
        String cd = request.getParameter("cd");

        // 2. ビジネスロジック
        SubjectDao sDao = new SubjectDao();
        // 科目コードと学校コードで科目の詳細データを取得
        Subject subject = sDao.get(cd, teacher.getSchool());

        // 3. レスポンス設定
        request.setAttribute("subject", subject);

        // 4. 削除確認画面（subject_delete.jsp）へ
        return "subject_delete.jsp";
    }
}
