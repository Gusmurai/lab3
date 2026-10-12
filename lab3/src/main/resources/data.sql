INSERT INTO nationality (name) VALUES ('Русский'), ('Татарин'), ('Чуваш');

INSERT INTO address (zip_code, country, region, city, street, house, apartment)
VALUES ('101000', 'Россия', 'Московская область', 'Москва', 'Арбат', '10', '15'),
       ('420000', 'Россия', 'Республика Татарстан', 'Казань', 'Баумана', '5', '42');

INSERT INTO person (last_name, first_name, middle_name, gender, height, weight, birth_date, phone_number, nationality_id, address_id)
VALUES ('Иванов', 'Иван', 'Иванович', 'Мужской', 180, 75.5, '1995-05-15', '+79991234567', 1, 1),
       ('Петрова', 'Анна', 'Сергеевна', 'Женский', 165, 55.0, '2000-11-20', '+79997654321', 1, 2);