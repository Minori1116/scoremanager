package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        // ログイン中のユーザー情報を取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. リクエストパラメータの取得（student_create.jspのname属性と一致させる）
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        int entYear = Integer.parseInt(request.getParameter("ent_year"));
        String classNum = request.getParameter("class_num");

        // 2. Studentオブジェクトの作成と値のセット
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(true); // 新規登録時は「在学中」固定
        student.setSchool(teacher.getSchool()); // 先生の所属校をセット

        // 3. DAOを使ってデータベースに保存
        StudentDao sDao = new StudentDao();
        sDao.save(student);

        // 4. 完了画面へ遷移
        return "student_create_done.jsp";
    }
}