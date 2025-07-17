# Test Faker API

## Lệnh curl để test các endpoint:

### 1. Kiểm tra thông tin API
```bash
curl -X GET http://localhost:8080/api/faker/info
```

### 2. Tạo dữ liệu giả cho tất cả bảng
```bash
curl -X POST http://localhost:8080/api/faker/generate-all \
  -H "Content-Type: application/json"
```

### 3. Tạo dữ liệu cho từng bảng riêng lẻ

#### Tạo chi nhánh
```bash
curl -X POST http://localhost:8080/api/faker/generate-branches \
  -H "Content-Type: application/json"
```

#### Tạo khách hàng
```bash
curl -X POST http://localhost:8080/api/faker/generate-customers \
  -H "Content-Type: application/json"
```

#### Tạo nhân viên
```bash
curl -X POST http://localhost:8080/api/faker/generate-employees \
  -H "Content-Type: application/json"
```

#### Tạo tài khoản
```bash
curl -X POST http://localhost:8080/api/faker/generate-accounts \
  -H "Content-Type: application/json"
```

#### Tạo thẻ
```bash
curl -X POST http://localhost:8080/api/faker/generate-cards \
  -H "Content-Type: application/json"
```

#### Tạo giao dịch
```bash
curl -X POST http://localhost:8080/api/faker/generate-transactions \
  -H "Content-Type: application/json"
```

#### Tạo khoản vay
```bash
curl -X POST http://localhost:8080/api/faker/generate-loans \
  -H "Content-Type: application/json"
```

#### Tạo cổ phiếu
```bash
curl -X POST http://localhost:8080/api/faker/generate-stocks \
  -H "Content-Type: application/json"
```

#### Tạo danh mục đầu tư
```bash
curl -X POST http://localhost:8080/api/faker/generate-portfolios \
  -H "Content-Type: application/json"
```

### 4. Xóa tất cả dữ liệu
```bash
curl -X DELETE http://localhost:8080/api/faker/clear-all
```

## PowerShell Commands (cho Windows):

### 1. Test thông tin API
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/faker/info" -Method GET
```

### 2. Tạo tất cả dữ liệu
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/faker/generate-all" -Method POST -ContentType "application/json"
```

### 3. Tạo chi nhánh
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/faker/generate-branches" -Method POST -ContentType "application/json"
```

### 4. Xóa dữ liệu
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/faker/clear-all" -Method DELETE
```

## Test với Postman

1. Tạo collection mới: "VBBank Faker API"
2. Base URL: `http://localhost:8080/api/faker`
3. Tạo các request sau:

### GET Info
- Method: GET
- URL: `{{baseUrl}}/info`

### POST Generate All
- Method: POST
- URL: `{{baseUrl}}/generate-all`
- Headers: Content-Type: application/json

### POST Generate Branches
- Method: POST
- URL: `{{baseUrl}}/generate-branches`
- Headers: Content-Type: application/json

### DELETE Clear All
- Method: DELETE
- URL: `{{baseUrl}}/clear-all`

## Workflow test

1. Khởi động ứng dụng: `./mvnw spring-boot:run`
2. Đợi ứng dụng start (thường 20-30 giây)
3. Test API info: `GET /api/faker/info`
4. Tạo dữ liệu: `POST /api/faker/generate-all`
5. Kiểm tra database để xem dữ liệu đã được tạo
6. Xóa dữ liệu: `DELETE /api/faker/clear-all`
