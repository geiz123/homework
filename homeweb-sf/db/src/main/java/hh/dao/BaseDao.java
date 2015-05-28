package hh.dao;

/**
 * Base Dao.  Should be used when you just need to get an entity and don't need to write
 * a Dao for it.
 * ex: @Autowired
 *     private BaseDao<Person, Integer> baseDao;
 */

import hh.entity.BaseEntity;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BaseDao<T extends BaseEntity<ID>, ID> {
    @PersistenceContext
    private EntityManager em;

    protected Class<T> entityClass;

    public BaseDao () {
        
        // This will only work if BaseDao is an abstract class because the generics are
        // "substituted", but if it is not an abstract then the generics are "sub" when contructor
        // is called
        // ParameterizedType genericSuperClass = ((ParameterizedType) getClass().getGenericSuperclass());
        // entityClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }
    
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    @SuppressWarnings("unchecked")
    public Class<T> getEntityClass() {
        if (entityClass == null)
            // only works if one extends BaseDao
            setEntityClass((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);

        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public void persist(T entity) {
        this.em.persist(entity);
    }

    @Transactional
    public T update(T entity) {
        return (T) this.em.merge(entity);
    }

    public T findById(ID id) {
        return (T) this.em.find(getEntityClass(), id);
    }

    @Transactional
    public void delete(ID id, Class<T> type) {
        Object ref = this.em.getReference(type, id);
        this.em.remove(ref);
    }

    @Transactional
    public void deleteById(ID id) {
        // Why getReference instead of find
        // http://stackoverflow.com/questions/5482141/what-is-the-difference-between-entitymanager-find-and-entitymanger-getreferenc
        T ref = this.em.getReference(getEntityClass(), id);
        this.em.remove(ref);
    }

    /**
     * TODO: Need to find out if this will delete relations too
     * 
     * @param entity
     */
    @Transactional
    public void deleteByEntity(T entity) {
        // Why we need to merge before delete
        // http://stackoverflow.com/questions/16086822/when-using-jpa-entitymanager-why-do-you-have-to-merge-before-you-remove

        if (em.contains(entity))
            em.remove(entity);
        else {
            T e = em.getReference(getEntityClass(), entity.getId());
            em.remove(em.merge(e));
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return em.createQuery("Select entity FROM " + getEntityClass().getSimpleName() + " entity").getResultList();
    }

    public Number getCount() {
        // HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        // Session session = hem.getSession();
        // Number ret = (Number) session.createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult();
        //
        // return ret;
        
        String queryString = "SELECT Count(*) FROM " + getEntityClass().getSimpleName();  
        Query query = em.createNativeQuery(queryString); 
        
        return (Number) query.getSingleResult();
    }

}
