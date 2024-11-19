DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS promoters;

CREATE TABLE categories(
    id SERIAL PRIMARY KEY,
    external_id INTEGER NOT NULL UNIQUE,
    name VARCHAR(256) NOT NULL,
    primary_color VARCHAR(256) NOT NULL,
    secondary_color VARCHAR(256) NOT NULL
);

CREATE TABLE tags(
    id SERIAL PRIMARY KEY,
    external_id INTEGER NOT NULL UNIQUE,
    name VARCHAR(256) NOT NULL
);

CREATE TABLE promoters(
    id SERIAL PRIMARY KEY,
    external_id UUID NOT NULL UNIQUE,
    name VARCHAR(256) NOT NULL,
    icon_url VARCHAR(256) NOT NULL,
    web_page_url VARCHAR(256) NOT NULL
);