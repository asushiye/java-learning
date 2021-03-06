package system.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author : zhenyun.su
 * @since : 2018/9/21
 */

public class MyFilter2 implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter2 init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter2 before doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("MyFilter2 after doFilter");
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter2 destroy");
    }
}
