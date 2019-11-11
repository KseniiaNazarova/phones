--liquibase formatted sql

--changeset ks:testers_added
CREATE TABLE testers (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    email VARCHAR(30)
);