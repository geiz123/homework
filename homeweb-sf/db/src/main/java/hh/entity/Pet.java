package hh.entity;

import hh.entity.embeddable.PetPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pet")
public class Pet extends BaseEntity {

    private static final long serialVersionUID = -6003398703542913514L;
    
    @EmbeddedId
    private PetPK id;

    public PetPK getId() {
        return id;
    }

    public void setId(PetPK id) {
        this.id = id;
    }
}