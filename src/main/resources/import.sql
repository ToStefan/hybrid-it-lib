INSERT INTO USERS(user_id, firstname, lastname, username, password) VALUES(1, 's', 't', 'Stefan', 'Tofilovic');
INSERT INTO USERS(user_id, firstname, lastname, username, password) VALUES(2, 'v', 'n', 'Viktor', 'Nikolic');

INSERT INTO ROLES(role_id, name) VALUES (1, 'ADMINISTRATOR');
INSERT INTO ROLES(role_id, name) VALUES (2, 'USER');

INSERT INTO USER_ROLES(user_id, role_id) VALUES(1, 1);
INSERT INTO USER_ROLES(user_id, role_id) VALUES(2, 2);


INSERT INTO BOOKS(book_id, author, title, total_copies, available_copies) VALUES(1, 'a1', 't1', 10, 8);
INSERT INTO BOOKS(book_id, author, title, total_copies, available_copies) VALUES(2, 'a2', 't2', 20, 20);
INSERT INTO BOOKS(book_id, author, title, total_copies, available_copies) VALUES(3, 'a3', 't3', 30, 29);

#YYYY-MM-dd
INSERT INTO RENTS(rent_id, rented, user_id, book_id) VALUES(1, '2019-05-22', 1, 1)
INSERT INTO RENTS(rent_id, rented, user_id, book_id) VALUES(2, '2019-05-22', 1, 3)
INSERT INTO RENTS(rent_id, rented, user_id, book_id) VALUES(3, '2019-05-21', 2, 1)