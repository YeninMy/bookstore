package com.example.mybookstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Username can't be Null")
    private String username;
    @Email
    private String email;
    private String password;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    public Person() {
    }
}
