package com.vbbankhackathon.backend.service;

import com.github.javafaker.Faker;
import com.vbbankhackathon.backend.entity.*;
import com.vbbankhackathon.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class FakerService {
    
    private final Faker faker = new Faker(Locale.forLanguageTag("vi"));
    
    /*
     * FIX: Sửa lỗi primary key bị fix cứng từ 1
     * 
     * Trước đây: Tất cả ID đều bắt đầu từ 1, gây conflict nếu database đã có dữ liệu
     * Bây giờ: Kiểm tra ID cao nhất hiện tại và tiếp tục từ đó để tránh duplicate key
     * 
     * Lợi ích:
     * - Không bị lỗi primary key constraint khi database đã có dữ liệu
     * - Có thể gọi multiple lần mà không bị conflict
     * - Dữ liệu được tạo append vào thay vì replace
     */
    
    // Default counts for each entity type - Realistic scale for ~1M total records
    // Core entities (foundation)
    private static final int DEFAULT_BRANCH_COUNT = 50;           // 50 branches nationwide
    private static final int DEFAULT_BUSINESS_COUNT = 200;        // 200 business partners
    private static final int DEFAULT_ATM_COUNT = 500;             // 500 ATMs across branches
    
    // Human entities
    private static final int DEFAULT_CUSTOMER_COUNT = 100000;     // 100K customers (main users)
    private static final int DEFAULT_EMPLOYEE_COUNT = 2000;       // 2K employees (40 per branch avg)
    
    // Financial core entities
    private static final int DEFAULT_ACCOUNT_COUNT = 150000;      // 150K accounts (1.5 per customer avg)
    private static final int DEFAULT_CARD_COUNT = 120000;         // 120K cards (0.8 per account avg)
    private static final int DEFAULT_LOAN_COUNT = 25000;          // 25K loans (25% of customers)
    
    // High-volume transactional data
    private static final int DEFAULT_TRANSACTION_COUNT = 500000;  // 500K transactions (major volume)
    private static final int DEFAULT_EXCHANGE_RATE_COUNT = 365;   // 365 daily rates for 10 currencies
    
    // Loan-related entities
    private static final int DEFAULT_COLLATERAL_COUNT = 20000;    // 20K collaterals (80% of loans)
    private static final int DEFAULT_DEBT_COLLECTION_COUNT = 2500; // 2.5K debt collections (10% of loans)
    
    // Customer service entities
    private static final int DEFAULT_CREDIT_SCORE_COUNT = 80000;  // 80K credit scores (80% of customers)
    private static final int DEFAULT_RISK_ASSESSMENT_COUNT = 15000; // 15K risk assessments (15% of customers)
    private static final int DEFAULT_KYC_COUNT = 95000;           // 95K KYC records (95% of customers)
    private static final int DEFAULT_INSURANCE_POLICY_COUNT = 30000; // 30K insurance policies (30% of customers)
    private static final int DEFAULT_ONLINE_BANKING_COUNT = 75000; // 75K online banking (75% of customers)
    
    // Compliance and monitoring
    private static final int DEFAULT_AML_CHECK_COUNT = 50000;     // 50K AML checks (10% of transactions)
    
    // Investment entities
    private static final int DEFAULT_STOCK_COUNT = 1000;          // 1K stocks available
    private static final int DEFAULT_PORTFOLIO_COUNT = 8000;      // 8K portfolios (8% of customers invest)
    
    // Digital services
    private static final int DEFAULT_EWALLET_COUNT = 60000;       // 60K e-wallets (60% of customers)
    private static final int DEFAULT_PROMOTION_COUNT = 100;       // 100 promotions/campaigns
    
    // Transfer services  
    private static final int DEFAULT_INTERBANK_TRANSFER_COUNT = 40000; // 40K interbank transfers
    
    @Autowired private CustomerRepository customerRepository;
    @Autowired private AccountRepository accountRepository;
    @Autowired private BranchRepository branchRepository;
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private CardRepository cardRepository;
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private LoanRepository loanRepository;
    @Autowired private CollateralRepository collateralRepository;
    @Autowired private DebtCollectionRepository debtCollectionRepository;
    @Autowired private CreditScoreRepository creditScoreRepository;
    @Autowired private RiskAssessmentRepository riskAssessmentRepository;
    @Autowired private KYCRepository kycRepository;
    @Autowired private InsurancePolicyRepository insurancePolicyRepository;
    @Autowired private OnlineBankingRepository onlineBankingRepository;
    @Autowired private AMLCheckRepository amlCheckRepository;
    @Autowired private StockRepository stockRepository;
    @Autowired private PortfolioRepository portfolioRepository;
    @Autowired private EWalletRepository eWalletRepository;
    @Autowired private ExchangeRateRepository exchangeRateRepository;
    @Autowired private PromotionRepository promotionRepository;
    @Autowired private BusinessRepository businessRepository;
    @Autowired private ATMRepository atmRepository;
    @Autowired private InterbankTransferRepository interbankTransferRepository;
    
    public Map<String, Object> generateAllFakeData() {
        return generateAllFakeData(
            DEFAULT_BRANCH_COUNT, DEFAULT_CUSTOMER_COUNT, DEFAULT_EMPLOYEE_COUNT,
            DEFAULT_ACCOUNT_COUNT, DEFAULT_CARD_COUNT, DEFAULT_TRANSACTION_COUNT,
            DEFAULT_LOAN_COUNT, DEFAULT_COLLATERAL_COUNT, DEFAULT_DEBT_COLLECTION_COUNT,
            DEFAULT_CREDIT_SCORE_COUNT, DEFAULT_RISK_ASSESSMENT_COUNT, DEFAULT_KYC_COUNT,
            DEFAULT_INSURANCE_POLICY_COUNT, DEFAULT_ONLINE_BANKING_COUNT, DEFAULT_AML_CHECK_COUNT,
            DEFAULT_STOCK_COUNT, DEFAULT_PORTFOLIO_COUNT, DEFAULT_EWALLET_COUNT,
            DEFAULT_EXCHANGE_RATE_COUNT, DEFAULT_PROMOTION_COUNT, DEFAULT_BUSINESS_COUNT,
            DEFAULT_ATM_COUNT, DEFAULT_INTERBANK_TRANSFER_COUNT
        );
    }
    
    public Map<String, Object> generateAllFakeData(
            int branchCount, int customerCount, int employeeCount,
            int accountCount, int cardCount, int transactionCount,
            int loanCount, int collateralCount, int debtCollectionCount,
            int creditScoreCount, int riskAssessmentCount, int kycCount,
            int insurancePolicyCount, int onlineBankingCount, int amlCheckCount,
            int stockCount, int portfolioCount, int eWalletCount,
            int exchangeRateCount, int promotionCount, int businessCount,
            int atmCount, int interbankTransferCount) {
        
        Map<String, Object> result = new HashMap<>();
        
        // Tạo dữ liệu theo thứ tự phụ thuộc
        result.put("branches", generateBranches(branchCount));
        result.put("customers", generateCustomers(customerCount));
        result.put("employees", generateEmployees(employeeCount));
        result.put("accounts", generateAccounts(accountCount));
        result.put("cards", generateCards(cardCount));
        result.put("transactions", generateTransactions(transactionCount));
        result.put("loans", generateLoans(loanCount));
        result.put("collaterals", generateCollaterals(collateralCount));
        result.put("debtCollections", generateDebtCollections(debtCollectionCount));
        result.put("creditScores", generateCreditScores(creditScoreCount));
        result.put("riskAssessments", generateRiskAssessments(riskAssessmentCount));
        result.put("kyc", generateKYC(kycCount));
        result.put("insurancePolicies", generateInsurancePolicies(insurancePolicyCount));
        result.put("onlineBanking", generateOnlineBanking(onlineBankingCount));
        result.put("amlChecks", generateAMLChecks(amlCheckCount));
        result.put("stocks", generateStocks(stockCount));
        result.put("portfolios", generatePortfolios(portfolioCount));
        result.put("eWallets", generateEWallets(eWalletCount));
        result.put("exchangeRates", generateExchangeRates(exchangeRateCount));
        result.put("promotions", generatePromotions(promotionCount));
        result.put("businesses", generateBusinesses(businessCount));
        result.put("atms", generateATMs(atmCount));
        result.put("interbankTransfers", generateInterbankTransfers(interbankTransferCount));
        
        return result;
    }
    
    public List<Branch> generateBranches() {
        return generateBranches(DEFAULT_BRANCH_COUNT);
    }
    
    public List<Branch> generateBranches(int count) {
        List<Branch> branches = new ArrayList<>();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = getMaxId(branchRepository.findAll(), Branch::getBranchId);
        
        for (int i = 1; i <= count; i++) {
            Branch branch = new Branch();
            branch.setBranchId(maxId + i);
            branch.setAddress(faker.address().fullAddress());
            branch.setPhoneNumber(faker.phoneNumber().phoneNumber());
            // ManagerID sẽ được set sau khi tạo employees
            
            branches.add(branch);
        }
        
        return branchRepository.saveAll(branches);
    }
    
    public List<Customer> generateCustomers() {
        return generateCustomers(DEFAULT_CUSTOMER_COUNT);
    }
    
    public List<Customer> generateCustomers(int count) {
        List<Customer> customers = new ArrayList<>();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = getMaxId(customerRepository.findAll(), Customer::getCustomerId);
        
        for (int i = 1; i <= count; i++) {
            Customer customer = new Customer();
            customer.setCustomerId(maxId + i);
            customer.setFirstName(faker.name().firstName());
            customer.setLastName(faker.name().lastName());
            customer.setDateOfBirth(faker.date().birthday().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate());
            customer.setAddress(faker.address().fullAddress());
            customer.setPhoneNumber(faker.phoneNumber().phoneNumber());
            customer.setEmail(faker.internet().emailAddress());
            customer.setSsn(faker.idNumber().ssnValid());
            customer.setRegistrationDate(LocalDate.now().minusDays(faker.number().numberBetween(1, 365)));
            customer.setStatus(faker.options().option("Active", "Inactive", "Suspended"));
            customer.setGender(faker.options().option("Male", "Female", "Other"));
            customer.setMaritalStatus(faker.options().option("Single", "Married", "Divorced", "Widowed"));
            customer.setOccupation(faker.job().title());
            customer.setIncomeLevel(BigDecimal.valueOf(faker.number().numberBetween(1000000, 50000000))
                .setScale(2, RoundingMode.HALF_UP));
            customer.setEducationLevel(faker.options().option("High School", "Bachelor", "Master", "PhD"));
            customer.setNationality("Vietnamese");
            customer.setPreferredLanguage("Vietnamese");
            
            customers.add(customer);
        }
        
        return customerRepository.saveAll(customers);
    }
    
    public List<Employee> generateEmployees() {
        return generateEmployees(DEFAULT_EMPLOYEE_COUNT);
    }
    
    public List<Employee> generateEmployees(int count) {
        List<Employee> employees = new ArrayList<>();
        List<Integer> branchIds = getBranchIds();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = getMaxId(employeeRepository.findAll(), Employee::getEmployeeId);
        
        for (int i = 1; i <= count; i++) {
            Employee employee = new Employee();
            employee.setEmployeeId(maxId + i);
            employee.setFirstName(faker.name().firstName());
            employee.setLastName(faker.name().lastName());
            employee.setPosition(faker.options().option("Teller", "Manager", "Loan Officer", "Customer Service", "Security"));
            employee.setDepartment(faker.options().option("Operations", "Lending", "Customer Service", "IT", "Security"));
            employee.setBranchId(getRandomId(branchIds));
            employee.setHireDate(LocalDate.now().minusDays(faker.number().numberBetween(30, 1825)));
            employee.setSalary(BigDecimal.valueOf(faker.number().numberBetween(10000000, 50000000))
                .setScale(2, RoundingMode.HALF_UP));
            
            employees.add(employee);
        }
        
        List<Employee> savedEmployees = employeeRepository.saveAll(employees);
        
        // Update managers for branches
        updateBranchManagers(savedEmployees);
        
        return savedEmployees;
    }
    
    private void updateBranchManagers(List<Employee> employees) {
        List<Branch> branches = branchRepository.findAll();
        for (Branch branch : branches) {
            Employee manager = employees.stream()
                .filter(emp -> emp.getBranchId().equals(branch.getBranchId()) && 
                              "Manager".equals(emp.getPosition()))
                .findFirst()
                .orElse(employees.get(0)); // Fallback to first employee
            
            branch.setManagerId(manager.getEmployeeId());
            branchRepository.save(branch);
        }
    }
    
    public List<Account> generateAccounts() {
        return generateAccounts(DEFAULT_ACCOUNT_COUNT);
    }
    
    public List<Account> generateAccounts(int count) {
        List<Account> accounts = new ArrayList<>();
        List<Integer> customerIds = getCustomerIds();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = accountRepository.findAll().stream()
            .map(Account::getAccountId)
            .max(Integer::compareTo)
            .orElse(0);
        
        for (int i = 1; i <= count; i++) {
            Account account = new Account();
            account.setAccountId(maxId + i);
            account.setCustomerId(getRandomId(customerIds));
            account.setAccountType(faker.options().option("Savings", "Checking", "Credit", "Loan"));
            account.setBalance(BigDecimal.valueOf(faker.number().numberBetween(100000, 10000000))
                .setScale(2, RoundingMode.HALF_UP));
            account.setOpeningDate(LocalDate.now().minusDays(faker.number().numberBetween(1, 365)));
            account.setStatus(faker.options().option("Active", "Inactive", "Suspended", "Closed"));
            account.setInterestRate(BigDecimal.valueOf(faker.number().randomDouble(2, 0, 10))
                .setScale(2, RoundingMode.HALF_UP));
            account.setCreditLimit(BigDecimal.valueOf(faker.number().numberBetween(1000000, 50000000))
                .setScale(2, RoundingMode.HALF_UP));
            account.setAvailableCredit(account.getCreditLimit().subtract(
                BigDecimal.valueOf(faker.number().numberBetween(0, account.getCreditLimit().intValue()))));
            account.setOverdraftLimit(BigDecimal.valueOf(faker.number().numberBetween(100000, 5000000))
                .setScale(2, RoundingMode.HALF_UP));
            account.setMinimumBalance(BigDecimal.valueOf(faker.number().numberBetween(50000, 500000))
                .setScale(2, RoundingMode.HALF_UP));
            
            accounts.add(account);
        }
        
        return accountRepository.saveAll(accounts);
    }
    
    public List<Card> generateCards() {
        return generateCards(DEFAULT_CARD_COUNT);
    }
    
    public List<Card> generateCards(int count) {
        List<Card> cards = new ArrayList<>();
        List<Integer> accountIds = getAccountIds();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = cardRepository.findAll().stream()
            .map(Card::getCardId)
            .max(Integer::compareTo)
            .orElse(0);
        
        for (int i = 1; i <= count; i++) {
            Card card = new Card();
            card.setCardId(maxId + i);
            card.setAccountId(getRandomId(accountIds));
            card.setCardNumber(faker.finance().creditCard().replaceAll("-", ""));
            card.setExpirationDate(LocalDate.now().plusYears(faker.number().numberBetween(1, 5)));
            card.setCvv(String.format("%03d", faker.number().numberBetween(100, 999)));
            card.setCardType(faker.options().option("Credit", "Debit", "Prepaid"));
            card.setStatus(faker.options().option("Active", "Inactive", "Blocked", "Expired"));
            
            cards.add(card);
        }
        
        return cardRepository.saveAll(cards);
    }
    
    public List<Transaction> generateTransactions() {
        return generateTransactions(DEFAULT_TRANSACTION_COUNT);
    }
    
    public List<Transaction> generateTransactions(int count) {
        List<Transaction> transactions = new ArrayList<>();
        List<Integer> accountIds = getAccountIds();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = transactionRepository.findAll().stream()
            .map(Transaction::getTransactionId)
            .max(Integer::compareTo)
            .orElse(0);
        
        for (int i = 1; i <= count; i++) {
            Transaction transaction = new Transaction();
            transaction.setTransactionId(maxId + i);
            transaction.setAccountId(getRandomId(accountIds));
            transaction.setTransactionType(faker.options().option("Deposit", "Withdrawal", "Transfer", "Payment"));
            transaction.setAmount(BigDecimal.valueOf(faker.number().numberBetween(10000, 5000000))
                .setScale(2, RoundingMode.HALF_UP));
            transaction.setTransactionDate(LocalDateTime.now().minusDays(faker.number().numberBetween(0, 30)));
            transaction.setDescription(faker.lorem().sentence());
            transaction.setTransactionMethod(faker.options().option("ATM", "Online", "Branch", "Mobile"));
            transaction.setMerchantId(faker.number().numberBetween(1000, 9999));
            transaction.setCurrency("VND");
            transaction.setExchangeRate(BigDecimal.ONE);
            
            transactions.add(transaction);
        }
        
        return transactionRepository.saveAll(transactions);
    }
    
    public List<Loan> generateLoans() {
        return generateLoans(DEFAULT_LOAN_COUNT);
    }
    
    public List<Loan> generateLoans(int count) {
        List<Loan> loans = new ArrayList<>();
        List<Integer> customerIds = getCustomerIds();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = loanRepository.findAll().stream()
            .map(Loan::getLoanId)
            .max(Integer::compareTo)
            .orElse(0);
        
        for (int i = 1; i <= count; i++) {
            Loan loan = new Loan();
            loan.setLoanId(maxId + i);
            loan.setCustomerId(getRandomId(customerIds));
            loan.setLoanAmount(BigDecimal.valueOf(faker.number().numberBetween(10000000, 1000000000))
                .setScale(2, RoundingMode.HALF_UP));
            loan.setInterestRate(BigDecimal.valueOf(faker.number().randomDouble(2, 5, 25))
                .setScale(2, RoundingMode.HALF_UP));
            loan.setTerm(faker.number().numberBetween(12, 360)); // months
            loan.setStartDate(LocalDate.now().minusDays(faker.number().numberBetween(0, 365)));
            loan.setEndDate(loan.getStartDate().plusMonths(loan.getTerm()));
            loan.setStatus(faker.options().option("Active", "Paid", "Default", "Pending"));
            loan.setMonthlyPayment(loan.getLoanAmount().divide(BigDecimal.valueOf(loan.getTerm()), 2, RoundingMode.HALF_UP));
            loan.setLoanPurpose(faker.options().option("Home Purchase", "Car", "Education", "Business", "Personal"));
            loan.setRepaymentSchedule(faker.options().option("Monthly", "Quarterly", "Semi-Annual", "Annual"));
            
            loans.add(loan);
        }
        
        return loanRepository.saveAll(loans);
    }
    
    // Helper methods để lấy IDs từ các bảng đã tồn tại
    /**
     * Helper method để lấy ID cao nhất từ một list objects và function mapper
     */
    private <T> Integer getMaxId(List<T> entities, java.util.function.Function<T, Integer> idMapper) {
        return entities.stream()
            .map(idMapper)
            .max(Integer::compareTo)
            .orElse(0);
    }
    
    private List<Integer> getBranchIds() {
        return branchRepository.findAll().stream()
            .map(Branch::getBranchId)
            .toList();
    }
    
    private List<Integer> getCustomerIds() {
        return customerRepository.findAll().stream()
            .map(Customer::getCustomerId)
            .toList();
    }
    
    private List<Integer> getAccountIds() {
        return accountRepository.findAll().stream()
            .map(Account::getAccountId)
            .toList();
    }
    
    private List<Integer> getLoanIds() {
        return loanRepository.findAll().stream()
            .map(Loan::getLoanId)
            .toList();
    }
    
    private List<Integer> getTransactionIds() {
        return transactionRepository.findAll().stream()
            .map(Transaction::getTransactionId)
            .toList();
    }
    
    private List<Integer> getStockIds() {
        return stockRepository.findAll().stream()
            .map(Stock::getStockId)
            .toList();
    }
    
    private Integer getRandomId(List<Integer> ids) {
        if (ids.isEmpty()) return 1;
        return ids.get(ThreadLocalRandom.current().nextInt(ids.size()));
    }
    
    // Các method generate khác sẽ được thêm vào phần tiếp theo...
    
    public List<Collateral> generateCollaterals() {
        return generateCollaterals(DEFAULT_COLLATERAL_COUNT);
    }
    
    public List<Collateral> generateCollaterals(int count) {
        List<Collateral> collaterals = new ArrayList<>();
        List<Integer> loanIds = getLoanIds();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = collateralRepository.findAll().stream()
            .map(Collateral::getCollateralId)
            .max(Integer::compareTo)
            .orElse(0);
        
        for (int i = 1; i <= count; i++) {
            Collateral collateral = new Collateral();
            collateral.setCollateralId(maxId + i);
            collateral.setLoanId(getRandomId(loanIds));
            collateral.setType(faker.options().option("Real Estate", "Vehicle", "Jewelry", "Securities", "Equipment"));
            collateral.setValue(BigDecimal.valueOf(faker.number().numberBetween(50000000, 2000000000))
                .setScale(2, RoundingMode.HALF_UP));
            collateral.setDescription(faker.lorem().sentence());
            
            collaterals.add(collateral);
        }
        
        return collateralRepository.saveAll(collaterals);
    }
    
    public List<DebtCollection> generateDebtCollections() {
        return generateDebtCollections(DEFAULT_DEBT_COLLECTION_COUNT);
    }
    
    public List<DebtCollection> generateDebtCollections(int count) {
        List<DebtCollection> debtCollections = new ArrayList<>();
        List<Integer> loanIds = getLoanIds();
        
        for (int i = 1; i <= count; i++) {
            DebtCollection debtCollection = new DebtCollection();
            debtCollection.setLoan(loanRepository.findById(getRandomId(loanIds)).orElse(null));
            debtCollection.setAmountDue(BigDecimal.valueOf(faker.number().numberBetween(1000000, 50000000))
                .setScale(2, RoundingMode.HALF_UP));
            debtCollection.setDueDate(LocalDate.now().plusDays(faker.number().numberBetween(1, 90)));
            debtCollection.setStatus(faker.options().option("Pending", "Collected", "Overdue", "Written Off"));
            
            debtCollections.add(debtCollection);
        }
        
        return debtCollectionRepository.saveAll(debtCollections);
    }
    
    public List<CreditScore> generateCreditScores() {
        return generateCreditScores(DEFAULT_CREDIT_SCORE_COUNT);
    }
    
    public List<CreditScore> generateCreditScores(int count) {
        List<CreditScore> creditScores = new ArrayList<>();
        List<Integer> customerIds = getCustomerIds();
        
        for (int i = 1; i <= count; i++) {
            CreditScore creditScore = new CreditScore();
            creditScore.setCustomer(customerRepository.findById(getRandomId(customerIds)).orElse(null));
            creditScore.setScore(faker.number().numberBetween(300, 850));
            creditScore.setDate(LocalDate.now().minusDays(faker.number().numberBetween(0, 365)));
            
            creditScores.add(creditScore);
        }
        
        return creditScoreRepository.saveAll(creditScores);
    }
    
    public List<RiskAssessment> generateRiskAssessments() {
        return generateRiskAssessments(DEFAULT_RISK_ASSESSMENT_COUNT);
    }
    
    public List<RiskAssessment> generateRiskAssessments(int count) {
        List<RiskAssessment> riskAssessments = new ArrayList<>();
        List<Integer> customerIds = getCustomerIds();
        
        for (int i = 1; i <= count; i++) {
            RiskAssessment riskAssessment = new RiskAssessment();
            riskAssessment.setCustomer(customerRepository.findById(getRandomId(customerIds)).orElse(null));
            riskAssessment.setRiskLevel(faker.options().option("Low", "Medium", "High", "Critical"));
            riskAssessment.setAssessmentDate(LocalDate.now().minusDays(faker.number().numberBetween(0, 180)));
            
            riskAssessments.add(riskAssessment);
        }
        
        return riskAssessmentRepository.saveAll(riskAssessments);
    }
    
    public List<KYC> generateKYC() {
        return generateKYC(DEFAULT_KYC_COUNT);
    }
    
    public List<KYC> generateKYC(int count) {
        List<KYC> kycList = new ArrayList<>();
        List<Integer> customerIds = getCustomerIds();
        
        for (int i = 1; i <= count; i++) {
            KYC kyc = new KYC();
            kyc.setCustomer(customerRepository.findById(getRandomId(customerIds)).orElse(null));
            kyc.setDocumentType(faker.options().option("Passport", "ID Card", "Driver License", "Utility Bill"));
            kyc.setDocumentNumber(faker.idNumber().valid());
            kyc.setIssueDate(LocalDate.now().minusYears(faker.number().numberBetween(1, 10)));
            kyc.setExpiryDate(LocalDate.now().plusYears(faker.number().numberBetween(1, 10)));
            kyc.setVerificationStatus(faker.options().option("Pending", "Verified", "Rejected", "Expired"));
            
            kycList.add(kyc);
        }
        
        return kycRepository.saveAll(kycList);
    }
    
    public List<InsurancePolicy> generateInsurancePolicies() {
        return generateInsurancePolicies(DEFAULT_INSURANCE_POLICY_COUNT);
    }
    
    public List<InsurancePolicy> generateInsurancePolicies(int count) {
        List<InsurancePolicy> policies = new ArrayList<>();
        List<Integer> customerIds = getCustomerIds();
        
        for (int i = 1; i <= count; i++) {
            InsurancePolicy policy = new InsurancePolicy();
            policy.setCustomer(customerRepository.findById(getRandomId(customerIds)).orElse(null));
            policy.setPolicyType(faker.options().option("Life", "Health", "Auto", "Home", "Travel"));
            policy.setPremium(BigDecimal.valueOf(faker.number().numberBetween(1000000, 10000000))
                .setScale(2, RoundingMode.HALF_UP));
            policy.setCoverageAmount(BigDecimal.valueOf(faker.number().numberBetween(100000000, 2000000000))
                .setScale(2, RoundingMode.HALF_UP));
            policy.setStartDate(LocalDate.now().minusDays(faker.number().numberBetween(0, 365)));
            policy.setEndDate(policy.getStartDate().plusYears(faker.number().numberBetween(1, 5)));
            
            policies.add(policy);
        }
        
        return insurancePolicyRepository.saveAll(policies);
    }
    
    public List<OnlineBanking> generateOnlineBanking() {
        return generateOnlineBanking(DEFAULT_ONLINE_BANKING_COUNT);
    }
    
    public List<OnlineBanking> generateOnlineBanking(int count) {
        List<OnlineBanking> onlineBankingList = new ArrayList<>();
        List<Integer> customerIds = getCustomerIds();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = onlineBankingRepository.findAll().stream()
            .map(OnlineBanking::getOnlineBankingId)
            .max(Integer::compareTo)
            .orElse(0);
        
        for (int i = 1; i <= count; i++) {
            OnlineBanking onlineBanking = new OnlineBanking();
            onlineBanking.setOnlineBankingId(maxId + i);
            onlineBanking.setCustomerId(getRandomId(customerIds));
            onlineBanking.setUsername(faker.name().username());
            onlineBanking.setPassword(faker.internet().password());
            onlineBanking.setLastLogin(LocalDateTime.now().minusDays(faker.number().numberBetween(0, 30)));
            
            onlineBankingList.add(onlineBanking);
        }
        
        return onlineBankingRepository.saveAll(onlineBankingList);
    }
    
    public List<AMLCheck> generateAMLChecks() {
        return generateAMLChecks(DEFAULT_AML_CHECK_COUNT);
    }
    
    public List<AMLCheck> generateAMLChecks(int count) {
        List<AMLCheck> amlChecks = new ArrayList<>();
        List<Integer> transactionIds = getTransactionIds();
        
        for (int i = 1; i <= count; i++) {
            AMLCheck amlCheck = new AMLCheck();
            amlCheck.setTransaction(transactionRepository.findById(getRandomId(transactionIds)).orElse(null));
            amlCheck.setCheckDate(LocalDate.now().minusDays(faker.number().numberBetween(0, 30)));
            amlCheck.setResult(faker.options().option("Clear", "Suspicious", "Flagged", "Under Review"));
            
            amlChecks.add(amlCheck);
        }
        
        return amlCheckRepository.saveAll(amlChecks);
    }
    
    public List<Stock> generateStocks() {
        return generateStocks(DEFAULT_STOCK_COUNT);
    }
    
    public List<Stock> generateStocks(int count) {
        List<Stock> stocks = new ArrayList<>();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = stockRepository.findAll().stream()
            .map(Stock::getStockId)
            .max(Integer::compareTo)
            .orElse(0);
        
        for (int i = 1; i <= count; i++) {
            Stock stock = new Stock();
            stock.setStockId(maxId + i);
            stock.setSymbol(faker.stock().nsdqSymbol());
            stock.setCompanyName(faker.company().name());
            stock.setCurrentPrice(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 1000))
                .setScale(2, RoundingMode.HALF_UP));
            
            stocks.add(stock);
        }
        
        return stockRepository.saveAll(stocks);
    }
    
    public List<Portfolio> generatePortfolios() {
        return generatePortfolios(DEFAULT_PORTFOLIO_COUNT);
    }
    
    public List<Portfolio> generatePortfolios(int count) {
        List<Portfolio> portfolios = new ArrayList<>();
        List<Integer> customerIds = getCustomerIds();
        List<Integer> stockIds = getStockIds();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = portfolioRepository.findAll().stream()
            .map(Portfolio::getPortfolioId)
            .max(Integer::compareTo)
            .orElse(0);
        
        for (int i = 1; i <= count; i++) {
            Portfolio portfolio = new Portfolio();
            portfolio.setPortfolioId(maxId + i);
            portfolio.setCustomerId(getRandomId(customerIds));
            portfolio.setStockId(getRandomId(stockIds));
            portfolio.setQuantity(faker.number().numberBetween(1, 1000));
            portfolio.setPurchasePrice(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 500))
                .setScale(2, RoundingMode.HALF_UP));
            portfolio.setPurchaseDate(LocalDate.now().minusDays(faker.number().numberBetween(1, 365)));
            
            portfolios.add(portfolio);
        }
        
        return portfolioRepository.saveAll(portfolios);
    }
    
    public List<EWallet> generateEWallets() {
        return generateEWallets(DEFAULT_EWALLET_COUNT);
    }
    
    public List<EWallet> generateEWallets(int count) {
        List<EWallet> eWallets = new ArrayList<>();
        List<Integer> customerIds = getCustomerIds();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = eWalletRepository.findAll().stream()
            .map(EWallet::getEWalletId)
            .max(Integer::compareTo)
            .orElse(0);
        
        for (int i = 1; i <= count; i++) {
            EWallet eWallet = new EWallet();
            eWallet.setEWalletId(maxId + i);
            eWallet.setProvider(faker.options().option("MoMo", "ZaloPay", "ViettelPay", "VNPay", "AirPay"));
            eWallet.setAccountNumber(faker.number().digits(10));
            eWallet.setLinkedCustomerId(getRandomId(customerIds));
            
            eWallets.add(eWallet);
        }
        
        return eWalletRepository.saveAll(eWallets);
    }
    
    public List<ExchangeRate> generateExchangeRates() {
        return generateExchangeRates(DEFAULT_EXCHANGE_RATE_COUNT);
    }
    
    public List<ExchangeRate> generateExchangeRates(int count) {
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        String[] currencies = {"USD", "EUR", "JPY", "CNY", "KRW", "SGD", "THB", "AUD", "GBP", "CHF"};
        
        for (int i = 1; i <= Math.min(count, currencies.length); i++) {
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setCurrencyFrom("VND");
            exchangeRate.setCurrencyTo(currencies[i-1]);
            exchangeRate.setRate(BigDecimal.valueOf(faker.number().randomDouble(4, 1, 50000))
                .setScale(4, RoundingMode.HALF_UP));
            exchangeRate.setDate(LocalDate.now().minusDays(faker.number().numberBetween(0, 30)));
            
            exchangeRates.add(exchangeRate);
        }
        
        return exchangeRateRepository.saveAll(exchangeRates);
    }
    
    public List<Promotion> generatePromotions() {
        return generatePromotions(DEFAULT_PROMOTION_COUNT);
    }
    
    public List<Promotion> generatePromotions(int count) {
        List<Promotion> promotions = new ArrayList<>();
        
        for (int i = 1; i <= count; i++) {
            Promotion promotion = new Promotion();
            promotion.setName(faker.commerce().productName());
            promotion.setDescription(faker.lorem().paragraph());
            promotion.setStartDate(LocalDate.now().minusDays(faker.number().numberBetween(0, 30)));
            promotion.setEndDate(promotion.getStartDate().plusDays(faker.number().numberBetween(30, 365)));
            
            promotions.add(promotion);
        }
        
        return promotionRepository.saveAll(promotions);
    }
    
    public List<Business> generateBusinesses() {
        return generateBusinesses(DEFAULT_BUSINESS_COUNT);
    }
    
    public List<Business> generateBusinesses(int count) {
        List<Business> businesses = new ArrayList<>();
        
        for (int i = 1; i <= count; i++) {
            Business business = new Business();
            business.setName(faker.company().name());
            business.setTaxId(faker.number().digits(10));
            business.setAddress(faker.address().fullAddress());
            business.setContactPerson(faker.name().fullName());
            
            businesses.add(business);
        }
        
        return businessRepository.saveAll(businesses);
    }
    
    public List<ATM> generateATMs() {
        return generateATMs(DEFAULT_ATM_COUNT);
    }
    
    public List<ATM> generateATMs(int count) {
        List<ATM> atms = new ArrayList<>();
        
        // Lấy ID cao nhất hiện tại
        Integer maxId = atmRepository.findAll().stream()
            .map(ATM::getAtmId)
            .max(Integer::compareTo)
            .orElse(0);
        
        for (int i = 1; i <= count; i++) {
            ATM atm = new ATM();
            atm.setAtmId(maxId + i);
            atm.setLocation(faker.address().fullAddress());
            atm.setStatus(faker.options().option("Active", "Inactive", "Maintenance", "Out of Service"));
            
            atms.add(atm);
        }
        
        return atmRepository.saveAll(atms);
    }
    
    public List<InterbankTransfer> generateInterbankTransfers() {
        return generateInterbankTransfers(DEFAULT_INTERBANK_TRANSFER_COUNT);
    }
    
    public List<InterbankTransfer> generateInterbankTransfers(int count) {
        List<InterbankTransfer> transfers = new ArrayList<>();
        List<Integer> accountIds = getAccountIds();
        
        for (int i = 1; i <= count; i++) {
            InterbankTransfer transfer = new InterbankTransfer();
            transfer.setFromAccount(accountRepository.findById(getRandomId(accountIds)).orElse(null));
            transfer.setToBank(faker.company().name() + " Bank");
            transfer.setToAccountNumber(faker.number().digits(12));
            transfer.setAmount(BigDecimal.valueOf(faker.number().numberBetween(100000, 50000000))
                .setScale(2, RoundingMode.HALF_UP));
            transfer.setDate(LocalDateTime.now().minusDays(faker.number().numberBetween(0, 30)));
            transfer.setStatus(faker.options().option("Pending", "Completed", "Failed", "Cancelled"));
            
            transfers.add(transfer);
        }
        
        return interbankTransferRepository.saveAll(transfers);
    }
    
    // Method để xóa tất cả dữ liệu
    public void clearAllData() {
        interbankTransferRepository.deleteAll();
        atmRepository.deleteAll();
        businessRepository.deleteAll();
        promotionRepository.deleteAll();
        exchangeRateRepository.deleteAll();
        eWalletRepository.deleteAll();
        portfolioRepository.deleteAll();
        stockRepository.deleteAll();
        amlCheckRepository.deleteAll();
        onlineBankingRepository.deleteAll();
        insurancePolicyRepository.deleteAll();
        kycRepository.deleteAll();
        riskAssessmentRepository.deleteAll();
        creditScoreRepository.deleteAll();
        debtCollectionRepository.deleteAll();
        collateralRepository.deleteAll();
        loanRepository.deleteAll();
        transactionRepository.deleteAll();
        cardRepository.deleteAll();
        accountRepository.deleteAll();
        employeeRepository.deleteAll();
        customerRepository.deleteAll();
        branchRepository.deleteAll();
    }
    
    /**
     * Calculate total number of records that will be generated with current default values
     * @return total count of all records
     */
    public static int getTotalDefaultRecordCount() {
        return DEFAULT_BRANCH_COUNT + DEFAULT_BUSINESS_COUNT + DEFAULT_ATM_COUNT +
               DEFAULT_CUSTOMER_COUNT + DEFAULT_EMPLOYEE_COUNT +
               DEFAULT_ACCOUNT_COUNT + DEFAULT_CARD_COUNT + DEFAULT_LOAN_COUNT +
               DEFAULT_TRANSACTION_COUNT + DEFAULT_EXCHANGE_RATE_COUNT +
               DEFAULT_COLLATERAL_COUNT + DEFAULT_DEBT_COLLECTION_COUNT +
               DEFAULT_CREDIT_SCORE_COUNT + DEFAULT_RISK_ASSESSMENT_COUNT +
               DEFAULT_KYC_COUNT + DEFAULT_INSURANCE_POLICY_COUNT + DEFAULT_ONLINE_BANKING_COUNT +
               DEFAULT_AML_CHECK_COUNT + DEFAULT_STOCK_COUNT + DEFAULT_PORTFOLIO_COUNT +
               DEFAULT_EWALLET_COUNT + DEFAULT_PROMOTION_COUNT + DEFAULT_INTERBANK_TRANSFER_COUNT;
    }
    
    /**
     * Generate scaled down version for development/testing (approximately 10,000 records total)
     * Maintains proportional relationships but with smaller scale
     */
    public Map<String, Object> generateAllFakeDataSmallScale() {
        return generateAllFakeData(
            5,      // branches
            1000,   // customers  
            100,    // employees
            1500,   // accounts
            1200,   // cards
            5000,   // transactions
            250,    // loans
            200,    // collaterals
            25,     // debtCollections
            800,    // creditScores
            150,    // riskAssessments
            950,    // kyc
            300,    // insurancePolicies
            750,    // onlineBanking
            500,    // amlChecks
            100,    // stocks
            80,     // portfolios
            600,    // eWallets
            50,     // exchangeRates
            10,     // promotions
            20,     // businesses
            25,     // atms
            400     // interbankTransfers
        );
    }
    
    /**
     * Generate medium scale for staging environment (approximately 100,000 records total)
     * Good for performance testing and staging
     */
    public Map<String, Object> generateAllFakeDataMediumScale() {
        return generateAllFakeData(
            20,     // branches
            10000,  // customers  
            800,    // employees
            15000,  // accounts
            12000,  // cards
            50000,  // transactions
            2500,   // loans
            2000,   // collaterals
            250,    // debtCollections
            8000,   // creditScores
            1500,   // riskAssessments
            9500,   // kyc
            3000,   // insurancePolicies
            7500,   // onlineBanking
            5000,   // amlChecks
            500,    // stocks
            800,    // portfolios
            6000,   // eWallets
            100,    // exchangeRates
            50,     // promotions
            100,    // businesses
            100,    // atms
            4000    // interbankTransfers
        );
    }
}
