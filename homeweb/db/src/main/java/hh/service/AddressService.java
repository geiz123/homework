package hh.service;

import java.util.List;

import hh.dao.AddressDao;
import hh.dao.impl.AddressDaoImpl;
import hh.model.Address;

public class AddressService {
    private static AddressDao<Address, Integer> addressDao;
    
    public AddressService(){
        addressDao = new AddressDaoImpl();
    }
    
    public List<Address> findAll() {
        List<Address> addresses = addressDao.findAll();
        
        return addresses;
    }
    
    
}
