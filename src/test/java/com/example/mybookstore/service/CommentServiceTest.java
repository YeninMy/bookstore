package com.example.mybookstore.service;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Comment;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.repository.CommentRepo;
import com.example.mybookstore.servise.CommentService;
import com.example.mybookstore.servise.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class CommentServiceTest {
        @Mock
        CommentRepo commentRepo;

        @Mock
        PersonService personService;

        @InjectMocks
        CommentService commentService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void shouldSaveComment() {
            Person person = new Person();
            person.setId(1);
            Book book = new Book();
            String commentText = "Great book!";

            Person mockPerson = new Person();
            mockPerson.setId(1);

            when(personService.getPersonById(person.getId())).thenReturn(mockPerson);

            commentService.saveComment(person, book, commentText);

            ArgumentCaptor<Comment> commentCaptor = ArgumentCaptor.forClass(Comment.class);
            verify(commentRepo, times(1)).save(commentCaptor.capture());

            Comment providedComment = commentCaptor.getValue();
            Assertions.assertEquals(mockPerson, providedComment.getPerson());
            Assertions.assertEquals(book, providedComment.getBook());
            Assertions.assertEquals(commentText, providedComment.getText());
        }

        @Test
        void shouldGetCommentById() {
            int commentId = 1;
            Comment expectedComment = new Comment();

            when(commentRepo.getCommentById(commentId)).thenReturn(expectedComment);

            Comment resultComment = commentService.getCommentById(commentId);

            verify(commentRepo, times(1)).getCommentById(commentId);
            Assertions.assertEquals(expectedComment, resultComment);
        }

        @Test
        void shouldDeleteComment() {
            Comment comment = new Comment();

            commentService.deleteComment(comment);

            verify(commentRepo, times(1)).delete(comment);
        }
    }
