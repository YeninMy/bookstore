package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.servise.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.security.Security;
import java.util.Optional;

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
    public String getProfilePage(Model model, @AuthenticationPrincipal Person user) {
        if (user != null) {
//            model.addAttribute("username", user.getUsername());
            model.addAttribute("user", user);
        }
        return "profile";
    }

//    @PostMapping("/profile")
//    public String AddPerson(Model model, @RequestParam("old-password") String oldPassword,
//                            @RequestParam("new-password") String newPassword,
//                            @RequestParam("confirm-password") String confirmPassword,
//                            @AuthenticationPrincipal Person user) {
//        boolean hasErrors = false;
//        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
//            model.addAttribute("errorOldPassword", "Wrong password");
//            hasErrors = true;
//        }
////        if (bindingResult.getFieldError("password") != null) {
////            model.addAttribute("errorPassword", "Password can't be empty");
////            hasErrors = true;
////        }
//        if (!newPassword.equals(confirmPassword)) {
//            model.addAttribute("errorPasswordConfirmation", "Passwords do not match");
//            hasErrors = true;
//        }
//        if (hasErrors) {
//            return "profile";
//        }
//        user.setPassword(newPassword);
//        personService.savePerson(user);
//        return "redirect:/";
//    }

    @PostMapping("/profile")
    public String AddPerson(Model model,
                            @RequestParam("old-password") String oldPassword,
                            @RequestParam("new-password") String newPassword,
                            @RequestParam("confirm-password") String confirmPassword,
                            @AuthenticationPrincipal Person user) {
                boolean hasErrors = false;
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            model.addAttribute("errorOldPassword", "Wrong password");
            hasErrors = true;
        }
        if (newPassword.trim().isEmpty()) {
            model.addAttribute("errorNewPassword", "New password can't be empty");
            hasErrors = true;
        }
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("errorPasswordConfirmation", "Passwords do not match");
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
