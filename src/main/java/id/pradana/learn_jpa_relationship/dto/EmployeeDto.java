package id.pradana.learn_jpa_relationship.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

public class EmployeeDto {
  private Long id;
  private String firstname;
  private String lastname;
  private String fullname;
  private Date birthdate;
  private Date hiredate;
  @JsonProperty(value = "titles")
  private List<TitleDto> titleDtos;

  public static EmployeeDto from(String id, String fullname, Date birthDate,
      Date hiredate) {
    return new EmployeeDto(id, fullname, birthDate, hiredate);
  }

  public EmployeeDto() {
  };

  private EmployeeDto(String id, String fullname, Date birthdate,
      Date hiredate) {
    this.id = new Long(id);
    this.fullname = fullname;
    this.birthdate = birthdate;
    this.hiredate = hiredate;
  }

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

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public Date getHiredate() {
    return hiredate;
  }

  public void setHiredate(Date hiredate) {
    this.hiredate = hiredate;
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
}
