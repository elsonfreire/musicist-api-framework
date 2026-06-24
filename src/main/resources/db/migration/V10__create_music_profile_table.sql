CREATE TABLE music_profiles
(
    user_id         UUID                PRIMARY KEY
        REFERENCES users (id)
        ON DELETE CASCADE,
    instrument      instrument_type,
    level           level_type,
    favorite_genre  music_genre_type
);

CREATE TABLE music_profile_interests
(
    music_profile_id  UUID NOT NULL
        REFERENCES music_profiles (user_id)
        ON DELETE CASCADE,
    interest interest_type NOT NULL,

    CONSTRAINT pk_music_profile_interests
        PRIMARY KEY (music_profile_id, interest)
);

INSERT INTO music_profiles
    (user_id, instrument, level, favorite_genre)
    SELECT id, instrument::instrument_type, level::level_type, favorite_genre::music_genre_type
    FROM users;

INSERT INTO music_profile_interests
    (music_profile_id, interest)
    SELECT mp.user_id, ui.interest::interest_type
    FROM music_profiles mp
    JOIN user_interests ui
        ON ui.user_id = mp.user_id;


DROP TABLE user_interests;

ALTER TABLE users
    DROP COLUMN instrument,
    DROP COLUMN level,
    DROP COLUMN favorite_genre;