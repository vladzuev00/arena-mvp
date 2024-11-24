DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS shows_categories;
DROP TABLE IF EXISTS shows_tags;
DROP TABLE IF EXISTS shows;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS promoters;
DROP TABLE IF EXISTS venues;
DROP PROCEDURE IF EXISTS refresh_show(INTEGER, VARCHAR, VARCHAR, VARCHAR, UUID, VARCHAR, UUID, INTEGER[], VARCHAR[]);

CREATE TABLE categories(
    id SERIAL PRIMARY KEY,
    external_id INTEGER NOT NULL UNIQUE,
    name VARCHAR(256) NOT NULL,
    primary_color VARCHAR(256) NOT NULL,
    secondary_color VARCHAR(256) NOT NULL
);

CREATE TABLE tags(
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE
);

CREATE TABLE promoters(
    id SERIAL PRIMARY KEY,
    external_id UUID NOT NULL UNIQUE,
    name VARCHAR(256) NOT NULL,
    icon_url VARCHAR(256) NOT NULL,
    web_page_url VARCHAR(256) NOT NULL
);

CREATE TABLE venues(
    id SERIAL PRIMARY KEY,
    external_id UUID NOT NULL UNIQUE,
    name VARCHAR(256) NOT NULL,
    address VARCHAR(256) NOT NULL,
    latitude DECIMAL NOT NULL,
    longitude DECIMAL NOT NULL
);

CREATE TABLE shows(
    id SERIAL PRIMARY KEY,
    external_short_id INTEGER NOT NULL UNIQUE,
    title VARCHAR(256) NOT NULL,
    subtitle VARCHAR(256) NOT NULL,
    description TEXT NOT NULL,
    venue_id INTEGER NOT NULL,
    image_url VARCHAR(256) NOT NULL,
    promoter_id INTEGER NOT NULL
);

ALTER TABLE shows ADD CONSTRAINT fk_shows_to_venues FOREIGN KEY (venue_id) REFERENCES venues(id);
ALTER TABLE shows ADD CONSTRAINT fk_shows_to_promoters FOREIGN KEY (promoter_id) REFERENCES promoters(id);

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
    external_short_id INTEGER NOT NULL UNIQUE,
    title VARCHAR(256) NOT NULL,
    subtitle VARCHAR(256) NOT NULL,
    description TEXT NOT NULL,
    date_time TIMESTAMP(0) NOT NULL,
    show_id INTEGER NOT NULL
);

ALTER TABLE events ADD CONSTRAINT fk_events_to_shows FOREIGN KEY (show_id) REFERENCES shows(id);

CREATE OR REPLACE PROCEDURE refresh_show(
    in_external_short_id INTEGER,
    in_title VARCHAR,
    in_subtitle VARCHAR,
    in_description VARCHAR,
    in_venue_external_id UUID,
    in_image_url VARCHAR,
    in_promoter_external_id UUID,
    in_category_external_ids INTEGER[],
    in_tag_names VARCHAR[]
)
AS '
DECLARE
    refreshed_show_id INTEGER;
	category_external_id INTEGER;
	tag_name VARCHAR;
BEGIN
  INSERT INTO shows(external_short_id, title, subtitle, description, venue_id, image_url, promoter_id)
  VALUES(
      in_external_short_id,
      in_title,
      in_subtitle,
      in_description,
      (SELECT id FROM venues WHERE external_id = in_venue_external_id),
      in_image_url,
      (SELECT id FROM promoters WHERE external_id = in_promoter_external_id)
  )
  ON CONFLICT (external_short_id) DO
  UPDATE SET
      title = in_title,
      subtitle = in_subtitle,
      description = in_description,
      venue_id = (SELECT id FROM venues WHERE external_id = in_venue_external_id),
      image_url = in_image_url,
      promoter_id = (SELECT id FROM promoters WHERE external_id = in_promoter_external_id)
  WHERE shows.external_short_id = in_external_short_id
  RETURNING id
  INTO refreshed_show_id;

  DELETE FROM shows_categories WHERE show_id = refreshed_show_id;
  FOREACH category_external_id IN ARRAY in_category_external_ids
  LOOP
     INSERT INTO shows_categories(show_id, category_id)
     VALUES (refreshed_show_id, (SELECT id FROM categories WHERE external_id = category_external_id));
  END LOOP;

  DELETE FROM shows_tags WHERE show_id = refreshed_show_id;
  FOREACH tag_name IN ARRAY in_tag_names
  LOOP
     INSERT INTO shows_tags(show_id, tag_id)
     VALUES (refreshed_show_id, (SELECT id FROM tags WHERE name = tag_name));
  END LOOP;

END;
' LANGUAGE plpgsql;