package pl.sop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.entities.Dictionary;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

  @Query(value = "SELECT d from Dictionary d WHERE d.dictionaryType = 'STUDY_MODE'")
  List<Dictionary> getAllStudyModes();

  @Query(value = "SELECT d from Dictionary d WHERE d.dictionaryType = 'SEX_TYPE'")
  List<Dictionary> getAllSexTypes();

  @Query(value = "SELECT d FROM Dictionary d WHERE d.dictionaryType = 'ACADEMIC_DEGREE'")
  List<Dictionary> getAllAcademicDegrees();

  @Query(value = "SELECT d FROM Dictionary d WHERE d.dictionaryType = 'REQUEST_DECISION_STATUS'")
  List<Dictionary> getAllRequestDecisionStatuses();

  @Query(value = "SELECT d FROM Dictionary d WHERE d.dictionaryType = 'REQUEST_TYPE'")
  List<Dictionary> getAllRequestTypes();

  @Query(value = "SELECT d from Dictionary d WHERE d.value = :dictionaryValue")
  Dictionary getByValue(@Param("dictionaryValue") String value);

}
