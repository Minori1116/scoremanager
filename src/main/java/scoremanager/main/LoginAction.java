package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * ログイン画面を表示するためのアクションクラス
 */
public class LoginAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ログイン画面（login.jsp）へ遷移するための文字列を返す
        // FrontControllerはこの文字列を受け取って、login.jspをフォワード（表示）します
        return "login.jsp";
    }
}