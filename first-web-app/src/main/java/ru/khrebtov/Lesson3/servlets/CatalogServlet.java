package ru.khrebtov.Lesson3.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet( urlPatterns = "/catalog_servlet/*")
public class CatalogServlet extends HttpServlet {
    Logger log = LoggerFactory.getLogger(CatalogServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Method get() CatalogServlet...");

        getServletContext().getRequestDispatcher("/WEB-INF/catalog.jsp").forward(req,resp);
    }
}
