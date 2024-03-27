package id.pradana.ems.dto;

public class TitleDto extends BaseDto {
  private Long employeeNo;
  private String title;

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

  @Override
  public String toString() {
    return "TitleDto [employeeNo=" + employeeNo + ", title=" + title + "]";
  }
}
