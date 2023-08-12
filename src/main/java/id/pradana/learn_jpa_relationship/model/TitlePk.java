package id.pradana.learn_jpa_relationship.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class TitlePk implements Serializable {
  @Column(name = "emp_no", nullable = false, length = 11)
  private Long employeeNo;

  @Column(name = "title", nullable = false, length = 50)
  private String title;

  @Column(name = "from_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date fromDate;

  public Long getEmployeeNo() {
    return employeeNo;
  }

  public void setEmployeeNo(Long employeNo) {
    this.employeeNo = employeNo;
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
}
