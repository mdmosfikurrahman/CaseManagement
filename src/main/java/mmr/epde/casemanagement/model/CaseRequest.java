package mmr.epde.casemanagement.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CaseRequest {
    private String organizationName;
    private String bin;
    private String caseSummary;
    private CaseStatus caseStatus;
    private CourtName courtName;
    private Date hearingDate;
    private String verdict;
    private List<String> officersList;
    private byte[] attachment;
}
