DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS shows_categories;
DROP TABLE IF EXISTS shows_tags;
DROP TABLE IF EXISTS shows;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS promoters;
DROP TABLE IF EXISTS venues;
DROP TABLE IF EXISTS providers;

CREATE TABLE providers(
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE
);

CREATE TABLE categories(
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE,
    primary_color VARCHAR(256) NOT NULL,
    secondary_color VARCHAR(256) NOT NULL
);

CREATE TABLE tags(
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE
);

CREATE TABLE promoters(
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE,
    icon_url VARCHAR(256) NOT NULL,
    web_page_url VARCHAR(256) NOT NULL
);

CREATE TABLE venues(
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE,
    address VARCHAR(256) NOT NULL,
    latitude DECIMAL NOT NULL,
    longitude DECIMAL NOT NULL
);

CREATE TABLE shows(
    id SERIAL PRIMARY KEY,
    external_short_id VARCHAR(256) NOT NULL,
    title VARCHAR(256) NOT NULL,
    subtitle VARCHAR(256) NOT NULL,
    description TEXT NOT NULL,
    venue_id INTEGER NOT NULL,
    image_url VARCHAR(256) NOT NULL,
    promoter_id INTEGER NOT NULL,
    provider_id INTEGER NOT NULL
);

ALTER TABLE shows ADD UNIQUE (external_short_id, provider_id);
ALTER TABLE shows ADD CONSTRAINT fk_shows_to_venues FOREIGN KEY (venue_id) REFERENCES venues(id);
ALTER TABLE shows ADD CONSTRAINT fk_shows_to_promoters FOREIGN KEY (promoter_id) REFERENCES promoters(id);
ALTER TABLE shows ADD CONSTRAINT fk_shows_to_providers FOREIGN KEY (provider_id) REFERENCES providers(id);

CREATE TABLE shows_categories(
    show_id INTEGER,
    category_id INTEGER
);

ALTER TABLE shows_categories ADD PRIMARY KEY (show_id, category_id);
ALTER TABLE shows_categories ADD CONSTRAINT fk_shows_categories_to_shows FOREIGN KEY (show_id) REFERENCES shows(id);
ALTER TABLE shows_categories ADD CONSTRAINT fk_shows_categories_to_categories FOREIGN KEY (category_id) REFERENCES categories(id);

CREATE TABLE shows_tags(
    show_id INTEGER,
    tag_id INTEGER
);

ALTER TABLE shows_tags ADD PRIMARY KEY (show_id, tag_id);
ALTER TABLE shows_tags ADD CONSTRAINT fk_shows_tags_to_shows FOREIGN KEY (show_id) REFERENCES shows(id);
ALTER TABLE shows_tags ADD CONSTRAINT fk_shows_tags_to_tags FOREIGN KEY (tag_id) REFERENCES tags(id);

CREATE TABLE events(
    id SERIAL PRIMARY KEY,
    external_short_id VARCHAR(256) NOT NULL,
    title VARCHAR(256) NOT NULL,
    subtitle VARCHAR(256) NOT NULL,
    description TEXT NOT NULL,
    date_time TIMESTAMP(0) NOT NULL,
    show_id INTEGER NOT NULL,
    provider_id INTEGER NOT NULL
);

ALTER TABLE events ADD UNIQUE (external_short_id, provider_id);
ALTER TABLE events ADD CONSTRAINT fk_events_to_shows FOREIGN KEY (show_id) REFERENCES shows(id);
ALTER TABLE events ADD CONSTRAINT fk_events_to_providers FOREIGN KEY (provider_id) REFERENCES providers(id);