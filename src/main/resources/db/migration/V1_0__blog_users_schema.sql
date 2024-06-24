CREATE SCHEMA IF NOT EXISTS blog_users

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    nickname VARCHAR(30) NOT NULL,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    c_password VARCHAR(30) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    date_of_creation DATE NOT NULL,
    is_active BOOLEAN,
    CHECK (char_length(nickname) >= 2 AND char_length(nickname) <= 30),
    CHECK (char_length(firstName) >= 2 AND char_length(firstName) <= 30),
    CHECK (char_length(lastName) >= 2 AND char_length(lastName) <= 30),
    CHECK (char_length(c_password) >= 6 AND char_length(c_password) <= 30)
);

CREATE TABLE user_roles (
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    role VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, role)
);

