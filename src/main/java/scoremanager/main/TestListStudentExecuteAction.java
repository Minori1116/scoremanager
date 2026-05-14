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
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;


public class TestListStudentExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	System.out.println("★★ Action が呼ばれた ★★");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // 1. JSPのプルダウン用のデータを準備 (再取得)
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();
        
        // クラス一覧を取得
        List<String> class_list = cNumDao.filter(school);
        // 科目一覧を取得
        List<Subject> subject_list = sDao.filter(school);
        // 入学年度一覧を作成 (現在の年から10年前まで)
        List<Integer> entYearList = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = year - 10; i <= year; i++) {
            entYearList.add(i);
        }

        // 2. 検索パラメータを取得
        String student_no = request.getParameter("f4");
        System.out.println("★ student_no = " + student_no);


        // 3. 検索実行
        

        if (student_no == null){student_no="";}
        
        List<TestListStudent> test_student_list = null;
        TestListStudentDao dao = new TestListStudentDao();
            // Student生成して検索
        if (!student_no.isEmpty()) {
            Student student = new Student();
            student.setNo(student_no);
            student.setSchool(school);
            test_student_list = dao.filter(student);
            
            if (test_student_list == null || test_student_list.isEmpty()) {
                request.setAttribute("message", "学生情報が存在しませんでした");
            }
        }
        

        // 4. JSPへデータを渡す (JSPの変数を名前に合わせるのが重要)
        
        // 検索結果リスト
        request.setAttribute("test_student_list", test_student_list);
        
        // 入力された学生番号を保持 (value="${student_no}")
        request.setAttribute("student_no", student_no);
        // <c:forEach var="year" items="${ent_year_set}">
        request.setAttribute("ent_year_set", entYearList);
        
        // <c:forEach var="num" items="${class_num_set}">
        request.setAttribute("class_num_set", class_list);
        
        // <c:forEach var="subject" items="${subjects}">
        request.setAttribute("subjects", subject_list);
        
        request.setAttribute("f1", null);
        request.setAttribute("f2", null);
        request.setAttribute("f3", null);
        request.setAttribute("f4", student_no);

        
        

        return "test_list_student.jsp";
    }
}