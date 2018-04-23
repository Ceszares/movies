package edu.movies.servlet;

import edu.movies.model.Movie;
import edu.movies.repository.MovieDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteMovieServlet", urlPatterns = "/delete")
public class DeleteMovieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean authenticated = (Boolean) request.getSession().getAttribute("Authenticated");
        if (authenticated != null && authenticated) {
            MovieDAO movieDAO = new MovieDAO();
            String id = request.getParameter("id");
            Movie movie = movieDAO.getMovieById(id);
            if (movie != null) {
                movieDAO.delete(movie);
            }
        }
        response.sendRedirect("/movies");
    }
}
