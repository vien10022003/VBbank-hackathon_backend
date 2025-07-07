-- Insert sample data for Customers table
INSERT INTO customers (customerid, first_name, last_name, date_of_birth, address, phone_number, email, ssn, registration_date, status, gender, marital_status, occupation, income_level, education_level, nationality, preferred_language) VALUES
(1, 'Nguyen', 'Van A', '1990-01-15', '123 Le Loi Street, District 1, Ho Chi Minh City', '0901234567', 'nguyenvana@email.com', '123456789', '2020-01-01', 'Active', 'Male', 'Single', 'Software Engineer', 50000000.00, 'Bachelor', 'Vietnamese', 'Vietnamese'),
(2, 'Tran', 'Thi B', '1985-05-20', '456 Nguyen Hue Street, District 1, Ho Chi Minh City', '0912345678', 'tranthib@email.com', '234567890', '2020-02-15', 'Active', 'Female', 'Married', 'Doctor', 80000000.00, 'Master', 'Vietnamese', 'Vietnamese'),
(3, 'Le', 'Van C', '1992-03-10', '789 Dong Khoi Street, District 1, Ho Chi Minh City', '0923456789', 'levanc@email.com', '345678901', '2020-03-20', 'Active', 'Male', 'Single', 'Teacher', 30000000.00, 'Bachelor', 'Vietnamese', 'Vietnamese'),
(4, 'Pham', 'Thi D', '1988-07-25', '321 Pasteur Street, District 3, Ho Chi Minh City', '0934567890', 'phamthid@email.com', '456789012', '2020-04-10', 'Active', 'Female', 'Married', 'Lawyer', 70000000.00, 'Master', 'Vietnamese', 'Vietnamese'),
(5, 'Hoang', 'Van E', '1995-11-30', '654 Vo Van Tan Street, District 3, Ho Chi Minh City', '0945678901', 'hoangvane@email.com', '567890123', '2020-05-05', 'Active', 'Male', 'Single', 'Marketing Manager', 45000000.00, 'Bachelor', 'Vietnamese', 'Vietnamese');

-- Insert sample data for Accounts table
INSERT INTO accounts (accountid, customerid, account_type, balance, opening_date, status, interest_rate, credit_limit, available_credit, overdraft_limit, minimum_balance) VALUES
(1001, 1, 'Checking', 5000000.00, '2020-01-01', 'Active', 0.50, NULL, NULL, 1000000.00, 50000.00),
(1002, 1, 'Savings', 25000000.00, '2020-01-01', 'Active', 2.50, NULL, NULL, NULL, 100000.00),
(1003, 2, 'Checking', 8000000.00, '2020-02-15', 'Active', 0.50, NULL, NULL, 2000000.00, 50000.00),
(1004, 2, 'Credit', 0.00, '2020-02-15', 'Active', 18.00, 50000000.00, 50000000.00, NULL, NULL),
(1005, 3, 'Checking', 3000000.00, '2020-03-20', 'Active', 0.50, NULL, NULL, 500000.00, 50000.00),
(1006, 4, 'Savings', 35000000.00, '2020-04-10', 'Active', 2.50, NULL, NULL, NULL, 100000.00),
(1007, 5, 'Checking', 6000000.00, '2020-05-05', 'Active', 0.50, NULL, NULL, 1500000.00, 50000.00);

-- Insert sample data for Transactions table
INSERT INTO transactions (transactionid, accountid, transaction_type, amount, transaction_date, description, transaction_method, currency, exchange_rate) VALUES
(1, 1001, 'Deposit', 1000000.00, '2024-01-01 10:00:00', 'Salary deposit', 'Transfer', 'VND', 1.0000),
(2, 1001, 'Withdrawal', 500000.00, '2024-01-02 14:30:00', 'ATM withdrawal', 'ATM', 'VND', 1.0000),
(3, 1002, 'Deposit', 5000000.00, '2024-01-03 09:15:00', 'Savings deposit', 'Transfer', 'VND', 1.0000),
(4, 1003, 'Purchase', 200000.00, '2024-01-04 16:45:00', 'Coffee shop purchase', 'Card', 'VND', 1.0000),
(5, 1004, 'Purchase', 1500000.00, '2024-01-05 11:20:00', 'Online shopping', 'Card', 'VND', 1.0000),
(6, 1005, 'Transfer', 800000.00, '2024-01-06 13:10:00', 'Transfer to friend', 'Online', 'VND', 1.0000),
(7, 1006, 'Interest', 50000.00, '2024-01-07 00:00:00', 'Monthly interest', 'System', 'VND', 1.0000),
(8, 1007, 'Bill Payment', 300000.00, '2024-01-08 15:30:00', 'Electricity bill', 'Online', 'VND', 1.0000);

-- Insert sample data for Cards table
INSERT INTO cards (cardid, accountid, card_number, expiration_date, cvv, card_type, status) VALUES
(2001, 1001, '1234567890123456', '2026-12-31', '123', 'Debit', 'Active'),
(2002, 1002, '2345678901234567', '2026-12-31', '234', 'Debit', 'Active'),
(2003, 1003, '3456789012345678', '2026-12-31', '345', 'Debit', 'Active'),
(2004, 1004, '4567890123456789', '2026-12-31', '456', 'Credit', 'Active'),
(2005, 1005, '5678901234567890', '2026-12-31', '567', 'Debit', 'Active'),
(2006, 1006, '6789012345678901', '2026-12-31', '678', 'Debit', 'Active'),
(2007, 1007, '7890123456789012', '2026-12-31', '789', 'Debit', 'Active');

-- Insert sample data for Loans table
INSERT INTO loans (loanid, customerid, loan_amount, interest_rate, term, start_date, end_date, status, monthly_payment, loan_purpose, repayment_schedule) VALUES
(3001, 2, 500000000.00, 8.50, 240, '2020-03-01', '2040-03-01', 'Active', 4500000.00, 'Home Purchase', 'Monthly'),
(3002, 4, 200000000.00, 12.00, 60, '2020-06-01', '2025-06-01', 'Active', 4500000.00, 'Car Purchase', 'Monthly'),
(3003, 1, 100000000.00, 15.00, 36, '2021-01-01', '2024-01-01', 'Completed', 3500000.00, 'Personal', 'Monthly'),
(3004, 5, 150000000.00, 10.00, 84, '2021-03-01', '2028-03-01', 'Active', 2200000.00, 'Business', 'Monthly');

-- Insert sample data for Branches table (without managerid first)
INSERT INTO branches (branchid, address, phone_number) VALUES
(4001, '123 Nguyen Hue Street, District 1, Ho Chi Minh City', '0283822222'),
(4002, '456 Le Loi Street, District 1, Ho Chi Minh City', '0283833333'),
(4003, '789 Dong Khoi Street, District 1, Ho Chi Minh City', '0283844444'),
(4004, '321 Pasteur Street, District 3, Ho Chi Minh City', '0283855555');

-- Insert sample data for Employees table
INSERT INTO employees (employeeid, first_name, last_name, position, department, branchid, hire_date, salary) VALUES
(5001, 'Nguyen', 'Van Manager1', 'Branch Manager', 'Management', 4001, '2015-01-01', 50000000.00),
(5002, 'Tran', 'Thi Manager2', 'Branch Manager', 'Management', 4002, '2016-01-01', 52000000.00),
(5003, 'Le', 'Van Manager3', 'Branch Manager', 'Management', 4003, '2017-01-01', 48000000.00),
(5004, 'Pham', 'Thi Manager4', 'Branch Manager', 'Management', 4004, '2018-01-01', 55000000.00),
(5005, 'Hoang', 'Van Teller1', 'Teller', 'Operations', 4001, '2019-01-01', 20000000.00),
(5006, 'Vu', 'Thi Teller2', 'Teller', 'Operations', 4002, '2019-06-01', 22000000.00),
(5007, 'Do', 'Van Advisor1', 'Loan Advisor', 'Lending', 4001, '2020-01-01', 35000000.00),
(5008, 'Bui', 'Thi Advisor2', 'Investment Advisor', 'Investment', 4003, '2020-03-01', 40000000.00);

-- Update branches table with managerid after employees are inserted
UPDATE branches SET managerid = 5001 WHERE branchid = 4001;
UPDATE branches SET managerid = 5002 WHERE branchid = 4002;
UPDATE branches SET managerid = 5003 WHERE branchid = 4003;
UPDATE branches SET managerid = 5004 WHERE branchid = 4004;

-- Insert sample data for Stocks table
INSERT INTO stocks (stockid, symbol, company_name, current_price) VALUES
(6001, 'VCB', 'Vietcombank', 85000.00),
(6002, 'BID', 'BIDV', 45000.00),
(6003, 'CTG', 'VietinBank', 35000.00),
(6004, 'VIC', 'Vingroup', 65000.00),
(6005, 'VNM', 'Vinamilk', 75000.00),
(6006, 'HPG', 'Hoa Phat Group', 25000.00),
(6007, 'VRE', 'Vincom Retail', 32000.00);

-- Insert sample data for Portfolios table (after stocks)
INSERT INTO portfolios (portfolioid, customerid, stockid, quantity, purchase_price, purchase_date) VALUES
(7001, 1, 6001, 100, 80000.00, '2023-01-15'),
(7002, 1, 6005, 200, 70000.00, '2023-02-20'),
(7003, 2, 6002, 500, 42000.00, '2023-03-10'),
(7004, 2, 6004, 150, 60000.00, '2023-04-05'),
(7005, 4, 6003, 300, 33000.00, '2023-05-12'),
(7006, 4, 6006, 400, 22000.00, '2023-06-18'),
(7007, 5, 6007, 250, 30000.00, '2023-07-25');

-- Insert sample data for Collateral table (after loans)
INSERT INTO collateral (collateralid, loanid, type, value, description) VALUES
(10001, 3001, 'Real Estate', 600000000.00, 'Apartment in District 1'),
(10002, 3002, 'Vehicle', 250000000.00, 'Toyota Camry 2020'),
(10003, 3004, 'Real Estate', 200000000.00, 'Commercial property');

-- Insert sample data for Debt Collection table (after loans)
INSERT INTO debt_collection (loanid, amount_due, due_date, status) VALUES
(3001, 4500000.00, '2024-02-01', 'Pending'),
(3002, 4500000.00, '2024-02-01', 'Pending'),
(3004, 2200000.00, '2024-02-01', 'Pending');

-- Insert sample data for AML Checks table (after transactions)
INSERT INTO amlchecks (transactionid, check_date, result) VALUES
(1, '2024-01-01', 'Clear'),
(2, '2024-01-02', 'Clear'),
(3, '2024-01-03', 'Clear'),
(4, '2024-01-04', 'Clear'),
(5, '2024-01-05', 'Review'),
(6, '2024-01-06', 'Clear'),
(7, '2024-01-07', 'Clear'),
(8, '2024-01-08', 'Clear');

-- Insert sample data for Interbank Transfers table (after accounts)
INSERT INTO interbank_transfers (from_accountid, to_bank, to_account_number, amount, date, status) VALUES
(1001, 'Techcombank', '1234567890', 2000000.00, '2024-01-05 10:30:00', 'Completed'),
(1003, 'ACB', '2345678901', 1500000.00, '2024-01-06 14:15:00', 'Completed'),
(1007, 'Sacombank', '3456789012', 1000000.00, '2024-01-07 09:45:00', 'Pending');

-- Insert sample data for Online Banking table
INSERT INTO online_banking (online_bankingid, customerid, username, password, last_login) VALUES
(8001, 1, 'nguyenvana', '$2a$10$hashedpassword1', '2024-01-08 09:30:00'),
(8002, 2, 'tranthib', '$2a$10$hashedpassword2', '2024-01-08 10:15:00'),
(8003, 3, 'levanc', '$2a$10$hashedpassword3', '2024-01-07 14:20:00'),
(8004, 4, 'phamthid', '$2a$10$hashedpassword4', '2024-01-08 08:45:00'),
(8005, 5, 'hoangvane', '$2a$10$hashedpassword5', '2024-01-08 11:00:00');

-- Insert sample data for Insurance Policies table
INSERT INTO insurance_policies (customerid, policy_type, premium, coverage_amount, start_date, end_date) VALUES
(1, 'Life Insurance', 5000000.00, 500000000.00, '2020-01-01', '2030-01-01'),
(2, 'Health Insurance', 8000000.00, 200000000.00, '2020-02-15', '2025-02-15'),
(3, 'Car Insurance', 3000000.00, 800000000.00, '2020-03-20', '2024-03-20'),
(4, 'Home Insurance', 6000000.00, 2000000000.00, '2020-04-10', '2030-04-10'),
(5, 'Travel Insurance', 2000000.00, 100000000.00, '2023-01-01', '2024-01-01');

-- Insert sample data for E-Wallets table
INSERT INTO ewallets (ewalletid, provider, account_number, linked_customerid) VALUES
(9001, 'MoMo', '0901234567', 1),
(9002, 'ZaloPay', '0912345678', 2),
(9003, 'ViettelPay', '0923456789', 3),
(9004, 'ShopeePay', '0934567890', 4),
(9005, 'GrabPay', '0945678901', 5);

-- Insert sample data for Credit Scores table
INSERT INTO credit_scores (customerid, score, date) VALUES
(1, 750, '2024-01-01'),
(2, 820, '2024-01-01'),
(3, 680, '2024-01-01'),
(4, 790, '2024-01-01'),
(5, 720, '2024-01-01');

-- Insert sample data for KYC table
INSERT INTO kyc (customerid, document_type, document_number, issue_date, expiry_date, verification_status) VALUES
(1, 'National ID', '123456789012', '2020-01-01', '2030-01-01', 'Verified'),
(2, 'Passport', 'B1234567', '2019-05-15', '2029-05-15', 'Verified'),
(3, 'National ID', '234567890123', '2021-03-10', '2031-03-10', 'Verified'),
(4, 'National ID', '345678901234', '2020-07-20', '2030-07-20', 'Verified'),
(5, 'Passport', 'B2345678', '2022-11-30', '2032-11-30', 'Verified');

-- Insert sample data for Risk Assessments table
INSERT INTO risk_assessments (customerid, risk_level, assessment_date) VALUES
(1, 'Medium', '2024-01-01'),
(2, 'Low', '2024-01-01'),
(3, 'Medium', '2024-01-01'),
(4, 'Low', '2024-01-01'),
(5, 'Medium', '2024-01-01');

-- Insert sample data for ATMs table
INSERT INTO atms (atmid, location, status) VALUES
(11001, 'District 1, Ho Chi Minh City', 'Active'),
(11002, 'District 3, Ho Chi Minh City', 'Active'),
(11003, 'District 7, Ho Chi Minh City', 'Active'),
(11004, 'Thu Duc City, Ho Chi Minh City', 'Maintenance'),
(11005, 'Tan Binh District, Ho Chi Minh City', 'Active');

-- Insert sample data for Businesses table
INSERT INTO businesses (name, taxid, address, contact_person) VALUES
('Coffee Shop ABC', '0123456789', '123 Le Loi Street, District 1, Ho Chi Minh City', 'Nguyen Van Owner'),
('Tech Company XYZ', '0234567890', '456 Nguyen Hue Street, District 1, Ho Chi Minh City', 'Tran Thi CEO'),
('Restaurant 123', '0345678901', '789 Dong Khoi Street, District 1, Ho Chi Minh City', 'Le Van Manager');

-- Insert sample data for Exchange Rates table
INSERT INTO exchange_rates (currency_from, currency_to, rate, date) VALUES
('USD', 'VND', 24000.0000, '2024-01-08'),
('EUR', 'VND', 26000.0000, '2024-01-08'),
('JPY', 'VND', 170.0000, '2024-01-08'),
('GBP', 'VND', 30000.0000, '2024-01-08'),
('VND', 'USD', 0.0000, '2024-01-08');

-- Insert sample data for Promotions table
INSERT INTO promotions (name, description, start_date, end_date) VALUES
('New Year Promotion', 'Special interest rates for new savings accounts', '2024-01-01', '2024-02-29'),
('Student Loan Discount', 'Reduced interest rates for student loans', '2024-01-01', '2024-12-31'),
('Credit Card Cashback', '5% cashback on all purchases', '2024-01-01', '2024-03-31'),
('Home Loan Special', 'Low interest rates for home loans', '2024-01-01', '2024-06-30');
