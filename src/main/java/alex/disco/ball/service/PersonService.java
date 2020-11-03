package alex.disco.ball.service;

import alex.disco.ball.dao.common.AbstractRepo;
import alex.disco.ball.dao.common.PersonRepo;
import alex.disco.ball.entity.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends EntityAbstractService<Person> {

    private PersonRepo repo;

    @Override
    AbstractRepo<Person> getRepo() {
        return repo;
    }
}
