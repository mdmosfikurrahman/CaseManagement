package mmr.epde.casemanagement.service;

import mmr.epde.casemanagement.model.Case;

import java.util.List;
import java.util.Optional;

public interface CaseService {
    List<Case> getAllCases();

    Optional<Case> getCaseById(Long id);

    Case saveCase(Case caseObject);

    void deleteCase(Long id);
}
