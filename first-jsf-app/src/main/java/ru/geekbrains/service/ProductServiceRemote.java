package ru.geekbrains.service;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

@Local
public interface ProductServiceRemote {

    List<ProductRepr> findAll();

    ProductRepr findById(Long id);

    Long countAll();
}
