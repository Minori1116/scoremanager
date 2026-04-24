
package scoremanager.main;

import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    Teacher teacher = (Teacher) session.getAttribute("user");
	
	    String cd = request.getParameter("cd");
	    System.out.println("cd=" + cd);
	
	    // 1. Subjectのインスタンスを作る
	    bean.Subject subject = new bean.Subject();
	    
	    // 2. 科目コードをセット
	    subject.setCd(cd);
	    
	    // 3. 学校情報をセット（★これが抜けるとDAOでヌルポになる！）
	    subject.setSchool(teacher.getSchool());
	
	    // 4. DAOを呼ぶ
	    SubjectDao sDao = new SubjectDao();
	    sDao.delete(subject); 
	
	    return "subject_delete_done.jsp";
	}
}