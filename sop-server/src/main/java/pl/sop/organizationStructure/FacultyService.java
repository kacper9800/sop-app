package pl.sop.organizationStructure;

import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {

  @Autowired
  private FacultyRepository facultyRepository;

  public Faculty getById(Long id) {
    return facultyRepository.findById(id).get();
  }
}
