CREATE SEQUENCE IF NOT EXISTS lab3_publisher_table_id_seq;
CREATE TABLE IF NOT EXISTS lab3_publisher_table
(
    id integer NOT NULL DEFAULT nextval('lab3_publisher_table_id_seq'::regclass),
    publisher_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT lab3_publisher_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_publisher_table_publisher_name_key UNIQUE (publisher_name),
    CONSTRAINT lab3_publisher_table_publisher_name_key1 UNIQUE (publisher_name),
    CONSTRAINT lab3_publisher_table_check_publisher_name_not_empty CHECK (publisher_name <> '')
);

CREATE SEQUENCE IF NOT EXISTS lab3_language_table_id_seq;
CREATE TABLE IF NOT EXISTS lab3_language_table
(
    id integer NOT NULL DEFAULT nextval('lab3_language_table_id_seq'::regclass),
    language character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT lab3_language_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_language_table_language_key UNIQUE (language),
    CONSTRAINT lab3_language_table_check_language_not_empty CHECK (language <> '')

);

CREATE SEQUENCE IF NOT EXISTS lab3_category_table_id_seq;
CREATE TABLE IF NOT EXISTS lab3_category_table
(
    id integer NOT NULL DEFAULT nextval('lab3_category_table_id_seq'::regclass),
    category_name character varying(255) NOT NULL,
    CONSTRAINT lab3_category_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_category_table_category_name_key UNIQUE (category_name),
    CONSTRAINT lab3_category_table_check_category_name_not_empty CHECK (category_name <> '')

);

CREATE SEQUENCE IF NOT EXISTS lab3_author_table_id_seq;
CREATE TABLE IF NOT EXISTS lab3_author_table
(
    id integer NOT NULL DEFAULT nextval('lab3_author_table_id_seq'::regclass),
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    CONSTRAINT lab3_author_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_author_table_first_name_last_name_key UNIQUE (first_name, last_name),
    CONSTRAINT lab3_author_table_check_first_name_not_empty CHECK (first_name <> ''),
    CONSTRAINT lab3_author_table_check_last_name_not_empty CHECK (last_name <> '')
);



CREATE SEQUENCE IF NOT EXISTS lab3_book_table_id_seq;
CREATE TABLE IF NOT EXISTS lab3_book_table
(
    id integer NOT NULL DEFAULT nextval('lab3_book_table_id_seq'::regclass),
    title character varying(1000) NOT NULL,
    author_id integer NOT NULL,
    category_id integer NOT NULL,
    language_id integer NOT NULL,
    publisher_id integer NOT NULL,
    prequel_id integer,
    CONSTRAINT lab3_book_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_book_table_title_author_id_key UNIQUE (title, author_id),
    CONSTRAINT lab3_book_table_author_id_fkey FOREIGN KEY (author_id)
        REFERENCES lab3_author_table (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT lab3_book_table_category_id_fkey FOREIGN KEY (category_id)
        REFERENCES lab3_category_table (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT lab3_book_table_language_id_fkey FOREIGN KEY (language_id)
        REFERENCES lab3_language_table (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT lab3_book_table_prequel_id_fkey FOREIGN KEY (prequel_id)
        REFERENCES lab3_book_table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL,
    CONSTRAINT lab3_book_table_publisher_id_fkey FOREIGN KEY (publisher_id)
        REFERENCES lab3_publisher_table (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT lab3_book_table_check_prequel_not_same_id CHECK (id <> prequel_id),
    CONSTRAINT lab3_book_table_check_title_not_empty CHECK (title <> '')

);
