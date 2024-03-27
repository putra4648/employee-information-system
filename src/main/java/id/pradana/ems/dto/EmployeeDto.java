package id.pradana.ems.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.pradana.ems.model.Gender;
import java.util.List;

public class EmployeeDto {
  private Long id;
  private String firstname;
  private String lastname;
  private String fullname;
  // Convert to milisecond
  private Long birthdate;
  private Long hiredate;
  private Gender gender;
  private String deptname;

  public String getDeptname() {
    return deptname;
  }

  public void setDeptname(String deptname) {
    this.deptname = deptname;
  }

  @JsonProperty(value = "titles")
  private List<TitleDto> titles;

  @JsonProperty(value = "salaries")
  private List<SalaryDto> salaries;

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

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public Long getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Long birthdate) {
    this.birthdate = birthdate;
  }

  public Long getHiredate() {
    return hiredate;
  }

  public void setHiredate(Long hiredate) {
    this.hiredate = hiredate;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public List<TitleDto> getTitles() {
    return titles;
  }

  public void setTitles(List<TitleDto> titles) {
    this.titles = titles;
  }

  public List<SalaryDto> getSalaries() {
    return salaries;
  }

  public void setSalaries(List<SalaryDto> salaries) {
    this.salaries = salaries;
  }

  @Override
  public String toString() {
    return "EmployeeDto [id=" + id + ", firstname=" + firstname +
        ", fullname=" + fullname + ", birthdate=" + birthdate +
        ", hiredate=" + hiredate + ", gender=" + gender +
        ", titles=" + titles + ", salaries=" + salaries + "]";
  }
}
