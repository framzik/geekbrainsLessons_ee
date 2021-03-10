package ru.geekbrains.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import ru.geekbrains.entity.Category;
import ru.geekbrains.repository.CategoryRepository;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.DtoEntities.CategoryRepr;

@Stateless
public class CategoryServiceImpl implements CategoryService {

  @EJB
  private CategoryRepository categoryRepository;

  @Override
  public List<CategoryRepr> findAll() {
    return categoryRepository.findAll().stream().map(CategoryRepr::new).collect(
        Collectors.toList());
  }

  @Override
  public CategoryRepr findById(Long id) {
    Category category = categoryRepository.findById(id);
    if (category != null) {
      return new CategoryRepr(category);
    }
    return null;
  }

  @Override
  @TransactionAttribute
  public void saveOrUpdate(CategoryRepr categoryRepr) {
    categoryRepository.saveOrUpdate(new Category(categoryRepr) );
  }

  @Override
  @TransactionAttribute
  public void deleteById(Long id) {
    categoryRepository.deleteById(id);
  }

  @Override
  public Long countAll() {
    return categoryRepository.countAll();
  }
}
