package hh.entity.embeddable;

import hh.entity.Person;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PetPK implements Serializable {

    private static final long serialVersionUID = -7287583447872388049L;

    @Column(name = "petname")
    private String petName;
    
    @Column(name = "dateofbirth")
    private Date dateOfBirth;
    
    @ManyToOne
    @JoinColumn(name = "personid")
    private Person person;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result
//                + ((branchName == null) ? 0 : branchName.hashCode());
//        result = prime * result + idEmployee;
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        EmployeeId other = (EmployeeId) obj;
//        if (branchName == null) {
//            if (other.branchName != null)
//                return false;
//        } else if (!branchName.equals(other.branchName))
//            return false;
//        if (idEmployee != other.idEmployee)
//            return false;
//        return true;
//    }
}
