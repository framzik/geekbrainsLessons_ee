
package ru.geekbrains.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import ru.geekbrains.CategoryService;
import ru.geekbrains.DtoEntities.CategoryRepr;
import ru.geekbrains.ProductService;
import ru.geekbrains.ProductRepr;

@Named
@SessionScoped
public class ProductController implements Serializable {

  @EJB
  private ProductService productService;

  @EJB
  private CategoryService categoryService;

  @Inject
  private HttpSession httpSession;

  private ProductRepr product;

  private List<ProductRepr> products;

  private List<CategoryRepr> categories;

  public void preloadData(ComponentSystemEvent componentSystemEvent) {
    products = productService.findAll();
    categories = categoryService.findAll();
  }

  public ProductRepr getProduct() {
    return product;
  }

  public void setProduct(ProductRepr product) {
    this.product = product;
  }

  public String createProduct() {
    this.product = new ProductRepr();
    return "/product_form.xhtml?faces-redirect=true";
  }

  public List<ProductRepr> getAllProducts() {
    return products;
  }

  public String editProduct(ProductRepr product) {
    this.product = product;
    return "/product_form.xhtml?faces-redirect=true";
  }

  public void deleteProduct(ProductRepr product) {
    productService.deleteById(product.getId());
  }

  public String saveProduct() {
    productService.saveOrUpdate(product);
    return "/product.xhtml?faces-redirect=true";
  }

  public List<CategoryRepr> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoryRepr> categories) {
    this.categories = categories;
  }

  public String logout() {
    //HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    httpSession.invalidate();
    return "/product.xhtml?faces-redirect=true";
  }
}

