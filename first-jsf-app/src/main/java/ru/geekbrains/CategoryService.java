package ru.geekbrains;

import java.util.List;
import javax.ejb.Local;

import ru.geekbrains.DtoEntities.CategoryRepr;

@Local
public interface CategoryService {

  List<CategoryRepr> findAll();

  CategoryRepr findById(Long id);

  void saveOrUpdate(CategoryRepr category);

  void deleteById(Long id);

  Long countAll();
}
