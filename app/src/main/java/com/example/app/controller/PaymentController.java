package com.example.app.controller;

import com.example.app.service.SpringUser;
import com.example.service.PaymentService;
import com.model.Payment;
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

    @GetMapping("/payment")
    public String payment(ModelMap modelMap, @AuthenticationPrincipal SpringUser springUser) {
        List<Payment> payments = paymentService.findAll();
        modelMap.addAttribute("Payment", payments);

        return "payments";
    }

    @GetMapping("/payments/delete")
    public String deletes(@RequestParam("id") int id) {
        paymentService.deleteById((long) id);
        return "redirect:/paymants";
    }

    @GetMapping("/paymants/add")
    public String paymants() {
        return "addPaymants";
    }

    @PostMapping("/paymants/add")
    public String addPaymants(@ModelAttribute Payment payments) {
        paymentService.save(payments);
        return "redirect:/payments";

    }
}


