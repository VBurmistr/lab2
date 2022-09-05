create sequence if not exists lab3_language_table_id_seq
    as integer;
GO
CREATE TABLE IF NOT EXISTS lab3_language_table
(
    id integer NOT NULL DEFAULT nextval('lab3_language_table_id_seq'),
    language character varying(255),
    CONSTRAINT lab3_language_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_language_table_language_unique UNIQUE (language),
    CONSTRAINT lab3_language_table_language_check CHECK (language <> '')
);
GO