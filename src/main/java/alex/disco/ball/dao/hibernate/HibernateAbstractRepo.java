package alex.disco.ball.dao.hibernate;

import alex.disco.ball.entity.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class HibernateAbstractRepo<T extends AbstractEntity>{
    private SessionFactory sessionFactory;
    public HibernateAbstractRepo(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    public boolean save(T entity) {
        session().save(entity);
        return true;
    }

    public boolean delete(T entity) {
        session().delete(entity);
        return true;
    }

    public boolean update(T entity) {
        session().update(entity);
        return true;
    }

}
