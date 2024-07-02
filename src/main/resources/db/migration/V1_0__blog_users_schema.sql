CREATE SCHEMA IF NOT EXISTS blog_users

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    nickname VARCHAR(30) NOT NULL,
    firstname VARCHAR(30) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    date_of_creation DATE NOT NULL,
    is_active BOOLEAN,
    CHECK (char_length(nickname) >= 2 AND char_length(nickname) <= 30),
    CHECK (char_length(firstname) >= 2 AND char_length(firstname) <= 30),
    CHECK (char_length(lastname) >= 2 AND char_length(lastname) <= 30),
    CHECK (char_length(password) >= 6 AND char_length(password) <= 30)
);

CREATE TABLE user_roles (
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    role VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, role)
);

