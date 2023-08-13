package id.pradana.learn_jpa_relationship.repository;

import id.pradana.learn_jpa_relationship.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  List<Employee> findByFullname(String fullname);
}
