package mmr.epde.casemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class CaseResponse {
    private Long id;
    private String organizationName;
    private String bin;
    private CaseStatus caseStatus;
    private String caseNo;
    private Date caseDate;
    private String demand;
    private String caseSummary;  // Add this field
    private CourtName courtName; // Add this field
    private Date hearingDate;    // Add this field
    private String verdict;      // Add this field
    private List<String> officersList; // Add this field
    private byte[] attachment;
}
