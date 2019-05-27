INSERT INTO USERS(id, firstname, lastname, username, password) VALUES(1, 's', 't', 'Stefan', 'Tofilovic');
INSERT INTO USERS(id, firstname, lastname, username, password) VALUES(2, 'v', 'n', 'Viktor', 'Nikolic');

INSERT INTO ROLES(id, name) VALUES (1, 'ADMINISTRATOR');
INSERT INTO ROLES(id, name) VALUES (2, 'USER');

INSERT INTO USER_ROLES(user_id, role_id) VALUES(1, 1);
INSERT INTO USER_ROLES(user_id, role_id) VALUES(2, 2);


INSERT INTO BOOKS(id, author, title, total_copies, available_copies) VALUES(1, 'a1', 't1', 10, 8);
INSERT INTO BOOKS(id, author, title, total_copies, available_copies) VALUES(2, 'a2', 't2', 20, 20);
INSERT INTO BOOKS(id, author, title, total_copies, available_copies) VALUES(3, 'a3', 't3', 30, 29);


INSERT INTO RENTS(id, rent_date, user_id, book_id) VALUES(1, '2019-05-23', 1, 3)
INSERT INTO RENTS(id, rent_date, user_id, book_id) VALUES(2, '2019-05-23', 2, 3)
INSERT INTO RENTS(id, rent_date, user_id, book_id) VALUES(3, '2019-05-21', 2, 1)