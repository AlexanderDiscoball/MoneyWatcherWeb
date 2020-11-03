package alex.disco.ball.service;

public interface AbstractService<T> {

    void save(T entity);

    T findById(Long entityId);

    boolean delete(T entity);

    boolean deleteById(Long entityId);

}
