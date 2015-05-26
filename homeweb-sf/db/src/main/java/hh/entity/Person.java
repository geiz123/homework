package hh.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
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

    @Column(name = "branchid")
    private Integer branchId;

    @Column(name = "addressid")
    private Integer addressId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "addressid", insertable = false, updatable = false)
    private Address address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

}
