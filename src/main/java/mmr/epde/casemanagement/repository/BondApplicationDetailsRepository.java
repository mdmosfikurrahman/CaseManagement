package mmr.epde.casemanagement.repository;

import mmr.epde.casemanagement.model.bondApplication.BondApplicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondApplicationDetailsRepository extends JpaRepository<BondApplicationDetails, Long> {
}
