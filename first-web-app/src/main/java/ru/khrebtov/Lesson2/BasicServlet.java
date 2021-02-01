package ru.khrebtov.Lesson2;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "BasicServlet", urlPatterns = "/basic_servlet")
public class BasicServlet implements Servlet {

  private static final Logger log = LoggerFactory.getLogger(BasicServlet.class);
  private transient ServletConfig config;

  @Override
  public void init(ServletConfig config) throws ServletException {
    this.config = config;
  }

  @Override
  public ServletConfig getServletConfig() {
    return config;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    log.info("New request!");
    res.getWriter().println("<h1>Servlet Content</h1>");
  }

  @Override
  public String getServletInfo() {
    return "BasicServlet";
  }

  @Override
  public void destroy() {
    log.info("Servlet {} destroyed", getServletInfo());
  }
}
