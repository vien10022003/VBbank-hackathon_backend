# VBBank Hackathon Backend API

## Mô tả dự án

Backend API cho hệ thống ngân hàng VBBank Hackathon, được phát triển bằng Spring Boot. Dự án cung cấp các API RESTful cho hệ thống ngân hàng hoàn chỉnh bao gồm quản lý khách hàng, tài khoản, giao dịch, và nhiều tính năng khác.

## Công nghệ sử dụng

- **Java 21** - Ngôn ngữ lập trình chính
- **Spring Boot 3.5.3** - Framework chính
- **Spring Data JPA** - ORM và truy cập dữ liệu
- **MySQL 8.0** - Cơ sở dữ liệu
- **Maven** - Quản lý dependencies
- **Lombok** - Giảm thiểu boilerplate code
- **SpringDoc OpenAPI** - Tài liệu API tự động
- **Spring Boot Actuator** - Monitoring và health check

## Cấu trúc dự án

```
src/
├── main/
│   ├── java/com/vbbankhackathon/backend/
│   │   ├── BackendApplication.java         # Main application class
│   │   ├── config/                         # Cấu hình ứng dụng
│   │   ├── controller/                     # REST Controllers
│   │   │   ├── AccountController.java      # Quản lý tài khoản
│   │   │   ├── CustomerController.java     # Quản lý khách hàng
│   │   │   ├── TransactionController.java  # Quản lý giao dịch
│   │   │   ├── LoanController.java         # Quản lý vay vốn
│   │   │   ├── CardController.java         # Quản lý thẻ
│   │   │   ├── ATMController.java          # Quản lý ATM
│   │   │   ├── BranchController.java       # Quản lý chi nhánh
│   │   │   ├── BusinessController.java     # Quản lý doanh nghiệp
│   │   │   ├── EWalletController.java      # Quản lý ví điện tử
│   │   │   ├── ExchangeRateController.java # Tỷ giá hối đoái
│   │   │   ├── InsurancePolicyController.java # Bảo hiểm
│   │   │   ├── KYCController.java          # Know Your Customer
│   │   │   ├── AMLCheckController.java     # Chống rửa tiền
│   │   │   ├── CreditScoreController.java  # Điểm tín dụng
│   │   │   ├── RiskAssessmentController.java # Đánh giá rủi ro
│   │   │   └── ... (và nhiều controller khác)
│   │   ├── entity/                         # JPA Entities
│   │   └── repository/                     # Data Access Layer
│   └── resources/
│       ├── application.properties          # Cấu hình ứng dụng
│       ├── static/                         # Static resources
│       └── templates/                      # Templates
└── test/                                   # Unit tests
```

## Yêu cầu hệ thống

### Phần mềm cần thiết:
- **Java 21** hoặc cao hơn
- **Maven 3.6+**
- **MySQL 8.0+**
- **IDE**: IntelliJ IDEA, Eclipse, hoặc VS Code

### Cơ sở dữ liệu:
- MySQL Server 8.0+
- Tạo database: `vbbankhackathon`

## Hướng dẫn cài đặt

### 1. Clone dự án

```bash
git clone [repository-url]
cd VBbank-hackathon-BE-git
```

### 2. Cài đặt cơ sở dữ liệu

#### Cài đặt MySQL:
1. Tải và cài đặt MySQL 8.0+ từ [MySQL Downloads](https://dev.mysql.com/downloads/)
2. Khởi động MySQL Server (database sẽ tự động tạo lần đầu chạy dự án)

### 3. Cấu hình ứng dụng

Chỉnh sửa file `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/vbbankhackathon
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Application Configuration
server.port=8080
spring.application.name=VBBank-Hackathon-Backend
```

### 4. Chạy ứng dụng

#### Sử dụng Maven:
```bash
# Trên Windows
mvnw.cmd spring-boot:run

# Trên Linux/Mac
./mvnw spring-boot:run
```

#### Sử dụng IDE:
1. Import project vào IDE
2. Chạy `BackendApplication.java`

#### Build và chạy JAR:
```bash
# Build project
mvnw.cmd clean package

# Chạy JAR file
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

### 5. Khởi tạo dữ liệu mẫu

Chạy file `initial_data.sql` để tạo dữ liệu mẫu:

```sql
-- Kết nối vào MySQL và chạy:
USE vbbankhackathon;
SOURCE /path/to/initial_data.sql;
```

## Sử dụng API

### Base URL
```
http://localhost:8080
```

### API Documentation
Sau khi chạy ứng dụng, truy cập Swagger UI tại:
```
http://localhost:8080/swagger-ui.html
```

### Các API endpoints chính:

#### 1. Customer Management
- `GET /api/customers` - Lấy danh sách khách hàng
- `POST /api/customers` - Tạo khách hàng mới
- `GET /api/customers/{id}` - Lấy thông tin khách hàng
- `PUT /api/customers/{id}` - Cập nhật thông tin khách hàng
- `DELETE /api/customers/{id}` - Xóa khách hàng

#### 2. Account Management
- `GET /api/accounts` - Lấy danh sách tài khoản
- `POST /api/accounts` - Tạo tài khoản mới
- `GET /api/accounts/{id}` - Lấy thông tin tài khoản
- `PUT /api/accounts/{id}` - Cập nhật tài khoản
- `GET /api/accounts/customer/{customerId}` - Lấy tài khoản theo khách hàng

#### 3. Transaction Management
- `GET /api/transactions` - Lấy danh sách giao dịch
- `POST /api/transactions` - Tạo giao dịch mới
- `GET /api/transactions/{id}` - Lấy chi tiết giao dịch
- `GET /api/transactions/account/{accountId}` - Lấy giao dịch theo tài khoản

#### 4. Loan Management
- `GET /api/loans` - Lấy danh sách khoản vay
- `POST /api/loans` - Tạo khoản vay mới
- `GET /api/loans/{id}` - Lấy chi tiết khoản vay
- `PUT /api/loans/{id}` - Cập nhật khoản vay

#### 5. Card Management
- `GET /api/cards` - Lấy danh sách thẻ
- `POST /api/cards` - Tạo thẻ mới
- `GET /api/cards/{id}` - Lấy thông tin thẻ
- `PUT /api/cards/{id}` - Cập nhật thẻ

### Ví dụ request:

#### Tạo khách hàng mới:
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Nguyen",
    "lastName": "Van Test",
    "dateOfBirth": "1990-01-01",
    "address": "123 Test Street",
    "phoneNumber": "0901234567",
    "email": "test@example.com",
    "ssn": "123456789"
  }'
```

#### Lấy danh sách khách hàng:
```bash
curl -X GET http://localhost:8080/api/customers
```

## Monitoring và Health Check

### Health Check:
```
GET http://localhost:8080/actuator/health
```

### Application Info:
```
GET http://localhost:8080/actuator/info
```

## Testing

### Chạy unit tests:
```bash
mvnw.cmd test
```

### Chạy integration tests:
```bash
mvnw.cmd verify
```

## Troubleshooting

### Lỗi thường gặp:

1. **Lỗi kết nối database:**
   - Kiểm tra MySQL Server đã chạy
   - Kiểm tra thông tin kết nối trong `application.properties`
   - Kiểm tra database đã được tạo

2. **Lỗi port 8080 đã được sử dụng:**
   - Thay đổi port trong `application.properties`: `server.port=8081`

3. **Lỗi Java version:**
   - Kiểm tra Java version: `java -version`
   - Cài đặt Java 21 nếu chưa có

4. **Lỗi Maven:**
   - Kiểm tra Maven: `mvn -version`
   - Sử dụng wrapper: `mvnw.cmd` (Windows) hoặc `./mvnw` (Linux/Mac)

## Đóng góp

1. Fork dự án
2. Tạo branch mới (`git checkout -b feature/amazing-feature`)
3. Commit thay đổi (`git commit -m 'Add amazing feature'`)
4. Push lên branch (`git push origin feature/amazing-feature`)
5. Tạo Pull Request

## Liên hệ

- Email: [your-email@example.com]
- GitHub: [your-github-profile]

## License

Dự án này được phát triển cho mục đích hackathon và học tập.

---

**Lưu ý:** Đây là phiên bản development, không sử dụng cho production environment.
