package hh.dao;

import hh.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
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

    public List<Person> getByRangeWithAlivePet(int first, int pageSize) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);

        Root<Person> person = criteriaQuery.from(Person.class);

        CriteriaQuery<Person> select = criteriaQuery.select(person);

        Path<Object> path = person.join("pets").get("isDead");

        select.where(cb.equal(path, true));

        TypedQuery<Person> typedQuery = em.createQuery(select);

        typedQuery.setFirstResult(first);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }

    public List<Person> getByRangeWithFilter(int pageSize, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);

        Root<Person> person = criteriaQuery.from(Person.class);

        CriteriaQuery<Person> select = criteriaQuery.select(person);

        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Entry<String, Object> entry : filters.entrySet()) {
            predicates.add(cb.like(person.<String> get(entry.getKey()), entry.getValue().toString() + "%"));
        }

        // Root<Pet> pet = criteriaQuery.from(Pet.class);
        // Predicate test = cb.equal(pet.<String> get("id").<String> get("petName"), "Penny");
        // predicates.add(test);

        select.where(predicates.toArray(new Predicate[] {}));

        TypedQuery<Person> typedQuery = em.createQuery(select);

        typedQuery.setFirstResult(0);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }

    public Long getByRangeWithFilterCnt(Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<Person> person = cq.from(Person.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Entry<String, Object> entry : filters.entrySet()) {
            predicates.add(cb.like(person.<String> get(entry.getKey()), entry.getValue().toString() + "%"));
        }

        cq.select(cb.count(person));
        cq.where(predicates.toArray(new Predicate[] {}));

        return em.createQuery(cq).getSingleResult();
    }
}
