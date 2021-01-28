package ru.khrebtov;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstServlet implements Servlet {
  private static final Logger logger = LoggerFactory.getLogger(FirstServlet.class);
  private ServletConfig config;

  @Override
  public void init(ServletConfig config) throws ServletException {
    logger.info("FirstServlet is initialized");
    this.config = config;
  }

  @Override
  public ServletConfig getServletConfig() {
    return config;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    logger.info("New request to FirstServlet");
    res.getWriter().println("<h1>Hello from firstServlet!</h1>");
  }

  @Override
  public String getServletInfo() {
    return null;
  }

  @Override
  public void destroy() {

  }
}
