package hh.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@AttributeOverride(name = "id", column = @Column(name = "personid"))
public class Person extends BaseEntity<Integer> implements Serializable {
    private static final long serialVersionUID = -8791209533998520826L;

    @Column(name = "firstname")
    private String firstName;

    // cascade - https://howtoprogramwithjava.com/hibernate-manytoone-unidirectional-tutorial/
    @ManyToOne(cascade = CascadeType.ALL)
    private Address addressId;

    @Column(name = "branchid")
    private Integer branchId;

    // cascade - https://howtoprogramwithjava.com/hibernate-manytoone-unidirectional-tutorial/
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Address address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

}
