DROP TABLE IF EXISTS sample;

CREATE TABLE sample
(
    user_id     IDENTITY        PRIMARY KEY,
    name   VARCHAR(255)    NOT NULL
);