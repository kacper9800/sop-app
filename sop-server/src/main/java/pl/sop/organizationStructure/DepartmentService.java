package pl.sop.organizationStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

  @Autowired
  private DepartmentRepository departmentRepository;

  public Department getById(Long id) {
   return departmentRepository.findById(id).get();
  }

}
