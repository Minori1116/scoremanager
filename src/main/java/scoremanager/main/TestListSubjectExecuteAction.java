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

        // 【デバッグ用】コンソールで値を確認（動かない時にEclipseで見てください）
        System.out.println("f1:" + entYearStr + " f2:" + classNum + " f3:" + subjectCd);

        // 2. 入力チェック (未入力項目がある場合)
        if (entYearStr == null || entYearStr.equals("0") || 
            classNum == null || classNum.equals("0") || 
            subjectCd == null || subjectCd.equals("0") || subjectCd.isEmpty()) {
            
            request.setAttribute("message", "入学年度とクラスと科目を選択してください");
            // リストを再セットして検索画面(test_list.jsp)に戻る
            return new TestListAction().execute(request, response);
        }

        // 3. 数値変換とデータ取得準備
        int entYear = Integer.parseInt(entYearStr);
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(subjectCd, school);

        // 科目が存在しない場合の安全策
        if (subject == null) {
            request.setAttribute("message", "指定された科目が見つかりません");
            return new TestListAction().execute(request, response);
        }

        // 4. 成績データの取得
        TestListSubjectDao tlsDao = new TestListSubjectDao();
        List<TestListSubject> tests = tlsDao.filter(entYear, classNum, subject, school);

        // 5. 検索結果が0件の場合
        if (tests == null || tests.isEmpty()) {
            request.setAttribute("message", "学生情報が存在しませんでした");
        }

        // 6. リクエスト属性のセット (JSPへ渡すデータ)
        request.setAttribute("tests", tests);
        request.setAttribute("subject_name", subject.getName());
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);

        // ★ここが重要：プルダウン用のリスト(ent_year_set等)をセットするために呼ぶ
        new TestListAction().execute(request, response);

        // 7. 表示するJSPの決定
        // testsが空でも、このActionの役割は「結果表示画面」へ行くことなので
        // return先は test_list_subject.jsp に固定します
        return "test_list_subject.jsp";
    }
}