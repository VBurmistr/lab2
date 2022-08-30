create sequence if not exists lab3_language_table_id_seq
    as integer;
GO
CREATE TABLE IF NOT EXISTS public.lab3_language_table
(
    id integer NOT NULL DEFAULT nextval('lab3_language_table_id_seq'::regclass),
    language character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT lab3_language_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_language_table_language_unique UNIQUE (language),
    CONSTRAINT lab3_language_table_language_check CHECK (language::text <> ''::text)
);
GO