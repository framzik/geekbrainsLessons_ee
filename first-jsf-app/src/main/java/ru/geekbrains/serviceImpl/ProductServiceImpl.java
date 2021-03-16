package ru.geekbrains.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.ProductRepr;
import ru.geekbrains.ProductService;
import ru.geekbrains.rest.ProductServiceRest;
import ru.geekbrains.service.ProductServiceRemote;
import ru.geekbrains.entity.Category;
import ru.geekbrains.entity.Product;
import ru.geekbrains.repository.CategoryRepository;
import ru.geekbrains.repository.ProductRepository;

@Stateless
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductServiceRest {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(this::buildProductRepr)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductRepr> getAllProductFromCategoryId(Long categoryId) {
        Category currentCategory = categoryRepository.findById(categoryId);
        return productRepository.getAllProducts(currentCategory).stream().map(this::buildProductRepr).collect(
                Collectors.toList());
    }

    @Override
    public ProductRepr findById(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            return buildProductRepr(product);
        }
        return null;
    }

    @Override
    public ProductRepr findByName(String name) {
        Product product = productRepository.findByName(name);
        if (product != null) {
            return buildProductRepr(product);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @Override
    public void insert(ProductRepr product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
    }

    @Override
    public void update(ProductRepr product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductRepr product) {
        logger.info("Saving product with id {}", product.getId());

        Category category = null;
        if (product.getCategoryId() != null) {
            category = categoryRepository.getReference(product.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(product, category));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private ProductRepr buildProductRepr(Product product) {
        ProductRepr repr = new ProductRepr();

        repr.setId(product.getId());
        repr.setName(product.getName());
        repr.setDescription(product.getDescription());
        repr.setPrice(product.getPrice());
        Category category = product.getCategory();
        repr.setCategoryId(category != null ? category.getId() : null);
        repr.setCategoryName(category != null ? category.getName() : null);

        return repr;
    }

}
