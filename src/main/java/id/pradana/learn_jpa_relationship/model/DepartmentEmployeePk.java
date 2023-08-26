package id.pradana.learn_jpa_relationship.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DepartmentEmployeePk implements Serializable {
  @Column(name = "emp_no", nullable = false)
  private Long empNo;

  @Column(name = "dept_no", nullable = false)
  private String deptNo;

  public Long getEmpNo() {
    return empNo;
  }

  public void setEmpNo(Long empNo) {
    this.empNo = empNo;
  }

  public String getDeptNo() {
    return deptNo;
  }

  public void setDeptNo(String deptNo) {
    this.deptNo = deptNo;
  }
}
