package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"*.action"})
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. パスを取得（例: main/TestListStudentExecute.action）
            String path = request.getServletPath().substring(1);

            // 2. クラス名を完全修飾名（パッケージ名込み）に変換
            // 前に "scoremanager." を固定で付け、パスの ".a" を "A" に、"/" を "." に置換する
            String name = "scoremanager." + path.replace(".a", "A").replace('/', '.');

            // デバッグ用：コンソールで組み立てたクラス名が正しいか確認できる
            System.out.println("DEBUG: 実行クラス -> " + name);

            // 3. クラス名からインスタンスを生成
            Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();

            // 4. Actionを実行し、戻り値（遷移先JSPのパス）を受け取る
            String url = action.execute(request, response);

            // 5. 【重要】戻り値のJSPへフォワード（画面表示）する
            // これがないと、Actionの処理が終わっても画面が真っ白になります
            if (url != null) {
                request.getRequestDispatcher(url).forward(request, response);
            }

        } catch (Exception e) {
            // エラー時はコンソールに詳細を出し、error.jspへ飛ばす
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POSTリクエストもGETと同じ処理（doGet）に集約
        doGet(request, response);
    }
}