package ru.geekbrains.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import ru.geekbrains.CartService;
import ru.geekbrains.ProductRepr;

@Named
@SessionScoped
public class CartController implements Serializable {

  @EJB
  private CartService cartService;

  public void addToCart(ProductRepr product) {
    cartService.addToCart(product);
  }

  public void removeFromCart(ProductRepr product) {
    cartService.remove(product);
  }

  public List<ProductRepr> getAllProducts() {
    return cartService.getAllProducts();
  }
}
