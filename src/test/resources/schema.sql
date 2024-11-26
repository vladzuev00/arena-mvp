DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS shows_categories;
DROP TABLE IF EXISTS shows;
DROP TYPE IF EXISTS sale_status;
DROP TABLE IF EXISTS venues;
DROP TABLE IF EXISTS promoters;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS providers;

CREATE TABLE providers(
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE
);

CREATE TABLE categories(
    id SERIAL PRIMARY KEY,
    external_id VARCHAR(256) NOT NULL,
    name VARCHAR(256) NOT NULL,
    hidden BOOLEAN NOT NULL,
    provider_id INTEGER NOT NULL
);

ALTER TABLE categories ADD CONSTRAINT fk_categories_to_providers FOREIGN KEY (provider_id) REFERENCES providers(id);
ALTER TABLE categories ADD UNIQUE (external_id, provider_id);

CREATE TABLE promoters(
    id SERIAL PRIMARY KEY,
    external_id VARCHAR(256) NOT NULL,
    name VARCHAR(256) NOT NULL,
    address VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL,
    phone VARCHAR(256) NOT NULL,
    image_url VARCHAR(256) NOT NULL,
    external_url VARCHAR(256) NOT NULL,
    provider_id INTEGER NOT NULL
);

ALTER TABLE promoters ADD CONSTRAINT fk_promoters_to_providers FOREIGN KEY (provider_id) REFERENCES providers(id);
ALTER TABLE promoters ADD UNIQUE (external_id, provider_id);

CREATE TABLE venues(
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    address VARCHAR(256) NOT NULL,
    latitude DECIMAL NOT NULL,
    longitude DECIMAL NOT NULL,
    image_url VARCHAR(256) NOT NULL
);

CREATE TYPE sale_status AS ENUM ('SELLING');

CREATE TABLE shows(
    id SERIAL PRIMARY KEY,
    external_id VARCHAR(256) NOT NULL,
    name VARCHAR(256) NOT NULL,
    description TEXT NOT NULL,
    start_date_time TIMESTAMP(0) NOT NULL,
    end_date_time TIMESTAMP(0) NOT NULL,
    youtube_url VARCHAR(256) NOT NULL,
    discount VARCHAR(256) NOT NULL,
    image_url VARCHAR(256) NOT NULL,
    sale_status sale_status NOT NULL,
    duration VARCHAR(256) NOT NULL,
    tags TEXT NOT NULL,
    price_from INTEGER NOT NULL,
    price_from_with_taxes INTEGER NOT NULL,
    club_tickets_available BOOLEAN NOT NULL,
    venue_id INTEGER NOT NULL,
    promoter_id INTEGER NOT NULL,
    provider_id INTEGER NOT NULL
);

ALTER TABLE shows ADD CONSTRAINT fk_shows_to_venues FOREIGN KEY (venue_id) REFERENCES venues(id);
ALTER TABLE shows ADD CONSTRAINT fk_shows_to_promoters FOREIGN KEY (promoter_id) REFERENCES promoters(id);
ALTER TABLE shows ADD CONSTRAINT fk_shows_to_providers FOREIGN KEY (provider_id) REFERENCES promoters(id);
ALTER TABLE shows ADD UNIQUE (external_id, provider_id);

CREATE TABLE shows_categories(
    show_id INTEGER,
    category_id INTEGER
);

ALTER TABLE shows_categories ADD PRIMARY KEY (show_id, category_id);
ALTER TABLE shows_categories ADD CONSTRAINT fk_shows_categories_to_shows FOREIGN KEY (show_id) REFERENCES shows(id);
ALTER TABLE shows_categories ADD CONSTRAINT fk_shows_categories_to_categories FOREIGN KEY (category_id) REFERENCES categories(id);

CREATE TABLE events(
    id SERIAL PRIMARY KEY,
    external_id VARCHAR(256) NOT NULL,
    show_id INTEGER NOT NULL,
    provider_id INTEGER NOT NULL
);

ALTER TABLE events ADD UNIQUE (external_short_id, provider_id);
ALTER TABLE events ADD CONSTRAINT fk_events_to_shows FOREIGN KEY (show_id) REFERENCES shows(id);
ALTER TABLE events ADD CONSTRAINT fk_events_to_providers FOREIGN KEY (provider_id) REFERENCES providers(id);