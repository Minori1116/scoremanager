package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 学生番号取得
        String student_no = request.getParameter("student_no");

        // Student生成
        Student student = new Student();
        student.setNo(student_no);

        // DAO呼び出し
        TestListStudentDao dao = new TestListStudentDao();
        List<TestListStudent> test_student_list = dao.filter(student);

        // JSPへ渡す
        request.setAttribute("test_student_list", test_student_list);
        request.setAttribute("student", student);
        request.setAttribute("student_no",student_no);

        return "test_list_student.jsp";
    }
}