package hh.service;

import java.util.List;

import javax.inject.Named;

import hh.dao.AddressDao;
import hh.dao.impl.AddressDaoImpl;
import hh.model.Address;

@Named
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
