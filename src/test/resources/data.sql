INSERT INTO categories(id, external_id, name, primary_color, secondary_color)
VALUES(1000, 2000, 'first-category', '#FFF', '#FFFA'),
      (1001, 2001, 'second-category', '#FFFFFFAA', '#56135a');

INSERT INTO promoters(id, external_id, name, icon_url, web_page_url)
VALUES(1000, '11aa329a-44a6-11ed-a81c-000d3a29937e', 'first-promoter', 'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2', 'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a3'),
      (1001, '11aa329a-44a6-11ed-a81c-000d3a29938e', 'Organizatorius Z, VŠĮ', 'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4', 'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5');

INSERT INTO tags(id, name) VALUES(1000, 'first-tag'),
                                 (1001, 'PROMOTION');

--TODO исправить
INSERT INTO venues(id, external_id, name, address, latitude, longitude)
VALUES(1000, '550e8400-e29b-41d4-a716-446655440000', 'first-name', 'first-address', 5.5, 6.6),
      (1001, '550e8400-e29b-41d4-a716-446655440001', 'second-name', 'second-address', 7.7, 8.8);

-- INSERT INTO shows(id, external_short_id, title, subtitle, description, category_id, venue_id, image_url)
-- VALUES(1000, 2000, 'first-title', 'first-subtitle', 'first-description', 1000, 1000, 'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2'),
--       (1001, 2001, 'second-title', 'second-subtitle', 'second-description', 1001, 1001, 'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a3');
--
-- INSERT INTO events(id, external_short_id, title, subtitle, description, date_time, show_id)
-- VALUES(1000, 2000, 'first-title', 'first-subtitle', 'first-description', '2005-04-02 12:00:00', 1000);