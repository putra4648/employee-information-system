package id.pradana.learn_jpa_relationship.filter;

import id.pradana.learn_jpa_relationship.model.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Date;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecFilter {
  public static Specification<Employee> filterByFullname(String fullname) {
    return new Specification<Employee>() {
      @Override
      public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> rc,
          CriteriaBuilder cb) {
        return cb.like(root.get("fullname"), "%" + fullname + "%");
      }
    };
  }

  public static Specification<Employee> filterById(String id) {
    return new Specification<Employee>() {
      @Override
      public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> arg1,
          CriteriaBuilder cb) {
        return cb.like(root.get("id").as(String.class), "%" + id + "%");
      }
    };
  }

  public static Specification<Employee> filterByBirthdate(Date birthdate) {
    return new Specification<Employee>() {
      @Override
      public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> arg1,
          CriteriaBuilder cb) {
        return cb.like(root.get("birthdate").as(String.class),
            "%" + birthdate + "%");
      }
    };
  }
}
