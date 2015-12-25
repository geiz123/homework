package hh.entity;

import hh.entity.embeddable.AppointmentPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AppointmentPK id;

    public AppointmentPK getId() {
        return id;
    }

    public void setId(AppointmentPK id) {
        this.id = id;
    }
}
