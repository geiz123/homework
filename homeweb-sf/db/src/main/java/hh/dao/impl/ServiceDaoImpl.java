package hh.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import hh.bean.Service;
import hh.dao.ServiceDao;

/**
 * Need to replace or remove this.  It was for testing.
 *
 */
public class ServiceDaoImpl implements ServiceDao, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8222289056878850833L;
    private List<Service> listOfService;
    private HashMap<String, List<Service>> serviceMap;

    public ServiceDaoImpl() {
        listOfService = new ArrayList<Service>();
        serviceMap = new HashMap<String, List<Service>>();

        Service s = new Service();
        s.setId(1);
        s.setName("Eat");
        s.setDescription("Eat something.");
        listOfService.add(s);

        s = new Service();
        s.setId(2);
        s.setDescription("Go to sleep.");
        s.setName("Sleep");
        listOfService.add(s);

        serviceMap.put("Lazy", listOfService);

        listOfService = new ArrayList<Service>();

        s = new Service();
        s.setId(3);
        s.setName("Run");
        s.setDescription("Run really fast");
        listOfService.add(s);
        
        serviceMap.put("Active", listOfService);

    }

    public List<Service> getAllServices() {
        List<Service> ret = new ArrayList<Service>();
        
        for (List<Service> ls : serviceMap.values()){
            ret.addAll(ls);
        }
        
        return ret;
    }

    public Set<String> getAllServicesCategory() {
        return serviceMap.keySet();
    }

    public List<Service> getServiceByCategory(String category) {
        return serviceMap.get(category);
    }

}
