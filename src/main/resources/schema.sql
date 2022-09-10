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

                                     user_id BIGINT NOT NULL,
                                     imageFileName VARCHAR(512),
                                     CONSTRAINT pk_item PRIMARY KEY (id),
                                     CONSTRAINT fk_owner FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

