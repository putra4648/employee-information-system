package id.pradana.learn_jpa_relationship.repository;

import id.pradana.learn_jpa_relationship.model.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>,
        JpaSpecificationExecutor<Employee> {

    interface Specs {
        static Specification<Employee> byFullName(String fullname) {
            return (root, query, builder) -> builder.like(root.get("fullname"), "%" + fullname + "%");
        }
    }
}
