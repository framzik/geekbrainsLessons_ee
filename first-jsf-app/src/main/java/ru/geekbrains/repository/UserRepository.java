package ru.geekbrains.repository;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.entity.User;

@Stateless
public class UserRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext(name = "ds")
    private EntityManager em;

    public UserRepository() {
    }

    public List<User> findAll() {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> query = cb.createQuery(User.class);
//        Root<User> from = query.from(User.class);
//        from.fetch("roles", JoinType.LEFT);
//        query.select(from).distinct(true);
//
//        return em.createQuery(query).getResultList();
        return em.createNamedQuery("findAllUsers", User.class).getResultList();
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public User saveOrUpdate(User user) {
        if (user.getId() == null) {
            em.persist(user);
            return user;
        }
        return em.merge(user);
    }

    public void deleteById(Long id) {
        logger.info("Deleting user");
        try {
            User attached = findById(id);
            if (attached != null) {
                em.remove(attached);
            }
        } catch (Exception ex) {
            logger.error("Error with entity class", ex);
            throw new IllegalStateException(ex);
        }
    }

    public Long getCount() {
        return em.createNamedQuery("countAllUsers", Long.class).getSingleResult();
    }

    public boolean existsById(Long id) {
        return findById(id) != null;
    }
}
