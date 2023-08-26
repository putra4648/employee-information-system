package id.pradana.learn_jpa_relationship.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.pradana.learn_jpa_relationship.dto.EmployeeDto;
import id.pradana.learn_jpa_relationship.dto.SalaryDto;
import id.pradana.learn_jpa_relationship.dto.TitleDto;
import id.pradana.learn_jpa_relationship.filter.EmployeeFilterDTO;
import id.pradana.learn_jpa_relationship.filter.EmployeeSpecFilter;
import id.pradana.learn_jpa_relationship.model.Employee;
import id.pradana.learn_jpa_relationship.repository.EmployeeRepository;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository repository;

  public ResponseEntity<?> getAll(String filterJsonString, String sortBy,
      String direction, int page, int size) {
    Map<String, Object> response;
    try {
      // Pagination
      Direction dir = direction.toLowerCase().equalsIgnoreCase("asc")
          ? Direction.ASC
          : Direction.DESC;
      Pageable paging = PageRequest.of(page, size, Sort.by(dir, sortBy.toLowerCase()));

      // Filtering
      Page<EmployeeDto> pageEmployee = null;
      Specification<Employee> specFilter = null;

      // Param to filter
      final ObjectMapper mapper = new ObjectMapper();
      EmployeeFilterDTO filter = mapper.readValue(filterJsonString, EmployeeFilterDTO.class);

      specFilter = EmployeeSpecFilter.filterAll(filter);

      pageEmployee = getPageEmployee(specFilter, paging);

      response = new HashMap<>();
      response.put("data", pageEmployee.getContent());
      response.put("currentPage", pageEmployee.getNumber());
      response.put("recordsTotal", pageEmployee.getTotalElements());
      response.put("recordsFiltered", pageEmployee.getTotalPages());

      return ResponseEntity.ok().body(new TreeMap<>(response));

    } catch (Exception e) {
      response = new HashMap<>();
      response.put("errorMessage", e.toString());
      return new ResponseEntity<>(new TreeMap<>(response),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private Page<EmployeeDto> getPageEmployee(Specification<Employee> filter,
      Pageable paging) {
    return repository.findAll(filter, paging)
        .map(new Function<Employee, EmployeeDto>() {
          @Override
          public EmployeeDto apply(Employee emp) {
            EmployeeDto dto = new EmployeeDto();
            // Set title
            List<TitleDto> titleDtos = emp.getTitles()
                .stream()
                .map(t -> {
                  TitleDto titleDto = new TitleDto();
                  titleDto.setEmployeeNo(t.getEmployeeNo());
                  titleDto.setTitle(t.getTitle());
                  titleDto.setFromDate(t.getFromDate().getTime());
                  titleDto.setToDate(t.getToDate().getTime());
                  return titleDto;
                })
                .toList();

            dto.setTitleDtos(titleDtos);

            // Set employee
            dto.setId(emp.getId().longValue());
            dto.setFirstname(emp.getFirstname());
            dto.setLastname(emp.getLastname());
            dto.setFullname(emp.getFullname());
            dto.setBirthdate(emp.getBirthdate().getTime());
            dto.setHiredate(emp.getHiredate().getTime());

            // Set salaries
            List<SalaryDto> salariesDto = emp.getSalaries()
                .stream()
                .map(sal -> {
                  SalaryDto salaryDto = new SalaryDto();
                  salaryDto.setEmpNo(sal.getEmpNo());
                  salaryDto.setSalary(sal.getSalary());
                  salaryDto.setFromDate(sal.getFromDate().getTime());
                  salaryDto.setToDate(sal.getToDate().getTime());
                  return salaryDto;
                })
                .toList();
            dto.setSalaryDtos(salariesDto);

            return dto;
          }
        });
  }

  public ResponseEntity<?> getEmployeeById(Long id) {
    Optional<Employee> emp = repository.findById(id);
    Map<String, Object> response;
    try {
      if (emp.isPresent()) {
        response = new HashMap<>();
        Employee ep = emp.get();

        EmployeeDto dto = new EmployeeDto();

        dto.setId(ep.getId().longValue());
        dto.setFirstname(ep.getFirstname());
        dto.setLastname(ep.getLastname());
        dto.setFullname(ep.getFullname());
        dto.setHiredate(ep.getHiredate().getTime());
        dto.setBirthdate(ep.getBirthdate().getTime());

        List<TitleDto> titleDtos = ep.getTitles()
            .stream()
            .map(d -> {
              TitleDto titleDto = new TitleDto();
              titleDto.setEmployeeNo(d.getEmployeeNo());
              titleDto.setTitle(d.getTitle());
              titleDto.setFromDate(d.getFromDate().getTime());
              titleDto.setToDate(d.getToDate().getTime());
              return titleDto;
            })
            .toList();

        dto.setTitleDtos(titleDtos);

        response.put("errorMessage", null);
        response.put("data", dto);
        return new ResponseEntity<>(new TreeMap<>(response), HttpStatus.OK);
      } else {
        response = new HashMap<>();
        response.put("errorMessage", "No Employee available with id " + id);
        response.put("data", null);
        return new ResponseEntity<>(new TreeMap<>(response), HttpStatus.OK);
      }
    } catch (Exception e) {
      response = new HashMap<>();
      response.put("errorMessage", e.toString());
      return new ResponseEntity<>(new TreeMap<>(response),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
