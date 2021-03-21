package ru.geekbrains.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.entity.Role;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RoleRepository {

    private final Logger logger = LoggerFactory.getLogger(RoleRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    @TransactionAttribute
    public Role merge(Role role) {
        return em.merge(role);
    }

    public Role findById(Long id) {
        return em.find(Role.class, id);
    }

    public List<Role> getAllRoles() {
        return em.createQuery("from Role ", Role.class).getResultList();
    }

    public long getCount() {
        return em.createQuery("select count(*) from Role", Long.class)
                .getSingleResult();
    }

    public Role saveOrUpdate(Role role) {
        if (role.getId() == null) {
            em.persist(role);
            return role;
        }
        return em.merge(role);
    }

    public void deleteById(Long id) {
//        try {
//            Role attached = findById(id);
//            if (attached != null) {
//                em.remove(attached);
//            }
//        } catch (Exception ex) {
//            logger.error("Error with entity class", ex);
//            throw new IllegalStateException(ex);
//        }
        em.createQuery("delete from Role r where r.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
