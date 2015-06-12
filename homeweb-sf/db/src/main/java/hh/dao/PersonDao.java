package hh.dao;

import hh.entity.Person;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDao extends BaseDao<Person, Integer> {

    public List<Person> getPersonByAddressId(Integer addId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Person> q = cb.createQuery(Person.class);
        Root<Person> c = q.from(Person.class);
        ParameterExpression<Integer> p = cb.parameter(Integer.class);
        q.select(c).where(cb.equal(c.get("addressId"), p));
        
        TypedQuery<Person> query = em.createQuery(q);
        query.setParameter(p, addId);
        
        // Query qry = getEntityManager().createQuery("FROM " + getEntityClass().getSimpleName() +
        // " WHERE addressId = :addId");
        // qry.setParameter("addId", addId);

        return query.getResultList();
    }
    
    public List<Person> getByRange(int first, int pageSize) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);
        Root<Person> from = criteriaQuery.from(Person.class);
        CriteriaQuery<Person> select = criteriaQuery.select(from);
        TypedQuery<Person> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult(first);
        typedQuery.setMaxResults(pageSize);
        
        return typedQuery.getResultList();
    }
}
