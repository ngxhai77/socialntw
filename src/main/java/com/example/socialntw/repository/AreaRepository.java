package com.example.socialntw.repository;

import com.example.socialntw.entity.Area;
import com.example.socialntw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AreaRepository  extends JpaRepository<Area, Integer> {
    Optional<Area> findByName(String name);
    Optional<Area> findById(Integer id);
    boolean existsByName(String name);
    boolean existsById(Integer id);
    List<Area> findAll();
    void deleteById(Integer id);
    void deleteByName(String name);
    void deleteAll();

    @Query("SELECT a FROM Area a WHERE a.createdBy = :userId")
    List<Area> findByCreatedBy(@Param("userId") Integer userId);
}
