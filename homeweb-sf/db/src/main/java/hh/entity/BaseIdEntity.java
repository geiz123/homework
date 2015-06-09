package hh.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseIdEntity<ID> extends BaseEntity {

    private static final long serialVersionUID = 7085553303997895828L;
    
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
