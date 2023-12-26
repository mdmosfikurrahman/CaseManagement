package mmr.epde.casemanagement.controller;

import lombok.RequiredArgsConstructor;
import mmr.epde.casemanagement.model.bondApplication.BondApplicationDetails;
import mmr.epde.casemanagement.model.caseModule.CaseResponse;
import mmr.epde.casemanagement.model.caseModule.CaseRequest;
import mmr.epde.casemanagement.repository.BondApplicationDetailsRepository;
import mmr.epde.casemanagement.service.CaseService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cases")
public class CaseController {

    private final CaseService caseService;
    private final BondApplicationDetailsRepository bondApplicationDetailsRepository;

    @GetMapping("/create")
    public String showCreateCaseForm(Model model) {
        List<BondApplicationDetails> bondApplicationDetailsList = bondApplicationDetailsRepository.findAll();
        model.addAttribute("bondApplications", bondApplicationDetailsList);

        model.addAttribute("caseRequest", new CaseRequest());
        return "createCase";
    }

    @PostMapping("/create")
    public String createCase(@ModelAttribute CaseRequest caseRequest, BindingResult result) throws IOException, ParseException {
        if (result.hasErrors()) {
            return "createCase";
        }

        String binOrganization = caseRequest.getBinOrganization();
        String bin = extractBin(binOrganization);
        String organizationName = extractOrganizationName(binOrganization);

        caseService.createCase(organizationName, bin, caseRequest.getCaseSummary(),
                caseRequest.getCaseStatus(), caseRequest.getCourtName(), parseDate(caseRequest.getHearingDate()),
                caseRequest.getVerdict(), caseRequest.getOfficersList(), caseRequest.getAttachment().getBytes());

        return "redirect:/cases/list";
    }


    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateStr);
    }

    private String extractBin(String binOrganization) {
        return binOrganization.substring(binOrganization.lastIndexOf("(") + 1, binOrganization.lastIndexOf(")")).trim();
    }

    private String extractOrganizationName(String binOrganization) {
        return binOrganization.substring(0, binOrganization.indexOf("(")).trim();
    }

    @GetMapping("/list")
    public String showCaseList(Model model) {
        List<CaseResponse> caseList = caseService.getCaseDetails();
        model.addAttribute("caseList", caseList);
        return "caseList";
    }

    @GetMapping("/view/{id}")
    public String viewCase(@PathVariable Long id, Model model) {
        CaseResponse caseResponse = caseService.getCaseDetailsById(id);
        model.addAttribute("caseResponse", caseResponse);
        return "viewCase";
    }

    @GetMapping("/edit/{id}")
    public String showEditCaseForm(@PathVariable Long id, Model model) {
        CaseResponse caseResponse = caseService.getCaseDetailsById(id);
        List<BondApplicationDetails> bondApplicationDetailsList = bondApplicationDetailsRepository.findAll();
        model.addAttribute("bondApplications", bondApplicationDetailsList);
        model.addAttribute("caseResponse", caseResponse);
        model.addAttribute("caseRequest", new CaseRequest());
        return "editCase";
    }

    @PostMapping("/edit/{id}")
    public String editCase(@PathVariable Long id, @ModelAttribute CaseRequest caseRequest, BindingResult result) throws IOException, ParseException {
        if (result.hasErrors()) {
            return "editCase";
        }

        String binOrganization = caseRequest.getBinOrganization();
        String organizationName = extractOrganizationName(binOrganization);
        String bin = extractBin(binOrganization);

        caseService.editCase(id, organizationName, bin, caseRequest.getCaseSummary(),
                caseRequest.getCaseStatus(), caseRequest.getCourtName(), parseDate(caseRequest.getHearingDate()),
                caseRequest.getVerdict(), caseRequest.getOfficersList(), caseRequest.getAttachment().getBytes());

        return "redirect:/cases/list";
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadAttachment(@PathVariable Long id) {
        CaseResponse caseResponse = caseService.getCaseDetailsById(id);

        if (caseResponse != null && caseResponse.getAttachment() != null) {
            String bin = caseResponse.getCaseNo();

            String safeFilename = bin.replaceAll("[^a-zA-Z0-9.-]", "_");

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header("Content-Disposition", "attachment; filename=\"" + safeFilename + ".pdf\"")
                    .body(caseResponse.getAttachment());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

