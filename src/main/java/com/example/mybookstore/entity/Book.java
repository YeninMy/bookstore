package com.example.mybookstore.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


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
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<Author> authors;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Comment> comments;

    private double price;

    private int quantity;

    private String coverImage;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<Purchase> purchases = new ArrayList<>();
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<Wishlist> wishlists = new ArrayList<>();
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

    public Book() {
    }


    public Book(String name, List<Author> authors, Genre genre, double price, int quantity) {
        this.name = name;
        this.authors = authors;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;

    }

    public Book(String name, List<Author> authors, Genre genre, double price, int quantity, String coverImage) {
        this.name = name;
        this.authors = authors;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
        this.coverImage = coverImage;
    }

    public String getPlaceHolder() {
        String placeHolder = "https://drive.google.com/uc?export=view&id=1f39LJaI3PKC5uMorR3XcsW-oVtbEDeQA";
        return placeHolder;
    }

    public String getDirectLinkToImage() {
        if (coverImage != null) {
            String fileId = null;

            if (coverImage.contains("/d/")) {
                fileId = coverImage.split("/d/")[1].split("/")[0];
            } else if (coverImage.contains("id=")) {
                fileId = coverImage.split("id=")[1];
            }

            if (fileId != null) {
                return "https://drive.google.com/uc?export=view&id=" + fileId;
            }
        }
        return null;
    }

    public double averageRating() {
        return ratings.stream()
                .mapToDouble(Rating::getMark)
                .average()
                .orElse(0.0);
    }


}
