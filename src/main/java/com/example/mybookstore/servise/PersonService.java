package com.example.mybookstore.servise;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Optional<Person> getPersonByEmail(final String email){
        return personRepo.findAllByEmail(email);
    }

    public void savePerson(final Person person){
        personRepo.save(person);
    }

}
