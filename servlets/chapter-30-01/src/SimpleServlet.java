import javax.servlet.*;
import java.io.IOException;

// http://localhost:8080/yesOrNo?param=NO
public class SimpleServlet extends GenericServlet {

    private String iniParam = null;
    private String contextParam = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.iniParam = config.getInitParameter("iniParam");
        this.contextParam = config.getServletContext().getInitParameter("myParam");
        super.init(config);
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String yesOrNoParam = servletRequest.getParameter("param");

        servletResponse.setContentType("text/html");
        if ("YES".equals(yesOrNoParam)){
            servletResponse.getWriter().write("<html><body>You said YES from "+this.iniParam+this.contextParam+"!</body></html>");
        }

        if ("NO".equals(yesOrNoParam)){
            servletResponse.getWriter().write("<html><body>You said NO from "+this.iniParam+this.contextParam+"!</body></html>");
        }

    }
}
