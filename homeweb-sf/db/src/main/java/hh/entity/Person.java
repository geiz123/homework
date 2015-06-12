package hh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@AttributeOverride(name = "id",
                   column = @Column(name = "personid"))
public class Person extends BaseIdEntity<Integer> implements Serializable {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressid",
                insertable = false,
                updatable = false)
    private Address address;

    @OneToMany
    @JoinTable(name = "personphone",
               joinColumns = @JoinColumn(name = "personid"),
               inverseJoinColumns = @JoinColumn(name = "phoneid"))
    private List<Phone> phones;

    // @ElementCollection(targetClass=PetCollection.class)
    // @CollectionTable(name="pet",
    // joinColumns=@JoinColumn(name="personid"))

    @OneToMany
    @JoinColumn(name = "personid",
                referencedColumnName = "personid")
    List<Pet> pets;

    public Person() {

    }

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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}