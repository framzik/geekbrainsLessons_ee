package ru.khrebtov.Lesson3.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.khrebtov.Lesson3.entity.Category;
import ru.khrebtov.Lesson3.repository.CategoryRepository;

@WebServlet(urlPatterns = "/category/*")
public class CategoryServlet extends HttpServlet {

  private CategoryRepository categoryRepository;

  @Override
  public void init() throws ServletException {
    this.categoryRepository = (CategoryRepository) getServletContext()
        .getAttribute("categoryRepository");
    if (categoryRepository == null) {
      throw new ServletException();
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
      req.setAttribute("categories", categoryRepository.findAll());
      getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(req, resp);
    } else if (req.getPathInfo().equals("/edit")) {
      long id;
      try {
        id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException e) {
        resp.setStatus(400);
        return;
      }
      Category category = categoryRepository.findById(id);
      if (category == null) {
        resp.setStatus(404);
        return;
      }
      req.setAttribute("category", category);
      getServletContext().getRequestDispatcher("/WEB-INF/category_form.jsp").forward(req, resp);
    } else if (req.getPathInfo().equals("/delete")) {
      long id;
      try {
        id = Long.parseLong(req.getParameter("id"));
      } catch (NumberFormatException e) {
        resp.setStatus(400);
        return;
      }
      categoryRepository.deleteById(id);
      resp.sendRedirect(req.getServletContext().getContextPath() + "/category");
    }else if(req.getPathInfo().equals("/add")){
      Category newCategory = new Category();
      categoryRepository.saveOrUpdate(newCategory);
      req.setAttribute("category",newCategory);
      getServletContext().getRequestDispatcher("/WEB-INF/category_form.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    long id;
    try {
      id = Long.parseLong(req.getParameter("id"));
    } catch (NumberFormatException e) {
      resp.setStatus(400);
      return;
    }
    Category category = new Category(id, req.getParameter("name"));
    categoryRepository.saveOrUpdate(category);
    resp.sendRedirect(req.getServletContext().getContextPath() + "/category");
  }
}
