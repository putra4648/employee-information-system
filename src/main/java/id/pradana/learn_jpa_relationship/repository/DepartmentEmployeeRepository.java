package id.pradana.learn_jpa_relationship.repository;

import id.pradana.learn_jpa_relationship.model.DepartmentEmployee;
import id.pradana.learn_jpa_relationship.model.DepartmentEmployeePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentEmployeeRepository
        extends JpaRepository<DepartmentEmployee, DepartmentEmployeePk> {
}
