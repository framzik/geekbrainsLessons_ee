package ru.geekbrains.controller;

import ru.geekbrains.DtoEntities.RoleRepr;
import ru.geekbrains.DtoEntities.UserRepr;
import ru.geekbrains.UserService;
import ru.geekbrains.entity.Role;
import ru.geekbrains.serviceImpl.RoleService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    private UserRepr user;
    @EJB
    private UserService userService;

    @EJB
    private RoleService roleService;

    private List<UserRepr> users;
    private List<RoleRepr> roles;

    public List<RoleRepr> getRoles() {
        return roles;
    }

    public List<UserRepr> getUsers() {
        return users;
    }

    public void setUsers(List<UserRepr> users) {
        this.users = users;
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.roles = roleService.getAllRoles();
        this.users = userService.findAll();
    }

    public UserRepr getUser() {
        return user;
    }

    public void setUser(UserRepr user) {
        this.user = user;
    }

    public List<UserRepr> getAllUsers() {
        return users;
    }


    public String editUser(UserRepr user) {
        this.user = user;
        return "user_form.xhtml?faces-redirect=true";
    }

    public void deleteUser(UserRepr user) {
        userService.deleteById(user.getId());
    }

    public String saveUser() {
            userService.merge(this.user);
//        userService.saveOrUpdate(user);
        return "user.xhtml?faces-redirect=true";
    }

    public String createUser() {
        this.user = new UserRepr();
        return "user_form.xhtml?faces-redirect=true";
    }

    public List<RoleRepr> getAllRoles() {
        return roles;
    }

    public List<Role> getRolesForUser(UserRepr user) {
        return userService.getRolesForUser(user);
    }
}
