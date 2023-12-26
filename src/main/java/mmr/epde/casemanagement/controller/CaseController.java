package mmr.epde.casemanagement.controller;

import lombok.RequiredArgsConstructor;
import mmr.epde.casemanagement.model.CaseInfo;
import mmr.epde.casemanagement.model.CaseResponse;
import mmr.epde.casemanagement.model.CaseRequest;
import mmr.epde.casemanagement.service.CaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cases")
public class CaseController {

    private final CaseService caseService;

    @PostMapping("/create")
    public CaseInfo createCase(@RequestBody CaseRequest request) {
        return caseService.createCase(request.getOrganizationName(), request.getBin(), request.getCaseSummary(),
                request.getCaseStatus(), request.getCourtName(), request.getHearingDate(),
                request.getVerdict(), request.getOfficersList(), request.getAttachment());
    }

    @GetMapping("/getCaseDetails")
    public List<CaseResponse> getCaseDetails() {
        return caseService.getCaseDetails();
    }
}