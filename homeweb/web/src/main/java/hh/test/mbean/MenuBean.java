package hh.test.mbean;

import hh.bean.Service;
import hh.dao.AddressDao;
import hh.dao.ServiceDao;
import hh.entity.Address;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

@ManagedBean(name = "menuBean")
@ViewScoped
public class MenuBean implements Serializable {
    private static final long serialVersionUID = 175198413945959731L;

    @Inject
    private ServiceDao serviceDao;

    @Inject
    private AddressDao addDao;
    
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

}
