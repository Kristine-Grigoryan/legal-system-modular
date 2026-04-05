package com.example.app.controller;

import com.example.dto.LegalCaseSearchCriteria;
import com.example.model.LegalCase;
import com.example.model.Status;
import com.example.model.User;
import com.example.service.LegalCaseService;
import com.example.service.UserService;
import com.example.service.specification.LegalCaseSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
    @RequiredArgsConstructor
    public class LegalCaseController {


    private final UserService userService;
    private final LegalCaseService legalCaseService;


    @GetMapping("/legalCases")
    public String legalCases(ModelMap modelMap,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @ModelAttribute LegalCaseSearchCriteria searchCriteria
    ) {
        if (searchCriteria.getTitle() == null && searchCriteria.getDescription() == null && searchCriteria.getAmount() == null) {
            int currentPage = page.orElse(0);
            int pageSize = size.orElse(5);
            Sort sort = Sort.by(Sort.Direction.DESC, "id");

            PageRequest pageRequest = PageRequest.of(currentPage, pageSize, sort);

            Page<LegalCase> result = legalCaseService.findAll(pageRequest);

            int totalPages = result.getTotalPages();

            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .toList();
                modelMap.addAttribute("pageNumbers", pageNumbers);
            }

            modelMap.addAttribute("legalCases", result);
        } else {
            LegalCaseSpecification studentSpecification = new LegalCaseSpecification(searchCriteria);
            Page<LegalCase> result = legalCaseService.findAllWithSpecification(studentSpecification);
            modelMap.addAttribute("legalCases", result);
            modelMap.addAttribute("searchCriteria", searchCriteria);

        }
        return "legalCases";
    }
    @GetMapping("/LegalCases/add")
    public String addLegalCasePages(ModelMap modelMap) {
        modelMap.addAttribute("legalCase",new LegalCase());
        modelMap.addAttribute("users", userService.findAll());
        modelMap.addAttribute("statuses", Status.values());
        return "addLegalCases";

    }

    @GetMapping("/LegalCases/edit/{id}")
    public String editLegalCasePage(@PathVariable("id") long id, ModelMap modelMap) {
        LegalCase legalCase = legalCaseService.findById(id);
        modelMap.addAttribute("legalCase", legalCase);
        modelMap.addAttribute("users", userService.findAll());
        modelMap.addAttribute("statuses", Status.values());
        return "addLegalCases";
    }
//       test branch test change

    @GetMapping("/legalCases/delete")
    public String delete(@RequestParam("id") long id) {
        legalCaseService.deleteById((long) id);
        return "redirect:/legalCases";
    }


    @PostMapping("/legalCases/update")
    public String updateLegalCase(@ModelAttribute LegalCase legalCase,
                                  @RequestParam("pic") MultipartFile multipartFile) {
        legalCaseService.save(legalCase, multipartFile);
        return "redirect:/legalCases";

    }


     }

