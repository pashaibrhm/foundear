--
-- PostgreSQL database cluster dump
--

-- Started on 2025-01-11 13:11:23

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:fGnAnKWAzDQMqWD4aKYMfA==$d3Z4zsctOxCCD1dR4fwYBQPZ/a6uFfQWPmGB7TBeNGQ=:rpBpXFyLtYV3yQyLf+iiZoS2jdNf4fQ6C2CVwAoonm0=';

--
-- User Configurations
--








-- Completed on 2025-01-11 13:11:23

--
-- PostgreSQL database cluster dump complete
--

