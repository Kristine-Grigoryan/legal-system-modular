package com.example.app.controller;

import com.example.dto.LegalCaseSearchCriteria;
import com.example.service.LegalCaseService;
import com.example.service.UserService;
import com.example.service.specification.LegalCaseSpecification;
import com.model.LegalCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
    @RequiredArgsConstructor
    public class LegalCaseController {

    private final UserService userService;
    private final LegalCaseService legalCaseService;

    @GetMapping("/legalCase")
    public String legalCases(ModelMap modelMap,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size,
                             @ModelAttribute LegalCaseSearchCriteria searchCriteria
    ) {
        if (searchCriteria.getTitle() == null && searchCriteria.getDescription() == null && searchCriteria.getStatus() == null && searchCriteria.getAmount() == null) {
            int currentPage = page.orElse(1);
            int pageSize = size.orElse(5);
            Sort sort = Sort.by(Sort.Direction.DESC, "id");

            PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize, sort);

            Page<LegalCase> result = legalCaseService.findAll(pageRequest);

            int totalPages = result.getTotalPages();

            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .toList();
                modelMap.addAttribute("pageNumbers", pageNumbers);
            }

            modelMap.addAttribute("legalCase", result);
        } else {
            LegalCaseSpecification legalCaseSpecification = new LegalCaseSpecification(searchCriteria);
            Page<LegalCase> result = legalCaseService.findAllWithSpecification(legalCaseSpecification);
            modelMap.addAttribute("legalCases", result);
            modelMap.addAttribute("searchCriteria", searchCriteria);

        }


        return "legalCases";
    }

    @GetMapping("/legalCases/add")
    public String addLegalCasePage(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.findAll());
        return "addLegalCase";
    }


    @PostMapping("/legalcases/add")
    public String addLegalCase(@ModelAttribute LegalCase legalCase,
                             @RequestParam("pic") MultipartFile multipartFile) {
        legalCaseService.save(legalCase, multipartFile);
        return "redirect:/legalCases";
    }


}

