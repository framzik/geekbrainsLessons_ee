package ru.geekbrains.serviceImpl;

import ru.geekbrains.DtoEntities.RoleRepr;
import ru.geekbrains.entity.Role;
import ru.geekbrains.repository.RoleRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RoleService implements Serializable {

    @Inject
    private RoleRepository roleRepository;

    @TransactionAttribute
    public List<RoleRepr> getAllRoles() {
        return roleRepository.getAllRoles().stream()
                .map(RoleRepr::new)
                .collect(Collectors.toList());
    }

    public RoleRepr findById(Long id) {
        Role role = roleRepository.findById(id);
        if(role!=null){
            return new RoleRepr(role);
        }
        return null;
    }

    @TransactionAttribute
    public void saveOrUpdate(RoleRepr roleRepr) {
        Role saved = roleRepository.saveOrUpdate(new Role(roleRepr));
        roleRepr.setId(saved.getId());
    }

    @TransactionAttribute
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
