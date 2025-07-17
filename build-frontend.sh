#!/bin/bash

# Script to build React frontend and copy to Spring Boot static folder

echo "Building React frontend..."
cd frontend
npm run build

echo "Creating static directory if it doesn't exist..."
if [ ! -d "../src/main/resources/static" ]; then
    mkdir -p "../src/main/resources/static"
fi

echo "Copying build files to static folder..."
cp -r build/* ../src/main/resources/static/

echo "Build and copy completed successfully!"
echo "You can now run the Spring Boot application and access the frontend at http://localhost:8080"
