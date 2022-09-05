create sequence if not exists PUBLIC.lab3_user_table_id_seq as integer;
GO
CREATE TABLE IF NOT EXISTS PUBLIC.users
(
    id integer NOT NULL DEFAULT nextval('lab3_user_table_id_seq'),
    login character varying(255),
    password character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    role character varying(255),
    status character varying(255) ,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uc_users_login UNIQUE (login)
);
GO