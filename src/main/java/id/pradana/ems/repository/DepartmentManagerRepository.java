package id.pradana.ems.repository;

import id.pradana.ems.model.DepartmentManager;
import id.pradana.ems.model.DepartmentManagerPK;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentManagerRepository
    extends CrudRepository<DepartmentManager, DepartmentManagerPK> {
}
