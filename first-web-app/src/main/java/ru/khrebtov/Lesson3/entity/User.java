package ru.khrebtov.Lesson3.entity;

public class User {

  private Long id;
  private String name;
  private String phone;
  private String email;

  public User(Long id, String name, String phone, String email) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.email = email;
  }

  public User() {
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
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
