package mmr.epde.casemanagement.model.caseModule;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CaseRequest {
    private String organizationName;
    private String bin;
    private String caseSummary;
    private CaseStatus caseStatus;
    private CourtName courtName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String hearingDate;
    private String verdict;
    private List<String> officersList;
    private MultipartFile attachment;
}
