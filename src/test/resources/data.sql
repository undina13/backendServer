
INSERT INTO USERS(NAME, PASSWORD, EMAIL, ROLE)
VALUES ('user3', '$2a$12$ZHKME4W/x8G9RNNDy.hnKe6llXgdGLnDoDxFthnIrGRNxbs4H03a6', 'user1rhere@mail.ru', 'ADMIN');

INSERT INTO advertisements(name, description, contact, status, owner_id, image_file_name)
VALUES ('name1', 'description1', 'contact1', 'ACTIVE', 1, null );
INSERT INTO advertisements(name, description, contact, status, owner_id, image_file_name)
VALUES ('name2', 'description2', 'contact2', 'CANCELED', 1, null );
INSERT INTO advertisements(name, description, contact, status, owner_id, image_file_name)
VALUES ('name3', 'description3', 'contact3', 'ACTIVE', 2, null );

INSERT INTO messages(sender_id, recipient_id, msg, time)
VALUES (1, 2, 'hello1' , '2022-09-10T12:15' );
INSERT INTO messages(sender_id, recipient_id, msg, time)
VALUES (2, 1, 'hello2' , '2022-09-10T12:17' );
INSERT INTO messages(sender_id, recipient_id, msg, time)
VALUES (1, 2, 'hello3' , '2022-09-10T12:20' );