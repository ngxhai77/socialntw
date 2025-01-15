package com.example.socialntw.service;

import com.example.socialntw.entity.Report;

import java.util.List;

public interface ReportService {
    Report createReport(Report report);
    List<Report> getAllReports();
    void resolveReport(Integer reportId);
    List<Report> getAllReportsByArea(Integer areaId);
}