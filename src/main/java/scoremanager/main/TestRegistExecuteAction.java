package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String[] students = request.getParameterValues("regist");
        String subjectCd = request.getParameter("subject");
        int count = Integer.parseInt(request.getParameter("count"));
        String num=request.getParameter("num");

        List<Test> list = new ArrayList<>();
        
        Map<String, String> pointMap = new HashMap<>();

        for (String studentNo : students) {

            String pointStr = request.getParameter("point_" + studentNo);
            pointMap.put(studentNo, pointStr);

            // 未入力はスキップ
            if (pointStr != null && !pointStr.isEmpty()) {

                int point = Integer.parseInt(pointStr);

                // 0～100チェック
                if (point < 0 || point > 100) {
                    session.setAttribute("errorMsg", "0〜100の範囲で入力してください");
                    session.setAttribute("pointMap", pointMap);
                    return "TestRegist.action?search=1"
                    + "&f1=" + request.getParameter("f1")
                    + "&f2=" + num
                    + "&f3=" + subjectCd
                    + "&f4=" + count;
            
                }

                Test test = new Test();

                // 学生
                Student student = new Student();
                student.setNo(studentNo);
                student.setClassNum(num);
                test.setStudent(student);

                // 科目
                Subject subject = new Subject();
                subject.setCd(subjectCd);
                test.setSubject(subject);

                // 学校
                School school = teacher.getSchool();
                test.setSchool(school);

                // 回数・点数
                test.setNo(count);
                test.setPoint(point);
                
                test.setClassNum(num);

                list.add(test);
            }
        }

        // 得点を保存
        TestDao dao = new TestDao();
        dao.save(list);

        return "test_regist_done.jsp";
    }
}