package id.pradana.ems.repository;

import id.pradana.ems.model.DepartmentEmployee;
import id.pradana.ems.model.DepartmentEmployeePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentEmployeeRepository
                extends JpaRepository<DepartmentEmployee, DepartmentEmployeePk> {
}
