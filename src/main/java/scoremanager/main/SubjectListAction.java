package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user"); // ログインユーザー
        
        // 科目一覧取得
        SubjectDao dao = new SubjectDao();
        List<Subject> subjects = dao.filter(teacher.getSchool());
        
     // レスポンス設定（JSPへ渡すデータ）
        request.setAttribute("subjects", subjects);
        
     // JSPのパスを返す
        return "subject_list.jsp";
	}
}
