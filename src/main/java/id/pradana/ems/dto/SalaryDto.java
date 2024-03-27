package id.pradana.ems.dto;

public class SalaryDto extends BaseDto {
  private Long empNo;
  private Long salary;

  public Long getEmpNo() {
    return empNo;
  }

  public void setEmpNo(Long empNo) {
    this.empNo = empNo;
  }

  public Long getSalary() {
    return salary;
  }

  public void setSalary(Long salary) {
    this.salary = salary;
  }

  @Override
  public String toString() {
    return "SalaryDto [empNo=" + empNo + ", salary=" + salary + "]";
  }
}
