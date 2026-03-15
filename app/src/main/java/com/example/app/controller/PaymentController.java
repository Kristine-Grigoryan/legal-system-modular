package com.example.app.controller;

import com.example.app.service.SpringUser;
import com.example.model.Payment;
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

    @GetMapping("/payment")
    public String payment(ModelMap modelMap, @AuthenticationPrincipal SpringUser springUser) {
        List<Payment> payments = paymentService.findAll();
        modelMap.addAttribute("Payment", payments);

        return "payments";
    }

    @GetMapping("/payments/delete")
    public String deletes(@RequestParam("id") int id) {
        paymentService.deleteById((long) id);
        return "redirect:/payments";
    }

    @GetMapping("/payments/add")
    public String payments() {
        return "addPayments";
    }

    @PostMapping("/payments/add")
    public String addPayments(@ModelAttribute Payment payment) {
        paymentService.save(payment);
        return "redirect:/payments";

    }
}