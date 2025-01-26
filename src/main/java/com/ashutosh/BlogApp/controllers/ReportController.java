package com.ashutosh.BlogApp.controllers;

import com.ashutosh.BlogApp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report")
    public String generateReport(Model model) {
        Map<String, Long> frequentWords = reportService.getTopFrequentWords();

        model.addAttribute("report", frequentWords); // Change to "report" to match your template

        return "report"; // Return the report template
    }
}