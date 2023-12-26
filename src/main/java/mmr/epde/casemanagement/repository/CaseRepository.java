package mmr.epde.casemanagement.repository;

import mmr.epde.casemanagement.model.caseModule.CaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<CaseInfo, Long> {
    @Query(value = "SELECT MAX(id) FROM CaseInfo")
    Long findLastId();
}
