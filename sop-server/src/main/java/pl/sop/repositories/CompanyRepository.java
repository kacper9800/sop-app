package pl.sop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

  @Query(value = "select c from Company c "
      + " where c.active = true and c.deleted = false")
  List<Company> getAllCompanies(@Param("id") Long id);

  @Query(value = "SELECT c from Company c where c.nip = :nip")
  List<Company> checkIfCompanyExists(@Param("nip") String nip);

  @Query(value = "SELECT c FROM Company c where c.active = true and c.nip = :nip")
  Company getCompanyByNip(@Param("nip") String nip);
}
