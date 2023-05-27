package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Person;
import com.example.mybookstore.servise.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileController(PersonService personService, PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model, @AuthenticationPrincipal Person person) {
        if (person != null) {
            model.addAttribute("user", person);
        }
        return "profile";
    }


    @PostMapping("/profile")
    public String changePassword(Model model,
                            @RequestParam("old-password") String oldPassword,
                            @RequestParam("new-password") String newPassword,
                            @RequestParam("confirm-password") String confirmPassword,
                            @AuthenticationPrincipal Person user) {
        boolean hasErrors = false;
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            model.addAttribute("errorOldPasswordProfile", "Wrong password");
            hasErrors = true;
        }
        if (newPassword.trim().isEmpty()) {
            model.addAttribute("errorNewPasswordProfile", "New password can't be empty");
            hasErrors = true;
        }
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("errorPasswordConfirmationProfile", "Passwords do not match");
            hasErrors = true;
        }

        if (hasErrors) {
            model.addAttribute("user", user);
            return "profile";
        }
        user.setPassword(newPassword);
        personService.savePerson(user);
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/login";
    }
}
