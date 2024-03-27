package id.pradana.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

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
