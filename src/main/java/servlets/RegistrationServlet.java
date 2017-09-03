package servlets;

import dbService.DBException;
import dbService.DBService;
import dbService.datasets.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/reg_user")
public class RegistrationServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){response.sendRedirect("index.jsp");}

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        String country = request.getParameter("country");
        String location = request.getParameter("loc");

        if(email == null || pass == null){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("/loginsystem/registration.jsp");
            return;
        }

        DBService dbService = new DBService();
        User userTest = null;
        try {
            userTest = dbService.getUser(email);
        } catch (DBException e) {
            e.printStackTrace();
        }

        if(userTest != null || pass.length() < 6 || !pass.equals(repass)){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect("/loginsystem/registration.jsp");
            return;
        }

        try {
            dbService.addUser(name, email, pass, country, location);
            session.setAttribute("user", dbService.getUser(email));
        } catch (DBException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){response.sendRedirect("/loginsystem/login.jsp");}
        
        session.removeAttribute("user");
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/index.jsp");
    }
}
