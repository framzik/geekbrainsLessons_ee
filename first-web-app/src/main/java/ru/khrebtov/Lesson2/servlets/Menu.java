package ru.khrebtov.Lesson2.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "menu")
public class Menu extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.getWriter().println("<ul>" +
        "<li><a href=" + getServletContext().getContextPath() + "/main>Главная.</a></li>" +
        "<li><a href=" + getServletContext().getContextPath() + "/catalog>Каталог.</a></li>" +
        "<li><a href=" + getServletContext().getContextPath() + "/product>Продукт.</a></li>" +
        "<li><a href=" + getServletContext().getContextPath() + "/cart>Корзина.</a></li>" +
        "<li><a href=" + getServletContext().getContextPath() + "/order>Заказ.</a>.</li>" +
        "</ul>");
  }
}
