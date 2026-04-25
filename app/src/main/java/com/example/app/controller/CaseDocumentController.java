package com.example.app.controller;

import com.example.app.service.SpringUser;
import com.example.model.CaseDocument;
import com.example.service.CaseDocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CaseDocumentController {

    private final CaseDocumentService caseDocumentService;

    @GetMapping("/caseDocuments")
    public String caseDocuments(ModelMap modelMap, @AuthenticationPrincipal SpringUser springUser) {
        List<CaseDocument> caseDocuments = caseDocumentService.findAll();
        modelMap.addAttribute("caseDocuments", caseDocuments);

        return "caseDocuments";
    }

    @GetMapping("/caseDocuments/delete")
    public String deleteCaseDocuments(@RequestParam("id") int id) {
        caseDocumentService.deleteById((long) id);
        return "redirect:/caseDocuments";
    }

    @GetMapping("/caseDocuments/add")
    public String addCaseDocuments() {
        return "addCaseDocuments";
    }

    @PostMapping("/caseDocuments/add")
    public String addCaseDocuments(@ModelAttribute CaseDocument caseDocument) {
        caseDocumentService.save(caseDocument);
        return "redirect:/caseDocuments";

    }
}


