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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/doLogin")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initPages(request);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);
        HttpSession session = request.getSession();
        if (user == null) {
        } else {
            if (user.getPassword().equals(password)) {
                session.setAttribute("Authenticated", true);
                response.sendRedirect("/movies");
                return;
            }
        }
        session.setAttribute("Authenticated", false);
        RequestDispatcher requestDispatcher;
        request.setAttribute("message", "Incorrect username or password");
        requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    private void initPages(HttpServletRequest request) {
        NavigationPageManager navigationPageManager = NavigationPageManager.getInstance();
        List<Page> pages = navigationPageManager.getPages(request.getSession());
        navigationPageManager.setActive(pages, "doLogin");
        request.setAttribute("pages", pages);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initPages(request);
        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
