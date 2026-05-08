package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	//Student student = (Student)session.getAttribute("user");
    	//Subject subject = (Subject)session.getAttribute("user");
    	//Test test = (Test)session.getAttribute("user");
	
      	String entYearStr = request.getParameter("f1"); // 入学年度
    	String classNum = request.getParameter("f2"); // クラス
    	String name = request.getParameter("f3"); //科目名
    	String numStr = request.getParameter("f4"); //回数
    	
    	if (classNum == null) {
    		classNum = "0";
        } 
        int entYear = 0;
        
    	List<Test> test = null; 
    	LocalDate todaysDate = LocalDate.now(); // 今日の日付
    	int year = todaysDate.getYear(); // 現在の年
    	StudentDao sDao = new StudentDao();
    	SubjectDao suDao = new SubjectDao();
    	ClassNumDao cDao = new ClassNumDao();
    	TestDao tDao = new TestDao();
    	Map<String, String> errors = new HashMap<>(); // エラーメッセージ
    	
    	// 2. ビジネスロジック
    	// 入学年度が選択されている場合、int型に変換
    	if (entYearStr != null && !entYearStr.equals("0")) {
    		entYear = Integer.parseInt(entYearStr);
    	}
    	
    	int num = 0;
        if (numStr != null && !numStr.equals("0")) {
            num = Integer.parseInt(numStr);
        }
    	
    	// クラス番号一覧を取得（プルダウン用）
    	List<String> class_num_set = cDao.filter(teacher.getSchool());
    	
    	// 入学年度リストを生成（プルダウン用：現在の10年前〜現在まで）
    	List<Integer> ent_year_set = new ArrayList<>();
    	for (int i = year - 10; i <= year; i++) {
    		ent_year_set.add(i);
    	}
    	// 科目名一覧を取得（プルダウン用）
    	List<Subject> name_set = suDao.filter(teacher.getSchool());
    	
    	//List<Test> num_set = new ArrayList<>();
    	//List<Test> num_set = tDao.filter(teacher.getSchool());
    	List<Integer> num_set = new ArrayList<>();
    	for (int i = 1; i <= 5; i++) {
    	    num_set.add(i);
    	}
    	
    	
        
    	// 3. レスポンス（JSPへ渡すデータ）の設定
    	request.setAttribute("f1", entYearStr);
    	request.setAttribute("f2", classNum);
    	request.setAttribute("f3", name);
    	request.setAttribute("f4", numStr);
    	request.setAttribute("class_num_set", class_num_set);
    	request.setAttribute("ent_year_set", ent_year_set);
    	request.setAttribute("name_set", name_set);
    	request.setAttribute("num_set", num_set);
    	request.setAttribute("errors", errors);
    	
    	// 4. JSPのパスを返す
    	return "test_regist.jsp";
    }
     
     
}