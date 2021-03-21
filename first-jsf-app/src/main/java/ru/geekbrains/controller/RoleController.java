package ru.geekbrains.controller;


import ru.geekbrains.DtoEntities.RoleRepr;
import ru.geekbrains.serviceImpl.RoleService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RoleController implements Serializable {

    @EJB
    private RoleService roleService;
    private RoleRepr role;
    private List<RoleRepr> roles;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        roles = roleService.getAllRoles();
    }

    public RoleRepr getRole() {
        return role;
    }

    public void setRole(RoleRepr role) {
        this.role = role;
    }

    public String createRole() {
        this.role = new RoleRepr();
        return "role_form.xhtml?faces-redirect=true";
    }

    public List<RoleRepr> getAll() {
        return roleService.getAllRoles();
    }

    public String editRole(RoleRepr category) {
        this.role = category;
        return "role_form.xhtml?faces-redirect=true";
    }

    public void deleteRole(RoleRepr role) {
        roleService.deleteById(role.getId());
    }

    public String saveRole() {
        roleService.saveOrUpdate(role);
        return "role.xhtml?faces-redirect=true";
    }
}
