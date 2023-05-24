package com.example.mybookstore.repository;


import com.example.mybookstore.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    Comment getCommentById(int id);
}

