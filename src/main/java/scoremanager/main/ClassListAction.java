package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user"); // ログインユーザー
        
        
        // 1. リクエストパラメータの取得
        
        ClassNumDao cDao = new ClassNumDao(); // クラス番号DAO
        Map<String, String> errors = new HashMap<>(); // エラーメッセージ

       
        // クラス番号一覧を取得（プルダウン用）
        List<String> classes = cDao.filter(teacher.getSchool());
        
        
        request.setAttribute("classes", classes);
        request.setAttribute("errors", errors);

        // 4. JSPのパスを返す
        return "class_list.jsp";
    }
}