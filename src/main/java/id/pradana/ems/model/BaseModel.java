package id.pradana.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class BaseModel {
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
}
