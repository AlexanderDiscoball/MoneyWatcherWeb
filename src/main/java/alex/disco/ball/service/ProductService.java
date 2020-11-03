package alex.disco.ball.service;

import alex.disco.ball.dao.common.AbstractRepo;
import alex.disco.ball.dao.common.ProductRepo;
import alex.disco.ball.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService extends EntityAbstractService<Product> {

    private ProductRepo repo;

    public boolean update(Product product){
        return getRepo().update(product);
    }

    @Override
    AbstractRepo<Product> getRepo() {
        return repo;
    }

    public void setRepo(ProductRepo repo) {
        this.repo = repo;
    }
}
