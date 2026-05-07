package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        // ログイン中のユーザー情報を取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. リクエストパラメータの取得（student_create.jspのname属性と一致させる）
        
        String classNum = request.getParameter("class_num");

        // 2. Studentオブジェクトの作成と値のセット
        ClassNum classnum = new ClassNum();
        classnum.setClass_num(classNum);
        classnum.setSchool(teacher.getSchool()); // 先生の所属校をセット

        // 3. DAOを使ってデータベースに保存
        ClassNumDao classnumDao = new ClassNumDao();
        classnumDao.save(classnum);

        // 4. 完了画面へ遷移
        return "class_create_done.jsp";
    }
}