package id.pradana.learn_jpa_relationship.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Table(name = "titles")
@Entity
public class Title {
  @Id
  @Column(name = "emp_no", nullable = false, length = 11)
  private Long employeeNo;

  @Column(name = "title", nullable = false, length = 50)
  private String title;

  @Column(name = "from_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date fromDate;

  @Column(name = "to_date")
  @Temporal(TemporalType.DATE)
  private Date toDate;

  public Long getEmployeeNo() {
    return employeeNo;
  }

  public void setEmployeeNo(Long employeeNo) {
    this.employeeNo = employeeNo;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

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
}
