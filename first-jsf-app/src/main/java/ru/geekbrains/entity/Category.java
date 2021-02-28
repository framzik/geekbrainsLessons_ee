package ru.geekbrains.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@NamedQueries({
    @NamedQuery(name = "findAllCategories", query = "from Category"),
    @NamedQuery(name = "deleteCategoryById", query = "delete from Category c where c.id=:id"),
    @NamedQuery(name = "countAllCategories", query = "select count(*) from Category")
})
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  //  @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
//  private List<Product> products;
//
//  public List<Product> getProducts() {
//    return products;
//  }
//
//  public void setProducts(List<Product> products) {
//    this.products = products;
//  }
  public Category() {
  }

  public Category(Long id, String name) {
    this.id = id;
    this.name = name;
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

}
