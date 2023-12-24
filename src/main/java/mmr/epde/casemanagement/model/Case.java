package mmr.epde.casemanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organizationName;
    private String bin;

    @Enumerated(EnumType.STRING)
    private CaseStatus caseStatus;

    private String caseNo;
    private Date caseDate;
    private String demand;
    private String caseSummary;

    @Enumerated(EnumType.STRING)
    private CourtName courtName;

    private Date hearingDate;
    private String verdict;
    private String officersList;

    @Lob
    private byte[] attachment;
}
