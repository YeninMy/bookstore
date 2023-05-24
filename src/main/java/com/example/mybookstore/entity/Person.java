package com.example.mybookstore.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Getter
@Setter
@Entity
public class Person
        implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Username can't be empty")
    private String username;
    @Email
    @NotBlank(message = "Email can't be empty")
    private String email;
    @NotBlank(message = "Password can't be empty")
    private String password;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Wishlist wishlist;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Comment> comments;
    int booksChosen;
    int booksWanted;
    @OneToMany(mappedBy = "person")
    private List<Rating> ratings = new ArrayList<>();

    public Person() {
    }

    public Person(String username, String email, String password, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;

    }

    public String chosenBooks() {
        if (booksChosen <= 0) {
            return "";
        }
        return String.valueOf(booksChosen);
    }
    public String wantedBooks() {
        if (booksWanted <= 0) {
            return "";
        }
        return String.valueOf(booksWanted);
    }
    public boolean isAdmin() {
        if (this.roles.contains(Role.ADMIN)) {
            return true;
        }
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
