import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet", value = "/http")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("uid", "13795467304");
        cookie.setMaxAge(5);
        response.addCookie(cookie);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("zhenyun.su");
        String userid = null;

        Cookie[] cookies =  request.getCookies();
        for(Cookie cookie1: cookies){
            if("uid".equals(cookie.getName())){
                userid = cookie.getValue();
            }
        }

        //request.getRequestDispatcher("/hello").forward(request, response);
    }
}
