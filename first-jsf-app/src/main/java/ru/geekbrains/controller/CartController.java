package ru.geekbrains.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import ru.geekbrains.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class CartController implements Serializable {

  private Map<Long, Product> productMap = new HashMap<>();

  public String addToCart(Product product) {
    productMap.put(product.getId(), product);
    return "/product.xhtml?faces-redirect-true";
  }

  public void removeFromCart(Product product) {
      productMap.remove(product.getId());
  }

  public List<Product> getAllProducts() {
    return new ArrayList<>(productMap.values());
  }
}
