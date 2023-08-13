package id.pradana.learn_jpa_relationship.controller;

import id.pradana.learn_jpa_relationship.service.EmployeeService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1")
public class EmployeeController {
  @Autowired
  private EmployeeService service;

  @PostMapping("/employees")
  @ResponseBody
  public ResponseEntity<?> getAllEmployee(
      @RequestParam(name = "sortBy", defaultValue = "fullname") String sortBy,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "size", defaultValue = "25") int size,
      @RequestParam(name = "employee_fullname", defaultValue = "") String fullname) {
    return service.getAll(fullname, sortBy, page, size);
  }

  @GetMapping("/employees/{id}")
  @ResponseBody
  public ResponseEntity<?> getEmployeeById(@PathVariable("id") int id) {
    return service.getEmployeeById(id);
  }
}
