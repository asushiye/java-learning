import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import java.io.IOException;

public class SimpleServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String param = servletRequest.getParameter("param");
        if ("gg".equals(param)){
            servletResponse.getWriter().write("SimpleServletFilter is done; ");
            filterChain.doFilter(servletRequest, servletResponse);
            return ;
        }
    }
    @Override
    public void destroy() {
    }
}
