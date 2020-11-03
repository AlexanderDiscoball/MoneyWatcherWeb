package alex.disco.ball.dao.hibernate;

import alex.disco.ball.dao.common.PersonRepo;
import alex.disco.ball.entity.Person;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository("hibernatePersonRepo")
public class HibernatePersonRepo extends HibernateAbstractRepo<Person> implements PersonRepo {

    @Autowired
    public HibernatePersonRepo(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean deleteById(Long entityId) {
        Person product = session().get(Person.class, entityId);
        session().delete(product);
        return true;
    }

    @Override
    public Optional<Person> findById(Long entityId) {
        Person product = session().get(Person.class, entityId);
        return product == null? Optional.empty() :Optional.of(product);
    }

    @Override
    public Long count() {
        return (Long) session().createQuery("select count(p) from Person p").uniqueResult();
    }

    @Override
    public Set<Person> findAll() {
        List persons = session().createQuery("FROM Person").list();
        return persons.isEmpty()? Set.of() : new HashSet<Person>(persons);
    }
}
