package alex.disco.ball.dao.common;

import alex.disco.ball.entity.AbstractEntity;

import java.util.Optional;
import java.util.Set;

public interface AbstractRepo <T extends AbstractEntity>{

    boolean save(T entity);

    boolean delete(T entity);

    boolean update(T entity);

    boolean deleteById(Long entityId);

    Optional<T> findById(Long entityId);

    Long count();

    Set<T> findAll();


}
