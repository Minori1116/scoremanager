package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassDeleteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. パラメータ（科目コード）の取得
        String class_num = request.getParameter("class_num");

        // 2. ビジネスロジック
        ClassNumDao classnumDao = new ClassNumDao();
        // 科目コードと学校コードで科目の詳細データを取得
        ClassNum classnum = classnumDao.get(class_num, teacher.getSchool());

        // 3. レスポンス設定
        request.setAttribute("classnum", classnum);

        // 4. 削除確認画面（subject_delete.jsp）へ
        return "class_delete.jsp";
    }
}
