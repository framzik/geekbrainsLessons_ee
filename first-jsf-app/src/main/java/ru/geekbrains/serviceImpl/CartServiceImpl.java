package ru.geekbrains.serviceImpl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateful;

import ru.geekbrains.CartService;
import ru.geekbrains.ProductRepr;

@Stateful
public class CartServiceImpl implements CartService {

  private final Map<Long, ProductRepr> productMap = new HashMap<>();

  @Override
  public void addToCart(ProductRepr product) {
    productMap.put(product.getId(), product);
  }

  @Override
  public void remove(ProductRepr productRepr) {
    productMap.remove(productRepr.getId());
  }

  @Override
  public List<ProductRepr> getAllProducts() {
    return new ArrayList<>(productMap.values());
  }
}
