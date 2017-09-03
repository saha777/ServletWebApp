package servlets;

import dbService.DBException;
import dbService.DBService;
import dbService.dao.impls.UsersDAO;
import dbService.datasets.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login_user")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){response.sendRedirect("index.jsp");}

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        if(email == null || pass == null){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("/loginsystem/login.jsp");
            return;
        }

        DBService dbService = new DBService();
        User userProfile = null;
        try {
            userProfile = dbService.getUser(email);
        } catch (DBException e) {
            e.printStackTrace();
        }

        if(userProfile == null || !userProfile.getPass().equals(pass)){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect("/loginsystem/login.jsp");
            return;
        }

        session.setAttribute("user", userProfile);
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
