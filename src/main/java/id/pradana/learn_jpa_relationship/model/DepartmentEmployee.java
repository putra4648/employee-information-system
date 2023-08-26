package id.pradana.learn_jpa_relationship.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Table(name = "dept_emp")
@Entity
public class DepartmentEmployee {
  @EmbeddedId
  private DepartmentEmployeePk departmentEmployeePk;

  @Column(name = "from_date")
  @Temporal(TemporalType.DATE)
  private Date fromDate;

  @Column(name = "to_date")
  @Temporal(TemporalType.DATE)
  private Date toDate;

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public DepartmentEmployeePk getDepartmentEmployeePk() {
    return departmentEmployeePk;
  }

  public void setDepartmentEmployeePk(DepartmentEmployeePk departmentEmployeePk) {
    this.departmentEmployeePk = departmentEmployeePk;
  }
}
