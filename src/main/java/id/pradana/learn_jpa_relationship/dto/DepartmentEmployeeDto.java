package id.pradana.learn_jpa_relationship.dto;

public class DepartmentEmployeeDto {
  private Long employeeNo;
  private String departmentNo;
  private Long fromDate;
  private Long toDate;

  public Long getEmployeeNo() {
    return employeeNo;
  }

  public void setEmployeeNo(Long employeeNo) {
    this.employeeNo = employeeNo;
  }

  public String getDepartmentNo() {
    return departmentNo;
  }

  public void setDepartmentNo(String departmentNo) {
    this.departmentNo = departmentNo;
  }

  public Long getFromDate() {
    return fromDate;
  }

  public void setFromDate(Long fromDate) {
    this.fromDate = fromDate;
  }

  public Long getToDate() {
    return toDate;
  }

  public void setToDate(Long toDate) {
    this.toDate = toDate;
  }
}
