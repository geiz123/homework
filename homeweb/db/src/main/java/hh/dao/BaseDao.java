package hh.dao;

import hh.entity.BaseEntity;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("baseDao")
public class BaseDao<T extends BaseEntity<ID>, ID> {
    @PersistenceContext
    private EntityManager entityManager;

    protected Class<T> entityClass;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    @SuppressWarnings("unchecked")
    public Class<T> getEntityClass() {
        if (entityClass == null)
            // only works if one extends BaseDao, we will take care of it with CDI
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void persist(T entity) {
        this.entityManager.persist(entity);
    }

    public T update(T entity) {
        return (T) this.entityManager.merge(entity);
    }

    public T findById(ID id, Class<T> type) {
        return (T) this.entityManager.find(type, id);
    }

    public void delete(ID id, Class<T> type) {
        Object ref = this.entityManager.getReference(type, id);
        this.entityManager.remove(ref);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("Select entity FROM "+getEntityClass().getSimpleName() +" entity").getResultList();
    }

}
