/**
 * We are not using CDI to do EL (@Named) because CDI does not have
 * @ViewScoped or an equilvalent. 
 */
package hh.test.mbean;

import hh.bean.Service;
import hh.dao.AddressDao;
import hh.dao.PersonDao;
import hh.dao.ServiceDao;
import hh.entity.Address;
import hh.entity.Person;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

@ManagedBean
@ViewScoped
public class MenuBean implements Serializable {
    private static final long serialVersionUID = 175198413945959731L;

    @ManagedProperty("#{serviceDaoImpl}")
    private ServiceDao serviceDao;

    @ManagedProperty("#{addressDao}")
    private AddressDao addDao;

    @ManagedProperty("#{personDao}")
    private PersonDao personDao;

    private List<Address> addresses;

    private DefaultMenuModel dMenu;

    @PostConstruct
    public void init() {
        makeMenu();
        
        setAddresses(addDao.findAll());

        System.out.println(MenuBean.class.getName() + ": init()");
    }

    private void makeMenu() {
        dMenu = new DefaultMenuModel();

        Set<String> serviceCat = serviceDao.getAllServicesCategory();

        for (String c : serviceCat) {
            DefaultSubMenu submenu = new DefaultSubMenu(c);

            for (Service s : serviceDao.getServiceByCategory(c)) {
                DefaultMenuItem item = new DefaultMenuItem(s.getName());
                item.setUpdate("message");
                item.setCommand("#{menuBean.sendMessage}");
                submenu.addElement(item);
                // http://stackoverflow.com/questions/18741731/primefaces-4-dynamic-menu-setcommand-method
            }

            dMenu.addElement(submenu);
        }

    }

    /**
     * Testing JSF 2.0+ passing in parameters
     * 
     * @param addId
     * @return
     */
    public List<Person> getPersonFromAddId(Integer addId) {
        return personDao.getPersonByAddressId(addId);
        // ArrayList<Person> ret = new ArrayList<Person>();
        // Person p1 = new Person();
        // p1.setId(22);
        // p1.setFirstName("Henry");
        // ret.add(p1);
        //
        // return ret;
    }

    public void sendMessage() {
        addMessage("Hello", "No idea who clicked");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DefaultMenuModel getdMenu() {
        return dMenu;
    }

    public void setdMenu(DefaultMenuModel dMenu) {
        this.dMenu = dMenu;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public AddressDao getAddDao() {
        return addDao;
    }

    public void setAddDao(AddressDao addDao) {
        this.addDao = addDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public ServiceDao getServiceDao() {
        return serviceDao;
    }

    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

}
