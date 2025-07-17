@echo off
echo Building React frontend...
cd frontend
call npm run build

echo Creating static directory if it doesn't exist...
if not exist "..\src\main\resources\static" mkdir "..\src\main\resources\static"

echo Copying build files to static folder...
xcopy /E /I /Y build\* ..\src\main\resources\static\

echo Build and copy completed successfully!
echo You can now run the Spring Boot application and access the frontend at http://localhost:8080
pause
