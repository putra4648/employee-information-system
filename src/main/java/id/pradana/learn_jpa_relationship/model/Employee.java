package id.pradana.learn_jpa_relationship.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.Formula;

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

  @Formula("CONCAT(first_name, ' ', last_name)")
  private String fullname;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumns({ @JoinColumn(name = "emp_no"), })
  @OrderBy(value = "from_date")
  private List<Title> titles;

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

  public List<Title> getTitles() {
    return titles;
  }

  public void setTitles(List<Title> titles) {
    this.titles = titles;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }
}
