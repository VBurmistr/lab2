create sequence if not exists lab3_author_table_id_seq
    as integer;
GO

CREATE TABLE IF NOT EXISTS public.lab3_author_table
(
    id integer NOT NULL DEFAULT nextval('lab3_author_table_id_seq'::regclass),
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT lab3_author_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_author_table_first_name_last_name_key UNIQUE (first_name, last_name),
    CONSTRAINT lab3_author_table_check CHECK (first_name::text <> ''::text AND last_name::text <> ''::text)
);
GO