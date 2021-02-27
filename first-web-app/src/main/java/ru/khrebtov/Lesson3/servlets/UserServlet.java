package ru.khrebtov.Lesson3.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.Lesson3.entity.User;
import ru.khrebtov.Lesson3.repository.UserRepository;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

  private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);
  private UserRepository userRepo;

  @Override
  public void init() throws ServletException {
    logger.info("Method init() UserServlet...");
    this.userRepo = (UserRepository) getServletContext().getAttribute("userRepository");
    if (userRepo == null) {
      throw new ServletException("UserRepository not initialized");
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
      req.setAttribute("users", userRepo.findAll());
      getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
    } else if (req.getPathInfo().equals("/edit")) {
      long id;
      try {
        id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException e) {
        resp.setStatus(400);
        return;
      }
      User user = userRepo.findById(id);
      if (user == null) {
        resp.setStatus(404);
        return;
      }
      req.setAttribute("user", user);
      getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
    } else if (req.getPathInfo().equals("/delete")) {
      long id;
      try {
        id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException e) {
        resp.setStatus(400);
        return;
      }
      userRepo.deleteById(id);
      resp.sendRedirect(req.getServletContext().getContextPath() + "/user");
    } else if (req.getPathInfo().equals("/add")) {
      req.setAttribute("user", new User());
      getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Long id = null;
    try {
      String strId = req.getParameter("id");
      if (strId != null && !strId.isBlank()) {
        id = Long.parseLong(strId);
      }
    } catch (NumberFormatException e) {
      resp.setStatus(400);
      return;
    }
    User user = new User(id, req.getParameter("name"), req.getParameter("phone"),
        req.getParameter("email"));
    userRepo.saveOrUpdate(user);
    resp.sendRedirect(req.getServletContext().getContextPath() + "/user");
  }
}
