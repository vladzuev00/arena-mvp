INSERT INTO categories(id, external_id, name, primary_color, secondary_color)
VALUES(128, 255, 'first-category', '#90caef', '#faa538');

INSERT INTO promoters(id, external_id, name, icon_url, web_page_url)
VALUES(
       129,
       '550e8400-e29b-41d4-a716-446655440000',
       'first-promoter',
       'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2',
       'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a3'
);

INSERT INTO tags(id, external_id, name) VALUES(130, 255, 'first-tag');

INSERT INTO venues(id, external_id, name, address, latitude, longitude)
VALUES(131, '550e8400-e29b-41d4-a716-446655440000', 'first-name', 'first-address', 5.5, 6.6)