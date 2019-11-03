--liquibase formatted sql

CREATE TABLE history (
    id BIGSERIAL PRIMARY KEY,
    phone_id BIGINT NOT NULL REFERENCES phones,
    tester_id BIGINT NOT NULL REFERENCES testers,
    booked_on TIMESTAMP NOT NULL,
    returned_on TIMESTAMP
);