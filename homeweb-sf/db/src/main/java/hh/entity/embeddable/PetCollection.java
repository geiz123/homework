/**
 * ****
 * This class is used in conjunction with the related Entity like below.
 * @ElementCollection(targetClass=PetCollection.class)
 * @CollectionTable(name="pet",
 *     joinColumns=@JoinColumn(name="personid"))
 * List<PetCollection> pets;
 * ***
 */
package hh.entity.embeddable;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PetCollection implements Serializable {

    private static final long serialVersionUID = 9138324419322024167L;

    public PetCollection() {}
    
    @Column(name = "petname")
    private String petName;
    
    @Column(name = "dateofbirth")
    private Date dateOfBirth;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
