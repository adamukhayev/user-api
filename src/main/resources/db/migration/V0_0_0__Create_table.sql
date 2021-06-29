CREATE SCHEMA IF NOT EXISTS main;

CREATE SEQUENCE IF NOT EXISTS  main.users_user_id_seq
    START WITH 101
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
    role        VARCHAR(20)                                                         NOT NULL,
    is_active   VARCHAR(20)                                                         NOT NULL
);

INSERT INTO main.users (email, password, create_date, is_active, role)
VALUES ('admin@mail.ru', '$2y$12$tU7DzgkkTzl2iBK88LC3O.ng6v5X.Yif.7izuhF8Lk29SqTfHZjOa',
        '2018-09-06 11:13:07.556', 'ACTIVE', 'ADMIN');

CREATE SEQUENCE IF NOT EXISTS  main.survey_survey_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE main.survey
(
    survey_id   INTEGER DEFAULT nextval('main.survey_survey_id_seq'::regclass) PRIMARY KEY NOT NULL,
    title       VARCHAR                                                                    NOT NULL,
    description VARCHAR                                                                    NOT NULL,
    create_date TIMESTAMP                                                                  NOT NULL,
    end_date    TIMESTAMP                                                                  NOT NULL,
    status      VARCHAR                                                                    NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS  main.question_question_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE main.question
(
    question_id   INTEGER DEFAULT nextval('main.question_question_id_seq'::regclass) NOT NULL,
    title         VARCHAR                                                            NOT NULL,
    question      VARCHAR                                                            NOT NULL,
    survey_id     INTEGER,
    question_type VARCHAR                                                            NOT NULL,
    status        VARCHAR                                                            NOT NULL,

    CONSTRAINT question_pk PRIMARY KEY (question_id),
    CONSTRAINT fk_survey_id_survey
        FOREIGN KEY (survey_id)
            REFERENCES main.survey (survey_id)
);

CREATE SEQUENCE IF NOT EXISTS  main.answer_answer_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE main.answer
(
    answer_id   INTEGER DEFAULT nextval('main.answer_answer_id_seq'::regclass) NOT NULL,
    answer_text VARCHAR                                                        NOT NULL,
    question_id INTEGER,

    CONSTRAINT answer_pk PRIMARY KEY (answer_id),
    CONSTRAINT fk_question_id_question
        FOREIGN KEY (question_id)
            REFERENCES main.question (question_id)

);

CREATE SEQUENCE IF NOT EXISTS  main.customer_customer_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE main.customer
(

    customer_id INTEGER DEFAULT nextval('main.customer_customer_id_seq'::regclass),
    user_id     INTEGER,
    survey_id   INTEGER,
    answer_id   INTEGER,
    question_id INTEGER,
    answer_text VARCHAR

)

-- CREATE SEQUENCE IF NOT EXISTS  main.survey_operation_survey_operation_id_seq
--     START WITH 100
--     INCREMENT BY 1
--     NO MINVALUE
--     NO MAXVALUE
--     CACHE 1;

-- CREATE TABLE main.survey_operation (
--     survey_operation_id INTEGER DEFAULT nextval('main.survey_operation_survey_operation_id'::regclass) NOT NULL,
--     action VARCHAR NOT NULL ,
--     survey_id INTEGER ,
--     admin_id INTEGER ,
--     customer_id INTEGER ,
--     question_id INTEGER ,
--     answer_id INTEGER ,
--     create_date TIMESTAMP NOT NULL
--
--         CONSTRAINT survey_operation_pk PRIMARY KEY (survey_operation_id),
--     CONSTRAINT fk_user_id_user
--         FOREIGN KEY (user_id)
--             REFERENCES main.users (user_id),
--     CONSTRAINT fk_user_id_user
--         FOREIGN KEY (survey_id)
--         REFERENCES main.question (survey_id)
--
-- )