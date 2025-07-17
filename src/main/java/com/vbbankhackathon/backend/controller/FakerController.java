package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.service.FakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/faker")
@CrossOrigin(originPatterns = "*")
public class FakerController {
    
    @Autowired
    private FakerService fakerService;
    
    @PostMapping("/generate-all")
    public ResponseEntity<Map<String, Object>> generateAllFakeData() {
        try {
            Map<String, Object> result = fakerService.generateAllFakeData();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Fake data generated successfully");
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating fake data: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-branches")
    public ResponseEntity<Map<String, Object>> generateBranches() {
        try {
            var result = fakerService.generateBranches();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Fake branches generated successfully");
            response.put("count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating fake branches: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-customers")
    public ResponseEntity<Map<String, Object>> generateCustomers() {
        try {
            var result = fakerService.generateCustomers();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Fake customers generated successfully");
            response.put("count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating fake customers: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-employees")
    public ResponseEntity<Map<String, Object>> generateEmployees() {
        try {
            var result = fakerService.generateEmployees();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Fake employees generated successfully");
            response.put("count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating fake employees: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-accounts")
    public ResponseEntity<Map<String, Object>> generateAccounts() {
        try {
            var result = fakerService.generateAccounts();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Fake accounts generated successfully");
            response.put("count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating fake accounts: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-cards")
    public ResponseEntity<Map<String, Object>> generateCards() {
        try {
            var result = fakerService.generateCards();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Fake cards generated successfully");
            response.put("count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating fake cards: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-transactions")
    public ResponseEntity<Map<String, Object>> generateTransactions() {
        try {
            var result = fakerService.generateTransactions();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Fake transactions generated successfully");
            response.put("count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating fake transactions: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-loans")
    public ResponseEntity<Map<String, Object>> generateLoans() {
        try {
            var result = fakerService.generateLoans();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Fake loans generated successfully");
            response.put("count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating fake loans: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-stocks")
    public ResponseEntity<Map<String, Object>> generateStocks() {
        try {
            var result = fakerService.generateStocks();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Fake stocks generated successfully");
            response.put("count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating fake stocks: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-portfolios")
    public ResponseEntity<Map<String, Object>> generatePortfolios() {
        try {
            var result = fakerService.generatePortfolios();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Fake portfolios generated successfully");
            response.put("count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating fake portfolios: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @DeleteMapping("/clear-all")
    public ResponseEntity<Map<String, Object>> clearAllData() {
        try {
            fakerService.clearAllData();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "All fake data cleared successfully");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error clearing data: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getFakerInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("description", "Enhanced Faker API for VBBank Hackathon Backend with realistic data scaling");
        info.put("version", "3.0.0");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("POST /api/faker/generate-all", "Generate all fake data with default scale (~1M records)");
        endpoints.put("POST /api/faker/generate-small-scale", "Generate small scale data for development (~10K records)");
        endpoints.put("POST /api/faker/generate-medium-scale", "Generate medium scale data for staging (~100K records)");
        endpoints.put("POST /api/faker/generate-full-scale", "Generate full scale data for production testing (~1M records)");
        endpoints.put("POST /api/faker/generate-all-custom", "Generate all fake data with custom counts (use query parameters)");
        endpoints.put("POST /api/faker/generate-branches", "Generate fake branches (default: 50)");
        endpoints.put("POST /api/faker/generate-branches-custom", "Generate fake branches with custom count (?count=N)");
        endpoints.put("POST /api/faker/generate-customers", "Generate fake customers (default: 100K)");
        endpoints.put("POST /api/faker/generate-customers-custom", "Generate fake customers with custom count (?count=N)");
        endpoints.put("POST /api/faker/generate-employees", "Generate fake employees (default: 2K)");
        endpoints.put("POST /api/faker/generate-accounts", "Generate fake accounts (default: 150K)");
        endpoints.put("POST /api/faker/generate-accounts-custom", "Generate fake accounts with custom count (?count=N)");
        endpoints.put("POST /api/faker/generate-cards", "Generate fake cards (default: 120K)");
        endpoints.put("POST /api/faker/generate-transactions", "Generate fake transactions (default: 500K)");
        endpoints.put("POST /api/faker/generate-transactions-custom", "Generate fake transactions with custom count (?count=N)");
        endpoints.put("POST /api/faker/generate-loans", "Generate fake loans (default: 25K)");
        endpoints.put("POST /api/faker/generate-stocks", "Generate fake stocks (default: 1K)");
        endpoints.put("POST /api/faker/generate-portfolios", "Generate fake portfolios (default: 8K)");
        endpoints.put("DELETE /api/faker/clear-all", "Clear all data");
        endpoints.put("GET /api/faker/info", "Get faker info");
        
        info.put("available_endpoints", endpoints);
        
        // Data scale information
        Map<String, Object> scaleInfo = new HashMap<>();
        
        Map<String, Object> smallScale = new HashMap<>();
        smallScale.put("description", "Development and testing scale");
        smallScale.put("total_records", "~10,000");
        smallScale.put("customers", "1,000");
        smallScale.put("accounts", "1,500");
        smallScale.put("transactions", "5,000");
        smallScale.put("use_case", "Local development, unit testing, demo");
        
        Map<String, Object> mediumScale = new HashMap<>();
        mediumScale.put("description", "Staging and integration testing scale");
        mediumScale.put("total_records", "~100,000");
        mediumScale.put("customers", "10,000");
        mediumScale.put("accounts", "15,000");
        mediumScale.put("transactions", "50,000");
        mediumScale.put("use_case", "Staging environment, performance testing, integration testing");
        
        Map<String, Object> fullScale = new HashMap<>();
        fullScale.put("description", "Production-like scale for realistic testing");
        fullScale.put("total_records", FakerService.getTotalDefaultRecordCount());
        fullScale.put("customers", "100,000");
        fullScale.put("accounts", "150,000");
        fullScale.put("transactions", "500,000");
        fullScale.put("use_case", "Load testing, stress testing, production simulation");
        
        scaleInfo.put("small", smallScale);
        scaleInfo.put("medium", mediumScale);
        scaleInfo.put("full", fullScale);
        info.put("data_scales", scaleInfo);
        
        // Realistic ratios explanation
        Map<String, String> ratios = new HashMap<>();
        ratios.put("accounts_per_customer", "1.5 (average customer has 1-2 accounts)");
        ratios.put("cards_per_account", "0.8 (not all accounts have cards)");
        ratios.put("loans_per_customer", "0.25 (25% of customers have loans)");
        ratios.put("transactions_per_account", "3.3 (realistic transaction volume)");
        ratios.put("online_banking_adoption", "75% (3/4 customers use online banking)");
        ratios.put("ewallet_adoption", "60% (modern digital payment usage)");
        ratios.put("investment_adoption", "8% (small percentage actively invest)");
        ratios.put("employees_per_branch", "40 (realistic staffing)");
        info.put("realistic_ratios", ratios);
        
        Map<String, String> examples = new HashMap<>();
        examples.put("Development setup", "POST /api/faker/generate-small-scale");
        examples.put("Staging environment", "POST /api/faker/generate-medium-scale");
        examples.put("Load testing", "POST /api/faker/generate-full-scale");
        examples.put("Custom small test", "POST /api/faker/generate-customers-custom?count=100");
        examples.put("Custom transaction load", "POST /api/faker/generate-transactions-custom?count=10000");
        
        info.put("usage_examples", examples);
        
        Map<String, String> recommendations = new HashMap<>();
        recommendations.put("Development", "Use small scale (10K records) for fast iteration");
        recommendations.put("CI/CD Pipeline", "Use small scale for automated testing");
        recommendations.put("Staging Tests", "Use medium scale (100K records) for integration testing");
        recommendations.put("Performance Testing", "Use full scale (1M records) for realistic load testing");
        recommendations.put("Demo/Presentation", "Use small or medium scale for quick setup");
        
        info.put("recommendations", recommendations);
        
        info.put("note", "All scales maintain realistic proportional relationships between entities. Foreign key references are properly maintained. Generate in dependency order (branches → customers → employees → accounts → etc.)");
        
        return ResponseEntity.ok(info);
    }
    
    @PostMapping("/generate-all-custom")
    public ResponseEntity<Map<String, Object>> generateAllFakeDataCustom(
            @RequestParam(defaultValue = "10") int branchCount,
            @RequestParam(defaultValue = "10") int customerCount,
            @RequestParam(defaultValue = "10") int employeeCount,
            @RequestParam(defaultValue = "10") int accountCount,
            @RequestParam(defaultValue = "10") int cardCount,
            @RequestParam(defaultValue = "10") int transactionCount,
            @RequestParam(defaultValue = "10") int loanCount,
            @RequestParam(defaultValue = "10") int collateralCount,
            @RequestParam(defaultValue = "10") int debtCollectionCount,
            @RequestParam(defaultValue = "10") int creditScoreCount,
            @RequestParam(defaultValue = "10") int riskAssessmentCount,
            @RequestParam(defaultValue = "10") int kycCount,
            @RequestParam(defaultValue = "10") int insurancePolicyCount,
            @RequestParam(defaultValue = "10") int onlineBankingCount,
            @RequestParam(defaultValue = "10") int amlCheckCount,
            @RequestParam(defaultValue = "10") int stockCount,
            @RequestParam(defaultValue = "10") int portfolioCount,
            @RequestParam(defaultValue = "10") int eWalletCount,
            @RequestParam(defaultValue = "10") int exchangeRateCount,
            @RequestParam(defaultValue = "10") int promotionCount,
            @RequestParam(defaultValue = "10") int businessCount,
            @RequestParam(defaultValue = "10") int atmCount,
            @RequestParam(defaultValue = "10") int interbankTransferCount) {
        try {
            Map<String, Object> result = fakerService.generateAllFakeData(
                branchCount, customerCount, employeeCount,
                accountCount, cardCount, transactionCount,
                loanCount, collateralCount, debtCollectionCount,
                creditScoreCount, riskAssessmentCount, kycCount,
                insurancePolicyCount, onlineBankingCount, amlCheckCount,
                stockCount, portfolioCount, eWalletCount,
                exchangeRateCount, promotionCount, businessCount,
                atmCount, interbankTransferCount
            );
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Custom fake data generated successfully");
            
            Map<String, Integer> counts = new HashMap<>();
            counts.put("branches", branchCount);
            counts.put("customers", customerCount);
            counts.put("employees", employeeCount);
            counts.put("accounts", accountCount);
            counts.put("cards", cardCount);
            counts.put("transactions", transactionCount);
            counts.put("loans", loanCount);
            counts.put("collaterals", collateralCount);
            counts.put("debtCollections", debtCollectionCount);
            counts.put("creditScores", creditScoreCount);
            counts.put("riskAssessments", riskAssessmentCount);
            counts.put("kyc", kycCount);
            counts.put("insurancePolicies", insurancePolicyCount);
            counts.put("onlineBanking", onlineBankingCount);
            counts.put("amlChecks", amlCheckCount);
            counts.put("stocks", stockCount);
            counts.put("portfolios", portfolioCount);
            counts.put("eWallets", eWalletCount);
            counts.put("exchangeRates", exchangeRateCount);
            counts.put("promotions", promotionCount);
            counts.put("businesses", businessCount);
            counts.put("atms", atmCount);
            counts.put("interbankTransfers", interbankTransferCount);
            
            response.put("counts", counts);
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating custom fake data: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-branches-custom")
    public ResponseEntity<Map<String, Object>> generateBranchesCustom(@RequestParam(defaultValue = "10") int count) {
        try {
            var result = fakerService.generateBranches(count);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Custom fake branches generated successfully");
            response.put("requested_count", count);
            response.put("actual_count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating custom fake branches: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-customers-custom")
    public ResponseEntity<Map<String, Object>> generateCustomersCustom(@RequestParam(defaultValue = "10") int count) {
        try {
            var result = fakerService.generateCustomers(count);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Custom fake customers generated successfully");
            response.put("requested_count", count);
            response.put("actual_count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating custom fake customers: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-accounts-custom")
    public ResponseEntity<Map<String, Object>> generateAccountsCustom(@RequestParam(defaultValue = "10") int count) {
        try {
            var result = fakerService.generateAccounts(count);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Custom fake accounts generated successfully");
            response.put("requested_count", count);
            response.put("actual_count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating custom fake accounts: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-transactions-custom")
    public ResponseEntity<Map<String, Object>> generateTransactionsCustom(@RequestParam(defaultValue = "10") int count) {
        try {
            var result = fakerService.generateTransactions(count);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Custom fake transactions generated successfully");
            response.put("requested_count", count);
            response.put("actual_count", result.size());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating custom fake transactions: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-small-scale")
    public ResponseEntity<Map<String, Object>> generateSmallScale() {
        try {
            Map<String, Object> result = fakerService.generateAllFakeDataSmallScale();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Small scale fake data generated successfully (~10K records)");
            response.put("scale", "SMALL");
            response.put("estimated_total_records", "~10,000");
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating small scale fake data: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-medium-scale")
    public ResponseEntity<Map<String, Object>> generateMediumScale() {
        try {
            Map<String, Object> result = fakerService.generateAllFakeDataMediumScale();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Medium scale fake data generated successfully (~100K records)");
            response.put("scale", "MEDIUM");
            response.put("estimated_total_records", "~100,000");
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating medium scale fake data: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @PostMapping("/generate-full-scale")
    public ResponseEntity<Map<String, Object>> generateFullScale() {
        try {
            Map<String, Object> result = fakerService.generateAllFakeData();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Full scale fake data generated successfully (~1M records)");
            response.put("scale", "FULL");
            response.put("estimated_total_records", "~1,000,000");
            response.put("actual_total_records", FakerService.getTotalDefaultRecordCount());
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error generating full scale fake data: " + e.getMessage());
            
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
}
