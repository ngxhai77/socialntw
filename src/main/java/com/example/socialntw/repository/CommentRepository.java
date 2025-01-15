package com.example.socialntw.repository;


import com.example.socialntw.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository  extends JpaRepository < Comment , Integer>{



}
