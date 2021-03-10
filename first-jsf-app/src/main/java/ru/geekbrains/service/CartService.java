package ru.geekbrains.service;

import java.util.List;
import javax.ejb.Local;
import ru.geekbrains.service.DtoEntities.ProductRepr;

@Local
public interface CartService {

  void addToCart(ProductRepr product);

  void remove(ProductRepr productRepr);

  List<ProductRepr> getAllProducts();
}
