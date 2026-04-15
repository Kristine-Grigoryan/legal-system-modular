package com.example.app.controller;

import com.example.app.service.SpringUser;
import com.example.model.*;

import com.example.service.LegalCaseService;
import com.example.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
 @Slf4j
public class PaymentController {

    private final PaymentService paymentService;
    private final LegalCaseService legalCaseService;



    @GetMapping("/payments")
    public String payments(ModelMap modelMap, @AuthenticationPrincipal SpringUser springUser) {
        List<Payment> payments = paymentService.findAll();
        modelMap.addAttribute("payments", payments);

        return "payments";
    }


    @GetMapping("/payments/add")
    public String addPaymentsForm(ModelMap modelMap) {
        modelMap.addAttribute("legalCases", legalCaseService.findAll());

        modelMap.addAttribute("methods", PaymentMethod.values());

        return "addPayments";
    }


    @GetMapping("/payments/delete")
    public String delete(@RequestParam("id") long id) {
        paymentService.deleteById((long) id);
        return "redirect:/payments";
    }
    @PostMapping("/payments/add")
    public String savePayment(@ModelAttribute("payment") Payment payment) {
        LegalCase existingCase = legalCaseService.findById(payment.getLegalCase().getId());
        payment.setLegalCase(existingCase);
        paymentService.save(payment);
        return "redirect:/payments";
    }

}
























