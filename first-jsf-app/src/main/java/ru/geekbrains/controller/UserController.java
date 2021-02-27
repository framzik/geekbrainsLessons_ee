package ru.geekbrains.controller;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ru.geekbrains.persist.User;
import ru.geekbrains.repository.UserRepository;

@Named
@SessionScoped
public class UserController implements Serializable {

  private User user;
  @Inject
  private UserRepository userRepository;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }


  public String editUser(User user) {
    this.user = user;
    return "user_form.xhtml?faces-redirect-true";
  }

  public void deleteUser(User user) {
    userRepository.deleteById(user.getId());
  }

  public String saveUser() {
    userRepository.saveOrUpdate(user);
    return "/user.xhtml?faces-redirect-true";
  }

  public String createUser() {
    this.user = new User();
    return "user_form.xhtml?faces-redirect-true";

  }
}
