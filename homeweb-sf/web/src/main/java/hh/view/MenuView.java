package hh.view;

import java.io.Serializable;

import hh.entity.Person;
import hh.view.data.LazyPersonDataModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
@ManagedBean
@ViewScoped
public class MenuView implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private LazyPersonDataModel lazyModel;
    
    private Person selectedPerson;
    
    @PostConstruct
    public void init() {
        lazyModel = new LazyPersonDataModel();
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