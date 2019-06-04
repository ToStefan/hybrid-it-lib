CREATE VIEW most_rented_view AS SELECT books.id as id, books.author as author, books.title as title, COUNT(books.id) as rented_count FROM rents JOIN books ON rents.book_id = books.id GROUP BY books.id ORDER BY books.id DESC;

INSERT INTO USERS(id, username, password, firstname, lastname) VALUES(1, 's', '$2a$10$qzTe6SMMLC29LXs3mTCE.uUAgWm4qmkhu4sOcjdy4IrevomZHpMMq', 'Stefan', 'Tofilovic');
INSERT INTO USERS(id, username, password, firstname, lastname) VALUES(2, 'v', '$2a$10$LcQfIdi0rTz0/YheLBPpKOQ77bj77P2EMWy9O1F6lJa0vM1eg7qXW', 'Viktor', 'Nikolic');

INSERT INTO ROLES(id, name) VALUES (1, 'ROLE_ADMINISTRATOR');
INSERT INTO ROLES(id, name) VALUES (2, 'ROLE_USER');

INSERT INTO USER_ROLES(user_id, role_id) VALUES(1, 1);
INSERT INTO USER_ROLES(user_id, role_id) VALUES(1, 2);
INSERT INTO USER_ROLES(user_id, role_id) VALUES(2, 2);

INSERT INTO BOOKS(id, author, title, total_copies, available_copies) VALUES(1, 'a1', 't1', 10, 8);
INSERT INTO BOOKS(id, author, title, total_copies, available_copies) VALUES(2, 'a2', 't2', 10, 10);
INSERT INTO BOOKS(id, author, title, total_copies, available_copies) VALUES(3, 'a3', 't3', 10, 9);


INSERT INTO RENTS(id, rent_date, user_id, book_id) VALUES(1, '2019-05-23', 1, 1);
INSERT INTO RENTS(id, rent_date, user_id, book_id) VALUES(2, '2019-05-23', 2, 1);
INSERT INTO RENTS(id, rent_date, user_id, book_id) VALUES(3, '2019-05-21', 2, 3);