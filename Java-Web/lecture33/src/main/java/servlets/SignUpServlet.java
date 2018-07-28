package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    private final AccountService account;

    public SignUpServlet(AccountService account) {
        this.account = account;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        //SignUpServlet должн запомнить логин и пароль в AccountService
        String login = req.getParameter("login");
        String pwd = req.getParameter("password");

        if (login == null || pwd == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile user = new UserProfile(login, pwd);
        account.addUser(user);
    }
}
