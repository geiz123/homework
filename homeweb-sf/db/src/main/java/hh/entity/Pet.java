package hh.entity;

import hh.entity.embeddable.PetPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pet")
public class Pet extends BaseEntity {

    private static final long serialVersionUID = -6003398703542913514L;
    
    @EmbeddedId
    private PetPK id;

    @Column(name="isDead")
    private boolean isDead;
    
    public PetPK getId() {
        return id;
    }

    public void setId(PetPK id) {
        this.id = id;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }
}