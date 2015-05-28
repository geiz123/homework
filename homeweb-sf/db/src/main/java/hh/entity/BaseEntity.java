package hh.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<ID> implements Serializable {

    private static final long serialVersionUID = 5935064778010496440L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    // Since id is auto generated we have this annotation
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
