DROP TABLE user_interests;

ALTER TABLE users
    DROP COLUMN instrument,
    DROP COLUMN level,
    DROP COLUMN favorite_genre;