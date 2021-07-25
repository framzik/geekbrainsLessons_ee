package ru.geekbrains.Lesson3.repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import ru.geekbrains.Lesson3.entity.Product;

public class ProductRepository implements MainRepo<Product> {

  private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

  private final AtomicLong identity = new AtomicLong(0);

  @Override
  public ArrayList<Product> findAll() {
    return new ArrayList<>(productMap.values());
  }

  @Override
  public Product findById(Long id) {
    return productMap.get(id);
  }

  @Override
  public void saveOrUpdate(Product product) {
    if (product.getId() == null) {
      Long id = identity.incrementAndGet();
      product.setId(id);
    }
    productMap.put(product.getId(), product);
  }

  @Override
  public void deleteById(Long id) {
    productMap.remove(id);
  }
}
