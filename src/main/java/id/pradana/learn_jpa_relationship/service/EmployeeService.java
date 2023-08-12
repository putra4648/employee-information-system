package id.pradana.learn_jpa_relationship.service;

import id.pradana.learn_jpa_relationship.dto.EmployeeDto;
import id.pradana.learn_jpa_relationship.dto.TitleDto;
import id.pradana.learn_jpa_relationship.model.Employee;
import id.pradana.learn_jpa_relationship.repository.EmployeeRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository repository;

  public ResponseEntity<?> getAll(String sortBy, int page, int size) {
    Map<String, Object> response;
    try {
      // Pagination
      Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
      Page<EmployeeDto> pageEmployee = repository.findAll(paging).map(new Function<Employee, EmployeeDto>() {
        @Override
        public EmployeeDto apply(Employee e) {
          EmployeeDto dto = new EmployeeDto();
          List<TitleDto> titleDtos = e.getTitles()
              .stream()
              .map(d -> {
                TitleDto titleDto = new TitleDto();
                titleDto.setEmployeeNo(d.getTitlePk().getEmployeeNo());
                titleDto.setTitle(d.getTitlePk().getTitle());
                titleDto.setFromDate(d.getTitlePk().getFromDate());
                titleDto.setToDate(d.getToDate());
                return titleDto;
              })
              .toList();

          dto.setId(e.getId().longValue());
          dto.setFirstname(e.getFirstname());
          dto.setLastname(e.getLastname());
          dto.setFullname(e.getFullname());
          dto.setBirthdate(e.getBirthDate());
          dto.setHiredate(e.getHireDate());
          dto.setTitleDtos(titleDtos);
          return dto;
        }
      });

      response = new HashMap<>();
      response.put("errorMessage", null);
      response.put("data", pageEmployee.getContent());
      response.put("limit", pageEmployee.getSize());
      response.put("page", pageEmployee.getNumber());
      response.put("totalItems", pageEmployee.getTotalElements());

      return ResponseEntity.ok().body(new TreeMap<>(response));

    } catch (Exception e) {
      response = new HashMap<>();
      response.put("errorMessage", e.toString());
      return new ResponseEntity<>(new TreeMap<>(response),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<?> getEmployeeById(Integer id) {
    Optional<Employee> emp = repository.findById(id);
    Map<String, Object> response;

    if (emp.isPresent()) {
      response = new HashMap<>();
      Employee ep = emp.get();

      EmployeeDto dto = new EmployeeDto();

      dto.setId(ep.getId().longValue());
      dto.setFirstname(ep.getFirstname());
      dto.setLastname(ep.getLastname());
      dto.setFullname(ep.getFullname());
      dto.setHiredate(ep.getHireDate());
      dto.setBirthdate(ep.getBirthDate());

      List<TitleDto> titleDtos = ep.getTitles()
          .stream()
          .map(d -> {
            TitleDto titleDto = new TitleDto();
            titleDto.setEmployeeNo(d.getTitlePk().getEmployeeNo());
            titleDto.setTitle(d.getTitlePk().getTitle());
            titleDto.setFromDate(d.getTitlePk().getFromDate());
            titleDto.setToDate(d.getToDate());
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
  }
}
