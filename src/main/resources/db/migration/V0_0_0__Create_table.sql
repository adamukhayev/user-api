CREATE SCHEMA IF NOT EXISTS main;

CREATE SEQUENCE IF NOT EXISTS  main.users_user_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE main.users
(
    user_id     INTEGER DEFAULT
                            nextval('main.users_user_id_seq'::regclass) PRIMARY KEY NOT NULL,
    email       VARCHAR                                                             NOT NULL,
    password    VARCHAR                                                             NOT NULL,
    create_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    is_active   VARCHAR(20)                                                         NOT NULL
);

INSERT INTO main.users (email, password, create_date, is_active)
VALUES ('admin@mail.ru', '$2y$12$VqyPIlBTTxNAdJuv2i6w/OA3rEDuJVDffk9LRnb8SNA2F67mnLe/C',
        '2018-09-06 11:13:07.556', 'ACTIVE');

CREATE SEQUENCE IF NOT EXISTS  main.roles_role_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE main.roles
(
    role_id INTEGER DEFAULT nextval('main.roles_role_id_seq'::regclass) NOT NULL,
    role    VARCHAR                                                     NOT NULL,
    user_id INTEGER                                                     NOT NULL,
    FOREIGN KEY (user_id) references main.users (user_id)
);

INSERT INTO main.roles(role, user_id)
VALUES ('ADMIN', 100)