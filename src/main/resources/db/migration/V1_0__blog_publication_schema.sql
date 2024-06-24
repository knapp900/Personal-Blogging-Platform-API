CREATE SCHEMA IF NOT EXISTS publications 
(

CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    CHECK (char_length(name) >= 2 AND char_length(name) <= 30)
);

CREATE TABLE publication (
    id SERIAL PRIMARY KEY,
    title VARCHAR NOT NULL,
    creationDateTime TIMESTAMP,
    publishedDate TIMESTAMP,
    isPublished BOOLEAN,
    content TEXT NOT NULL
);

CREATE TABLE publication_authors (
    publication_id INTEGER NOT NULL,
    author_id INTEGER NOT NULL,
    PRIMARY KEY (publication_id, author_id),
    FOREIGN KEY (publication_id) REFERENCES publication(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);

	
);