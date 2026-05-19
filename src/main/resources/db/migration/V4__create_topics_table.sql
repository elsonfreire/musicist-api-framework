CREATE TYPE topic_category_type AS ENUM ('tips', 'technical', 'social', 'theory', 'tools');

CREATE TABLE topics
(
    id          UUID                NOT NULL PRIMARY KEY,
    title       VARCHAR(100)        NOT NULL,
    category    topic_category_type NOT NULL,
    description TEXT                NOT NULL,
    user_id     UUID                NOT NULL,
    created_at  TIMESTAMP           NOT NULL
);