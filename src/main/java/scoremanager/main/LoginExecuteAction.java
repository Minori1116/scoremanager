package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. リクエストパラメータの取得
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        
        // エラーメッセージを格納するリスト
        List<String> errors = new ArrayList<>();

        // 2. 認証処理
        TeacherDao tDao = new TeacherDao();
        Teacher teacher = tDao.login(id, password);

        if (teacher != null) {
            // --- 【認証成功】 ---
            HttpSession session = request.getSession(true);
            
            // ログイン済みフラグを立てる（Userクラスを継承している前提）
            teacher.setAuthenticated(true);
            
            // セッションに教員情報を保存（header.jspの${user.name}などで使用）
            session.setAttribute("user", teacher);

            // メニュー画面のActionへリダイレクト（あるいは遷移）
            return "Menu.action";

        } else {
            // --- 【認証失敗】 ---
            // 画像③の仕様：エラーメッセージをリストに入れてJSPへ渡す
            errors.add("ログインに失敗しました。IDまたはパスワードが正しくありません。");
            
            request.setAttribute("errors", errors);
            // 入力されたIDを保持させて再表示（画像③でIDが残っている状態を再現）
            request.setAttribute("id", id);

            // ログイン画面に戻る
            return "login.jsp";
        }
    }
}