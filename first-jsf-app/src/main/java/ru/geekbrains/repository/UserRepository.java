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
import ru.geekbrains.entity.User;

@Named
@ApplicationScoped
public class UserRepository implements MainRepo<User> {

  private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

  @PersistenceContext(name = "ds")
  private EntityManager em;
  @Resource
  private UserTransaction ut;

  @PostConstruct
  public void init() throws Exception {
    if (countAll() == 0) {
      try {
        ut.begin();
        saveOrUpdate(new User(null, "Vasiliy", "+7(999) 999 99 99", "fr@ya.ru"));
        saveOrUpdate(new User(null, "Petr", "+7(999) 999 99 98", "fr1@ya.ru"));
        saveOrUpdate(new User(null, "Борис", "+7(999) 999 99 97", "fr2@ya.ru"));
        ut.commit();
      } catch (Exception ex) {
        logger.error("", ex);
        ut.rollback();
      }
    }
  }


  @Override
  public List<User> findAll() {
    return em.createNamedQuery("findAllUsers", User.class).getResultList();
  }

  @Override
  public User findById(Long id) {
    return em.find(User.class, id);
  }

  @Transactional
  @Override
  public void saveOrUpdate(User user) {
    if (user.getId() == null) {
      em.persist(user);
    }
    em.merge(user);
  }

  @Transactional
  @Override
  public void deleteById(Long id) {
    em.createNamedQuery("deleteUserById").setParameter("id", id).executeUpdate();
  }

  public Long countAll() {
    return em.createNamedQuery("countAllUsers", Long.class).getSingleResult();
  }

}
