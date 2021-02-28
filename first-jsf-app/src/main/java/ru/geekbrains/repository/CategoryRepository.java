package ru.geekbrains.repository;


import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.entity.Category;

@Named
@ApplicationScoped
public class CategoryRepository implements MainRepo<Category> {

  private static Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

  @PersistenceContext
  private EntityManager em;
  @Resource
  private UserTransaction ut;

  @PostConstruct
  public void init() throws Exception {
    if (countAll() == 0) {
      try {
        ut.begin();
        saveOrUpdate(new Category(null, "Бижутерия"));
        saveOrUpdate(new Category(null, "Компьютеры"));
        saveOrUpdate(new Category(null, "Авто"));
        ut.commit();
      } catch (Exception e) {
        logger.error("", e);
        ut.rollback();
      }

    }
  }

  @Override
  public List<Category> findAll() {
    return em.createNamedQuery("findAllCategories", Category.class).getResultList();
  }

  @Override
  public Category findById(Long id) {
    return em.find(Category.class, id);
  }

  @Override
  @Transactional
  public void saveOrUpdate(Category category) {
    if (category.getId() == null) {
      em.persist(category);
    }
    em.merge(category);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    em.createNamedQuery("deleteCategoryById").setParameter("id", id).executeUpdate();
  }

  public Long countAll() {
    return em.createNamedQuery("countAllCategories", Long.class).getSingleResult();
  }

}
