package ru.khrebtov.Lesson3.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "menu")
public class Menu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<ul>" +
                "<li><a href=/first-web-app/main>Главная.</a></li>" +
                "<li><a href=/first-web-app/catalog>Каталог.</a></li>" +
                "<li><a href=/first-web-app/product>Продукт.</a></li>" +
                "<li><a href=/first-web-app/cart>Корзина.</a></li>" +
                "<li><a href=/first-web-app/order>Заказ.</a>.</li>" +
                "</ul>");
    }
}
