package mmr.epde.casemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

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
}
