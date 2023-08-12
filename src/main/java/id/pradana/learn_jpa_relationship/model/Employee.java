package id.pradana.learn_jpa_relationship.model;

import id.pradana.learn_jpa_relationship.converter.GenderConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {
  @Id
  @Column(name = "emp_no", nullable = false, length = 11)
  private Integer id;

  @Column(name = "first_name", nullable = false, length = 14)
  private String firstname;

  @Column(name = "last_name", nullable = false, length = 16)
  private String lastname;

  @Column(name = "gender", nullable = false)
  private Gender gender;

  @Column(name = "hire_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date hireDate;

  @Column(name = "birth_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date birthDate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }
}
