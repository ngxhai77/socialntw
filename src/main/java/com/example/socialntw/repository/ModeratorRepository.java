package com.example.socialntw.repository;

import com.example.socialntw.entity.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {

    @Query("SELECT m FROM Moderator m WHERE m.area.areaId = :areaId")
    List<Moderator> findAllByAreaId(@Param("areaId") Integer areaId);

}