
package scoremanager.main;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassDeleteExecuteAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    Teacher teacher = (Teacher) session.getAttribute("user");
	
	    String class_num = request.getParameter("class_num");
	    System.out.println("class_num=" + class_num);
	
	    // 1. Subjectのインスタンスを作る
	    bean.ClassNum classnum = new bean.ClassNum();
	    
	    // 2. 科目コードをセット
	    classnum.setClass_num(class_num);
	    
	    // 3. 学校情報をセット（★これが抜けるとDAOでヌルポになる！）
	    classnum.setSchool(teacher.getSchool());
	
	    // 4. DAOを呼ぶ
	    ClassNumDao classnumDao = new ClassNumDao();
	    classnumDao.delete(classnum); 
	
	    return "class_delete_done.jsp";
	}
}