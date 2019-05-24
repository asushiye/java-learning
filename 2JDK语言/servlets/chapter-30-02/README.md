# Java Servelet class

chapter-30-02

```
        HttpServlet
        HttpRequest
        HttpResponse
        HttpSession
        RequestDispatcher
        ServletContext
        web.xml Servlet Configuration
```

## HttpServlet

The`javax.servlet.http.HttpServlet`class is a slightly more advanced base class than the`GenericServlet`

The`HttpServlet`class has methods you can override for each HTTP method (GET, POST etc.). Here is a list of the methods you can override:

- doGet()
- doPost()
- doHead()
- doPut()
- doDelete()
- doOptions()
- doTrace()

Most often you just want to respond to either HTTP GET or POST requests, so you just override these two methods.

If you want to handle both GET and POST request from a given servlet, you can override both methods, and have one call the other

```
public class SimpleHttpServlet extends HttpServlet {

  protected void doGet( HttpServletRequest request,
                        HttpServletResponse response)
        throws ServletException, IOException {

      doPost(request, response);
  }

  protected void doPost( HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException {

      response.getWriter().write("GET/POST response");
    }
}
```

## HttpRequest

- [Parameters](http://tutorials.jenkov.com/java-servlets/httprequest.html#parameters)
- [Headers](http://tutorials.jenkov.com/java-servlets/httprequest.html#headers)
- [InputStream](http://tutorials.jenkov.com/java-servlets/httprequest.html#inputstream)
- [Session](http://tutorials.jenkov.com/java-servlets/httprequest.html#session)
- [ServletContext](http://tutorials.jenkov.com/java-servlets/httprequest.html#servletcontext)

The`HttpServlet`class request processing methods take two parameters.

1. javax.servlet.http.HttpRequest
2. javax.servlet.http.HttpResponse

For instance, here is the signature of the`HttpServlet.doGet()`method:

```
protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response)
      throws ServletException, IOException {

}
```

### Parameters

http://jenkov.com/somePage.html?param1=hello¶m2=world

```
 String param1 = request.getParameter("param1");
 String param2 = request.getParameter("param2");
```

### Headers

`String contentLength = request.getHeader("Content-Length"); ` 

### InputStream

`InputStream requestBodyInput = request.getInputStream();    `

To give you access to the request body of an HTTP POST request, you can obtain an InputStream pointing to the HTTP request body

### Session

The session object can hold information about a given user, between requests

`HttpSession session = request.getSession();`

### ServletContext

The ServletContext contains meta information about the web application. For instance, you can access context parameters set in the web.xml file, you can forward the request to other servlets, and you can store application wide parameters in the ServletContext too.

`ServletContext context = request.getSession().getServletContext();   `

## HttpResponse

- Headers
- Writing
- Content-Length

### Headers

```
response.setHeader("Header-Name", "Header Value");
response.setHeader("Content-Type", "text/html");
response.setHeader("Content-Type", "text/plain");
`
```

### Writing

```
response.setHeader("Content-Type", "text/plain");
PrintWriter writer = response.getWriter();
writer.write("This is just plain text");
or
writer.write("<html><body>GET/POST response</body></html>");
```

### Writing Binary Data

the Content-Length header tells the browser how many bytes your servlet is sending back. If you are sending binary data back you need to set the content length header.

`response.setHeader("Content-Length", "31642");

OutputStream outputStream = response.getOutputStream();
outputStream.write(...);
`

### Redirecting to a Different URL

`response.sendRedirect("http://jenkov.com");`

 You cannot send any data back to the browser when redirecting.

## HttpSession

 The HttpSession object represents a user session. A user session contains information about the user across multiple HTTP requests.

 `HttpSession session = request.getSession();`

 `session.setAttribute("userName", "theUserName");`

 `String userName = (String) session.getAttribute("userName");`

## RequestDispatcher

 The RequestDispatcher class enables your servlet to "call" another servlet from inside another servlet. The other servlet is called as if an HTTP request was sent to it by a browser.

 `request.getRequestDispatcher("/index.jsp").forward(request, response);`

## ServletContext

 The ServletContext is an object that contains meta informaton about your web application. You can access it via the HttpRequest object, like this:

`ServletContext context = request.getSession().getServletContext();`

`context.setAttribute("someValue", "aValue");`

`Object attribute = context.getAttribute("someValue");`

## web.xml Servlet Configuration

### Configuring and Mapping a Servlet

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaeehttp://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">


    <servlet>
        <servlet-name>SimpleServlet</servlet-name>
        <servlet-class>SimpleServlet</servlet-class>
        <init-param>
            <param-name>param</param-name>
            <param-value>YES</param-value>
        </init-param>
    </servlet>

    <servlet-mapping>
        <servlet-name>SimpleServlet</servlet-name>
        <url-pattern>/yesOrNo</url-pattern>
    </servlet-mapping>

</web-app>
```

### Servlet Init Parameters

```
  protected String myParam = null;

  public void init(ServletConfig servletConfig) throws ServletException{
    this.myParam = servletConfig.getInitParameter("myParam");
  }
```

### Servlet Load-on-Startup

 By setting a <load-on-startup> element, you can tell the servlet container to load the servlet as soon as the servlet container starts
the servlets init() method is called when the servlet is loaded

```
     <servlet>
        <servlet-name>SimpleServlet</servlet-name>
        <servlet-class>SimpleServlet</servlet-class>
        <init-param>
            <param-name>iniParam</param-name>
            <param-value>SimpleServlet</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
```

### Context Parameters

You can also set some context parameters which can be read from all servlets in your application

```
<context-param>
    <param-name>myParam</param-name>
    <param-value>Context param the value</param-value>
</context-param>
```

```
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
```

```
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.iniParam = config.getInitParameter("iniParam");
        this.contextParam = config.getServletContext().getInitParameter("myParam");
        super.init(config);
    }
```
