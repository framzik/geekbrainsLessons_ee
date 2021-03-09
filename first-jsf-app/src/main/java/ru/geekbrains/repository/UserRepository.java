package ru.geekbrains.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.entity.User;

@Stateless
public class UserRepository {

  private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

  @PersistenceContext(name = "ds")
  private EntityManager em;

  public List<User> findAll() {
    return em.createNamedQuery("findAllUsers", User.class).getResultList();
  }

  public User findById(Long id) {
    return em.find(User.class, id);
  }

  public void saveOrUpdate(User user) {
    if (user.getId() == null) {
      em.persist(user);
    }
    em.merge(user);
  }

  public void deleteById(Long id) {
    em.createNamedQuery("deleteUserById").setParameter("id", id).executeUpdate();
  }

  public Long countAll() {
    return em.createNamedQuery("countAllUsers", Long.class).getSingleResult();
  }

}
