package id.pradana.learn_jpa_relationship.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Table(name = "titles")
@Entity
public class Title {
  @EmbeddedId
  private TitlePk titlePk;

  @Column(name = "to_date")
  @Temporal(TemporalType.DATE)
  private Date toDate;

  public TitlePk getTitlePk() {
    return titlePk;
  }

  public void setTitlePk(TitlePk titlePk) {
    this.titlePk = titlePk;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }
}
