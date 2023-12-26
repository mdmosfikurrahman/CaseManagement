package mmr.epde.casemanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String organizationName;
    private String bin;
    @Enumerated(EnumType.STRING)
    private CaseStatus caseStatus;
    private String caseNo;
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date caseDate;
    private String demand;
    private String caseSummary;
    @Enumerated(EnumType.STRING)
    private CourtName courtName;
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date hearingDate;
    private String verdict;
    @ElementCollection
    private List<String> officersList;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] attachment;
}