--changeset: create type for book status
CREATE TYPE book_status AS ENUM ('AVAILABLE', 'ISSUED');

--changeset: create table books
CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    status book_status NOT NULL DEFAULT 'AVAILABLE',
    user_id BIGINT
)