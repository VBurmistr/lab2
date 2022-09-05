create sequence if not exists  lab3_book_table_id_seq
    as integer;
GO
CREATE TABLE IF NOT EXISTS lab3_book_table
(
    id integer NOT NULL DEFAULT nextval('lab3_book_table_id_seq'),
    title character varying(255) NOT NULL,
    author_id integer NOT NULL,
    category_id integer NOT NULL,
    language_id integer NOT NULL,
    prequel_id integer,
    publisher_id integer NOT NULL,
    CONSTRAINT lab3_book_table_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_book_table_title_author_id_key UNIQUE (title, author_id),
    CONSTRAINT lab3_book_table_author_id_fkey FOREIGN KEY (author_id)
        REFERENCES lab3_author_table (id)
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT lab3_book_table_category_id_fkey FOREIGN KEY (category_id)
        REFERENCES lab3_category_table (id)
        ON UPDATE NO ACTION
        ON DELETE CASCADE ,
    CONSTRAINT lab3_book_table_language_id_fkey FOREIGN KEY (language_id)
        REFERENCES lab3_language_table (id)
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT lab3_book_table_prequel_id_fkey FOREIGN KEY (prequel_id)
        REFERENCES lab3_book_table (id)
        ON UPDATE NO ACTION
        ON DELETE SET NULL ,
    CONSTRAINT lab3_book_table_publisher_id_fkey FOREIGN KEY (publisher_id)
        REFERENCES lab3_publisher_table (id)
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT lab3_book_table_check CHECK (id <> prequel_id AND title <> '')
);
GO