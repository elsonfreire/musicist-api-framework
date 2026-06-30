ALTER TYPE topic_category_type RENAME TO topic_category_type_old;

CREATE TYPE topic_category_type AS ENUM (
    'TIPS',
    'TECHNICAL',
    'SOCIAL',
    'KNOWLEDGE',
    'RESOURCES'
);

ALTER TABLE topics
ALTER COLUMN category TYPE topic_category_type
USING (
    CASE category::text
        WHEN 'tips' THEN 'TIPS'
        WHEN 'technical' THEN 'TECHNICAL'
        WHEN 'social' THEN 'SOCIAL'
        WHEN 'theory' THEN 'KNOWLEDGE'
        WHEN 'tools' THEN 'RESOURCES'
    END
)::topic_category_type;

DROP TYPE topic_category_type_old;