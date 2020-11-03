package alex.disco.ball.service.jpa.impl;

import alex.disco.ball.dao.jpa.PersonRepoJpa;
import alex.disco.ball.dao.jpa.ProductRepoJpa;
import alex.disco.ball.entity.Person;
import alex.disco.ball.entity.Product;
import alex.disco.ball.service.jpa.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private PersonRepoJpa personRepoJpa;

    @Autowired
    public PersonServiceImpl(PersonRepoJpa personRepoJpa) {
        this.personRepoJpa = personRepoJpa;
    }

    @Override
    public List<Person> findAll() {
        return personRepoJpa.findAll();
    }

    @Override
    public long countProducts() {
        return personRepoJpa.count();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personRepoJpa.findById(id);
    }

    @Override
    @Transactional
    public Person save(Person person) {
        person.setModifiedAt(LocalDateTime.now().toLocalDate());
        return personRepoJpa.save(person);
    }

    @Override
    @Transactional
    public Person updateFirstName(Person person, String firstName) {
        personRepoJpa.updateFirstName(person.getId(), firstName);
        person.setModifiedAt(LocalDateTime.now().toLocalDate());
        person.setFirstName(firstName);
        return person;
    }

    @Override
    @Transactional
    public Person update(Person person) {
        person.setModifiedAt(LocalDateTime.now().toLocalDate());
        return personRepoJpa.save(person);
    }

    @Override
    @Transactional
    public void delete(Person person) {
        personRepoJpa.delete(person);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        personRepoJpa.deleteById(id);
    }
}
