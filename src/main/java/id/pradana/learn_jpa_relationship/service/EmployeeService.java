package id.pradana.learn_jpa_relationship.service;

import id.pradana.learn_jpa_relationship.dto.EmployeeDto;
import id.pradana.learn_jpa_relationship.dto.TitleDto;
import id.pradana.learn_jpa_relationship.model.Employee;
import id.pradana.learn_jpa_relationship.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
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
          dto.setBirthdate(e.getBirthDate());
          dto.setHiredate(e.getHireDate());
          dto.setTitleDtos(titleDtos);
          return dto;
        }
      });

      response = new HashMap<>();
      response.put("errorMessage", null);
      response.put("data", pageEmployee.getContent());
      response.put("statusCode", HttpStatus.OK.value());
      response.put("page", pageEmployee.getNumber());
      response.put("totalItems", pageEmployee.getTotalElements());

      return ResponseEntity.ok().body(new TreeMap<>(response));

    } catch (Exception e) {
      response = new HashMap<>();
      response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("errorMessage", e.toString());
      return new ResponseEntity<>(new TreeMap<>(response),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
