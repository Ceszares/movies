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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MoviesServlet", urlPatterns = "/movies")
public class MoviesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initPages(request);

        String filterBy = request.getParameter("filterBy").toLowerCase();
        System.out.println(filterBy);
        MovieDAO accessMovieDAO = new MovieDAO();
        List<Movie> movies = accessMovieDAO.findAllMovies();
        int i = 0;
        List<Movie> filteredList = new ArrayList<>();
        while (i < movies.size()) {
            Movie movie = movies.get(i);
            String foundTitle = movie.getTitle();
            if (foundTitle.toLowerCase().contains(filterBy)) {
                filteredList.add(movie);
            }
            i++;
        }
        dispatchMoviePage(request, response, filteredList);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initPages(request);

        MovieDAO movieDAO = new MovieDAO();
        List<Movie> movies;
        movies = movieDAO.findAllMovies();
        System.out.println("movies.get(0).getTitle() = " + movies.get(0).getTitle());
        System.out.println("movies.size() = " + movies.size());
        Movie firstMovie;
        firstMovie = movies.get(0);
        System.out.println("Title: " + firstMovie.getTitle() + " Genre: " + firstMovie.getGenre() + " Actor: " + firstMovie.getActor());
        request.setAttribute("Title", firstMovie.getTitle());
        request.setAttribute("FirstMovie", firstMovie);
        dispatchMoviePage(request, response, movies);
    }

    private void dispatchMoviePage(HttpServletRequest request, HttpServletResponse response, List<Movie> movies) throws ServletException, IOException {
        Boolean authenticated = (Boolean) request.getSession().getAttribute("Authenticated");
        if (authenticated == null) {
            authenticated = false;
        }
        request.setAttribute("Authenticated", authenticated);
        RequestDispatcher requestDispatcher;
        request.setAttribute("movies", movies);
        requestDispatcher = request.getRequestDispatcher("/movies.jsp");
        requestDispatcher.forward(request, response);
    }

    private void initPages(HttpServletRequest request) {
        NavigationPageManager navigationPageManager = NavigationPageManager.getInstance();
        List<Page> pages = navigationPageManager.getPages(request.getSession());
        navigationPageManager.setActive(pages, "movies");
        request.setAttribute("pages", pages);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieDAO movieDAO = new MovieDAO();
        String id = req.getParameter("id");
        movieDAO.findById(id);
        movieDAO.delete(id);
        super.doDelete(req, resp);
    }
}
