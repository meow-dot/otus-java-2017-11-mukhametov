package war_ioc.server;

import war_ioc.account.AccountDBService;
import war_ioc.account.AccountDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {

    private final AccountDBService accountDBService;

    public LoginServlet(AccountDBService accountDBService) {
        this.accountDBService = accountDBService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (validateUser(req.getParameter("login"), req.getParameter("password"))) {
            req.getSession().setAttribute("user", "authorised");
            resp.sendRedirect("/stat");
        } else {
            resp.getWriter().println(TemplateProcessor.instance().getPage("loginFailed.html", new HashMap<>()));
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private boolean validateUser(String login, String password) {
        AccountDataSet account = accountDBService.readAccount(login);
        return account != null && account.getPassword().equals(password);
    }
}
