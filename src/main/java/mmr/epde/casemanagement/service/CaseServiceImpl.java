package mmr.epde.casemanagement.service;

import lombok.RequiredArgsConstructor;
import mmr.epde.casemanagement.model.CaseInfo;
import mmr.epde.casemanagement.model.CaseResponse;
import mmr.epde.casemanagement.model.CaseStatus;
import mmr.epde.casemanagement.model.CourtName;
import mmr.epde.casemanagement.repository.CaseRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CaseServiceImpl implements CaseService {

    private final CaseRepository caseRepository;

    @Override
    public CaseInfo createCase(String organizationName, String bin, String caseSummary, CaseStatus caseStatus,
                               CourtName courtName, Date hearingDate, String verdict, List<String> officersList,
                               byte[] attachment) {
        CaseInfo newCase = new CaseInfo();
        newCase.setOrganizationName(organizationName);
        newCase.setBin(bin);
        newCase.setCaseSummary(caseSummary);
        newCase.setCaseStatus(caseStatus);
        newCase.setCourtName(courtName);
        newCase.setHearingDate(hearingDate);
        newCase.setVerdict(verdict);
        newCase.setOfficersList(officersList);
        newCase.setAttachment(attachment);
        return caseRepository.save(newCase);
    }

    @Override
    public List<CaseResponse> getCaseDetails() {
        return caseRepository.findAll().stream()
                .map(caseEntity -> new CaseResponse(
                        caseEntity.getOrganizationName(),
                        caseEntity.getBin(),
                        caseEntity.getCaseStatus(),
                        caseEntity.getCaseNo(),
                        caseEntity.getCaseDate(),
                        caseEntity.getDemand()
                ))
                .collect(Collectors.toList());
    }
}