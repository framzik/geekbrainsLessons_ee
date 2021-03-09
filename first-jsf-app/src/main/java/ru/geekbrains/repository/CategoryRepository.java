package ru.geekbrains.repository;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.entity.Category;

@Stateless
public class CategoryRepository {

  private static Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

  @PersistenceContext
  private EntityManager em;

  public List<Category> findAll() {
    return em.createNamedQuery("findAllCategories", Category.class).getResultList();
  }

  public Category findById(Long id) {
    return em.find(Category.class, id);
  }

  public void saveOrUpdate(Category category) {
    if (category.getId() == null) {
      em.persist(category);
    }
    em.merge(category);
  }

  public void deleteById(Long id) {
    em.createNamedQuery("deleteCategoryById").setParameter("id", id).executeUpdate();
  }

  public Long countAll() {
    return em.createNamedQuery("countAllCategories", Long.class).getSingleResult();
  }

  public Category getReference(Long id) {
    return em.getReference(Category.class, id);
  }
}
