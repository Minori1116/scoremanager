package scoremanager.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
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
        School school = teacher.getSchool();

        // プルダウン用データ
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();

        List<String> class_list = cNumDao.filter(school);
        List<Subject> subject_list = sDao.filter(school);

        List<Integer> entYearList = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = year - 10; i <= year; i++) {
            entYearList.add(i);
        }

        // パラメータ取得
        String student_no = request.getParameter("f4");
        if (student_no == null) student_no = "";

        StudentDao sdao = new StudentDao();
        TestListStudentDao dao = new TestListStudentDao();

        List<TestListStudent> test_student_list = null;

        // ★ フラグ（JSP で使う）
        boolean studentExists = false;
        boolean hasScore = false;
        String studentName = "";

        if (!student_no.isEmpty()) {

            // ★ 学生情報取得
            Student studentinfo = sdao.get(student_no);

            if (studentinfo != null) {
                studentExists = true;
                studentName = studentinfo.getName();
            }

            // ★ 成績取得
            Student student = new Student();
            student.setNo(student_no);
            student.setSchool(school);

            test_student_list = dao.filter(student);

            if (test_student_list != null && !test_student_list.isEmpty()) {
                hasScore = true;
            }
        }

        // ★ JSP に渡す
        request.setAttribute("student_exists", studentExists);
        request.setAttribute("has_score", hasScore);
        request.setAttribute("student_name", studentName);
        request.setAttribute("student_no", student_no);
        request.setAttribute("test_student_list", test_student_list);

        request.setAttribute("ent_year_set", entYearList);
        request.setAttribute("class_num_set", class_list);
        request.setAttribute("subjects", subject_list);

        return "test_list_student.jsp";
    }
}
