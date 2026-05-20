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

        // 1. パラメータの取得
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");

        // 2. 入力チェック (未入力項目がある場合)
        if (entYearStr == null || entYearStr.equals("0") || 
            classNum == null || classNum.equals("0") || 
            subjectCd == null || subjectCd.equals("0") || subjectCd.isEmpty()) {
            
            request.setAttribute("message", "入学年度とクラスと科目を選択してください");
            return new TestListAction().execute(request, response);
        }

        // 3. データの準備
        int entYear = Integer.parseInt(entYearStr);
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(subjectCd, school);

        if (subject == null) {
            request.setAttribute("message", "指定された科目が見つかりません");
            return new TestListAction().execute(request, response);
        }

        // 4. 成績データの取得 (修正版DAOを呼び出し)
        // ※DAO側で TEST テーブルの class_num を取得するように設定してください
        TestListSubjectDao tlsDao = new TestListSubjectDao();
        List<TestListSubject> tests = tlsDao.filter(entYear, classNum, subject, school);

        // 5. 検索結果のチェック
        if (tests == null || tests.isEmpty()) {
            request.setAttribute("message", "学生情報が存在しませんでした");
        }

        // 6. リクエスト属性のセット (JSPへ渡すデータ)
        request.setAttribute("tests", tests); // 仕様書No.2
        request.setAttribute("subject", subject); // 仕様書No.1表示用
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);

        // プルダウン再セット用
        new TestListAction().execute(request, response);

        // 7. 結果表示画面へ遷移
        return "test_list_subject.jsp";
    }
}