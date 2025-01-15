package com.example.socialntw.service.implement;

import com.example.socialntw.entity.Report;
import com.example.socialntw.repository.ReportRepository;
import com.example.socialntw.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report createReport(Report report) {
        report.setIsResolved(false);
        return reportRepository.save(report);
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public void resolveReport(Integer reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        report.setIsResolved(true);
        reportRepository.save(report);
    }

    @Override
    public List<Report> getAllReportsByArea( Integer areaId) {
        return reportRepository.findAllByAreaId(areaId);
    }
}