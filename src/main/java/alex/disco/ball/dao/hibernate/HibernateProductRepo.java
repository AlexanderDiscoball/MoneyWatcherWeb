package alex.disco.ball.dao.hibernate;

import alex.disco.ball.dao.common.ProductRepo;
import alex.disco.ball.entity.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository("hibernateProductRepo")
public class HibernateProductRepo extends HibernateAbstractRepo<Product> implements ProductRepo {

    @Autowired
    public HibernateProductRepo(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean save(Product entity) {
        session().save(entity);
        return true;
    }

    @Override
    public boolean delete(Product entity) {
        session().delete(entity);
        return true;
    }

    @Override
    public boolean update(Product entity) {
        session().update(entity);
        return true;
    }

    @Override
    public boolean deleteById(Long entityId) {
        Product product = session().get(Product.class, entityId);
        session().delete(product);
        return true;
    }

    @Override
    public Optional<Product> findById(Long entityId) {
        Product product = session().get(Product.class, entityId);
        return product == null? Optional.empty() :Optional.of(product);
    }

    @Override
    public Long count() {
        return (Long) session().createQuery("select count(p) from Product p").uniqueResult();
    }

    @Override
    public Set<Product> findAll() {
        List persons = session().createQuery("FROM Product").list();
        return persons.isEmpty()? Set.of() : new HashSet<Product>(persons);
    }
}
