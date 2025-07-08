CREATE USER book_service with PASSWORD 'book_service';
CREATE SCHEMA book_service AUTHORIZATION book_service;
ALTER USER book_service SET search_path = 'book_service';
