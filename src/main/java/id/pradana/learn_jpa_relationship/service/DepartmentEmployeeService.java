package id.pradana.learn_jpa_relationship.service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import id.pradana.learn_jpa_relationship.dto.DepartmentEmployeeDto;
import id.pradana.learn_jpa_relationship.repository.DepartmentEmployeeRepository;

@Service
public class DepartmentEmployeeService {
  @Autowired
  private DepartmentEmployeeRepository repository;

  public ResponseEntity<?> getAll(String sortBy, String direction, int page,
      int size) {

    Map<String, Object> response = null;
    try {
      Direction dir = null;
      if (StringUtils.hasText(direction)) {
        dir = direction.toLowerCase().equalsIgnoreCase("asc") ? Direction.ASC
            : Direction.DESC;
      } else {
        dir = Direction.DESC;
      }

      String sort = null;
      if (StringUtils.hasText(sortBy)) {
        sort = sortBy.toLowerCase();
      } else {
        sort = "departmentEmployeePk.deptNo";
      }

      Pageable paging = PageRequest.of(page, size, Sort.by(dir, sort));

      Page<DepartmentEmployeeDto> pageDeptEmp = repository.findAll(paging).map((deptEmp) -> {
        DepartmentEmployeeDto dto = new DepartmentEmployeeDto();
        dto.setEmployeeNo(deptEmp.getDepartmentEmployeePk().getEmpNo());
        dto.setDepartmentNo(deptEmp.getDepartmentEmployeePk().getDeptNo());
        dto.setFromDate(deptEmp.getFromDate().getTime());
        dto.setToDate(deptEmp.getToDate().getTime());
        return dto;
      });

      response = new HashMap<>();
      response.put("data", pageDeptEmp.getContent());
      response.put("currentPage", pageDeptEmp.getNumber());
      response.put("recordsTotal", pageDeptEmp.getTotalElements());
      response.put("recordsFiltered", pageDeptEmp.getTotalPages());

      return ResponseEntity.ok().body(new TreeMap<>(response));
    } catch (Exception e) {
      response = new HashMap<>();
      response.put("errorMessage", e.toString());

      return new ResponseEntity<>(new TreeMap<>(response),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
