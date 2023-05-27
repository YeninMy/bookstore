package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Person;
import com.example.mybookstore.servise.PersonService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String AddPerson(@Valid Person person,
                            BindingResult bindingResult,
                            Model model, @RequestParam("confirm-password") String confirmPassword) {
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

}

