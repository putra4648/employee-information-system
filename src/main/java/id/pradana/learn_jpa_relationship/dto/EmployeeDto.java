package id.pradana.learn_jpa_relationship.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class EmployeeDto {
  private Long id;
  private String firstname;
  private String lastname;
  private String fullname;
  // Convert to milisecond
  private Long birthdate;
  private Long hiredate;
  @JsonProperty(value = "titles")
  private List<TitleDto> titleDtos;
  @JsonProperty(value = "salaries")
  private List<SalaryDto> salaryDtos;

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

  public List<TitleDto> getTitleDtos() {
    return titleDtos;
  }

  public void setTitleDtos(List<TitleDto> titleDtos) {
    this.titleDtos = titleDtos;
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

  public List<SalaryDto> getSalaryDtos() {
    return salaryDtos;
  }

  public void setSalaryDtos(List<SalaryDto> salaryDtos) {
    this.salaryDtos = salaryDtos;
  }
}
