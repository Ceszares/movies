package edu.movies.utils;

import edu.movies.model.Page;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class NavigationPageManager {
    private static NavigationPageManager navigationPageManager;

    private NavigationPageManager() {

    }

    public static NavigationPageManager getInstance() {
        if (navigationPageManager == null) {
            navigationPageManager = new NavigationPageManager();
        }
        return navigationPageManager;
    }

    public List<Page> getPages(HttpSession httpSession) {
        Boolean authenticated = (Boolean) httpSession.getAttribute("Authenticated");
        if (authenticated == null) {
            authenticated = false;
        }
        List<Page> pages = new ArrayList<>();
        Page page = new Page();
        page.setName("Home");
        page.setPath("");
        Page moviesPage = new Page();
        moviesPage.setName("Movies");
        moviesPage.setPath("movies");
        Page loginPage = new Page();
        loginPage.setName("Login");
        loginPage.setPath("doLogin");
        Page signUpPage = new Page();
        signUpPage.setName("Sign Up");
        signUpPage.setPath("signUp");
        Page newMoviePage = new Page();
        newMoviePage.setName("New Movie");
        newMoviePage.setPath("insertMovie");
        Page logoutPage = new Page();
        logoutPage.setName("Logout");
        logoutPage.setPath("logout");

        pages.add(page);
        pages.add(moviesPage);
        if (!authenticated) {
            pages.add(loginPage);
            pages.add(signUpPage);
        } else {
            pages.add(newMoviePage);
            pages.add(logoutPage);
        }
        return pages;
    }

    public void setActive(List<Page> pages, String path) {
        int index = 0;
        while (index < pages.size()) {
            if (pages.get(index).getPath().equals(path)) {
                pages.get(index).setActive(true);
            }
            index++;
        }
    }
}
