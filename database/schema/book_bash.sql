-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.0
-- PostgreSQL version: 9.6
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database
-- ;
-- -- ddl-end --
-- 

-- object: book_bash | type: SCHEMA --
-- DROP SCHEMA IF EXISTS book_bash CASCADE;
CREATE SCHEMA book_bash;
-- ddl-end --
ALTER SCHEMA book_bash OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,book_bash;
-- ddl-end --

-- object: book_bash.application_user_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS book_bash.application_user_id_seq CASCADE;
CREATE SEQUENCE book_bash.application_user_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE book_bash.application_user_id_seq OWNER TO postgres;
-- ddl-end --

-- object: book_bash.application_user | type: TABLE --
-- DROP TABLE IF EXISTS book_bash.application_user CASCADE;
CREATE TABLE book_bash.application_user(
	user_id bigint NOT NULL DEFAULT nextval('book_bash.application_user_id_seq'::regclass),
	email_address text,
	username text,
	password_hash text,
	date_email_confirmed timestamp,
	date_created timestamp DEFAULT now(),
	user_created bigint,
	date_updated timestamp DEFAULT now(),
	user_updated bigint,
	date_disabled timestamp,
	CONSTRAINT pk_application_user PRIMARY KEY (user_id)

);
-- ddl-end --
ALTER TABLE book_bash.application_user OWNER TO postgres;
-- ddl-end --

-- object: book_bash.security_role_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS book_bash.security_role_id_seq CASCADE;
CREATE SEQUENCE book_bash.security_role_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE book_bash.security_role_id_seq OWNER TO postgres;
-- ddl-end --

-- object: book_bash.security_role | type: TABLE --
-- DROP TABLE IF EXISTS book_bash.security_role CASCADE;
CREATE TABLE book_bash.security_role(
	role_id bigint NOT NULL DEFAULT nextval('book_bash.security_role_id_seq'::regclass),
	role_name text,
	role_description text,
	date_created timestamp DEFAULT now(),
	user_created bigint,
	date_updated timestamp DEFAULT now(),
	user_updated bigint,
	date_disabled timestamp,
	CONSTRAINT pk_security_role PRIMARY KEY (role_id)

);
-- ddl-end --
ALTER TABLE book_bash.security_role OWNER TO postgres;
-- ddl-end --

-- object: book_bash.application_user_security_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS book_bash.application_user_security_id_seq CASCADE;
CREATE SEQUENCE book_bash.application_user_security_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE book_bash.application_user_security_id_seq OWNER TO postgres;
-- ddl-end --

-- object: book_bash.application_user_security | type: TABLE --
-- DROP TABLE IF EXISTS book_bash.application_user_security CASCADE;
CREATE TABLE book_bash.application_user_security(
	record_id bigint NOT NULL DEFAULT nextval('book_bash.application_user_security_id_seq'::regclass),
	user_id bigint,
	role_id bigint,
	date_created timestamp DEFAULT now(),
	user_created bigint,
	date_updated timestamp DEFAULT now(),
	user_updated bigint,
	date_disabled timestamp,
	CONSTRAINT pk_application_user_security PRIMARY KEY (record_id)

);
-- ddl-end --
ALTER TABLE book_bash.application_user_security OWNER TO postgres;
-- ddl-end --

-- object: book_bash.book_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS book_bash.book_id_seq CASCADE;
CREATE SEQUENCE book_bash.book_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE book_bash.book_id_seq OWNER TO postgres;
-- ddl-end --

-- object: book_bash.book_backlog_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS book_bash.book_backlog_id_seq CASCADE;
CREATE SEQUENCE book_bash.book_backlog_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE book_bash.book_backlog_id_seq OWNER TO postgres;
-- ddl-end --

-- object: book_bash.backlog_status_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS book_bash.backlog_status_id_seq CASCADE;
CREATE SEQUENCE book_bash.backlog_status_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE book_bash.backlog_status_id_seq OWNER TO postgres;
-- ddl-end --

-- object: book_bash.book | type: TABLE --
-- DROP TABLE IF EXISTS book_bash.book CASCADE;
CREATE TABLE book_bash.book(
	book_id bigint NOT NULL DEFAULT nextval('book_bash.book_id_seq'::regclass),
	isbn text,
	book_title text,
	author_name text,
	date_published timestamp,
	number_of_pages bigint,
	date_created timestamp DEFAULT now(),
	user_created bigint,
	date_updated timestamp DEFAULT now(),
	user_updated bigint,
	date_disabled timestamp,
	CONSTRAINT pk_book PRIMARY KEY (book_id)

);
-- ddl-end --
ALTER TABLE book_bash.book OWNER TO postgres;
-- ddl-end --

-- object: book_bash.book_backlog | type: TABLE --
-- DROP TABLE IF EXISTS book_bash.book_backlog CASCADE;
CREATE TABLE book_bash.book_backlog(
	record_id bigint NOT NULL DEFAULT nextval('book_bash.book_backlog_id_seq'::regclass),
	user_id bigint,
	book_id bigint,
	status_id bigint,
	rating numeric,
	date_created timestamp DEFAULT now(),
	user_created bigint,
	date_updated timestamp DEFAULT now(),
	user_updated bigint,
	date_disabled timestamp,
	CONSTRAINT pk_book_backlog PRIMARY KEY (record_id)

);
-- ddl-end --
ALTER TABLE book_bash.book_backlog OWNER TO postgres;
-- ddl-end --

-- object: book_bash.backlog_status | type: TABLE --
-- DROP TABLE IF EXISTS book_bash.backlog_status CASCADE;
CREATE TABLE book_bash.backlog_status(
	type_id bigint NOT NULL DEFAULT nextval('book_bash.backlog_status_id_seq'::regclass),
	type_name text,
	type_description text,
	date_created timestamp DEFAULT now(),
	user_created bigint,
	date_updated timestamp DEFAULT now(),
	user_updated bigint,
	date_disabled timestamp,
	CONSTRAINT pk_backlog_status PRIMARY KEY (type_id)

);
-- ddl-end --
ALTER TABLE book_bash.backlog_status OWNER TO postgres;
-- ddl-end --

-- object: fk_user_id | type: CONSTRAINT --
-- ALTER TABLE book_bash.application_user_security DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE book_bash.application_user_security ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
REFERENCES book_bash.application_user (user_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_role_id | type: CONSTRAINT --
-- ALTER TABLE book_bash.application_user_security DROP CONSTRAINT IF EXISTS fk_role_id CASCADE;
ALTER TABLE book_bash.application_user_security ADD CONSTRAINT fk_role_id FOREIGN KEY (role_id)
REFERENCES book_bash.security_role (role_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_book_id | type: CONSTRAINT --
-- ALTER TABLE book_bash.book_backlog DROP CONSTRAINT IF EXISTS fk_book_id CASCADE;
ALTER TABLE book_bash.book_backlog ADD CONSTRAINT fk_book_id FOREIGN KEY (book_id)
REFERENCES book_bash.book (book_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_status_id | type: CONSTRAINT --
-- ALTER TABLE book_bash.book_backlog DROP CONSTRAINT IF EXISTS fk_status_id CASCADE;
ALTER TABLE book_bash.book_backlog ADD CONSTRAINT fk_status_id FOREIGN KEY (status_id)
REFERENCES book_bash.backlog_status (type_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_user_id | type: CONSTRAINT --
-- ALTER TABLE book_bash.book_backlog DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE book_bash.book_backlog ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
REFERENCES book_bash.application_user (user_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


