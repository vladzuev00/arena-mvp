INSERT INTO providers(id, name) VALUES(1, 'KAKAVA'), (2, 'BELETIA');

INSERT INTO categories(id, external_id, name, primary_color, secondary_color, provider_id)
VALUES(1000, 2000, 'first-category', '#FFF', '#FFFA', 1),
      (1001, 2001, 'second-category', '#FFFFFFAA', '#56135a', 1);

INSERT INTO tags(id, name, provider_id) VALUES(1000, 'first-tag', 1), (1001, 'PROMOTION', 1);

INSERT INTO promoters(id, external_id, name, icon_url, web_page_url, provider_id)
VALUES(1000, '11aa329a-44a6-11ed-a81c-000d3a29937e', 'first-promoter', 'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2', 'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a3', 1),
      (1001, '11aa329a-44a6-11ed-a81c-000d3a29938e', 'Organizatorius Z, VŠĮ', 'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4', 'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5', 1);

-- INSERT INTO venues(id, external_id, name, address, latitude, longitude)
-- VALUES(1000, 'a60de864-5c52-11ee-a81c-000d3aa868a2', 'first-venue', 'first-address', 5.5, 6.6),
--       (1001, 'a60de864-5c52-11ee-a81c-000d3aa868a3', 'Šiaulių arena', 'ner, Vilnius, Lithuania', 7.7, 8.8);
--
-- INSERT INTO shows(id, external_short_id, title, subtitle, description, venue_id, image_url, promoter_id)
-- VALUES(1000, 2000, 'Šiaulių arena. Šiaulių „Šiauliai“ - Mažeikių „M Basket', 'first-subtitle', '<a href=\\ "https://cms.kakava.lt/api/assets/7b0d4de6-aa49-4bd6-bb9b-6c5f106118a3?q=d7fcefda-248a-0f6e-bc3e-20543c095f6f\" target=\\ "_blank\"> taisyklės  Rajoną pavadinti  Pilaite  nuspręsta ne iš karto. Buvo siūlomi įvairūs vietovardžio variantai, pavyzdžiui  Sudervėlė . Kalbininkui Jonui Jurkštui ir parkotyrininkui Kęstučiui Labanauskui pavyko įtikinti, kad šis pavadinimas yra autentiškas.  Pilaitė  – istorinis vietovardis, kilęs iš bendrinio žodžio „pilis“. Netoli Pilaitės XIX a. buvo dvi gyvenvietės: Papilėnai ir Papilaičiai. Pasak Jono Jurkšto, jie žymi vietas, esančias prie pilies (pilaitės), ir patvirtina, kad čia yra buvęs vietovardis  Pilaitė  ir tikrai yra stovėjusi pilis. Todėl  <a href=\\ "https://lt.wikipedia.org/wiki/Lenk%C5%B3_kalba\" title=\\ "Lenkų kalba\"> lenk.   Zameczek  yra vertinys iš žodžio pilaitė, o ne atvirkščiai, kaip manė kai kurie tyrinėtojai.', 1000, 'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2', 1000),
--       (1001, 2001, 'Dinamika', 'second-subtitle', '<a href=\\ "https://cms.kakava.lt/api/assets/7b0d4de6-aa49-4bd6-bb9b-6c5f106118a3?q=d7fcefda-248a-0f6e-bc3e-205095f6f\" target=\\ "_blank\"> taisyklės  Rajoną pavadinti  Pilaite  nuspręsta ne iš karto. Buvo siūlomi įvairūs vietovardžio variantai, pavyzdžiui  Sudervėlė . Kalbininkui Jonui Jurkštui ir parkotyrininkui Kęstučiui Labanauskui pavyko įtikinti, kad šis pavadinimas yra autentiškas.  Pilaitė – istorinis vietovardis, kilęs iš bendrinio žodžio „pilis“. Netoli Pilaitės XIX a. buvo dvi gyvenvietės: Papilėnai ir Papilaičiai. Pasak Jono Jurkšto, jie žymi vietas, esančias prie pilies (pilaitės), ir patvirtina, kad čia yra buvęs vietovardis  Pilaitė  ir tikrai yra stovėjusi pilis. Todėl  <a href=\\ "https://lt.wikipedia.org/wiki/Lenk%C5%B3_kalba\" title=\\ "Lenkų kalba\"> lenk.   Zameczek  yra vertinys iš žodžio pilaitė, o ne atvirkščiai, kaip manė kai kurie tyrinėtojai.', 1000, 'https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a3', 1000);
--
-- INSERT INTO shows_categories(show_id, category_id) VALUES(1000, 1000), (1000, 1001);
-- INSERT INTO shows_tags(show_id, tag_id) VALUES(1000, 1000), (1000, 1001);
--
-- INSERT INTO events(id, external_short_id, title, subtitle, description, date_time, show_id)
-- VALUES(1000, 2000, 'Šiaulių arena. Šiaulių „Šiauliai“ - Mažeikių „M Basket', 'first-subtitle', '<a href=\\ "https://cms.kakava.lt/api/assets/7b0d4de6-aa49-4bd6-bb9b-6c5f106118a3?q=d7fcefda-248a-0f6e-bc3e-20543c095f6f\" target=\\ "_blank\"> taisyklės  Rajoną pavadinti  Pilaite  nuspręsta ne iš karto. Buvo siūlomi įvairūs vietovardžio variantai, pavyzdžiui  Sudervėlė . Kalbininkui Jonui Jurkštui ir parkotyrininkui Kęstučiui Labanauskui pavyko įtikinti, kad šis pavadinimas yra autentiškas.  Pilaitė  – istorinis vietovardis, kilęs iš bendrinio žodžio „pilis“. Netoli Pilaitės XIX a. buvo dvi gyvenvietės: Papilėnai ir Papilaičiai. Pasak Jono Jurkšto, jie žymi vietas, esančias prie pilies (pilaitės), ir patvirtina, kad čia yra buvęs vietovardis  Pilaitė  ir tikrai yra stovėjusi pilis. Todėl  <a href=\\ "https://lt.wikipedia.org/wiki/Lenk%C5%B3_kalba\" title=\\ "Lenkų kalba\"> lenk.   Zameczek  yra vertinys iš žodžio pilaitė, o ne atvirkščiai, kaip manė kai kurie tyrinėtojai.', '2026-10-14 16:03:00', 1000),
--       (1001, 2001, 'Dinamika', 'second-subtitle', '<a href=\\ "https://cms.kakava.lt/api/assets/7b0d4de6-aa49-4bd6-bb9b-6c5f106118a3?q=d7fcefda-248a-0f6e-bc3e-20543c095f6f\" target=\\ "_blank\"> taisyklės  Rajoną pavadinti  Pilaite  nuspręsta ne iš karto. Buvo siūlomi įvairūs vietovardžio variantai, pavyzdžiui  Sudervėlė . Kalbininkui Jonui Jurkštui ir parkotyrininkui Kęstučiui Labanauskui pavyko įtikinti, kad šis pavadinimas yra autentiškas.  Pilaitė  – istorinis vietovardis, kilęs iš bendrinio žodžio „pilis“. Netoli Pilaitės XIX a. buvo dvi gyvenvietės: Papilėnai ir Papilaičiai. Pasak Jono Jurkšto, jie žymi vietas, esančias prie pilies (pilaitės), ir patvirtina, kad čia yra buvęs vietovardis Pilaitė ir tikrai yra stovėjusi pilis. Todėl <a href=\\ "https://lt.wikipedia.org/wiki/Lenk%C5%B3_kalba\" title=\\ "Lenkų kalba\"> lenk.   Zameczek  yra vertinys iš žodžio pilaitė, o ne atvirkščiai, kaip manė kai kurie tyrinėtojai.', '2026-11-18 13:19:00', 1000);