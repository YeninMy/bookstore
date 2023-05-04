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
                            Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("errorUsername","User name can't be empty");
            return "registration";
        }

        final Optional<Person> byEmail = personService.getPersonByEmail(person.getEmail());

        if(byEmail.isPresent()){
            model.addAttribute("errorEmail","Email is not valid or already exists");
            return "registration";
        }

        personService.savePerson(person);
        return "redirect:/";
    }
}
