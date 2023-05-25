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
        about.setDescription("About my project");
        return about;
    }
}
