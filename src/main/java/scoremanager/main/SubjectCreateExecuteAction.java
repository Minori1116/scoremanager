package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");
        
        Map<String, String> errors = new HashMap<>();
        SubjectDao sDao = new SubjectDao();

        // 1. バリデーション
        if (cd == null || cd.length() != 3) {
            errors.put("cd", "科目コードは3文字で入力してください");
        } else {
            // 2. 重複チェック
            Subject check = sDao.get(cd, teacher.getSchool());
            if (check != null) {
                errors.put("cd", "科目コードが重複しています");
            }
        }

        // エラーがある場合
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            // 自画面（登録画面）に戻るパスを返す
            return "subject_create.jsp";
        }

        // 3. 登録処理
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        sDao.save(subject);

        // 成功時の遷移先（一覧画面）を返す
        return "SubjectList.action";
    }
}