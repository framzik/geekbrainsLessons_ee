package ru.geekbrains;

import java.util.List;
import javax.ejb.Local;

@Local
public interface CartService {

  void addToCart(ProductRepr product);

  void remove(ProductRepr productRepr);

  List<ProductRepr> getAllProducts();
}
