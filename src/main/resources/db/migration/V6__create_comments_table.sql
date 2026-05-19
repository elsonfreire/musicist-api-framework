CREATE TABLE comments
(
    id         UUID      NOT NULL PRIMARY KEY,
    content    TEXT      NOT NULL,
    author_id  UUID      NOT NULL,
    topic_id   UUID      NOT NULL,
    created_at TIMESTAMP NOT NULL
);