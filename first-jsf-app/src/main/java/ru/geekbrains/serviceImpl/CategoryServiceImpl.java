package ru.geekbrains.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import ru.geekbrains.CategoryService;
import ru.geekbrains.DtoEntities.CategoryRepr;
import ru.geekbrains.entity.Category;
import ru.geekbrains.repository.CategoryRepository;
import ru.geekbrains.rest.CategoryServiceRest;

@Stateless
public class CategoryServiceImpl implements CategoryService, CategoryServiceRest {

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
    public void insert(CategoryRepr category) {
        if (category.getId() != null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(category);
    }

    @Override
    public void update(CategoryRepr category) {
        if (category.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(category);
    }

    @Override
    @TransactionAttribute
    public void saveOrUpdate(CategoryRepr categoryRepr) {
        categoryRepository.saveOrUpdate(new Category(categoryRepr));
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
