package mmr.epde.casemanagement.service;

import mmr.epde.casemanagement.model.Case;
import mmr.epde.casemanagement.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaseServiceImpl implements CaseService {
    private final CaseRepository caseRepository;

    @Autowired
    public CaseServiceImpl(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Override
    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    @Override
    public Optional<Case> getCaseById(Long id) {
        return caseRepository.findById(id);
    }

    @Override
    public Case saveCase(Case caseObject) {
        return caseRepository.save(caseObject);
    }

    @Override
    public void deleteCase(Long id) {
        caseRepository.deleteById(id);
    }
}

