CREATE TABLE IF NOT EXISTS games
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR(255)     NOT NULL,
    genre        VARCHAR(255)     NOT NULL,
    price        double precision NOT NULL,
    rating       double precision,
    description  VARCHAR(255),
    release_date DATE,
    added_date   DATE DEFAULT CURRENT_DATE
);