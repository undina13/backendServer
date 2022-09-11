-- DELETE FROM users;

-- INSERT INTO users(name, password, email, role)
-- VALUES ('user', '123', 'user1@mail.ru', 'USER');
-- INSERT INTO USERS(NAME, PASSWORD, EMAIL, ROLE)
-- VALUES ('user2', '123', 'user1rere@mail.ru', 'ADMIN');
INSERT INTO USERS(NAME, PASSWORD, EMAIL, ROLE)
VALUES ('user3', '$2a$12$ZHKME4W/x8G9RNNDy.hnKe6llXgdGLnDoDxFthnIrGRNxbs4H03a6', 'user1rhere@mail.ru', 'ADMIN');

INSERT INTO advertisements(name, description, contact, status, owner_id, image_file_name)
VALUES ('name1', 'description1', 'contact1', 'ACTIVE', 1, null );
INSERT INTO advertisements(name, description, contact, status, owner_id, image_file_name)
VALUES ('name2', 'description2', 'contact2', 'CANCELED', 1, null );
INSERT INTO advertisements(name, description, contact, status, owner_id, image_file_name)
VALUES ('name3', 'description3', 'contact3', 'ACTIVE', 2, null );