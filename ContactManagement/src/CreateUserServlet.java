import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import java.io.*;
public class CreateUserServlet extends HttpServlet{
public void  doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
PrintWriter p=response.getWriter();
String username=request.getParameter("username");
String password=request.getParameter("password");

} 
}