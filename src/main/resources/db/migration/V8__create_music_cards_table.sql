CREATE TYPE card_status_type AS ENUM ('to_learn', 'learning', 'learned');
CREATE TYPE card_difficulty_type AS ENUM ('easy', 'medium', 'hard');

CREATE TABLE music_cards (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id INT NOT NULL,
    status card_status_type NOT NULL,
    title VARCHAR(100) NOT NULL,
    artist VARCHAR(100) NOT NULL,
    difficulty card_difficulty_type NOT NULL,
    created_at TIMESTAMP NOT NULL
)