package ru.geekbrains.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import ru.geekbrains.CategoryService;
import ru.geekbrains.DtoEntities.CategoryRepr;

@Named
@SessionScoped
public class CategoryController implements Serializable {

  @EJB
  private CategoryService categoryService;
  private CategoryRepr category;
  private List<CategoryRepr> categories;

  public void preloadData(ComponentSystemEvent componentSystemEvent) {
    categories = categoryService.findAll();
  }

  public CategoryRepr getCategory() {
    return category;
  }

  public void setCategory(CategoryRepr category) {
    this.category = category;
  }

  public String createCategory() {
    this.category = new CategoryRepr();
    return "/category_form.xhtml?faces-redirect=true";
  }

  public List<CategoryRepr> getAllCategories() {
    return categoryService.findAll();
  }

  public String editCategory(CategoryRepr category) {
    this.category = category;
    return "/category_form.xhtml?faces-redirect=true";
  }

  public void deleteCategory(CategoryRepr category) {
    categoryService.deleteById(category.getId());
  }

  public String saveCategory() {
    categoryService.saveOrUpdate(category);
    return "/category.xhtml?faces-redirect=true";
  }
}
