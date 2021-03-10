package ru.geekbrains.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.entity.Product;

@Stateless
public class ProductRepository {

  private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  public List<Product> findAll() {
    return em.createNamedQuery("findAll", Product.class)
        .getResultList();
  }

  public Product findById(Long id) {
    return em.find(Product.class, id);
  }

  public Long countAll() {
    return em.createNamedQuery("countAll", Long.class)
        .getSingleResult();
  }

  public void saveOrUpdate(Product product) {
    if (product.getId() == null) {
      em.persist(product);
    }
    em.merge(product);
  }

  public void deleteById(Long id) {
    em.createNamedQuery("deleteById")
        .setParameter("id", id)
        .executeUpdate();
  }
}
