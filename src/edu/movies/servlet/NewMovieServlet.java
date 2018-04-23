package edu.movies.servlet;

import edu.movies.model.Movie;
import edu.movies.model.Page;
import edu.movies.repository.MovieDAO;
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

@WebServlet(name = "NewMovieServlet", urlPatterns = "/insertMovie")
public class NewMovieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String year = request.getParameter("year");
        Integer yearInteger = null;
        if (!year.equals("")) {
            yearInteger = Integer.parseInt(request.getParameter("year"));
        }

        String actor = request.getParameter("actor");
        String genre = request.getParameter("genre");
        String imageUrl = request.getParameter("imageUrl");
        Movie newMovie = new Movie();
        newMovie.setTitle(title);
        newMovie.setActor(actor);
        newMovie.setGenre(genre);
        newMovie.setImageUrl(imageUrl);
        if (yearInteger != null) {
            newMovie.setCreatedDate(java.sql.Date.valueOf(yearInteger.toString() + "-1-1"));
        }
        MovieDAO movieDAO = new MovieDAO();
        movieDAO.insertMovie(newMovie);
        System.out.println("newMovie = " + newMovie);
        response.sendRedirect("/movies");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NavigationPageManager navigationPageManager = NavigationPageManager.getInstance();
        List<Page> pages = navigationPageManager.getPages(request.getSession());
        navigationPageManager.setActive(pages, "insertMovie");
        request.setAttribute("pages", pages);
        HttpSession session = request.getSession();
        Boolean authenticated = (Boolean) session.getAttribute("Authenticated");
        if (authenticated == null) {
            authenticated = false;
        }
        RequestDispatcher requestDispatcher;
        if (!authenticated) {
            navigationPageManager.setActive(pages, "doLogin");
            requestDispatcher = request.getRequestDispatcher("/login.jsp");
        } else {
            requestDispatcher = request.getRequestDispatcher("/insert_movie.jsp");
        }
        requestDispatcher.forward(request, response);
    }
}
