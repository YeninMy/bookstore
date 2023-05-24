package com.example.mybookstore.servise;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Comment;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
private final PersonService personService;
    private final CommentRepo commentRepo;
@Autowired
    public CommentService(PersonService personService, CommentRepo commentRepo) {
    this.personService = personService;
    this.commentRepo = commentRepo;
    }

    public void saveComment(Person person, Book book, String commentText) {
    Person user = personService.getPersonById(person.getId());

        Comment comment = new Comment();
        comment.setPerson(user);
        comment.setBook(book);
        comment.setText(commentText);

        commentRepo.save(comment);
    }
    public Comment getCommentById(int id){
        return commentRepo.getCommentById(id);
    }
    public void deleteComment(Comment comment){
    commentRepo.delete(comment);
    }

}
