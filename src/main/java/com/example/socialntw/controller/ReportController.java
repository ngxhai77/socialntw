package com.example.socialntw.controller;

import com.example.socialntw.entity.Report;
import com.example.socialntw.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        Report savedReport = reportService.createReport(report);
        return ResponseEntity.ok(savedReport);
    }

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<Void> resolveReport(@PathVariable Integer id) {
        reportService.resolveReport(id);
        return ResponseEntity.ok().build();
    }
}