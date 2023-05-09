package com.example.mybookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseController {

    @GetMapping("/basket")
    public String getHomePage(Model model) {
//        RegistrationController.addRegistrationErrorAttributesToModel(model);
        return "basket";
    }

}

