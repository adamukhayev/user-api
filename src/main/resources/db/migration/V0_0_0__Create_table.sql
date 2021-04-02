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
    create_date TIMESTAMP WITHOUT TIME ZONE                                         NOT NULL,
    role        VARCHAR(20)                                                         NOT NULL,
    is_active   VARCHAR(20)                                                         NOT NULL
);

INSERT INTO main.users (email, password, create_date, is_active, role)
VALUES ('admin@mail.ru', '$2y$12$MmnZuTjlexZR8MLpztKO/OBP9QSHvAeK0xgy.E1LO0gsDgWwn/aoi',
        '2018-09-06 11:13:07.556', 'ACTIVE', 'ADMIN');

CREATE SEQUENCE IF NOT EXISTS  main.rules_rules_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE main.rules
(
    rules_id INTEGER     DEFAULT
                             nextval('main.rules_rules_id_seq'::regclass) PRIMARY KEY NOT NULL,
    name     VARCHAR(20)                                                              NOT NULL,
    age      INTEGER                                                                  NOT NULL,
    is_active   VARCHAR(20) DEFAULT 'INACTIVE'
);

CREATE SEQUENCE IF NOT EXISTS  main.planets_planets_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE main.planets
(
    planets_id         INTEGER DEFAULT
                                   nextval('main.planets_planets_id_seq'::regclass) NOT NULL,
    rules_id           INTEGER,
    name_of_the_planet VARCHAR(20)                                                  NOT NULL,
    status             BOOL    DEFAULT FALSE,
    CONSTRAINT planets_pk PRIMARY KEY (planets_id),
    CONSTRAINT fk_rules_id_rules
        FOREIGN KEY (rules_id)
            REFERENCES main.rules (rules_id)

)
