--
-- PostgreSQL database dump
--

-- Dumped from database version 10.12 (Ubuntu 10.12-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.12 (Ubuntu 10.12-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: field_options; Type: TABLE; Schema: public; Owner: softarex
--

CREATE TABLE public.field_options (
    field_id bigint NOT NULL,
    options character varying(255)
);


ALTER TABLE public.field_options OWNER TO softarex;

--
-- Name: fields; Type: TABLE; Schema: public; Owner: softarex
--

CREATE TABLE public.fields (
    id bigint NOT NULL,
    is_active boolean,
    label character varying(255),
    required boolean NOT NULL,
    type character varying(255),
    user_id bigint
);


ALTER TABLE public.fields OWNER TO softarex;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: softarex
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO softarex;

--
-- Name: responses; Type: TABLE; Schema: public; Owner: softarex
--

CREATE TABLE public.responses (
    id bigint NOT NULL,
    content character varying(255),
    field_id bigint,
    user_id bigint
);


ALTER TABLE public.responses OWNER TO softarex;

--
-- Name: users; Type: TABLE; Schema: public; Owner: softarex
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    password_hash character varying(255),
    phone character varying(255)
);


ALTER TABLE public.users OWNER TO softarex;

--
-- Data for Name: field_options; Type: TABLE DATA; Schema: public; Owner: softarex
--

COPY public.field_options (field_id, options) FROM stdin;
\.


--
-- Data for Name: fields; Type: TABLE DATA; Schema: public; Owner: softarex
--

COPY public.fields (id, is_active, label, required, type, user_id) FROM stdin;
\.


--
-- Data for Name: responses; Type: TABLE DATA; Schema: public; Owner: softarex
--

COPY public.responses (id, content, field_id, user_id) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: softarex
--

COPY public.users (id, email, first_name, last_name, password_hash, phone) FROM stdin;
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: softarex
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- Name: fields fields_pkey; Type: CONSTRAINT; Schema: public; Owner: softarex
--

ALTER TABLE ONLY public.fields
    ADD CONSTRAINT fields_pkey PRIMARY KEY (id);


--
-- Name: responses responses_pkey; Type: CONSTRAINT; Schema: public; Owner: softarex
--

ALTER TABLE ONLY public.responses
    ADD CONSTRAINT responses_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: softarex
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: responses fka3n5a8n7a4o1v7uj6oo0o0bgn; Type: FK CONSTRAINT; Schema: public; Owner: softarex
--

ALTER TABLE ONLY public.responses
    ADD CONSTRAINT fka3n5a8n7a4o1v7uj6oo0o0bgn FOREIGN KEY (field_id) REFERENCES public.fields(id);


--
-- Name: fields fkevkcgfm2ljrikj9ffqgd29d6j; Type: FK CONSTRAINT; Schema: public; Owner: softarex
--

ALTER TABLE ONLY public.fields
    ADD CONSTRAINT fkevkcgfm2ljrikj9ffqgd29d6j FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: field_options fklytco1o8ac0yxsjm9cq01sace; Type: FK CONSTRAINT; Schema: public; Owner: softarex
--

ALTER TABLE ONLY public.field_options
    ADD CONSTRAINT fklytco1o8ac0yxsjm9cq01sace FOREIGN KEY (field_id) REFERENCES public.fields(id);


--
-- Name: responses fkqf8rt9h0wd5pmaxouhxqsoeuq; Type: FK CONSTRAINT; Schema: public; Owner: softarex
--

ALTER TABLE ONLY public.responses
    ADD CONSTRAINT fkqf8rt9h0wd5pmaxouhxqsoeuq FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

