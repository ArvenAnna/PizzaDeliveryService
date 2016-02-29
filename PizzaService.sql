CREATE DATABASE "PizzaServ"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Ukrainian_Ukraine.1251'
       LC_CTYPE = 'Ukrainian_Ukraine.1251'
       CONNECTION LIMIT = -1;
	   
	   
CREATE TABLE public.account
(
  id bigint NOT NULL,
  availability boolean,
  password character varying(255),
  username character varying(255),
  CONSTRAINT account_pkey PRIMARY KEY (id)
)

CREATE TABLE public.address
(
  id bigint NOT NULL,
  apartment integer,
  city character varying(255),
  house character varying(255),
  street character varying(255),
  CONSTRAINT address_pkey PRIMARY KEY (id)
)

CREATE TABLE public.card
(
  id bigint NOT NULL,
  sum integer,
  CONSTRAINT card_pkey PRIMARY KEY (id)
)

CREATE TABLE public.customer
(
  id bigint NOT NULL,
  name character varying(255),
  tel character varying(255),
  account_id bigint,
  address_id bigint,
  card_id bigint,
  CONSTRAINT customer_pkey PRIMARY KEY (id),
  CONSTRAINT fk_customer_to_account FOREIGN KEY (account_id)
      REFERENCES public.account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_customer_to_address FOREIGN KEY (address_id)
      REFERENCES public.address (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_customer_to_card FOREIGN KEY (card_id)
      REFERENCES public.card (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE public.order_details
(
  id bigint NOT NULL,
  price integer,
  pizza_id bigint,
  order_id bigint,
  CONSTRAINT order_details_pkey PRIMARY KEY (id),
  CONSTRAINT fk3vlxbj20acgg23qh2hpsbyws1 FOREIGN KEY (order_id)
      REFERENCES public.pizzaorder (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_order_detail_to_pizza FOREIGN KEY (pizza_id)
      REFERENCES public.pizza (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE public.pizza
(
  id bigint NOT NULL,
  description character varying(255),
  foto character varying(255),
  name character varying(255),
  pizzatype character varying(255),
  price integer,
  CONSTRAINT pizza_pkey PRIMARY KEY (id)
)

CREATE TABLE public.pizzaorder
(
  id bigint NOT NULL,
  cost integer,
  date date,
  status character varying(255),
  customer_id bigint,
  CONSTRAINT pizzaorder_pkey PRIMARY KEY (id),
  CONSTRAINT fk_order_to_customer FOREIGN KEY (customer_id)
      REFERENCES public.customer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE public.userrole
(
  id bigint NOT NULL,
  rolename character varying(255),
  CONSTRAINT userrole_pkey PRIMARY KEY (id)
)

CREATE TABLE public.userrole_account
(
  account_id bigint NOT NULL,
  userrole_id bigint NOT NULL,
  CONSTRAINT userrole_account_pkey PRIMARY KEY (account_id, userrole_id),
  CONSTRAINT fk_account_to_userrole_many FOREIGN KEY (account_id)
      REFERENCES public.account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_userrole_to_account_many FOREIGN KEY (userrole_id)
      REFERENCES public.userrole (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)