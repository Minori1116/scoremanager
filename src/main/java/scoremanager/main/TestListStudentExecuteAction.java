package scoremanager.main;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.SubjectDao;
import dao.TestDao;
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
       
       
        //科目名
        String name=request.getParameter("name");
        String no=request.getParameter("no");
        String subject_name = request.getParameter("subject_name");
        String subject_cd = request.getParameter("subject_cd");
        int num=Integer.parseInt(request.getParameter("num"));
        int point=Integer.parseInt(request.getParameter("point"));

        // 2. Studentオブジェクトの再構築
        Subject subject = new Subject();
        Student student=new Student();
        TestListStudent testliststudent=new TestListStudent();
        student.setName(name);
        student.setNo(no);
        subject.setName(subject_name);
        testliststudent.setSubjectCd(subject_cd);
        testliststudent.setNum(num);
        testliststudent.setPoint(point);
        TestListStudentDao teslisdao=new TestListStudentDao(); 
        teslisdao.filter(student);
        TestDao testdao=new TestDao();

        // 3. DAOで更新実行
        SubjectDao sbDao = new SubjectDao();
        sbDao.save(subject); // StudentDaoのsaveメソッドは、既存データがあればupdateするように実装されている前提です

        // 4. 完了画面へ
        return "test_list_student.jsp";
    }
}