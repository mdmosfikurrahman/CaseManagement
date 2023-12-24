package mmr.epde.casemanagement.repository;

import mmr.epde.casemanagement.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {

}
