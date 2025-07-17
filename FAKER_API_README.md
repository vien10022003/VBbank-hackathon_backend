# Faker API Documentation

## Mô tả
API Faker cho phép tạo dữ liệu giả cho hệ thống ngân hàng VBBank Hackathon sử dụng thư viện JavaFaker. API này giúp tạo dữ liệu test một cách nhanh chóng và dễ dàng.

## Cài đặt và Chạy

### 1. Thêm dependency vào pom.xml (đã được thêm):
```xml
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
</dependency>
```

### 2. Chạy ứng dụng:
```bash
./mvnw spring-boot:run
```

## API Endpoints

### Base URL: `http://localhost:8080/api/faker`

### 1. Tạo tất cả dữ liệu giả
```http
POST /api/faker/generate-all
```
**Mô tả:** Tạo dữ liệu giả cho tất cả các bảng (mỗi bảng 10 records)

**Response:**
```json
{
    "success": true,
    "message": "Fake data generated successfully",
    "data": {
        "branches": [...],
        "customers": [...],
        "employees": [...],
        "accounts": [...],
        "cards": [...],
        "transactions": [...],
        "loans": [...],
        "collaterals": [...],
        "debtCollections": [...],
        "creditScores": [...],
        "riskAssessments": [...],
        "kyc": [...],
        "insurancePolicies": [...],
        "onlineBanking": [...],
        "amlChecks": [...],
        "stocks": [...],
        "portfolios": [...],
        "eWallets": [...],
        "exchangeRates": [...],
        "promotions": [...],
        "businesses": [...],
        "atms": [...],
        "interbankTransfers": [...]
    }
}
```

### 2. Tạo dữ liệu cho từng bảng

#### Tạo chi nhánh
```http
POST /api/faker/generate-branches
```

#### Tạo khách hàng
```http
POST /api/faker/generate-customers
```

#### Tạo nhân viên
```http
POST /api/faker/generate-employees
```

#### Tạo tài khoản
```http
POST /api/faker/generate-accounts
```

#### Tạo thẻ
```http
POST /api/faker/generate-cards
```

#### Tạo giao dịch
```http
POST /api/faker/generate-transactions
```

#### Tạo khoản vay
```http
POST /api/faker/generate-loans
```

#### Tạo cổ phiếu
```http
POST /api/faker/generate-stocks
```

#### Tạo danh mục đầu tư
```http
POST /api/faker/generate-portfolios
```

### 3. Xóa tất cả dữ liệu
```http
DELETE /api/faker/clear-all
```
**Mô tả:** Xóa tất cả dữ liệu giả đã tạo

**Response:**
```json
{
    "success": true,
    "message": "All fake data cleared successfully"
}
```

### 4. Thông tin API
```http
GET /api/faker/info
```
**Mô tả:** Lấy thông tin về API Faker

**Response:**
```json
{
    "description": "Faker API for VBBank Hackathon Backend",
    "version": "1.0.0",
    "available_endpoints": {
        "POST /api/faker/generate-all": "Generate all fake data",
        "POST /api/faker/generate-branches": "Generate fake branches",
        "POST /api/faker/generate-customers": "Generate fake customers",
        "POST /api/faker/generate-employees": "Generate fake employees",
        "POST /api/faker/generate-accounts": "Generate fake accounts",
        "POST /api/faker/generate-cards": "Generate fake cards",
        "POST /api/faker/generate-transactions": "Generate fake transactions",
        "POST /api/faker/generate-loans": "Generate fake loans",
        "POST /api/faker/generate-stocks": "Generate fake stocks",
        "POST /api/faker/generate-portfolios": "Generate fake portfolios",
        "DELETE /api/faker/clear-all": "Clear all data",
        "GET /api/faker/info": "Get faker info"
    },
    "data_count_per_table": 10,
    "note": "Each table will generate 10 fake records. Foreign key references are maintained properly."
}
```

## Đặc điểm của dữ liệu giả

### 1. Quan hệ Foreign Key
- API đảm bảo tính toàn vẹn dữ liệu bằng cách:
  - Tạo dữ liệu theo thứ tự phụ thuộc (cha trước, con sau)
  - Random ID từ các bảng cha đã tồn tại
  - Ví dụ: Account sẽ random CustomerID từ danh sách Customer đã có

### 2. Dữ liệu được tạo

#### Branches (Chi nhánh)
- ID: 1-10
- Address: Địa chỉ giả sử dụng Vietnamese faker
- Phone Number: Số điện thoại giả
- Manager ID: Sẽ được cập nhật sau khi tạo Employee

#### Customers (Khách hàng)
- ID: 1-10
- Tên: Tên tiếng Việt
- Ngày sinh: Ngẫu nhiên
- Địa chỉ, Email, Số điện thoại: Dữ liệu giả
- Thu nhập: 1,000,000 - 50,000,000 VND
- Nghề nghiệp, học vấn: Dữ liệu giả

#### Accounts (Tài khoản)
- ID: 1-10
- Customer ID: Random từ Customer có sẵn
- Loại tài khoản: Savings, Checking, Credit, Loan
- Số dư: 100,000 - 10,000,000 VND
- Lãi suất: 0-10%

#### Transactions (Giao dịch)
- ID: 1-10
- Account ID: Random từ Account có sẵn
- Loại: Deposit, Withdrawal, Transfer, Payment
- Số tiền: 10,000 - 5,000,000 VND
- Phương thức: ATM, Online, Branch, Mobile

#### Loans (Khoản vay)
- ID: 1-10
- Customer ID: Random từ Customer có sẵn
- Số tiền vay: 10,000,000 - 1,000,000,000 VND
- Lãi suất: 5-25%
- Kỳ hạn: 12-360 tháng

### 3. Thứ tự tạo dữ liệu
1. Branches
2. Customers
3. Employees (và cập nhật Manager cho Branches)
4. Accounts
5. Cards
6. Transactions
7. Loans
8. Collaterals
9. DebtCollections
10. CreditScores
11. RiskAssessments
12. KYC
13. InsurancePolicies
14. OnlineBanking
15. AMLChecks
16. Stocks
17. Portfolios
18. EWallets
19. ExchangeRates
20. Promotions
21. Businesses
22. ATMs
23. InterbankTransfers

## Sử dụng với Postman

### 1. Import collection
Tạo collection mới trong Postman với base URL: `http://localhost:8080/api/faker`

### 2. Ví dụ request
```http
POST http://localhost:8080/api/faker/generate-all
Content-Type: application/json
```

### 3. Test workflow
1. Gọi `GET /api/faker/info` để kiểm tra API
2. Gọi `POST /api/faker/generate-all` để tạo tất cả dữ liệu
3. Kiểm tra dữ liệu trong database
4. Gọi `DELETE /api/faker/clear-all` để xóa dữ liệu khi cần

## Lưu ý quan trọng

### 1. Database Connection
- Đảm bảo database đã được kết nối và các bảng đã được tạo
- Kiểm tra file `application.properties` cho cấu hình database

### 2. Xử lý lỗi
- API có xử lý lỗi và trả về message rõ ràng
- Nếu có lỗi Foreign Key, kiểm tra thứ tự tạo dữ liệu

### 3. Performance
- Mỗi lần tạo 10 records cho mỗi bảng
- Có thể điều chỉnh số lượng trong `FakerService.java`

### 4. Data Cleanup
- Sử dụng `DELETE /api/faker/clear-all` để xóa sạch dữ liệu
- Thứ tự xóa ngược lại với thứ tự tạo để tránh lỗi Foreign Key

## Mở rộng

### Thêm bảng mới
1. Tạo method `generateNewTable()` trong `FakerService`
2. Thêm endpoint tương ứng trong `FakerController`
3. Cập nhật method `generateAllFakeData()` và `clearAllData()`

### Tùy chỉnh dữ liệu
- Sửa đổi logic trong `FakerService` cho từng bảng
- Thay đổi số lượng records tạo ra
- Điều chỉnh range của các giá trị số
