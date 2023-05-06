package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Person;
import com.example.mybookstore.servise.PersonService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class RegistrationController {
    private final PersonService personService;

    @Autowired
    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("/registration")
    public String getAllBooks(Model model) {
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
            model.addAttribute("errorPassword1", "Password can't be empty");
            hasErrors = true;
        }
        if (!person.getPassword().equals(confirmPassword)) {
            model.addAttribute("errorPassword2", "Passwords do not match");
            hasErrors = true;
        }
        if (hasErrors) {
            return "registration";
        }
        personService.savePerson(person);
        return "redirect:/login";
    }


}
