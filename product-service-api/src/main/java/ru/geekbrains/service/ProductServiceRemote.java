package ru.geekbrains.service;

import ru.geekbrains.ProductRepr;

import java.util.List;

public interface ProductServiceRemote {

  List<ProductRepr> findAll();

  ProductRepr findById(Long id);

  Long countAll();
}
