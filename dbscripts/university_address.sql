DROP SEQUENCE IF EXISTS public.address_seq;
DROP TABLE IF EXISTS public.address;

CREATE SEQUENCE public.address_seq 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

CREATE TABLE public.address (
  id int8 NOT NULL DEFAULT nextval('address_seq'::regclass),
  street varchar(100) COLLATE pg_catalog.default NOT NULL,
  city varchar(54) COLLATE pg_catalog.default NOT NULL
);

ALTER SEQUENCE public.address_seq OWNER TO postgres;
ALTER TABLE public.address OWNER TO postgres;
ALTER TABLE public.address ADD CONSTRAINT address_pkey PRIMARY KEY (id);

INSERT INTO public.address (street, city) VALUES ('Happy Street','Delhi');
INSERT INTO public.address (street, city) VALUES ('Down the town Street','NY');
