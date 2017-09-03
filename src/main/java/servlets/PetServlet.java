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

@WebServlet(name = "PetServlet", urlPatterns = "/petservlet")
public class PetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){response.sendRedirect("/loginsystem/login.jsp");}

        String name = request.getParameter("name");
        String animalClass = request.getParameter("class");
        int age = Integer.parseInt(request.getParameter("age"));

        if(name == null || animalClass == null || age < 0){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("/accounts/mypage.jsp");
            return;
        }

        DBService dbService = new DBService();
        try {
            dbService.addPet(user, name, animalClass, age);
        } catch (DBException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/accounts/mypage.jsp");
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){response.sendRedirect("/loginsystem/login.jsp");}

        long id = Long.parseLong(request.getParameter("id"));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/accounts/mypage.jsp");
    }
}
