package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. パラメータ取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        
        // 【追加】入学年度パラメータの取得と数値への変換
        String entYearStr = request.getParameter("ent_year");
        int entYear = 0;
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        // チェックボックスが未チェックの場合は null になるため判定が必要
        boolean isAttend = request.getParameter("is_attend") != null;

        // 2. Studentオブジェクトの再構築
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(teacher.getSchool());
        
        // 【追加】Studentクラスに入学年度を設定する
        student.setEntYear(entYear); 

        // 3. DAOで更新実行
        StudentDao sDao = new StudentDao();
        sDao.save(student); // StudentDaoのsaveメソッドは、既存データがあればupdateするように実装されている前提です

        // 4. 完了画面へ
        return "student_update_done.jsp";
    }
}