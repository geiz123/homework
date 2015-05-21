package hh.test.mbean;

import hh.bean.Service;
import hh.dao.ServiceDao;
import hh.dao.impl.ServiceDaoImpl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class View1 implements Serializable {
    
    private static final long serialVersionUID = 2681464039881971329L;
    
    private ServiceDao serviceDao = new ServiceDaoImpl();
    
    @PostConstruct
    public void init() {
        System.out.println(View1.class.getName() + ": init() " + this);
    }
    
    public List<Service> getServices(){
        return serviceDao.getAllServices();
    }
}
