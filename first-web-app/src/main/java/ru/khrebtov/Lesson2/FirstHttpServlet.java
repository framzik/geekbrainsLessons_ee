package ru.khrebtov.Lesson2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "FirstHttpServlet", urlPatterns = "/first_http_servlet")
public class FirstHttpServlet extends HttpServlet {

  private static final Logger log = LoggerFactory.getLogger(FirstHttpServlet.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    log.info("New Get request with include");
    getServletContext().getRequestDispatcher("/header.html").include(req, resp);
    //resp.getWriter().println("<p>Response body from servlet</p>");
    getServletContext().getRequestDispatcher("/footer.html").include(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    log.info("New POST request");
    resp.getWriter().println("<h1>New POST request</h1>");
    getServletContext().getRequestDispatcher("/index.html").forward(req, resp);
  }
}
