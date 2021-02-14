package ru.khrebtov.Lesson3.controller;

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
    if (request.getServletPath().equals("") || request.getServletPath().equals("/main")) {
      url += "index.jsp";
    } else if (request.getServletPath().equals("/catalog")) {
      url += "catalog.jsp";
    } else if (request.getServletPath().equals("/cart")) {
      url += "cart.jsp";
    } else if (request.getServletPath().equals("/order")) {
      url += "order.jsp";
    } else if (request.getServletPath().equals("/company")) {
      url += "company.jsp";
    }
    request.getRequestDispatcher(url).forward(request, response);
  }

}