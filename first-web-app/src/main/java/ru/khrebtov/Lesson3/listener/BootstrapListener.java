package ru.khrebtov.Lesson3.listener;


import java.math.BigDecimal;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import ru.khrebtov.Lesson3.entity.*;
import ru.khrebtov.Lesson3.repository.CategoryRepository;
import ru.khrebtov.Lesson3.repository.ProductRepository;
import ru.khrebtov.Lesson3.repository.UserRepository;

@WebListener
public class BootstrapListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ProductRepository productRepository = new ProductRepository();
    UserRepository userRepository = new UserRepository();
    CategoryRepository categoryRepository = new CategoryRepository();

    productRepository.saveOrUpdate(new Product(null, "Product  1",
        "Description of product 1", new BigDecimal(100)));
    productRepository.saveOrUpdate(new Product(null, "Product  2",
        "Description of product 2", new BigDecimal(200)));
    productRepository.saveOrUpdate(new Product(null, "Product  3",
        "Description of product 3", new BigDecimal(200)));
    sce.getServletContext().setAttribute("productRepository", productRepository);

    userRepository.saveOrUpdate(new User(null, "Vasiliy", "+7(999) 999 99 99", "fr@ya.ru"));
    userRepository.saveOrUpdate(new User(null, "Petr", "+7(999) 999 99 98", "fr1@ya.ru"));
    userRepository.saveOrUpdate(new User(null, "Борис", "+7(999) 999 99 97", "fr2@ya.ru"));
    sce.getServletContext().setAttribute("userRepository", userRepository);

    categoryRepository.saveOrUpdate(new Category(null, "Бижутерия"));
    categoryRepository.saveOrUpdate(new Category(null, "Компьютеры"));
    categoryRepository.saveOrUpdate(new Category(null, "Авто"));
    sce.getServletContext().setAttribute("categoryRepository", categoryRepository);

  }
}
