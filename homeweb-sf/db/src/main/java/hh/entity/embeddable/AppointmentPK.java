package hh.entity.embeddable;

import hh.entity.Person;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AppointmentPK implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "appttype")
    private Integer apptType;
    
    @Column(name = "apptdt")
    private Timestamp apptDt;
    
    @ManyToOne
    @JoinColumn(name = "personid")
    private Person person;

    public Integer getApptType() {
        return apptType;
    }

    public void setApptType(Integer apptType) {
        this.apptType = apptType;
    }

    public Timestamp getApptDt() {
        return apptDt;
    }

    public void setApptDt(Timestamp apptDt) {
        this.apptDt = apptDt;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
