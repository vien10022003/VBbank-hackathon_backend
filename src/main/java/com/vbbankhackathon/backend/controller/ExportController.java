package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.util.DatabaseExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    @Autowired
    private DatabaseExporter databaseExporter;

    @GetMapping("/core-data")
    public ResponseEntity<String> exportCoreData() {
        try {
            databaseExporter.exportCoreBankingData();
            return ResponseEntity.ok("✅ Core banking data exported successfully to csv_exports/core_banking_data.csv");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("❌ Error exporting data: " + e.getMessage());
        }
    }
    
    @GetMapping("/core-data-sample")
    public ResponseEntity<String> exportCoreDataSample() {
        try {
            databaseExporter.exportSampleBankingData(100); // Export only 100 records for testing
            return ResponseEntity.ok("✅ Sample banking data (100 records) exported successfully to csv_exports/sample_banking_data.csv");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("❌ Error exporting sample data: " + e.getMessage());
        }
    }
}
