package tool;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"*.action"})
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            // 1. パスを取得（例：/main/StudentList.action）
            String path = request.getServletPath().substring(1);

            // 2. クラス名を組み立てる
            // path.replace(...) だけだと "main.StudentListAction" になるため、
            // 固定パッケージ名 "scoremanager." を頭に付け足す
            String name = "scoremanager." + path.replace(".a", "A").replace('/', '.');

            // デバッグ：Eclipseのコンソールに正しいクラス名が出ているか確認
            System.out.println("DEBUG: 実行クラス -> " + name);

            // 3. Actionクラスを動的に読み込んでインスタンス化
            Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();

            // 4. Actionを実行し、遷移先のJSPパスを取得
            String url = action.execute(request, response);

            // 5. 取得したJSPへ画面遷移（ここが抜けていると画面が表示されません）
            if (url != null) {
                request.getRequestDispatcher(url).forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace(out); // ブラウザにエラーの詳細を表示（デバッグ用）
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}