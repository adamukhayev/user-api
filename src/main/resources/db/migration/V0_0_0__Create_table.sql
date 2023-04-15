CREATE SCHEMA IF NOT EXISTS main;

CREATE TABLE main.users_audit
(
    operation   char(1) NOT NULL,
    user_id     INTEGER,
    email       varchar NOT NULL,
    password    varchar,
    create_date timestamp,
    is_active   varchar
);

CREATE OR REPLACE FUNCTION process_users_audit() RETURNS TRIGGER AS
$users_audit$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO main.users_audit SELECT 'D', OLD.*;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO main.users_audit SELECT 'U', NEW.*;
    ELSIF (TG_OP = 'INSERT') THEN
        INSERT INTO main.users_audit SELECT 'I', NEW.*;
    END IF;
    RETURN NULL;
END;
$users_audit$ LANGUAGE plpgsql;

CREATE SEQUENCE IF NOT EXISTS main.users_user_id_seq
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
    is_active   VARCHAR(20)                                                         NOT NULL
);

INSERT INTO main.users (email, password, create_date, is_active)
VALUES ('admin@mail.ru', '$2y$12$VqyPIlBTTxNAdJuv2i6w/OA3rEDuJVDffk9LRnb8SNA2F67mnLe/C',
        '2018-09-06 11:13:07.556', 'ACTIVE');

CREATE TRIGGER users_audit
    AFTER INSERT OR UPDATE OR DELETE
    ON main.users
    FOR EACH ROW
EXECUTE FUNCTION process_users_audit();

CREATE SEQUENCE IF NOT EXISTS main.roles_role_id_seq
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