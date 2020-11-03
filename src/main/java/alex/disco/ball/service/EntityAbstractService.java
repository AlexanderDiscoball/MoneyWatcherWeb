package alex.disco.ball.service;

import alex.disco.ball.entity.AbstractEntity;
import alex.disco.ball.dao.common.AbstractRepo;

public abstract class EntityAbstractService<T extends AbstractEntity> implements AbstractService<T>{
    public void save(T entity) {
        getRepo().save(entity);
    }

    public T findById(Long entityId){
        return getRepo().findById(entityId).orElse(null);
    }

    @Override
    public boolean delete(T entity) {
        return getRepo().delete(entity);
    }

    @Override
    public boolean deleteById(Long entityId) {
        return getRepo().deleteById(entityId);
    }

    abstract AbstractRepo<T> getRepo();
}
