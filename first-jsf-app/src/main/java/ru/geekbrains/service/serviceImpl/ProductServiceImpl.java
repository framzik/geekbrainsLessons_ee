package ru.geekbrains.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.entity.Category;
import ru.geekbrains.entity.Product;
import ru.geekbrains.repository.CategoryRepository;
import ru.geekbrains.repository.ProductRepository;
import ru.geekbrains.service.DtoEntities.ProductRepr;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.service.ProductServiceRemote;

@Stateless
public class ProductServiceImpl implements ProductService, ProductServiceRemote {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductRepr findById(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            return new ProductRepr(product);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductRepr product) {
        logger.info("Saving product with id {}" , product.getId());

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
}
