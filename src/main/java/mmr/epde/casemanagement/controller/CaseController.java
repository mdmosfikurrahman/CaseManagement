package mmr.epde.casemanagement.controller;

import mmr.epde.casemanagement.model.CaseResponse;
import mmr.epde.casemanagement.model.CaseRequest;
import mmr.epde.casemanagement.service.CaseService;
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
@RequestMapping("/cases")
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping("/create")
    public String showCreateCaseForm(Model model) {
        model.addAttribute("caseRequest", new CaseRequest());
        return "createCase";
    }

    @PostMapping("/create")
    public String createCase(@ModelAttribute CaseRequest caseRequest,
                             BindingResult result) throws IOException, ParseException {
        if (result.hasErrors()) {
            return "createCase";
        }

        caseService.createCase(caseRequest.getOrganizationName(), caseRequest.getBin(), caseRequest.getCaseSummary(),
                caseRequest.getCaseStatus(), caseRequest.getCourtName(), parseDate(caseRequest.getHearingDate()),
                caseRequest.getVerdict(), caseRequest.getOfficersList(), caseRequest.getAttachment().getBytes());

        return "redirect:/cases/list";
    }


    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateStr);
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
        model.addAttribute("caseResponse", caseResponse);
        model.addAttribute("caseRequest", new CaseRequest());
        return "editCase";
    }

    @PostMapping("/edit/{id}")
    public String editCase(@PathVariable Long id, @ModelAttribute CaseRequest caseRequest, BindingResult result) throws IOException, ParseException {
        if (result.hasErrors()) {
            return "editCase";
        }

        caseService.editCase(id, caseRequest.getOrganizationName(), caseRequest.getBin(), caseRequest.getCaseSummary(),
                caseRequest.getCaseStatus(), caseRequest.getCourtName(), parseDate(caseRequest.getHearingDate()),
                caseRequest.getVerdict(), caseRequest.getOfficersList(), caseRequest.getAttachment().getBytes());

        return "redirect:/cases/list";
    }
}

