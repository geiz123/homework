package hh.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
@AttributeOverride(name = "id",
                   column = @Column(name = "phoneid"))
public class Phone extends BaseEntity<Integer> {
    private static final long serialVersionUID = 3361536808005114698L;

    @Column(name = "phonetype")
    private String phoneType;

    @Column(name = "phone")
    private String phoneNumber;

    /**
     * For bi-directional http://stackoverflow.com/questions/7979382/how-to-create-join-table-with-jpa-annotations
     * 
     * @return
     */
    // @ManyToOne
    // @JoinTable(name = "personphone",
    // joinColumns = @JoinColumn(name = "phoneid",
    // referencedColumnName = "phoneid"),
    // inverseJoinColumns = @JoinColumn(name = "personid",
    // referencedColumnName = "personid"))
    // private Person person;

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // public Person getPerson() {
    // return person;
    // }
    //
    // public void setPerson(Person person) {
    // this.person = person;
    // }

}
