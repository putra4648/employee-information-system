package id.pradana.learn_jpa_relationship.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "salaries")
@Entity
public class Salary extends BaseModel {
  @Id
  @Column(name = "emp_no")
  private Long empNo;

  @Column(name = "salary")
  private Long salary;

  public Long getEmpNo() {
    return empNo;
  }

  public void setEmpNo(Long empNo) {
    this.empNo = empNo;
  }

  public Long getSalary() {
    return salary;
  }

  public void setSalary(Long salary) {
    this.salary = salary;
  }
}
