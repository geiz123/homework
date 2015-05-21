package hh.dao;

import java.util.List;

import hh.entity.Person;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class PersonDao extends BaseDao<Person, Integer> {

    @SuppressWarnings({ "unchecked" })
    public List<Person> getPersonByAddressId(Integer addId) {
        Query qry = getEntityManager().createQuery(
                "FROM " + getEntityClass().getSimpleName() + " WHERE addressId = :addId");
        qry.setParameter("addId", addId);

        return qry.getResultList();
    }
}
