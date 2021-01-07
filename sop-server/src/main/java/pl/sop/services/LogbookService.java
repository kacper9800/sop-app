package pl.sop.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.FromDTO.DTOToLogbookConverter;
import pl.sop.converters.FromDTO.DTOToLogbookPostConverter;
import pl.sop.converters.ToDTO.LogbookPostToDTOConverter;
import pl.sop.converters.ToDTO.LogbookToDTOConverter;
import pl.sop.dto.LogbookDTO;
import pl.sop.dto.LogbookPostDTO;
import pl.sop.entities.Logbook;
import pl.sop.entities.LogbookPost;
import pl.sop.entities.User;
import pl.sop.entities.organizationStructure.College;
import pl.sop.entities.organizationStructure.CollegeRepository;
import pl.sop.entities.organizationStructure.CollegeService;
import pl.sop.entities.organizationStructure.Institute;
import pl.sop.entities.organizationStructure.InstituteRepository;
import pl.sop.entities.organizationStructure.InstituteService;
import pl.sop.repositories.LogbookPostRepository;
import pl.sop.repositories.LogbookRepository;
import pl.sop.repositories.UserRepository;

@Service
public class LogbookService {

  @Autowired
  private LogbookRepository logbookRepository;

  @Autowired
  private LogbookPostRepository logbookPostRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private InstituteService instituteService;

  @Autowired
  private InstituteRepository instituteRepository;

  @Autowired
  private CollegeService collegeService;

  @Autowired
  private CollegeRepository collegeRepository;

  private final LogbookToDTOConverter logbookToDTOConverter = new LogbookToDTOConverter();
  private final DTOToLogbookConverter dtoToLogbookConverter = new DTOToLogbookConverter();

  private final LogbookPostToDTOConverter logbookPostToDTOConverter = new LogbookPostToDTOConverter();
  private final DTOToLogbookPostConverter dtoToLogbookPostConverter = new DTOToLogbookPostConverter();

  public ResponseEntity<LogbookDTO> getLogbookById(Long id) {
    Logbook logbook = logbookRepository.findLogbookById(id);
    LogbookDTO logbookDTO = logbookToDTOConverter.convert(logbook);
    return ResponseEntity.ok(logbookDTO);
  }

  public ResponseEntity<List<LogbookDTO>> getAllLogbooks(Long instituteId) {
    List<Logbook> logbooks = logbookRepository.findAllLogbooksForInstituteId(instituteId);
    List<LogbookDTO> logbookDTOS = logbooks.stream().map(logbookToDTOConverter::convert)
        .collect(Collectors.toList());
    return ResponseEntity.ok(logbookDTOS);
  }

  public ResponseEntity<List<Logbook>> getAllLogbooksForInternIdAndCollegeId(Long internId,
      Long collegeId) {
    List<Logbook> logbooks = logbookRepository
        .findAllLogbooksForInternIdAndCollegeId(internId, collegeId);
    List<LogbookDTO> logbookDTOS = logbooks.stream().map(logbookToDTOConverter::convert)
        .collect(Collectors.toList());
    return new ResponseEntity(logbookDTOS, HttpStatus.OK);
  }

  public ResponseEntity<List<LogbookPostDTO>> getLogbookPostsByLogbookId(Long logbookId) {
    List<LogbookPost> logbookPosts = logbookPostRepository
        .findAllLogbookPostsForLogbookId(logbookId);
    List<LogbookPostDTO> logbookPostDTOS = logbookPosts.stream()
        .map(logbookPostToDTOConverter::convert).collect(
            Collectors.toList());
    return new ResponseEntity(logbookPostDTOS, HttpStatus.OK);
  }

  public ResponseEntity createNewLogbook(LogbookDTO logbookDTO) {
    Logbook logbook = dtoToLogbookConverter.convert(logbookDTO);
    User intern = userRepository.findUserById(logbookDTO.getInternId());
    logbook.setIntern(intern);

    Institute institute = instituteRepository.findActiveInstituteById(logbookDTO.getInstituteId());
    logbook.setInstitute(institute);

    College college = collegeRepository.findCollegeById(logbookDTO.getCollegeId());
    logbook.setCollege(college);
    return ResponseEntity.ok(this.logbookRepository.save(logbook));
  }

  public ResponseEntity createNewLogbookPost(LogbookPostDTO logbookPostDTO) {
    LogbookPost logbookPost = dtoToLogbookPostConverter.convert(logbookPostDTO);
    Logbook logbook = logbookRepository.findLogbookById(logbookPostDTO.getLogbookId());
    logbookPost.setLogbook(logbook);
    return ResponseEntity.ok(this.logbookPostRepository.save(logbookPost));
  }

}
