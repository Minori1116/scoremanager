package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
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
	
    	String errorMsg =
    		    (String)session.getAttribute("errorMsg");

    		request.setAttribute("errorMsg", errorMsg);

    		session.removeAttribute("errorMsg");
    		
    		Map<String, String> pointMap =
    			    (Map<String, String>)session.getAttribute("pointMap");

    			request.setAttribute("pointMap", pointMap);

    			session.removeAttribute("pointMap");
    			
      	String entYearStr = request.getParameter("f1"); // 入学年度
    	String classNum = request.getParameter("f2"); // クラス
    	String subjectCd = request.getParameter("f3"); //科目コード
    	String numStr = request.getParameter("f4"); //回数
    	
    	if (classNum == null) {
    		classNum = "0";
        } 
        
    	LocalDate todaysDate = LocalDate.now(); // 今日の日付
    	int year = todaysDate.getYear(); // 現在の年
    	StudentDao sDao = new StudentDao();
    	SubjectDao suDao = new SubjectDao();
    	ClassNumDao cDao = new ClassNumDao();
    	TestDao tDao = new TestDao();
    	
    	Map<String, String> errors = new HashMap<>(); // エラーメッセージ
    	
    	String search = request.getParameter("search");

    	if (search != null) {  
    		if ("0".equals(entYearStr) || "0".equals(classNum) || "0".equals(subjectCd) || "0".equals(numStr)) {
            errors.put("msg", "入学年度とクラスと科目と回数を選択してください");
            }
    	}
        
    	// 2. ビジネスロジック
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
    	for (int i = 1; i <= 2; i++) {
    	    num_set.add(i);
    	}
    	
    	 String subjectName = null;
         if (subjectCd != null && !"0".equals(subjectCd)) {
             for (Subject s : name_set) {
                 if (s.getCd().equals(subjectCd)) {
                     subjectName = s.getName();
                     break;
                 }
             }
         }
         
    	// 検索条件に応じた学生リストの取得
        List<Test> results = null;
        
        //正常入力時のみ検索を実行
        if (search != null && errors.isEmpty()) {
            int entYear = Integer.parseInt(entYearStr);
            int num = Integer.parseInt(numStr);

            Subject subject = new Subject();
            subject.setCd(subjectCd);
            
         //studentテーブルから該当学生を取得
            List<Student> students = sDao.filter(teacher.getSchool(), entYear, classNum, true);

            results = new ArrayList<>();

            //各学生についてtestを取得（なければ空のTestを作る）
            for (Student st : students) {

                Test test = tDao.get(st, subject, teacher.getSchool(), num);

                if (test == null) {
                    test = new Test();
                    test.setStudent(st);
                    test.setSubject(subject);
                    test.setSchool(teacher.getSchool());
                    test.setNo(num);
                    test.setPoint(0);
                }

                results.add(test);
            }
        }
        
    	// 3. レスポンス（JSPへ渡すデータ）の設定
    	request.setAttribute("f1", entYearStr);
    	request.setAttribute("f2", classNum);
    	request.setAttribute("f3", subjectCd);
    	request.setAttribute("f4", numStr);
    	
    	request.setAttribute("subjectName", subjectName);
    	request.setAttribute("results", results);
    	request.setAttribute("class_num_set", class_num_set);
    	request.setAttribute("ent_year_set", ent_year_set);
    	request.setAttribute("name_set", name_set);
    	request.setAttribute("num_set", num_set);
    	request.setAttribute("errors", errors);
    	    	
    	// 4. JSPのパスを返す
    	return "test_regist.jsp";
    }
     
     
}