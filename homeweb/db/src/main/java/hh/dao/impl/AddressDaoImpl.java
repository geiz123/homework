package hh.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import hh.dao.AddressDao;
import hh.model.Address;

public class AddressDaoImpl implements AddressDao<Address, Integer> {
//    @Inject
//    private EntityManager em;

    public void persist(Address entity) {
        // TODO Auto-generated method stub

    }

    public void update(Address entity) {
        // TODO Auto-generated method stub

    }

    public Address findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    public void delete(Address entity) {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("unchecked")
    public List<Address> findAll() {
//        Query qry = em.createQuery("FROM Address");
//        List <Address> ret = (List<Address>)qry.getResultList();
//        
//        return ret;
        return null;
    }

    public void deleteAll() {
        // TODO Auto-generated method stub

    }

}
