package id.pradana.learn_jpa_relationship.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.pradana.learn_jpa_relationship.dto.EmployeeDto;
import id.pradana.learn_jpa_relationship.dto.TitleDto;
import id.pradana.learn_jpa_relationship.filter.EmployeeFilter;
import id.pradana.learn_jpa_relationship.filter.EmployeeSpecFilter;
import id.pradana.learn_jpa_relationship.model.Employee;
import id.pradana.learn_jpa_relationship.repository.EmployeeRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private static Logger logger = Logger.getAnonymousLogger();

  @Autowired
  private EmployeeRepository repository;

  public ResponseEntity<?> getAll(String filterJsonString, String sortBy,
      int page, int size) {
    Map<String, Object> response;
    try {
      // Pagination
      Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));

      // Filtering
      Page<EmployeeDto> pageEmployee = null;
      Specification<Employee> specFilter = null;

      // Param to filter
      if (filterJsonString != null) {
        final ObjectMapper mapper = new ObjectMapper();
        EmployeeFilter filter = mapper.readValue(filterJsonString, EmployeeFilter.class);

        logger.log(Level.INFO, "Filter {0}", filter);

        if (filter.getId() != null) {
          specFilter = EmployeeSpecFilter.filterById(filter.getId());
        } else if (filter.getFullname() != null) {
          specFilter = EmployeeSpecFilter.filterByFullname(filter.getFullname());
        } else if (filter.getBirthdate() != null) {
          specFilter = EmployeeSpecFilter.filterByBirthdate(filter.getBirthdate());
        }
      }

      pageEmployee = getPageEmployee(paging, specFilter);

      response = new HashMap<>();
      response.put("data", pageEmployee.getContent());
      response.put("currentPage", pageEmployee.getNumber());
      response.put("recordsTotal", pageEmployee.getTotalElements());
      response.put("recordsFiltered", pageEmployee.getTotalPages());

      return ResponseEntity.ok().body(new TreeMap<>(response));

    } catch (Exception e) {
      response = new HashMap<>();
      response.put("errorMessage", e.toString());
      response.put("causedBy", e.getCause().toString());
      return new ResponseEntity<>(new TreeMap<>(response),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private Page<EmployeeDto> getPageEmployee(Pageable paging,
      Specification<Employee> filter) {
    return repository.findAll(filter, paging)
        .map(new Function<Employee, EmployeeDto>() {
          @Override
          public EmployeeDto apply(Employee e) {
            EmployeeDto dto = new EmployeeDto();
            List<TitleDto> titleDtos = e.getTitles()
                .stream()
                .map(d -> {
                  TitleDto titleDto = new TitleDto();
                  titleDto.setEmployeeNo(d.getEmployeeNo());
                  titleDto.setTitle(d.getTitle());
                  titleDto.setFromDate(d.getFromDate());
                  titleDto.setToDate(d.getToDate());
                  return titleDto;
                })
                .toList();

            dto.setId(e.getId().longValue());
            dto.setFirstname(e.getFirstname());
            dto.setLastname(e.getLastname());
            dto.setFullname(e.getFullname());
            dto.setBirthdate(e.getBirthDate().getTime());
            dto.setHiredate(e.getHireDate().getTime());
            dto.setTitleDtos(titleDtos);
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
        dto.setHiredate(ep.getHireDate().getTime());
        dto.setBirthdate(ep.getBirthDate().getTime());

        List<TitleDto> titleDtos = ep.getTitles()
            .stream()
            .map(d -> {
              TitleDto titleDto = new TitleDto();
              titleDto.setEmployeeNo(d.getEmployeeNo());
              titleDto.setTitle(d.getTitle());
              titleDto.setFromDate(d.getFromDate());
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
    } catch (Exception e) {
      response = new HashMap<>();
      response.put("errorMessage", e.toString());
      response.put("causedBy", e.getCause().toString());
      return new ResponseEntity<>(new TreeMap<>(response),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
