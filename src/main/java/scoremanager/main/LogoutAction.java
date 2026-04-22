package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LogoutAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        // セッションが存在する場合、セッションを無効化する
        if (session != null) {
            session.invalidate();
        }

        // ログアウト完了画面へ遷移
        return "logout.jsp";
    }
}