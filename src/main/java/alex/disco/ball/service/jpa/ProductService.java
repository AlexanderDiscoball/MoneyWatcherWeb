package alex.disco.ball.service.jpa;

import alex.disco.ball.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    long countProducts();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Product updateName(Product product, String name);

    Product update(Product product);

    void delete(Product product);

    void deleteById(long id);


}
