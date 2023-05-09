package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Person;
import com.example.mybookstore.servise.PersonService;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.*;

@Controller
@SessionAttributes({"errorUsername", "errorEmail", "errorPassword", "errorPasswordConfirmation", "user"})
public class RegistrationController {
    private final PersonService personService;

    @Autowired
    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/registration")
    public String getAllBooks() {
        return "registration";
    }

        @PostMapping("/registration")
    public String AddPerson(@Valid Person person,
                            BindingResult bindingResult,
                            Model model, @RequestParam("confirm-password") String confirmPassword){
        boolean hasErrors = false;
        if (bindingResult.getFieldError("username") != null) {
            model.addAttribute("errorUsername", "User name can't be empty");
            hasErrors = true;
        }
        if (bindingResult.getFieldError("email") != null) {
            model.addAttribute("errorEmail", "Email is not valid or already exists");
            hasErrors = true;
        }
        final Optional<Person> byEmail = personService.getPersonByEmail(person.getEmail());
        if (byEmail.isPresent()) {
            model.addAttribute("errorEmail", "Email is not valid or already exists");
            hasErrors = true;
        }
        if (bindingResult.getFieldError("password") != null) {
            model.addAttribute("errorPassword", "Password can't be empty");
            hasErrors = true;
        }
        if (!person.getPassword().equals(confirmPassword)) {
            model.addAttribute("errorPasswordConfirmation", "Passwords do not match");
            hasErrors = true;
        }
        if (hasErrors) {
            model.addAttribute("user", person);
            return "registration";
        }
        personService.savePerson(person);
        return "redirect:/login";
    }


//    public static void addRegistrationErrorAttributesToModel(Model model) {
//        model.addAttribute("errorUsername", model.getAttribute("errorUsername"));
//        model.addAttribute("errorEmail", model.getAttribute("errorEmail"));
//        model.addAttribute("errorPassword", model.getAttribute("errorPassword"));
//        model.addAttribute("errorPasswordConfirmation", model.getAttribute("errorPasswordConfirmation"));
//    }

}

