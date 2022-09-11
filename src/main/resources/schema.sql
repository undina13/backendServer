drop table if exists users, advertisements;

CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     name VARCHAR(255) NOT NULL,
                                     password VARCHAR(512) NOT NULL,
                                     email VARCHAR(512) NOT NULL,
                                     role VARCHAR(255) NOT NULL,
                                     CONSTRAINT pk_user PRIMARY KEY (id),
                                     CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS advertisements (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     name VARCHAR(255) NOT NULL,
                                     description VARCHAR(255) NOT NULL,
                                     contact VARCHAR(255) NOT NULL,
                                     status VARCHAR(512) NOT NULL,
                                     owner_id BIGINT NOT NULL,
                                     image_file_name VARCHAR(512),
                                     CONSTRAINT pk_advertisement PRIMARY KEY (id),
                                     CONSTRAINT fk_user FOREIGN KEY (owner_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS messages (
                                              id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                              sender_id BIGINT NOT NULL,
                                              recipient_id BIGINT NOT NULL,
                                              msg VARCHAR(512) NOT NULL,
                                              time TIMESTAMP  NOT NULL,
                                              CONSTRAINT pk_message PRIMARY KEY (id),
                                              CONSTRAINT fk_sender FOREIGN KEY (sender_id) REFERENCES users (id) ON DELETE CASCADE,
                                              CONSTRAINT fk_recipient FOREIGN KEY (recipient_id) REFERENCES users (id) ON DELETE CASCADE
);