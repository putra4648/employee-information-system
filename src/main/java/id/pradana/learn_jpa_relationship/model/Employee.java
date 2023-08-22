package id.pradana.learn_jpa_relationship.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "employees")
public class Employee {
  @Id
  @Column(name = "emp_no", nullable = false, length = 11)
  private Long id;

  @Column(name = "first_name", nullable = false, length = 14)
  private String firstname;

  @Column(name = "last_name", nullable = false, length = 16)
  private String lastname;

  @Column(name = "gender", nullable = false)
  private Gender gender;

  @Column(name = "hire_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date hiredate;

  @Column(name = "birth_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date birthdate;

  /* Custom Formula */
  @Formula("CONCAT(first_name, ' ', last_name)")
  private String fullname;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumns({ @JoinColumn(name = "emp_no"), })
  @OrderBy(value = "from_date")
  private List<Title> titles;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumns({ @JoinColumn(name = "emp_no") })
  private Set<Salary> salaries;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public Date getHiredate() {
    return hiredate;
  }

  public void setHiredate(Date hireDate) {
    this.hiredate = hireDate;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthDate) {
    this.birthdate = birthDate;
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

  public Set<Salary> getSalaries() {
    return salaries;
  }

  public void setSalaries(Set<Salary> salaries) {
    this.salaries = salaries;
  }
}
