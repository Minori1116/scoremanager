package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. パラメータ取得
        //科目番号
        String cd = request.getParameter("cd");
        //科目名
        String name = request.getParameter("name");
        

        // 2. Studentオブジェクトの再構築
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setSchool(teacher.getSchool());
        subject.setName(name);
        

        // 3. DAOで更新実行
        SubjectDao sbDao = new SubjectDao();
        sbDao.save(subject); // StudentDaoのsaveメソッドは、既存データがあればupdateするように実装されている前提です

        // 4. 完了画面へ
        return "subject_update.jsp";
    }
}