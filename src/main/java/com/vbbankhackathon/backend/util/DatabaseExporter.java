package com.vbbankhackathon.backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;

//@Component  // Uncomment this to enable automatic export on startup
@Component  // Enable for Spring to manage this bean
public class DatabaseExporter implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Auto export is disabled. Use /api/export/core-data endpoint instead
        // exportCoreBankingData();
    }

    public void exportCoreBankingData() {
        String sql = """
            SELECT 
                -- Customer Information
                c.customerid,
                c.first_name,
                c.last_name,
                c.date_of_birth,
                c.address,
                c.phone_number,
                c.email,
                c.ssn,
                c.registration_date,
                c.status as customer_status,
                c.gender,
                c.marital_status,
                c.occupation,
                c.income_level,
                c.education_level,
                c.nationality,
                c.preferred_language,
                
                -- Account Information
                a.accountid,
                a.account_type,
                a.balance,
                a.opening_date,
                a.status as account_status,
                a.interest_rate,
                a.credit_limit,
                a.available_credit,
                a.overdraft_limit,
                a.minimum_balance,
                
                -- Transaction Information
                t.transactionid,
                t.transaction_type,
                t.amount,
                t.transaction_date,
                t.description,
                t.transaction_method,
                t.currency,
                t.exchange_rate,
                
                -- Card Information (if exists)
                card.cardid,
                card.card_number,
                card.expiration_date,
                card.cvv,
                card.card_type,
                card.status as card_status,
                
                -- Loan Information (if exists)
                l.loanid,
                l.loan_amount,
                l.interest_rate as loan_interest_rate,
                l.term,
                l.start_date,
                l.end_date,
                l.status as loan_status,
                l.monthly_payment,
                l.loan_purpose,
                l.repayment_schedule

            FROM customers c
            LEFT JOIN accounts a ON c.customerid = a.customerid
            LEFT JOIN transactions t ON a.accountid = t.accountid
            LEFT JOIN cards card ON a.accountid = card.accountid
            LEFT JOIN loans l ON c.customerid = l.customerid

            ORDER BY c.customerid, a.accountid, t.transaction_date DESC
            """;

        try {
            // First, get count for progress tracking
            String countSql = "SELECT COUNT(*) FROM customers c LEFT JOIN accounts a ON c.customerid = a.customerid";
            Integer totalRecords = jdbcTemplate.queryForObject(countSql, Integer.class);
            System.out.println("📊 Total records to export: " + totalRecords);
            
            // Create csv_exports directory if it doesn't exist
            java.io.File directory = new java.io.File("csv_exports");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            // Write to CSV file with streaming approach
            try (FileWriter writer = new FileWriter("csv_exports/core_banking_data.csv")) {
                // Write header first
                String header = "customerid,first_name,last_name,date_of_birth,address,phone_number,email,ssn,registration_date,customer_status,gender,marital_status,occupation,income_level,education_level,nationality,preferred_language,accountid,account_type,balance,opening_date,account_status,interest_rate,credit_limit,available_credit,overdraft_limit,minimum_balance,transactionid,transaction_type,amount,transaction_date,description,transaction_method,currency,exchange_rate,cardid,card_number,expiration_date,cvv,card_type,card_status,loanid,loan_amount,loan_interest_rate,term,start_date,end_date,loan_status,monthly_payment,loan_purpose,repayment_schedule";
                writer.write(header + "\n");
                
                // Process data in batches to avoid memory issues
                int batchSize = 500; // Reduced batch size
                int offset = 0;
                int totalExported = 0;
                
                String batchSql = sql + " LIMIT ? OFFSET ?";
                
                while (true) {
                    System.out.println("🔄 Processing batch: " + (offset / batchSize + 1) + " (records " + offset + " to " + (offset + batchSize) + ")");
                    
                    List<Map<String, Object>> batch = jdbcTemplate.queryForList(batchSql, batchSize, offset);
                    
                    if (batch.isEmpty()) {
                        break; // No more data
                    }
                    
                    // Write batch data
                    for (Map<String, Object> row : batch) {
                        String line = row.values().stream()
                            .map(value -> {
                                if (value == null) return "";
                                String str = value.toString();
                                // Escape quotes and wrap in quotes if contains comma or quotes
                                if (str.contains(",") || str.contains("\"") || str.contains("\n")) {
                                    str = "\"" + str.replace("\"", "\"\"") + "\"";
                                }
                                return str;
                            })
                            .reduce((a, b) -> a + "," + b)
                            .orElse("");
                        writer.write(line + "\n");
                        totalExported++;
                    }
                    
                    writer.flush(); // Flush to disk periodically
                    offset += batchSize;
                    
                    // Force garbage collection every 5 batches
                    if (offset % (batchSize * 5) == 0) {
                        System.gc();
                        Thread.sleep(100); // Small pause to help GC
                    }
                }
                
                System.out.println("✅ Export completed successfully!");
                System.out.println("📁 File saved: csv_exports/core_banking_data.csv");
                System.out.println("📊 Total records exported: " + totalExported);
                
            }
        } catch (Exception e) {
            System.err.println("❌ Error exporting data: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Export failed: " + e.getMessage(), e);
        }
    }

    public void exportSampleBankingData(int limit) {
        String sql = """
            SELECT 
                c.customerid,
                c.first_name,
                c.last_name,
                c.date_of_birth,
                c.address,
                c.phone_number,
                c.email,
                c.ssn,
                c.registration_date,
                c.status as customer_status,
                c.gender,
                c.marital_status,
                c.occupation,
                c.income_level,
                c.education_level,
                c.nationality,
                c.preferred_language,
                a.accountid,
                a.account_type,
                a.balance,
                a.opening_date,
                a.status as account_status,
                a.interest_rate,
                a.credit_limit,
                a.available_credit,
                a.overdraft_limit,
                a.minimum_balance,
                t.transactionid,
                t.transaction_type,
                t.amount,
                t.transaction_date,
                t.description,
                t.transaction_method,
                t.currency,
                t.exchange_rate,
                card.cardid,
                card.card_number,
                card.expiration_date,
                card.cvv,
                card.card_type,
                card.status as card_status,
                l.loanid,
                l.loan_amount,
                l.interest_rate as loan_interest_rate,
                l.term,
                l.start_date,
                l.end_date,
                l.status as loan_status,
                l.monthly_payment,
                l.loan_purpose,
                l.repayment_schedule
            FROM customers c
            LEFT JOIN accounts a ON c.customerid = a.customerid
            LEFT JOIN transactions t ON a.accountid = t.accountid
            LEFT JOIN cards card ON a.accountid = card.accountid
            LEFT JOIN loans l ON c.customerid = l.customerid
            ORDER BY c.customerid, a.accountid, t.transaction_date DESC
            LIMIT ?
            """;

        try {
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, limit);
            
            // Create csv_exports directory if it doesn't exist
            java.io.File directory = new java.io.File("csv_exports");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            // Write to CSV file
            try (FileWriter writer = new FileWriter("csv_exports/sample_banking_data.csv")) {
                // Write header
                String header = "customerid,first_name,last_name,date_of_birth,address,phone_number,email,ssn,registration_date,customer_status,gender,marital_status,occupation,income_level,education_level,nationality,preferred_language,accountid,account_type,balance,opening_date,account_status,interest_rate,credit_limit,available_credit,overdraft_limit,minimum_balance,transactionid,transaction_type,amount,transaction_date,description,transaction_method,currency,exchange_rate,cardid,card_number,expiration_date,cvv,card_type,card_status,loanid,loan_amount,loan_interest_rate,term,start_date,end_date,loan_status,monthly_payment,loan_purpose,repayment_schedule";
                writer.write(header + "\n");
                
                // Write data rows
                for (Map<String, Object> row : results) {
                    String line = row.values().stream()
                        .map(value -> {
                            if (value == null) return "";
                            String str = value.toString();
                            // Escape quotes and wrap in quotes if contains comma or quotes
                            if (str.contains(",") || str.contains("\"") || str.contains("\n")) {
                                str = "\"" + str.replace("\"", "\"\"") + "\"";
                            }
                            return str;
                        })
                        .reduce((a, b) -> a + "," + b)
                        .orElse("");
                    writer.write(line + "\n");
                }
                
                System.out.println("✅ Sample export completed successfully!");
                System.out.println("📁 File saved: csv_exports/sample_banking_data.csv");
                System.out.println("📊 Total records exported: " + results.size());
                
            }
        } catch (Exception e) {
            System.err.println("❌ Error exporting sample data: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Sample export failed: " + e.getMessage(), e);
        }
    }

}
