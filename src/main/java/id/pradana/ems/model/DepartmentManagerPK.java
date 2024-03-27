package id.pradana.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DepartmentManagerPK {
  @Column(name = "emp_no", nullable = false)
  private Integer empno;

  @Column(name = "dept_no", nullable = false)
  private String deptno;

  public Integer getEmpno() {
    return empno;
  }

  public void setEmpno(Integer emp_no) {
    this.empno = emp_no;
  }

  public String getDeptno() {
    return deptno;
  }

  public void setDeptno(String dept_no) {
    this.deptno = dept_no;
  }
}
