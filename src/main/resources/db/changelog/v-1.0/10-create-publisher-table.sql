create sequence if not exists lab3_publisher_table_id_seq
    as integer;
GO
CREATE TABLE IF NOT EXISTS public.lab3_publisher_table
(
    id integer NOT NULL DEFAULT nextval('lab3_publisher_table_id_seq'::regclass),
    publisher_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT lab3_publisher_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_publisher_table_publisher_name UNIQUE (publisher_name),
    CONSTRAINT lab3_publisher_table_publisher_name_check CHECK (publisher_name::text <> ''::text)
);
GO