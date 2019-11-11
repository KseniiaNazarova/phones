--liquibase formatted sql

--changeset ks:phones_added
CREATE TABLE phones (
    id BIGSERIAL PRIMARY KEY,
    brand VARCHAR(30) NOT NULL,
    model VARCHAR(30) NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE (brand, model)
);

INSERT INTO phones (brand, model) VALUES ('SAMSUNG', 'GALAXY S9');
INSERT INTO phones (brand, model) VALUES ('SAMSUNG', 'GALAXY S8');
INSERT INTO phones (brand, model) VALUES ('SAMSUNG', 'GALAXY S7');
INSERT INTO phones (brand, model) VALUES ('MOTOROLA', 'NEXUS 6');
INSERT INTO phones (brand, model) VALUES ('LG', 'NEXUS 5X');
INSERT INTO phones (brand, model) VALUES ('HUAWEI', 'HONOR 7X');
INSERT INTO phones (brand, model) VALUES ('APPLE', 'IPHONE X');
INSERT INTO phones (brand, model) VALUES ('APPLE', 'IPHONE 8');
INSERT INTO phones (brand, model) VALUES ('APPLE', 'IPHONE 4S');
INSERT INTO phones (brand, model) VALUES ('NOKIA', '3310');