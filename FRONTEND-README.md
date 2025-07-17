# VBBank Hackathon - Transaction Management System

## Mô tả

Ứng dụng web để tra cứu giao dịch của khách hàng VBBank, bao gồm:
- Backend: Spring Boot REST API
- Frontend: React.js
- Database: MySQL/PostgreSQL

## Cấu trúc dự án

```
VBbank-hackathon-BE-git/
├── frontend/                 # React frontend
│   ├── src/
│   │   ├── App.js           # Component chính
│   │   ├── App.css          # Styles
│   │   └── ...
│   └── package.json
├── src/main/                # Spring Boot backend
│   ├── java/
│   │   └── com/vbbankhackathon/backend/
│   │       ├── controller/
│   │       ├── entity/
│   │       ├── repository/
│   │       └── ...
│   └── resources/
│       ├── static/          # Frontend build files (sau khi build)
│       └── application.properties
├── build-frontend.bat       # Script build cho Windows
├── build-frontend.sh        # Script build cho Linux/Mac
└── README.md
```

## Tính năng

### Frontend
- **Trang chủ**: Form nhập ID khách hàng
- **Tra cứu giao dịch**: Hiển thị danh sách giao dịch theo customer ID
- **Thông tin khách hàng**: Hiển thị thông tin cơ bản của khách hàng
- **Responsive design**: Tối ưu cho mobile và desktop
- **Error handling**: Xử lý lỗi khi không tìm thấy khách hàng

### Backend APIs
- `GET /api/customers/{id}` - Lấy thông tin khách hàng
- `GET /api/transactions/customer/{customerId}` - Lấy giao dịch theo customer ID
- `GET /api/transactions` - Lấy tất cả giao dịch
- `GET /api/transactions/{id}` - Lấy giao dịch theo ID

## Hướng dẫn cài đặt

### Prerequisites
- Node.js (v14 hoặc cao hơn)
- npm hoặc yarn
- Java 17 hoặc cao hơn
- Maven
- MySQL/PostgreSQL

### Bước 1: Cài đặt dependencies cho frontend

```bash
cd frontend
npm install
```

### Bước 2: Build frontend và copy vào backend

**Trên Windows:**
```bash
./build-frontend.bat
```

**Trên Linux/Mac:**
```bash
chmod +x build-frontend.sh
./build-frontend.sh
```

**Hoặc thủ công:**
```bash
cd frontend
npm run build
cp -r build/* ../src/main/resources/static/
```

### Bước 3: Chạy backend

```bash
./mvnw spring-boot:run
```

Hoặc:

```bash
mvn spring-boot:run
```

### Bước 4: Truy cập ứng dụng

Mở trình duyệt và truy cập: `http://localhost:8080`

## Cách sử dụng

1. **Nhập ID khách hàng**: Trong trang chủ, nhập ID của khách hàng cần tra cứu
2. **Xem thông tin**: Sau khi nhập ID hợp lệ, hệ thống sẽ hiển thị:
   - Thông tin cơ bản của khách hàng
   - Danh sách tất cả giao dịch của khách hàng
3. **Tra cứu khách hàng khác**: Nhấn nút "Tra cứu khách hàng khác" để quay lại trang chủ

## Cấu trúc Database

### Bảng Customers
- `CustomerId` (Primary Key)
- `FirstName`, `LastName`
- `Email`, `PhoneNumber`
- `Status`, `Address`
- Các thông tin khác...

### Bảng Accounts
- `AccountId` (Primary Key)
- `CustomerId` (Foreign Key)
- `AccountType`, `Balance`
- `Status`, `OpeningDate`
- Các thông tin khác...

### Bảng Transactions
- `TransactionId` (Primary Key)
- `AccountId` (Foreign Key)
- `TransactionType`, `Amount`
- `TransactionDate`, `Description`
- `TransactionMethod`, `Currency`
- Các thông tin khác...

## Development

### Frontend Development
```bash
cd frontend
npm start
```
Ứng dụng sẽ chạy trên `http://localhost:3000`

### Backend Development
```bash
./mvnw spring-boot:run
```
API sẽ chạy trên `http://localhost:8080`

## Lưu ý

- Đảm bảo backend đang chạy trên port 8080
- Frontend được build và serve thông qua Spring Boot
- CORS đã được cấu hình cho phép frontend gọi API
- Responsive design hỗ trợ mobile và desktop

## Troubleshooting

### Lỗi CORS
- Đảm bảo `@CrossOrigin(originPatterns = "*")` có trong các Controller

### Lỗi kết nối Database
- Kiểm tra cấu hình trong `application.properties`
- Đảm bảo database đang chạy

### Lỗi build frontend
- Kiểm tra Node.js và npm version
- Chạy `npm install` để cài đặt dependencies

### Lỗi không tìm thấy static files
- Đảm bảo đã build frontend và copy vào `src/main/resources/static/`
- Kiểm tra đường dẫn trong script build
