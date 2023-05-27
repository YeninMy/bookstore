package com.example.mybookstore.controller;

import com.example.mybookstore.entity.About;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/about")
public class AboutController {
    @GetMapping
    public About getAbout() {
        About about = new About();
        about.setCreator("Mykhailo Yenin");
        about.setDescription("This project was created as a thesis for the course of the java-developer of the Study Space school and serves as a demonstration of the experience I have gained.");
        return about;
    }
}
