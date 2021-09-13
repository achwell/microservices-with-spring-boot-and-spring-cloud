DROP SEQUENCE IF EXISTS public.student_seq;
DROP TABLE IF EXISTS public.student;

CREATE SEQUENCE public.student_seq 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

CREATE TABLE public.student (
  id int8 NOT NULL DEFAULT nextval('student_seq'::regclass),
  first_name varchar(50) COLLATE pg_catalog.default NOT NULL,
  last_name varchar(50) COLLATE pg_catalog.default,
  email varchar(30) COLLATE pg_catalog.default,
  address_id int8
);

ALTER SEQUENCE public.student_seq OWNER TO postgres;
ALTER TABLE public.student OWNER TO postgres;
ALTER TABLE public.student ADD CONSTRAINT student_pkey PRIMARY KEY (id);

INSERT INTO public.student (first_name, last_name, email, address_id) SELECT 'Raj','Dave','raj_dave@yahoo.com', id FROM address WHERE city = 'Delhi';
INSERT INTO public.student (first_name, last_name, email, address_id) SELECT 'John','Smith','john_smith@gmail.com', id FROM address WHERE city = 'NY';

