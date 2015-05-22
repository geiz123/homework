package hh.dao;

import hh.entity.Person;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDao extends BaseDao<Person, Integer> {

    @SuppressWarnings({ "unchecked" })
    public List<Person> getPersonByAddressId(Integer addId) {
        Query qry = getEntityManager().createQuery(
                "FROM " + getEntityClass().getSimpleName() + " WHERE addressId = :addId");
        qry.setParameter("addId", addId);

        return qry.getResultList();
    }
}
