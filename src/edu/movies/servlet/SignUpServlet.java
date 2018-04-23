package edu.movies.servlet;

import edu.movies.model.Page;
import edu.movies.model.User;
import edu.movies.repository.UserDAO;
import edu.movies.utils.NavigationPageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserDAO userDAO = new UserDAO();
        User returnedUser = userDAO.insertUser(user);
        if (returnedUser == null) {
            initPages(request);
            RequestDispatcher requestDispatcher;
            requestDispatcher = request.getRequestDispatcher("/signUp.jsp");
            request.setAttribute("message", "Wrong username");
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/doLogin");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initPages(request);
        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("/signUp.jsp");
        requestDispatcher.forward(request, response);
    }

    private void initPages(HttpServletRequest request) {
        NavigationPageManager navigationPageManager = NavigationPageManager.getInstance();
        List<Page> pages = navigationPageManager.getPages(request.getSession());
        navigationPageManager.setActive(pages, "signUp");
        request.setAttribute("pages", pages);
    }
}
