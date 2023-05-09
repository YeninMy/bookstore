package com.example.mybookstore.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    private double price;

    private int quantity;

    private String coverImage;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<Purchase> purchases = new ArrayList<>();

    public Book() {
    }


    public Book(String name, Author author, Genre genre, double price, int quantity) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
    }

    public Book(String name, Author author, Genre genre, double price, int quantity, String coverImage) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
        this.coverImage = coverImage;
    }
}
