CREATE DATABASE productservice;
CREATE USER productserviceuser IDENTIFIED BY "password";
GRANT ALL PRIVILEGES ON productservice.* TO productserviceuser;