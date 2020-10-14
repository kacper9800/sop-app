package pl.sop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.entities.ActivationKey;

@Repository
public interface ActivationKeyRepository extends JpaRepository<ActivationKey, Long> {

  @Query(value = "select activationKey from ActivationKey activationKey where activationKey.deleted = false")
  List<ActivationKey> getAllActivationKeys();

  @Query(value = "select activationKey from ActivationKey activationKey where activationKey.deleted = false and activationKey.id = :id")
  List<ActivationKey> getActivationKeyById(@Param("id") Long id);

  @Query(value = "select activationKey from ActivationKey activationKey"
      + " left join fetch activationKey.college c "
      + " where activationKey.deleted = false and c.id = :collegeId")
  List<ActivationKey> getAllActivationKeysByCollegeId(@Param("collegeId") Long id);

//  @Query(value = "select activationKey from ActivationKey activationKey left join fetch")
//  List<ActivationKey> getAllActivationKeysByCompanyId(Long id);

  @Query(value = "SELECT distinct activationKey from ActivationKey activationKey"
      + " WHERE activationKey.value = :activationKey"
      + " and activationKey.active = true"
      + " and activationKey.numberOfUses > 0")
  ActivationKey findValidActivationKeyByValue(@Param("activationKey") String activationKey);

//  @Query(value = "select activationKey from ActivationKey activationKey "
//      + "where activationKey.createdBy = :id and activationKey.deleted = false")
//  List<ActivationKey> findAllActivationKeysForLoggedUser(@Param("id") Long userId);
}
