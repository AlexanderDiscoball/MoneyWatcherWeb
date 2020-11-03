package alex.disco.ball.service.jpa;

import alex.disco.ball.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> findAll();

    long countProducts();

    Optional<Person> findById(Long id);

    Person save(Person person);

    Person updateFirstName(Person person, String name);

    Person update(Person person);

    void delete(Person person);

    void deleteById(long id);
}
