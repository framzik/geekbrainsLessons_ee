package ru.geekbrains.DtoEntities;

import ru.geekbrains.entity.Category;
import ru.geekbrains.entity.Product;

import java.util.List;

public class CategoryRepr {

  private Long id;

  private String name;

  private List<Product> products;

  public CategoryRepr() {
  }

  public CategoryRepr(Category category) {
    id=category.getId();
    name = category.getName();
    products = category.getProducts();

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

  public List<Product> getProducts() {
    return products;
  }

}
