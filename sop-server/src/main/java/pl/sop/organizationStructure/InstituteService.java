package pl.sop.organizationStructure;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstituteService {

  @Autowired
  private InstituteRepository instituteRepository;

  public Institute getById(Long id) {
    return instituteRepository.findById(id).get();
  }

}
