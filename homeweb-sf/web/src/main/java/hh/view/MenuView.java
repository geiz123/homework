package hh.view;

import hh.entity.Person;
import hh.view.data.LazyPersonDataModel;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
 
@ManagedBean
@ViewScoped
public class MenuView implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private static final Logger log4jLogger = Logger.getLogger(MenuView.class);

    private LazyPersonDataModel lazyModel;
    
    private Person selectedPerson;
    
    @PostConstruct
    public void init() {
        lazyModel = new LazyPersonDataModel();
        
        log4jLogger.info("### #-info -- " + log4jLogger.getLevel());
        log4jLogger.warn("### #-warn");
        log4jLogger.debug("### #-debug");
        log4jLogger.error("### #-error");
        log4jLogger.fatal("### #-fatal");
        
    }
    
    public LazyPersonDataModel getLazyModel() {
        if (lazyModel == null)
            lazyModel = new LazyPersonDataModel();
        
        return lazyModel;
    }

    public void setLazyModel(LazyPersonDataModel lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }
}