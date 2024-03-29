package id.pradana.ems.controller;

import id.pradana.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
  @Autowired
  private EmployeeService service;

  @PostMapping("/employees")
  public ResponseEntity<?> getAllEmployee(
      @RequestParam(name = "sortBy", required = false) String sortBy,
      @RequestParam(name = "dir", required = false) String direction,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "size", defaultValue = "25") int size,
      @RequestParam(name = "employee_filter", required = false) String filter) {
    return service.getAll(filter, sortBy, direction, page, size);
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<?> getEmployeeById(@PathVariable("id") long id) {
    return service.getEmployeeById(id);
  }
}
