import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet(value = "/hello.hello",loadOnStartup=1)
public class HelloWorld extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
       // request.getRequestDispatcher("/index.jsp").forward(request, response);

        String contextParam = request.getSession().getServletContext().getInitParameter("myParam");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer=response.getWriter();
        writer.append("<!DOCTYPE html>")
                .append("<html><head></head><body>")
                .append(contextParam)
                .append("HelloWorld")
                .append("</body></html>");
    }
}
