package com.example.mybookstore.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "purchase_books",
            joinColumns = {@JoinColumn(name = "purchase_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")})
    private List<Book> books = new ArrayList<>();

    private boolean closed;

    private LocalDate date;

    public Purchase() {
    }

    public boolean isClosed() {
        return closed;
    }

    public String getFormattedDate() {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }
}
