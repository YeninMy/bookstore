package com.example.mybookstore.servise;


import com.example.mybookstore.entity.Person;
import com.example.mybookstore.entity.Role;
import com.example.mybookstore.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Service
public class PersonService implements UserDetailsService {
    private final PersonRepo personRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired

    public PersonService(PersonRepo personRepo, PasswordEncoder passwordEncoder) {
        this.personRepo = personRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Person> getPersonByEmail(final String email) {
        return personRepo.findAllByEmail(email);
    }

    public void savePerson(final Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));

        person.setRoles(Collections.singleton(Role.USER));

        Person savedPerson = personRepo.save(person);

        System.out.println("Saved person: " + savedPerson); // Добавьте эту строку для логирования
    }

    public void setAdminRoleToPerson(final Person person) {
        person.setRoles(Collections.singleton(Role.ADMIN));
        personRepo.save(person);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepo.findByUsername(username);
        System.out.println("Loading user by username: " + username); // Добавьте эту строку для логирования
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
