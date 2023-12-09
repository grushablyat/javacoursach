--
-- PostgreSQL database dump
--

-- Dumped from database version 12.16 (Ubuntu 12.16-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 14.9 (Ubuntu 14.9-0ubuntu0.22.04.1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: comments; Type: TABLE; Schema: public; Owner: bonch_2105432
--

CREATE TABLE public.comments (
    id integer NOT NULL,
    service integer NOT NULL,
    text text
);


ALTER TABLE public.comments OWNER TO bonch_2105432;

--
-- Name: comments_id_seq; Type: SEQUENCE; Schema: public; Owner: bonch_2105432
--

CREATE SEQUENCE public.comments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comments_id_seq OWNER TO bonch_2105432;

--
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bonch_2105432
--

ALTER SEQUENCE public.comments_id_seq OWNED BY public.comments.id;


--
-- Name: request; Type: TABLE; Schema: public; Owner: bonch_2105432
--

CREATE TABLE public.request (
    id integer NOT NULL,
    client integer NOT NULL,
    date timestamp without time zone,
    description text,
    status integer NOT NULL
);


ALTER TABLE public.request OWNER TO bonch_2105432;

--
-- Name: request_id_seq; Type: SEQUENCE; Schema: public; Owner: bonch_2105432
--

CREATE SEQUENCE public.request_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.request_id_seq OWNER TO bonch_2105432;

--
-- Name: request_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bonch_2105432
--

ALTER SEQUENCE public.request_id_seq OWNED BY public.request.id;


--
-- Name: role; Type: TABLE; Schema: public; Owner: bonch_2105432
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.role OWNER TO bonch_2105432;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: bonch_2105432
--

CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO bonch_2105432;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bonch_2105432
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- Name: service; Type: TABLE; Schema: public; Owner: bonch_2105432
--

CREATE TABLE public.service (
    id integer NOT NULL,
    request integer NOT NULL,
    master integer
);


ALTER TABLE public.service OWNER TO bonch_2105432;

--
-- Name: service_id_seq; Type: SEQUENCE; Schema: public; Owner: bonch_2105432
--

CREATE SEQUENCE public.service_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.service_id_seq OWNER TO bonch_2105432;

--
-- Name: service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bonch_2105432
--

ALTER SEQUENCE public.service_id_seq OWNED BY public.service.id;


--
-- Name: status; Type: TABLE; Schema: public; Owner: bonch_2105432
--

CREATE TABLE public.status (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.status OWNER TO bonch_2105432;

--
-- Name: status_id_seq; Type: SEQUENCE; Schema: public; Owner: bonch_2105432
--

CREATE SEQUENCE public.status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.status_id_seq OWNER TO bonch_2105432;

--
-- Name: status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bonch_2105432
--

ALTER SEQUENCE public.status_id_seq OWNED BY public.status.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: bonch_2105432
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying NOT NULL,
    email character varying NOT NULL,
    login character varying NOT NULL,
    password character varying NOT NULL,
    role integer NOT NULL
);


ALTER TABLE public.users OWNER TO bonch_2105432;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: bonch_2105432
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO bonch_2105432;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bonch_2105432
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: comments id; Type: DEFAULT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.comments ALTER COLUMN id SET DEFAULT nextval('public.comments_id_seq'::regclass);


--
-- Name: request id; Type: DEFAULT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.request ALTER COLUMN id SET DEFAULT nextval('public.request_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- Name: service id; Type: DEFAULT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.service ALTER COLUMN id SET DEFAULT nextval('public.service_id_seq'::regclass);


--
-- Name: status id; Type: DEFAULT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.status ALTER COLUMN id SET DEFAULT nextval('public.status_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: bonch_2105432
--

COPY public.comments (id, service, text) FROM stdin;
\.


--
-- Data for Name: request; Type: TABLE DATA; Schema: public; Owner: bonch_2105432
--

COPY public.request (id, client, date, description, status) FROM stdin;
1	3	2023-11-27 00:00:00	U menya vse slomalos, pamagiti	1
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: bonch_2105432
--

COPY public.role (id, name) FROM stdin;
1	Admin
2	Master
3	Client
\.


--
-- Data for Name: service; Type: TABLE DATA; Schema: public; Owner: bonch_2105432
--

COPY public.service (id, request, master) FROM stdin;
2	1	1
4	1	4
\.


--
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: bonch_2105432
--

COPY public.status (id, name) FROM stdin;
1	Opened
2	In process
3	Closed
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: bonch_2105432
--

COPY public.users (id, name, email, login, password, role) FROM stdin;
1	Grusha Vvv	grushavvv@gmail.com	grushab	11111pass	1
2	Sergej Baranov	r43.baranov@gmail.com	sergge43	123456787f	2
3	Daria Tarasova	tarasova.alien@gmail.com	cornstar	mypass69	3
4	Semen Smirnov	semen300@gmail.com	Semen300	300bucks	2
55	Ilia Steinberg	ilia.stein@gmail.com	ya_ilia	8password	3
\.


--
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bonch_2105432
--

SELECT pg_catalog.setval('public.comments_id_seq', 1, false);


--
-- Name: request_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bonch_2105432
--

SELECT pg_catalog.setval('public.request_id_seq', 1, true);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bonch_2105432
--

SELECT pg_catalog.setval('public.role_id_seq', 3, true);


--
-- Name: service_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bonch_2105432
--

SELECT pg_catalog.setval('public.service_id_seq', 4, true);


--
-- Name: status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bonch_2105432
--

SELECT pg_catalog.setval('public.status_id_seq', 3, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bonch_2105432
--

SELECT pg_catalog.setval('public.users_id_seq', 57, true);


--
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: request request_pkey; Type: CONSTRAINT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: service service_pkey; Type: CONSTRAINT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);


--
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: comments comments_service_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_service_fkey FOREIGN KEY (service) REFERENCES public.service(id);


--
-- Name: request request_client_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_client_fkey FOREIGN KEY (client) REFERENCES public.users(id);


--
-- Name: request request_status_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_status_fkey FOREIGN KEY (status) REFERENCES public.status(id);


--
-- Name: service service_master_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.service
    ADD CONSTRAINT service_master_fkey FOREIGN KEY (master) REFERENCES public.users(id);


--
-- Name: service service_request_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.service
    ADD CONSTRAINT service_request_fkey FOREIGN KEY (request) REFERENCES public.request(id);


--
-- Name: users users_role_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bonch_2105432
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_role_fkey FOREIGN KEY (role) REFERENCES public.role(id);


--
-- PostgreSQL database dump complete
--

