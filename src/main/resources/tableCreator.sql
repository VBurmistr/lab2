create sequence if not exists lab3_author_table_id_seq
    as integer;

create sequence if not exists  lab3_book_table_id_seq
    as integer;

create sequence if not exists lab3_category_table_id_seq
    as integer;

create sequence if not exists lab3_language_table_id_seq
    as integer;

create sequence if not exists lab3_publisher_table_id_seq
    as integer;

CREATE TABLE IF NOT EXISTS public.lab3_author_table
(
    id integer NOT NULL DEFAULT nextval('lab3_author_table_id_seq'::regclass),
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT lab3_author_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_author_table_first_name_last_name_key UNIQUE (first_name, last_name),
    CONSTRAINT lab3_author_table_check CHECK (first_name::text <> ''::text AND last_name::text <> ''::text)
);


CREATE TABLE IF NOT EXISTS public.lab3_category_table
(
    id integer NOT NULL DEFAULT nextval('lab3_category_table_id_seq'::regclass),
    category_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT lab3_category_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_category_table_category_name_unique UNIQUE (category_name),
    CONSTRAINT lab3_category_table_category_name_check CHECK (category_name::text <> ''::text)
);

CREATE TABLE IF NOT EXISTS public.lab3_language_table
(
    id integer NOT NULL DEFAULT nextval('lab3_language_table_id_seq'::regclass),
    language character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT lab3_language_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_language_table_language_unique UNIQUE (language),
    CONSTRAINT lab3_language_table_language_check CHECK (language::text <> ''::text)
);

CREATE TABLE IF NOT EXISTS public.lab3_publisher_table
(
    id integer NOT NULL DEFAULT nextval('lab3_publisher_table_id_seq'::regclass),
    publisher_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT lab3_publisher_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_publisher_table_publisher_name UNIQUE (publisher_name),
    CONSTRAINT lab3_publisher_table_publisher_name_check CHECK (publisher_name::text <> ''::text)
);

CREATE TABLE IF NOT EXISTS public.lab3_book_table
(
    id integer NOT NULL DEFAULT nextval('lab3_book_table_id_seq'::regclass),
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    author_id integer NOT NULL,
    category_id integer NOT NULL,
    language_id integer NOT NULL,
    prequel_id integer,
    publisher_id integer NOT NULL,
    CONSTRAINT lab3_book_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_book_table_title_author_id_key UNIQUE (title, author_id),
    CONSTRAINT lab3_book_table_author_id_fkey FOREIGN KEY (author_id)
        REFERENCES public.lab3_author_table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT lab3_book_table_category_id_fkey FOREIGN KEY (category_id)
        REFERENCES public.lab3_category_table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE ,
    CONSTRAINT lab3_book_table_language_id_fkey FOREIGN KEY (language_id)
        REFERENCES public.lab3_language_table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT lab3_book_table_prequel_id_fkey FOREIGN KEY (prequel_id)
        REFERENCES public.lab3_book_table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lab3_book_table_publisher_id_fkey FOREIGN KEY (publisher_id)
        REFERENCES public.lab3_publisher_table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT lab3_book_table_check CHECK (id <> prequel_id AND title::text <> ''::text)
);



