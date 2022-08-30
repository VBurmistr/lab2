create sequence if not exists lab3_category_table_id_seq
    as integer;
GO
CREATE TABLE IF NOT EXISTS public.lab3_category_table
(
    id integer NOT NULL DEFAULT nextval('lab3_category_table_id_seq'::regclass),
    category_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT lab3_category_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_category_table_category_name_unique UNIQUE (category_name),
    CONSTRAINT lab3_category_table_category_name_check CHECK (category_name::text <> ''::text)
);
GO