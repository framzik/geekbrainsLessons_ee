package ru.geekbrains.service.DtoEntities;


import ru.geekbrains.entity.User;

public class UserRepr {

  private Long id;
  private String name;
  private String phone;
  private String email;

  public UserRepr() {
  }

  public UserRepr(User user) {
    id = user.getId();
    name = user.getName();
    phone = user.getPhone();
    email = user.getEmail();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
