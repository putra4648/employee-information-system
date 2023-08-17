package id.pradana.learn_jpa_relationship.filter;

import id.pradana.learn_jpa_relationship.model.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecFilter {

  public static Specification<Employee> filterAll(EmployeeFilterDTO filter) {
    return new Specification<Employee>() {
      @Override
      public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> cr,
          CriteriaBuilder cb) {
        System.out.println("Filter " + filter);
        String birthdate = filter.getBirthdate() == null
            ? ""
            : new SimpleDateFormat("dd-MM-yyyy")
                .format(filter.getBirthdate());
        String hiredate = filter.getHiredate() == null
            ? ""
            : new SimpleDateFormat("dd-MM-yyyy")
                .format(filter.getHiredate());

        Predicate predicate = cb.and(
            cb.like(root.get("id").as(String.class),
                "%" + filter.getId() + "%"),
            cb.like(root.get("fullname"), "%" + filter.getFullname() + "%"),
            cb.like(cb.function("DATE_FORMAT", String.class,
                root.get("birthdate"), cb.literal("%d-%m-%Y")),
                "%" + birthdate + "%"),
            cb.like(cb.function("DATE_FORMAT", String.class,
                root.get("hiredate"), cb.literal("%d-%m-%Y")),
                "%" + hiredate + "%"));
        return cb.and(predicate);
      }
    };
  }
}
