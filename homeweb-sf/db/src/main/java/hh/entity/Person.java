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

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "userid")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "branchid")
    private Integer branchId;

    @Column(name = "addressid")
    private Integer addressId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "addressid", insertable = false, updatable = false)
    private Address address;

    public Person(String firstName, String middleName, String lastName, String userId, String password, Integer branchId, Integer addressId) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.userId = userId;
        this.password = password;
        this.branchId = branchId;
        this.addressId = addressId;
    }

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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
