package mmr.epde.casemanagement.controller;

import mmr.epde.casemanagement.model.Case;
import mmr.epde.casemanagement.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cases")
public class CaseController {
    private final CaseService caseService;

    @Autowired
    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping
    public String getAllCases(Model model) {
        List<Case> cases = caseService.getAllCases();
        model.addAttribute("cases", cases);
        return "case-list";
    }

    @GetMapping("/{id}")
    public String getCaseById(@PathVariable Long id, Model model) {
        Case caseObject = caseService.getCaseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid case Id:" + id));
        model.addAttribute("case", caseObject);
        return "case-details";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("case", new Case());
        return "case-add";
    }

    @PostMapping("/add")
    public String addCase(@ModelAttribute Case caseObject) {
        caseService.saveCase(caseObject);
        return "redirect:/cases";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Case caseObject = caseService.getCaseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid case Id:" + id));
        model.addAttribute("case", caseObject);
        return "case-edit";
    }

    @PostMapping("/edit/{id}")
    public String editCase(@PathVariable Long id, @ModelAttribute Case updatedCase) {
        updatedCase.setId(id);
        caseService.saveCase(updatedCase);
        return "redirect:/cases";
    }

    @GetMapping("/delete/{id}")
    public String deleteCase(@PathVariable Long id) {
        caseService.deleteCase(id);
        return "redirect:/cases";
    }
}
