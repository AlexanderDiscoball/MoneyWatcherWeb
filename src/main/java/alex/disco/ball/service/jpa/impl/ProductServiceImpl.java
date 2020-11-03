package alex.disco.ball.service.jpa.impl;

import alex.disco.ball.dao.jpa.ProductRepoJpa;
import alex.disco.ball.entity.Product;
import alex.disco.ball.service.jpa.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("productServiceJpa")
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private ProductRepoJpa productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepoJpa productRepoJpa) {
        this.productRepo = productRepoJpa;
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public long countProducts() {
        return productRepo.count();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        product.setModifiedAt(LocalDateTime.now().toLocalDate());
        return productRepo.save(product);
    }

    @Override
    @Transactional
    public Product updateName(Product product, String name) {
        productRepo.updateName(product.getId(), name);
        product.setModifiedAt(LocalDateTime.now().toLocalDate());
        product.setName(name);
        return product;
    }

    @Override
    @Transactional
    public Product update(Product product) {
        product.setModifiedAt(LocalDateTime.now().toLocalDate());
        return productRepo.save(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        productRepo.delete(product);
    }

    @Transactional
    public void deleteById(long id) {
        productRepo.deleteById(id);
    }

}
