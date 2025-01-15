package com.example.socialntw.repository;


import com.example.socialntw.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
    List<Post> findByTitleContainingOrContentContaining(@Param("keyword") String keyword);

    @Query("SELECT p FROM Post p WHERE p.area.areaId = :id")
    List<Post> findByAreaId(@Param("id")Integer id);

    @Query("SELECT p FROM Post p WHERE p.user.Id = :id")
    List<Post>findByUserId(@Param("id")Integer id);
}
