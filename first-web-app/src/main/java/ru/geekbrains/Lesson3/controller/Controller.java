package ru.geekbrains.Lesson3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controller", urlPatterns = {"", "/main", "/catalog", "/cart",
    "/order", "/company"})
public class Controller extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    processRequest(req, resp);
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String url = "/WEB-INF/";
    switch (request.getServletPath()) {
      case "":
      case "/main":
        url += "index.jsp";
        break;
      case "/catalog":
        url += "catalog.jsp";
        break;
      case "/cart":
        url += "cart.jsp";
        break;
      case "/order":
        url += "order.jsp";
        break;
      case "/company":
        url += "company.jsp";
        break;
    }
    request.getRequestDispatcher(url).forward(request, response);
  }

}