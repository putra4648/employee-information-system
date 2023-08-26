package id.pradana.learn_jpa_relationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.pradana.learn_jpa_relationship.service.DepartmentEmployeeService;

@RestController
@RequestMapping("/api/v1")
public class DepartmentEmployeeController {
  @Autowired
  private DepartmentEmployeeService service;

  @GetMapping("/dept_employees")
  public ResponseEntity<?> getAll(@RequestParam(name = "sortBy", required = false) String sortBy,
      @RequestParam(name = "dir", required = false) String direction,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "size", defaultValue = "25") int size

  ) {
    return service.getAll(sortBy, direction, page, size);
  }
}
