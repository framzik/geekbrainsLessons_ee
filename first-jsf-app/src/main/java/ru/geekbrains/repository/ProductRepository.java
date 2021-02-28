package ru.geekbrains.repository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import ru.geekbrains.persist.Product;

@Named
@ApplicationScoped
public class ProductRepository implements MainRepo<Product> {

  private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

  private final AtomicLong identity = new AtomicLong(0);

  @PostConstruct
  public void init() {
    this.saveOrUpdate(new Product(null, "Product  1",
        "Description of product 1", new BigDecimal(100)));
    this.saveOrUpdate(new Product(null, "Product  2",
        "Description of product 2", new BigDecimal(200)));
    this.saveOrUpdate(new Product(null, "Product  3",
        "Description of product 3", new BigDecimal(200)));
  }

  public ArrayList findAll() {
    return new ArrayList<>(productMap.values());
  }

  public Product findById(Long id) {
    return productMap.get(id);
  }

  public void saveOrUpdate(Product product) {
    if (product.getId() == null) {
      Long id = identity.incrementAndGet();
      product.setId(id);
    }
    productMap.put(product.getId(), product);
  }

  public void deleteById(Long id) {
    productMap.remove(id);
  }
}
