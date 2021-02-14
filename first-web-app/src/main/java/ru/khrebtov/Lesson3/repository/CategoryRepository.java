package ru.khrebtov.Lesson3.repository;


import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import ru.khrebtov.Lesson3.entity.Category;

public class CategoryRepository implements MainRepo<Category> {

  private final Map<Long, Category> categoryMap = new ConcurrentHashMap<>();
  private final AtomicInteger identity = new AtomicInteger(0);

  @Override
  public ArrayList<Category> findAll() {
    return new ArrayList<>(categoryMap.values());
  }

  @Override
  public Category findById(Long id) {
    return categoryMap.get(id);
  }

  @Override
  public void saveOrUpdate(Category category) {
    if (category.getId() == null) {
      long id = identity.incrementAndGet();
      category.setId(id);
    }
    categoryMap.put(category.getId(), category);
  }

  @Override
  public void deleteById(Long id) {
    categoryMap.remove(id);
  }
}
