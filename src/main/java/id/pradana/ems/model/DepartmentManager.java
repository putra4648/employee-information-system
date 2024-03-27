package id.pradana.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "dept_manager")
public class DepartmentManager {
  @EmbeddedId
  private DepartmentManagerPK departmentManagerPK;

  @Temporal(TemporalType.DATE)
  @Column(name = "from_date")
  private Date fromdate;

  @Temporal(TemporalType.DATE)
  @Column(name = "to_date")
  private Date todate;

  @OneToOne
  @JoinColumn(name = "emp_no", insertable = false, updatable = false)
  private Departments departments;

  public DepartmentManagerPK getDepartmentManagerPK() {
    return departmentManagerPK;
  }

  public void setDepartmentManagerPK(DepartmentManagerPK departmentManagerPK) {
    this.departmentManagerPK = departmentManagerPK;
  }

  public Date getFromdate() {
    return fromdate;
  }

  public void setFromdate(Date fromdate) {
    this.fromdate = fromdate;
  }

  public Date getTodate() {
    return todate;
  }

  public void setTodate(Date todate) {
    this.todate = todate;
  }

  public Departments getDepartments() {
    return departments;
  }

  public void setDepartments(Departments departments) {
    this.departments = departments;
  }
}
