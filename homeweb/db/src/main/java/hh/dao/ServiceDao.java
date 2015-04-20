package hh.dao;

import hh.bean.Service;

import java.util.List;
import java.util.Set;

public interface ServiceDao {

    public List<Service> getAllServices();
    
    public Set<String> getAllServicesCategory();
    
    public List<Service> getServiceByCategory(String category);
    
}
