CREATE TABLE IF NOT EXISTS games
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR(255)     NOT NULL,
    genre        VARCHAR(255)     NOT NULL,
    price        double precision NOT NULL,
    rating       double precision,
    description  TEXT,
    release_date DATE NOT NULL,
    added_date   DATE DEFAULT CURRENT_DATE NOT NULL
);