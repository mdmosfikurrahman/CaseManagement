package mmr.epde.casemanagement.service;

import mmr.epde.casemanagement.model.CaseInfo;
import mmr.epde.casemanagement.model.CaseResponse;
import mmr.epde.casemanagement.model.CaseStatus;
import mmr.epde.casemanagement.model.CourtName;

import java.util.Date;
import java.util.List;

public interface CaseService {
    CaseInfo createCase(String organizationName, String bin, String caseSummary, CaseStatus caseStatus,
                        CourtName courtName, Date hearingDate, String verdict, List<String> officersList,
                        byte[] attachment);

    List<CaseInfo> getAllCases();

    List<CaseResponse> getCaseDetails();
}
