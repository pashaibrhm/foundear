--
-- PostgreSQL database dump
--

-- Dumped from database version 16.6 (Debian 16.6-1.pgdg120+1)
-- Dumped by pg_dump version 17.1

-- Started on 2025-01-11 13:13:22

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3546 (class 1262 OID 16384)
-- Name: foundear; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE foundear WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE foundear OWNER TO postgres;

\connect foundear

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 8 (class 2615 OID 16391)
-- Name: approval; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA approval;


ALTER SCHEMA approval OWNER TO postgres;

--
-- TOC entry 6 (class 2615 OID 16389)
-- Name: auth; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA auth;


ALTER SCHEMA auth OWNER TO postgres;

--
-- TOC entry 9 (class 2615 OID 16392)
-- Name: foundear; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA foundear;


ALTER SCHEMA foundear OWNER TO postgres;

--
-- TOC entry 10 (class 2615 OID 16393)
-- Name: lookup; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA lookup;


ALTER SCHEMA lookup OWNER TO postgres;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3547 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 7 (class 2615 OID 16390)
-- Name: regional; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA regional;


ALTER SCHEMA regional OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 16753)
-- Name: mst_approval_functions; Type: TABLE; Schema: approval; Owner: postgres
--

CREATE TABLE approval.mst_approval_functions (
    id uuid NOT NULL,
    approval_setup_id uuid NOT NULL,
    function_id uuid NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    last_approved_by uuid,
    last_approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE approval.mst_approval_functions OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16759)
-- Name: mst_approval_level_approvers; Type: TABLE; Schema: approval; Owner: postgres
--

CREATE TABLE approval.mst_approval_level_approvers (
    id uuid NOT NULL,
    approval_level_id uuid NOT NULL,
    entity_id uuid NOT NULL,
    entity_type uuid NOT NULL,
    priority_level numeric(1,0) DEFAULT 1 NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    last_approved_by uuid,
    last_approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE approval.mst_approval_level_approvers OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16766)
-- Name: mst_approval_levels; Type: TABLE; Schema: approval; Owner: postgres
--

CREATE TABLE approval.mst_approval_levels (
    id uuid NOT NULL,
    approval_setup_id uuid NOT NULL,
    level_name character varying(30) NOT NULL,
    number_of_approval numeric(2,0) NOT NULL,
    approver_domain uuid NOT NULL,
    rule_statement character varying(255),
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    last_approved_by uuid,
    last_approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE approval.mst_approval_levels OWNER TO postgres;

--
-- TOC entry 3548 (class 0 OID 0)
-- Dependencies: 222
-- Name: COLUMN mst_approval_levels.approver_domain; Type: COMMENT; Schema: approval; Owner: postgres
--

COMMENT ON COLUMN approval.mst_approval_levels.approver_domain IS 'Refer to mst_branch';


--
-- TOC entry 223 (class 1259 OID 16772)
-- Name: mst_approval_setups; Type: TABLE; Schema: approval; Owner: postgres
--

CREATE TABLE approval.mst_approval_setups (
    id uuid NOT NULL,
    name character varying(30) NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    last_approved_by uuid,
    last_approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE approval.mst_approval_setups OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 16895)
-- Name: trx_approval_transactions; Type: TABLE; Schema: approval; Owner: postgres
--

CREATE TABLE approval.trx_approval_transactions (
    id uuid NOT NULL,
    approval_setup_id uuid NOT NULL,
    function_id uuid NOT NULL,
    trx_status character varying(10) NOT NULL,
    serialized_data character varying(255) NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    last_approved_by uuid,
    last_approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL
);


ALTER TABLE approval.trx_approval_transactions OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16808)
-- Name: mst_failed_login_attempts; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE auth.mst_failed_login_attempts (
    id uuid NOT NULL,
    user_id uuid,
    attempt_at timestamp without time zone,
    log_message character varying(255),
    ip_address character varying(255)
);


ALTER TABLE auth.mst_failed_login_attempts OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16823)
-- Name: mst_functions; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE auth.mst_functions (
    id uuid NOT NULL,
    parent_id uuid NOT NULL,
    menu_id uuid NOT NULL,
    code character varying(30) NOT NULL,
    name character varying(50) NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    last_approved_by uuid,
    last_approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_active numeric(1,0) DEFAULT 1 NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE auth.mst_functions OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 16830)
-- Name: mst_group_function_permissions; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE auth.mst_group_function_permissions (
    id uuid NOT NULL,
    function_id uuid NOT NULL,
    group_id uuid NOT NULL
);


ALTER TABLE auth.mst_group_function_permissions OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 16835)
-- Name: mst_groups; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE auth.mst_groups (
    id uuid NOT NULL,
    parent_id uuid,
    org_id uuid NOT NULL,
    name character varying(30) NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    approved_by uuid,
    approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE auth.mst_groups OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 16853)
-- Name: mst_menus; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE auth.mst_menus (
    id uuid NOT NULL,
    parent_id uuid NOT NULL,
    code character varying(30) NOT NULL,
    name character varying(50) NOT NULL,
    title character varying(30) NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    last_approved_by uuid,
    last_approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_active numeric(1,0) DEFAULT 1 NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE auth.mst_menus OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 16860)
-- Name: mst_organizations; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE auth.mst_organizations (
    id uuid NOT NULL,
    name character varying(20) NOT NULL,
    email character varying(50),
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    approved_by uuid,
    approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE auth.mst_organizations OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 16872)
-- Name: mst_user_sessions; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE auth.mst_user_sessions (
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    start_time timestamp without time zone NOT NULL,
    end_time timestamp without time zone,
    user_agent character varying(255) NOT NULL,
    ip_address character varying(255) NOT NULL
);


ALTER TABLE auth.mst_user_sessions OWNER TO postgres;

--
-- TOC entry 3549 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN mst_user_sessions.user_agent; Type: COMMENT; Schema: auth; Owner: postgres
--

COMMENT ON COLUMN auth.mst_user_sessions.user_agent IS 'Browser or Device used by User, can be retrieved from the information sent by client-side';


--
-- TOC entry 240 (class 1259 OID 16879)
-- Name: mst_users; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE auth.mst_users (
    id uuid NOT NULL,
    group_id uuid,
    first_name character varying(20) NOT NULL,
    middle_name character varying(20),
    last_name character varying(20),
    username character varying(30) NOT NULL,
    email character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    address_detail character varying(255),
    village_id uuid,
    district_id uuid,
    city_id uuid,
    province_id uuid,
    country_id uuid,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    approved_by uuid,
    approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    lock_count numeric(1,0) DEFAULT 0,
    is_locked numeric(1,0) DEFAULT 0,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE auth.mst_users OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16802)
-- Name: mst_entity_files; Type: TABLE; Schema: foundear; Owner: postgres
--

CREATE TABLE foundear.mst_entity_files (
    id uuid NOT NULL,
    file_id uuid NOT NULL,
    entity_id uuid NOT NULL,
    entity_type uuid NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    approved_by uuid,
    approved_at timestamp without time zone,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE foundear.mst_entity_files OWNER TO postgres;

--
-- TOC entry 3550 (class 0 OID 0)
-- Dependencies: 228
-- Name: COLUMN mst_entity_files.entity_id; Type: COMMENT; Schema: foundear; Owner: postgres
--

COMMENT ON COLUMN foundear.mst_entity_files.entity_id IS 'Could be refer to User or Branch, possible to add more entity.';


--
-- TOC entry 230 (class 1259 OID 16815)
-- Name: mst_files; Type: TABLE; Schema: foundear; Owner: postgres
--

CREATE TABLE foundear.mst_files (
    id uuid NOT NULL,
    name character varying(255) NOT NULL,
    data bytea NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    approved_by uuid,
    approved_at timestamp without time zone,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE foundear.mst_files OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 16841)
-- Name: mst_lookup_details; Type: TABLE; Schema: lookup; Owner: postgres
--

CREATE TABLE lookup.mst_lookup_details (
    id uuid NOT NULL,
    lookup_header_id uuid,
    name character varying(30),
    value character varying(20),
    description character varying(100),
    created_at timestamp without time zone,
    created_by uuid,
    last_updated_at timestamp without time zone,
    last_updated_by uuid,
    last_approved_at timestamp without time zone,
    last_approved_by uuid,
    last_version_at timestamp without time zone,
    is_deleted numeric(1,0) DEFAULT 0
);


ALTER TABLE lookup.mst_lookup_details OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16847)
-- Name: mst_lookup_headers; Type: TABLE; Schema: lookup; Owner: postgres
--

CREATE TABLE lookup.mst_lookup_headers (
    id uuid NOT NULL,
    name character varying(30),
    value character varying(20),
    description character varying(100),
    created_at timestamp without time zone,
    created_by uuid,
    last_updated_at timestamp without time zone,
    last_updated_by uuid,
    last_approved_at timestamp without time zone,
    last_approved_by uuid,
    last_version_at timestamp without time zone,
    is_deleted numeric(1,0) DEFAULT 0
);


ALTER TABLE lookup.mst_lookup_headers OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16778)
-- Name: mst_branches; Type: TABLE; Schema: regional; Owner: postgres
--

CREATE TABLE regional.mst_branches (
    id uuid NOT NULL,
    entity_id uuid NOT NULL,
    entity_type uuid NOT NULL,
    type uuid NOT NULL,
    code character varying(20) NOT NULL,
    name character varying(30) NOT NULL,
    email character varying(50),
    phone_no character varying(20),
    address_detail character varying(255),
    village_id uuid,
    district_id uuid,
    city_id uuid,
    province_id uuid,
    country_id uuid,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    approved_by uuid,
    approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_main_branch numeric(1,0) DEFAULT 0 NOT NULL,
    is_deleted numeric(1,0) NOT NULL
);


ALTER TABLE regional.mst_branches OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16784)
-- Name: mst_cities; Type: TABLE; Schema: regional; Owner: postgres
--

CREATE TABLE regional.mst_cities (
    id uuid NOT NULL,
    province_id uuid,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    is_active numeric(1,0) NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    last_approved_by uuid,
    last_approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE regional.mst_cities OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16790)
-- Name: mst_countries; Type: TABLE; Schema: regional; Owner: postgres
--

CREATE TABLE regional.mst_countries (
    id uuid NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    is_active numeric(1,0) NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    last_approved_by uuid,
    last_approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE regional.mst_countries OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16796)
-- Name: mst_districts; Type: TABLE; Schema: regional; Owner: postgres
--

CREATE TABLE regional.mst_districts (
    id uuid NOT NULL,
    city_id uuid,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    is_active numeric(1,0) NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    last_approved_by uuid,
    last_approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE regional.mst_districts OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 16866)
-- Name: mst_provinces; Type: TABLE; Schema: regional; Owner: postgres
--

CREATE TABLE regional.mst_provinces (
    id uuid NOT NULL,
    country_id uuid,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    is_active numeric(1,0) NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    last_approved_by uuid,
    last_approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE regional.mst_provinces OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 16889)
-- Name: mst_villages; Type: TABLE; Schema: regional; Owner: postgres
--

CREATE TABLE regional.mst_villages (
    id uuid NOT NULL,
    district_id uuid,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    is_active numeric(1,0) NOT NULL,
    created_by uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    last_updated_by uuid NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    last_approved_by uuid,
    last_approved_at timestamp without time zone,
    last_version_at timestamp without time zone NOT NULL,
    is_deleted numeric(1,0) DEFAULT 0 NOT NULL
);


ALTER TABLE regional.mst_villages OWNER TO postgres;

--
-- TOC entry 3320 (class 2606 OID 16758)
-- Name: mst_approval_functions PK_MstApprovalFunction_Id; Type: CONSTRAINT; Schema: approval; Owner: postgres
--

ALTER TABLE ONLY approval.mst_approval_functions
    ADD CONSTRAINT "PK_MstApprovalFunction_Id" PRIMARY KEY (id);


--
-- TOC entry 3322 (class 2606 OID 16765)
-- Name: mst_approval_level_approvers PK_MstApprovalLevelApprover_Id; Type: CONSTRAINT; Schema: approval; Owner: postgres
--

ALTER TABLE ONLY approval.mst_approval_level_approvers
    ADD CONSTRAINT "PK_MstApprovalLevelApprover_Id" PRIMARY KEY (id);


--
-- TOC entry 3324 (class 2606 OID 16771)
-- Name: mst_approval_levels PK_MstApprovalLevel_Id; Type: CONSTRAINT; Schema: approval; Owner: postgres
--

ALTER TABLE ONLY approval.mst_approval_levels
    ADD CONSTRAINT "PK_MstApprovalLevel_Id" PRIMARY KEY (id);


--
-- TOC entry 3326 (class 2606 OID 16777)
-- Name: mst_approval_setups PK_MstApprovalSetup_Id; Type: CONSTRAINT; Schema: approval; Owner: postgres
--

ALTER TABLE ONLY approval.mst_approval_setups
    ADD CONSTRAINT "PK_MstApprovalSetup_Id" PRIMARY KEY (id);


--
-- TOC entry 3364 (class 2606 OID 16899)
-- Name: trx_approval_transactions PK_TrxApprovalTransaction_Id; Type: CONSTRAINT; Schema: approval; Owner: postgres
--

ALTER TABLE ONLY approval.trx_approval_transactions
    ADD CONSTRAINT "PK_TrxApprovalTransaction_Id" PRIMARY KEY (id);


--
-- TOC entry 3338 (class 2606 OID 16814)
-- Name: mst_failed_login_attempts PK_MstFailedLoginAttempt_Id; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_failed_login_attempts
    ADD CONSTRAINT "PK_MstFailedLoginAttempt_Id" PRIMARY KEY (id);


--
-- TOC entry 3342 (class 2606 OID 16829)
-- Name: mst_functions PK_MstFunction_Id; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_functions
    ADD CONSTRAINT "PK_MstFunction_Id" PRIMARY KEY (id);


--
-- TOC entry 3344 (class 2606 OID 16834)
-- Name: mst_group_function_permissions PK_MstGroupFunctionPermission_Id; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_group_function_permissions
    ADD CONSTRAINT "PK_MstGroupFunctionPermission_Id" PRIMARY KEY (id);


--
-- TOC entry 3346 (class 2606 OID 16840)
-- Name: mst_groups PK_MstGroup_Id; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_groups
    ADD CONSTRAINT "PK_MstGroup_Id" PRIMARY KEY (id);


--
-- TOC entry 3352 (class 2606 OID 16859)
-- Name: mst_menus PK_MstMenu_Id; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_menus
    ADD CONSTRAINT "PK_MstMenu_Id" PRIMARY KEY (id);


--
-- TOC entry 3354 (class 2606 OID 16865)
-- Name: mst_organizations PK_MstOrganization_Id; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_organizations
    ADD CONSTRAINT "PK_MstOrganization_Id" PRIMARY KEY (id);


--
-- TOC entry 3358 (class 2606 OID 16878)
-- Name: mst_user_sessions PK_MstUserSession_Id; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_user_sessions
    ADD CONSTRAINT "PK_MstUserSession_Id" PRIMARY KEY (id);


--
-- TOC entry 3360 (class 2606 OID 16888)
-- Name: mst_users PK_MstUser_Id; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_users
    ADD CONSTRAINT "PK_MstUser_Id" PRIMARY KEY (id);


--
-- TOC entry 3336 (class 2606 OID 16807)
-- Name: mst_entity_files PK_MstEntityFile_Id; Type: CONSTRAINT; Schema: foundear; Owner: postgres
--

ALTER TABLE ONLY foundear.mst_entity_files
    ADD CONSTRAINT "PK_MstEntityFile_Id" PRIMARY KEY (id);


--
-- TOC entry 3340 (class 2606 OID 16822)
-- Name: mst_files PK_MstFile_Id; Type: CONSTRAINT; Schema: foundear; Owner: postgres
--

ALTER TABLE ONLY foundear.mst_files
    ADD CONSTRAINT "PK_MstFile_Id" PRIMARY KEY (id);


--
-- TOC entry 3348 (class 2606 OID 16846)
-- Name: mst_lookup_details PK_MstLookupDetail_Id; Type: CONSTRAINT; Schema: lookup; Owner: postgres
--

ALTER TABLE ONLY lookup.mst_lookup_details
    ADD CONSTRAINT "PK_MstLookupDetail_Id" PRIMARY KEY (id);


--
-- TOC entry 3350 (class 2606 OID 16852)
-- Name: mst_lookup_headers PK_MstLookupHeader_Id; Type: CONSTRAINT; Schema: lookup; Owner: postgres
--

ALTER TABLE ONLY lookup.mst_lookup_headers
    ADD CONSTRAINT "PK_MstLookupHeader_Id" PRIMARY KEY (id);


--
-- TOC entry 3328 (class 2606 OID 16783)
-- Name: mst_branches PK_MstBranch_Id; Type: CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_branches
    ADD CONSTRAINT "PK_MstBranch_Id" PRIMARY KEY (id);


--
-- TOC entry 3330 (class 2606 OID 16789)
-- Name: mst_cities PK_MstCity_Id; Type: CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_cities
    ADD CONSTRAINT "PK_MstCity_Id" PRIMARY KEY (id);


--
-- TOC entry 3332 (class 2606 OID 16795)
-- Name: mst_countries PK_MstCountry_Id; Type: CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_countries
    ADD CONSTRAINT "PK_MstCountry_Id" PRIMARY KEY (id);


--
-- TOC entry 3334 (class 2606 OID 16801)
-- Name: mst_districts PK_MstDistrict_Id; Type: CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_districts
    ADD CONSTRAINT "PK_MstDistrict_Id" PRIMARY KEY (id);


--
-- TOC entry 3356 (class 2606 OID 16871)
-- Name: mst_provinces PK_MstProvince_Id; Type: CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_provinces
    ADD CONSTRAINT "PK_MstProvince_Id" PRIMARY KEY (id);


--
-- TOC entry 3362 (class 2606 OID 16894)
-- Name: mst_villages PK_MstVillage_Id; Type: CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_villages
    ADD CONSTRAINT "PK_MstVillage_Id" PRIMARY KEY (id);


--
-- TOC entry 3365 (class 2606 OID 16900)
-- Name: mst_approval_functions FK_MstApprovalFunction_ApprovalSetupId_MstApprovalSetup_Id; Type: FK CONSTRAINT; Schema: approval; Owner: postgres
--

ALTER TABLE ONLY approval.mst_approval_functions
    ADD CONSTRAINT "FK_MstApprovalFunction_ApprovalSetupId_MstApprovalSetup_Id" FOREIGN KEY (approval_setup_id) REFERENCES approval.mst_approval_setups(id);


--
-- TOC entry 3366 (class 2606 OID 16905)
-- Name: mst_approval_functions FK_MstApprovalFunction_FunctionId_MstFunction_Id; Type: FK CONSTRAINT; Schema: approval; Owner: postgres
--

ALTER TABLE ONLY approval.mst_approval_functions
    ADD CONSTRAINT "FK_MstApprovalFunction_FunctionId_MstFunction_Id" FOREIGN KEY (function_id) REFERENCES auth.mst_functions(id);


--
-- TOC entry 3367 (class 2606 OID 16910)
-- Name: mst_approval_level_approvers FK_MstApprovalLevelApprover_ApprovalSetupId_MstApprovalSetup_Id; Type: FK CONSTRAINT; Schema: approval; Owner: postgres
--

ALTER TABLE ONLY approval.mst_approval_level_approvers
    ADD CONSTRAINT "FK_MstApprovalLevelApprover_ApprovalSetupId_MstApprovalSetup_Id" FOREIGN KEY (approval_level_id) REFERENCES approval.mst_approval_levels(id);


--
-- TOC entry 3368 (class 2606 OID 16920)
-- Name: mst_approval_levels FK_MstApprovalLevel_ApproverDomain_MstBranch_Id; Type: FK CONSTRAINT; Schema: approval; Owner: postgres
--

ALTER TABLE ONLY approval.mst_approval_levels
    ADD CONSTRAINT "FK_MstApprovalLevel_ApproverDomain_MstBranch_Id" FOREIGN KEY (approver_domain) REFERENCES regional.mst_branches(id);


--
-- TOC entry 3369 (class 2606 OID 16915)
-- Name: mst_approval_levels FK_MstApprovalLevel_Id_MstApprovalSetup_Id; Type: FK CONSTRAINT; Schema: approval; Owner: postgres
--

ALTER TABLE ONLY approval.mst_approval_levels
    ADD CONSTRAINT "FK_MstApprovalLevel_Id_MstApprovalSetup_Id" FOREIGN KEY (approval_setup_id) REFERENCES approval.mst_approval_setups(id);


--
-- TOC entry 3396 (class 2606 OID 17055)
-- Name: trx_approval_transactions FK_TrxApprovalTransaction_ApprovalSetupId_MstApprovalSetup_Id; Type: FK CONSTRAINT; Schema: approval; Owner: postgres
--

ALTER TABLE ONLY approval.trx_approval_transactions
    ADD CONSTRAINT "FK_TrxApprovalTransaction_ApprovalSetupId_MstApprovalSetup_Id" FOREIGN KEY (approval_setup_id) REFERENCES approval.mst_approval_setups(id);


--
-- TOC entry 3397 (class 2606 OID 17060)
-- Name: trx_approval_transactions FK_TrxApprovalTransaction_FunctionId_MstFunction_Id; Type: FK CONSTRAINT; Schema: approval; Owner: postgres
--

ALTER TABLE ONLY approval.trx_approval_transactions
    ADD CONSTRAINT "FK_TrxApprovalTransaction_FunctionId_MstFunction_Id" FOREIGN KEY (function_id) REFERENCES auth.mst_functions(id);


--
-- TOC entry 3382 (class 2606 OID 16985)
-- Name: mst_functions FK_MstFunction_MenuId_MstMenu_Id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_functions
    ADD CONSTRAINT "FK_MstFunction_MenuId_MstMenu_Id" FOREIGN KEY (menu_id) REFERENCES auth.mst_menus(id);


--
-- TOC entry 3383 (class 2606 OID 16990)
-- Name: mst_group_function_permissions FK_MstGroupFunctionPermission_FunctionId_MstFunction_Id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_group_function_permissions
    ADD CONSTRAINT "FK_MstGroupFunctionPermission_FunctionId_MstFunction_Id" FOREIGN KEY (function_id) REFERENCES auth.mst_functions(id);


--
-- TOC entry 3384 (class 2606 OID 16995)
-- Name: mst_group_function_permissions FK_MstGroupFunctionPermission_GroupId_MstGroup_Id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_group_function_permissions
    ADD CONSTRAINT "FK_MstGroupFunctionPermission_GroupId_MstGroup_Id" FOREIGN KEY (group_id) REFERENCES auth.mst_groups(id);


--
-- TOC entry 3385 (class 2606 OID 17000)
-- Name: mst_groups FK_MstGroup_OrgId_MstOrganization_Id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_groups
    ADD CONSTRAINT "FK_MstGroup_OrgId_MstOrganization_Id" FOREIGN KEY (org_id) REFERENCES auth.mst_organizations(id);


--
-- TOC entry 3381 (class 2606 OID 16980)
-- Name: mst_failed_login_attempts FK_MstUserLoginAttempt_UserId_MstUser_Id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_failed_login_attempts
    ADD CONSTRAINT "FK_MstUserLoginAttempt_UserId_MstUser_Id" FOREIGN KEY (user_id) REFERENCES auth.mst_users(id);


--
-- TOC entry 3388 (class 2606 OID 17015)
-- Name: mst_user_sessions FK_MstUserSession_UserId_MstUser_Id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_user_sessions
    ADD CONSTRAINT "FK_MstUserSession_UserId_MstUser_Id" FOREIGN KEY (user_id) REFERENCES auth.mst_users(id);


--
-- TOC entry 3389 (class 2606 OID 17035)
-- Name: mst_users FK_MstUser_CityId_MstCity_Id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_users
    ADD CONSTRAINT "FK_MstUser_CityId_MstCity_Id" FOREIGN KEY (city_id) REFERENCES regional.mst_cities(id);


--
-- TOC entry 3390 (class 2606 OID 17045)
-- Name: mst_users FK_MstUser_CountryId_MstCountry_Id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_users
    ADD CONSTRAINT "FK_MstUser_CountryId_MstCountry_Id" FOREIGN KEY (country_id) REFERENCES regional.mst_countries(id);


--
-- TOC entry 3391 (class 2606 OID 17030)
-- Name: mst_users FK_MstUser_DistrictId_MstDistrict_Id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_users
    ADD CONSTRAINT "FK_MstUser_DistrictId_MstDistrict_Id" FOREIGN KEY (district_id) REFERENCES regional.mst_districts(id);


--
-- TOC entry 3392 (class 2606 OID 17020)
-- Name: mst_users FK_MstUser_GroupId_MstGroup_Id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_users
    ADD CONSTRAINT "FK_MstUser_GroupId_MstGroup_Id" FOREIGN KEY (group_id) REFERENCES auth.mst_groups(id);


--
-- TOC entry 3393 (class 2606 OID 17040)
-- Name: mst_users FK_MstUser_ProvinceId_MstProvince_Id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_users
    ADD CONSTRAINT "FK_MstUser_ProvinceId_MstProvince_Id" FOREIGN KEY (province_id) REFERENCES regional.mst_provinces(id);


--
-- TOC entry 3394 (class 2606 OID 17025)
-- Name: mst_users FK_MstUser_VillageId_MstVillage_Id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.mst_users
    ADD CONSTRAINT "FK_MstUser_VillageId_MstVillage_Id" FOREIGN KEY (village_id) REFERENCES regional.mst_villages(id);


--
-- TOC entry 3379 (class 2606 OID 16970)
-- Name: mst_entity_files FK_MstEntityFile_FileId_MstFile_Id; Type: FK CONSTRAINT; Schema: foundear; Owner: postgres
--

ALTER TABLE ONLY foundear.mst_entity_files
    ADD CONSTRAINT "FK_MstEntityFile_FileId_MstFile_Id" FOREIGN KEY (file_id) REFERENCES foundear.mst_files(id);


--
-- TOC entry 3380 (class 2606 OID 16975)
-- Name: mst_entity_files FK_MstEntityFile_LookupEntityType_MstLookupDetail_Id; Type: FK CONSTRAINT; Schema: foundear; Owner: postgres
--

ALTER TABLE ONLY foundear.mst_entity_files
    ADD CONSTRAINT "FK_MstEntityFile_LookupEntityType_MstLookupDetail_Id" FOREIGN KEY (entity_type) REFERENCES lookup.mst_lookup_details(id);


--
-- TOC entry 3386 (class 2606 OID 17005)
-- Name: mst_lookup_details FK_MstLookupDetail_LookupHeaderId_MstLookupHeader_Id; Type: FK CONSTRAINT; Schema: lookup; Owner: postgres
--

ALTER TABLE ONLY lookup.mst_lookup_details
    ADD CONSTRAINT "FK_MstLookupDetail_LookupHeaderId_MstLookupHeader_Id" FOREIGN KEY (lookup_header_id) REFERENCES lookup.mst_lookup_headers(id);


--
-- TOC entry 3370 (class 2606 OID 16955)
-- Name: mst_branches FK_MstBranch_BranchType_MstLookupDetail_Id; Type: FK CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_branches
    ADD CONSTRAINT "FK_MstBranch_BranchType_MstLookupDetail_Id" FOREIGN KEY (type) REFERENCES lookup.mst_lookup_details(id);


--
-- TOC entry 3371 (class 2606 OID 16935)
-- Name: mst_branches FK_MstBranch_CityId_MstCity_Id; Type: FK CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_branches
    ADD CONSTRAINT "FK_MstBranch_CityId_MstCity_Id" FOREIGN KEY (city_id) REFERENCES regional.mst_cities(id);


--
-- TOC entry 3372 (class 2606 OID 16945)
-- Name: mst_branches FK_MstBranch_CountryId_MstCountry_Id; Type: FK CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_branches
    ADD CONSTRAINT "FK_MstBranch_CountryId_MstCountry_Id" FOREIGN KEY (country_id) REFERENCES regional.mst_countries(id);


--
-- TOC entry 3373 (class 2606 OID 16930)
-- Name: mst_branches FK_MstBranch_DistrictId_MstDistrict_Id; Type: FK CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_branches
    ADD CONSTRAINT "FK_MstBranch_DistrictId_MstDistrict_Id" FOREIGN KEY (district_id) REFERENCES regional.mst_districts(id);


--
-- TOC entry 3374 (class 2606 OID 16950)
-- Name: mst_branches FK_MstBranch_EntityType_MstLookupDetail_Id; Type: FK CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_branches
    ADD CONSTRAINT "FK_MstBranch_EntityType_MstLookupDetail_Id" FOREIGN KEY (entity_type) REFERENCES lookup.mst_lookup_details(id);


--
-- TOC entry 3375 (class 2606 OID 16940)
-- Name: mst_branches FK_MstBranch_ProvinceId_MstProvince_Id; Type: FK CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_branches
    ADD CONSTRAINT "FK_MstBranch_ProvinceId_MstProvince_Id" FOREIGN KEY (province_id) REFERENCES regional.mst_provinces(id);


--
-- TOC entry 3376 (class 2606 OID 16925)
-- Name: mst_branches FK_MstBranch_VillageId_MstVillage_Id; Type: FK CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_branches
    ADD CONSTRAINT "FK_MstBranch_VillageId_MstVillage_Id" FOREIGN KEY (village_id) REFERENCES regional.mst_villages(id);


--
-- TOC entry 3377 (class 2606 OID 16960)
-- Name: mst_cities FK_MstCity_ProvinceId_MstProvince_Id; Type: FK CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_cities
    ADD CONSTRAINT "FK_MstCity_ProvinceId_MstProvince_Id" FOREIGN KEY (province_id) REFERENCES regional.mst_provinces(id);


--
-- TOC entry 3378 (class 2606 OID 16965)
-- Name: mst_districts FK_MstDistrict_CityId_MstCity_Id; Type: FK CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_districts
    ADD CONSTRAINT "FK_MstDistrict_CityId_MstCity_Id" FOREIGN KEY (city_id) REFERENCES regional.mst_cities(id);


--
-- TOC entry 3387 (class 2606 OID 17010)
-- Name: mst_provinces FK_MstProvince_CountryId_MstCountry_Id; Type: FK CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_provinces
    ADD CONSTRAINT "FK_MstProvince_CountryId_MstCountry_Id" FOREIGN KEY (country_id) REFERENCES regional.mst_countries(id);


--
-- TOC entry 3395 (class 2606 OID 17050)
-- Name: mst_villages FK_MstVillage_DistrictId_MstDistrict_Id; Type: FK CONSTRAINT; Schema: regional; Owner: postgres
--

ALTER TABLE ONLY regional.mst_villages
    ADD CONSTRAINT "FK_MstVillage_DistrictId_MstDistrict_Id" FOREIGN KEY (district_id) REFERENCES regional.mst_districts(id);


-- Completed on 2025-01-11 13:13:22

--
-- PostgreSQL database dump complete
--

