package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // パラメータの取得
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");

        // 入力チェック (シーケンス図の alt [入力項目が未入力の場合] に対応)
        if (entYearStr == null || entYearStr.equals("0") || 
            classNum == null || classNum.equals("0") || 
            subjectCd == null || subjectCd.equals("0")) {
            
            request.setAttribute("message", "入学年度とクラスと科目を選択してください");
            // 検索用Actionを呼び出して検索画面(test_list.jsp)に戻る
            return new TestListAction().execute(request, response);
        }

        int entYear = Integer.parseInt(entYearStr);
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(subjectCd, school);

        // 成績データの取得 (シーケンス図：入力された条件で成績データを取得)
        TestListSubjectDao tlsDao = new TestListSubjectDao();
        List<TestListSubject> tests = tlsDao.filter(entYear, classNum, subject, school);

        // 検索結果が0件の場合 (シーケンス図：alt [一覧が0件だった場合])
        if (tests.isEmpty()) {
            request.setAttribute("message", "学生情報が存在しませんでした");
        }

        // 検索結果と初期値のセット
        request.setAttribute("tests", tests);
        request.setAttribute("subject_name", subject.getName());
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);

        // 共通リストをセットするためにTestListActionのexecuteを内部で呼ぶ
        new TestListAction().execute(request, response);

        // 一覧画面を表示
        return "test_list_subject.jsp";
    }
}