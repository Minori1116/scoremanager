package scoremanager.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // 1. パラメータの取得
        String entYearStr = request.getParameter("ent_year");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");

        int entYear = Integer.parseInt(entYearStr);

        Map<String, String> errors = new HashMap<>();
        StudentDao sDao = new StudentDao();

        // 2. ① 入学年度未選択チェック（"0" の場合エラー）
        if (entYear == 0) {
            errors.put("ent_year", "入学年度を選択してください");
        }

        // 3. ② 学生番号重複チェック（既に存在する場合エラー）
        if (no != null && !no.isEmpty()) {
            Student existingStudent = sDao.get(no);
            if (existingStudent != null) {
                errors.put("no", "学生番号が重複しています");
            }
        }

        // 4. エラーがある場合は登録画面へ差し戻す
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            
            // 入力されていた値を保持して画面に戻す
            request.setAttribute("ent_year", entYear);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("class_num", classNum);

            // セレクトボックス用のデータを再取得
            ClassNumDao cDao = new ClassNumDao();
            List<String> class_num_set = cDao.filter(school);
            request.setAttribute("class_num_set", class_num_set);

            List<Integer> ent_year_set = new ArrayList<>();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = year - 10; i <= year; i++) {
                ent_year_set.add(i);
            }
            request.setAttribute("ent_year_set", ent_year_set);

            return "student_create.jsp";
        }

        // 5. エラーがなければ登録を実行
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(true); // 新規登録時は在学中(true)
        student.setSchool(school);

        sDao.save(student);

        return "student_create_done.jsp";
    }
}