package com.example.socialntw.repository;

import com.example.socialntw.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {



    @Query("SELECT r FROM Report r WHERE r.area.areaId= :areaId")
    List<Report> findAllByAreaId(Integer areaId);
}