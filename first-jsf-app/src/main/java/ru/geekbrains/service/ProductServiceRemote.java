package ru.geekbrains.service;

import java.util.List;
import javax.ejb.Remote;
import ru.geekbrains.service.DtoEntities.ProductRepr;

@Remote
public interface ProductServiceRemote {

  List<ProductRepr> findAll();

  ProductRepr findById(Long id);

  Long countAll();
}
