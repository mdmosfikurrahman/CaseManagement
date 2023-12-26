package mmr.epde.casemanagement.service;

import lombok.RequiredArgsConstructor;
import mmr.epde.casemanagement.model.caseModule.CaseInfo;
import mmr.epde.casemanagement.model.caseModule.CaseResponse;
import mmr.epde.casemanagement.model.caseModule.CaseStatus;
import mmr.epde.casemanagement.model.caseModule.CourtName;
import mmr.epde.casemanagement.repository.CaseRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
        String caseNo = generateCaseNumber();
        setValueForCase(organizationName, bin, caseSummary, caseStatus, courtName, hearingDate, verdict, officersList, attachment, newCase);
        newCase.setCaseNo(caseNo);
        return caseRepository.save(newCase);
    }

    @Override
    public List<CaseResponse> getCaseDetails() {
        return caseRepository.findAll().stream()
                .map(this::convertToCaseResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CaseResponse getCaseDetailsById(Long id) {
        return caseRepository.findById(id)
                .map(this::convertToCaseResponse)
                .orElse(null);
    }

    @Override
    public void editCase(Long id, String organizationName, String bin, String caseSummary, CaseStatus caseStatus,
                         CourtName courtName, Date hearingDate, String verdict, List<String> officersList,
                         byte[] attachment) {
        caseRepository.findById(id).ifPresent(existingCase -> {
            setValueForCase(organizationName, bin, caseSummary, caseStatus, courtName, hearingDate, verdict, officersList, attachment, existingCase);
            caseRepository.save(existingCase);
        });
    }

    private CaseResponse convertToCaseResponse(CaseInfo caseEntity) {
        return new CaseResponse(
                caseEntity.getId(),
                caseEntity.getOrganizationName(),
                caseEntity.getBin(),
                caseEntity.getCaseStatus(),
                caseEntity.getCaseNo(),
                caseEntity.getCaseDate(),
                caseEntity.getDemand(),
                caseEntity.getCaseSummary(),
                caseEntity.getCourtName(),
                caseEntity.getHearingDate(),
                caseEntity.getVerdict(),
                caseEntity.getOfficersList(),
                caseEntity.getAttachment()
        );
    }

    private void setValueForCase(String organizationName, String bin, String caseSummary, CaseStatus caseStatus, CourtName courtName, Date hearingDate, String verdict, List<String> officersList, byte[] attachment, CaseInfo newCase) {
        newCase.setOrganizationName(organizationName);
        newCase.setBin(bin);
        newCase.setCaseSummary(caseSummary);
        newCase.setCaseStatus(caseStatus);
        newCase.setCourtName(courtName);
        newCase.setHearingDate(hearingDate);
        newCase.setVerdict(verdict);
        newCase.setOfficersList(officersList);
        newCase.setAttachment(attachment);
    }

    private String generateCaseNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = dateFormat.format(new Date());
        Long lastId = caseRepository.findLastId();

        if (lastId == null) {
            lastId = 1L;
        } else {
            lastId++;
        }

        String formattedId = String.format("%04d", lastId);

        return formattedDate + formattedId;
    }

}