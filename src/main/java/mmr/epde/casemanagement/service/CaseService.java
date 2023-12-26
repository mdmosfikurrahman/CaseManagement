package mmr.epde.casemanagement.service;

import mmr.epde.casemanagement.model.caseModule.CaseInfo;
import mmr.epde.casemanagement.model.caseModule.CaseResponse;
import mmr.epde.casemanagement.model.caseModule.CaseStatus;
import mmr.epde.casemanagement.model.caseModule.CourtName;

import java.util.Date;
import java.util.List;

public interface CaseService {
    CaseInfo createCase(String organizationName, String bin, String caseSummary, CaseStatus caseStatus,
                        CourtName courtName, Date hearingDate, String verdict, List<String> officersList,
                        byte[] attachment);
    List<CaseResponse> getCaseDetails();
    CaseResponse getCaseDetailsById(Long id);
    void editCase(Long id, String organizationName, String bin, String caseSummary, CaseStatus caseStatus,
                  CourtName courtName, Date hearingDate, String verdict, List<String> officersList,
                  byte[] attachment);
}
