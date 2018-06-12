package server;

import db.DBService;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StatServlet extends HttpServlet {

    private final DBService dbService;

    public StatServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object userAttr = req.getSession().getAttribute("user");
        if (userAttr != null && userAttr.equals("authorised")) {
            resp.setStatus(HttpStatus.OK_200);
            Map<String, Object> pageVariables = createPageVariablesMap();

            resp.getWriter().println(TemplateProcessor.instance().getPage("stat.html", pageVariables));

            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private Map<String, Object> createPageVariablesMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("cacheSize", dbService.cacheSize());
        map.put("cacheHits", dbService.cacheHits());
        map.put("cacheMisses", dbService.cacheMisses());
        return map;
    }
}