package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // プルダウン用のデータ準備
        ClassNumDao cDao = new ClassNumDao();
        List<String> class_num_set = cDao.filter(teacher.getSchool());

        int year = LocalDate.now().getYear();
        List<Integer> ent_year_set = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            ent_year_set.add(i);
        }

        // JSPへ渡す
        request.setAttribute("class_num_set", class_num_set);
        request.setAttribute("ent_year_set", ent_year_set);

        return "student_create.jsp";
    }
}