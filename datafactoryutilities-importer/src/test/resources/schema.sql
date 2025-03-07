-- Database: mydatabase

-- DROP DATABASE IF EXISTS mydatabase;

CREATE DATABASE mydatabase
    WITH
    OWNER = myuser
    ENCODING = 'UTF8'
    LC_COLLATE = 'German_Germany.1252'
    LC_CTYPE = 'German_Germany.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- SCHEMA: public

-- DROP SCHEMA IF EXISTS public ;

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT USAGE ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO pg_database_owner;

-- Table: public.ags

-- DROP TABLE IF EXISTS public.ags;

CREATE TABLE IF NOT EXISTS public.ags
(
    created_at                timestamp(6) without time zone,
    modified_at               timestamp(6) without time zone,
    version                   timestamp(6) without time zone,
    importing_file_identifier character varying(255) COLLATE pg_catalog."default",
    kg_datum                  character varying(255) COLLATE pg_catalog."default",
    kg_name                   character varying(255) COLLATE pg_catalog."default",
    kg_satzart                character varying(255) COLLATE pg_catalog."default" NOT NULL,
    kg_schluessel             character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ags_pkey PRIMARY KEY (kg_satzart, kg_schluessel)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.ags
    OWNER to myuser;
-- Index: idx_ags___kg_schluessel__kg_satzart

-- DROP INDEX IF EXISTS public.idx_ags___kg_schluessel__kg_satzart;

CREATE INDEX IF NOT EXISTS idx_ags___kg_schluessel__kg_satzart
    ON public.ags USING btree
        (kg_schluessel COLLATE pg_catalog."default" ASC NULLS LAST, kg_satzart COLLATE pg_catalog."default" ASC NULLS
         LAST)
    TABLESPACE pg_default;

-- Table: public.import_result

-- DROP TABLE IF EXISTS public.import_result;

CREATE TABLE IF NOT EXISTS public.import_result
(
    import_successful boolean NOT NULL,
    duration          bigint,
    id                bigint  NOT NULL,
    import_date       timestamp(6) without time zone,
    file_name         character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT import_result_pkey PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.import_result
    OWNER to myuser;

-- Table: public.ort

-- DROP TABLE IF EXISTS public.ort;

CREATE TABLE IF NOT EXISTS public.ort
(
    already_applied_at        timestamp(6) without time zone,
    created_at                timestamp(6) without time zone,
    modified_at               timestamp(6) without time zone,
    outdated_at               timestamp(6) without time zone,
    version                   timestamp(6) without time zone,
    importing_file_identifier character varying(255) COLLATE pg_catalog."default",
    ort_alort                 character varying(255) COLLATE pg_catalog."default" NOT NULL,
    ort_alort_neu             character varying(255) COLLATE pg_catalog."default",
    ort_art_ozusatz           character varying(255) COLLATE pg_catalog."default",
    ort_datum                 character varying(255) COLLATE pg_catalog."default",
    ort_kgs                   character varying(255) COLLATE pg_catalog."default",
    ort_oname                 character varying(255) COLLATE pg_catalog."default",
    ort_oname24               character varying(255) COLLATE pg_catalog."default",
    ort_oname_post            character varying(255) COLLATE pg_catalog."default",
    ort_ozusatz               character varying(255) COLLATE pg_catalog."default",
    ort_status                character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ort_pkey PRIMARY KEY (ort_alort, ort_status)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.ort
    OWNER to myuser;
-- Index: idx_ort___ort_alort__ort_status

-- DROP INDEX IF EXISTS public.idx_ort___ort_alort__ort_status;

CREATE INDEX IF NOT EXISTS idx_ort___ort_alort__ort_status
    ON public.ort USING btree
        (ort_alort COLLATE pg_catalog."default" ASC NULLS LAST, ort_status COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

-- Table: public.ortsteil

-- DROP TABLE IF EXISTS public.ortsteil;

CREATE TABLE IF NOT EXISTS public.ortsteil
(
    already_applied_at        timestamp(6) without time zone,
    created_at                timestamp(6) without time zone,
    modified_at               timestamp(6) without time zone,
    outdated_at               timestamp(6) without time zone,
    version                   timestamp(6) without time zone,
    importing_file_identifier character varying(255) COLLATE pg_catalog."default",
    otl_alort                 character varying(255) COLLATE pg_catalog."default" NOT NULL,
    otl_datum                 character varying(255) COLLATE pg_catalog."default",
    otl_kgs                   character varying(255) COLLATE pg_catalog."default",
    otl_name                  character varying(255) COLLATE pg_catalog."default",
    otl_plz                   character varying(255) COLLATE pg_catalog."default" NOT NULL,
    otl_schl                  character varying(255) COLLATE pg_catalog."default" NOT NULL,
    otl_status                character varying(255) COLLATE pg_catalog."default" NOT NULL,
    otl_stverz                character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT ortsteil_pkey PRIMARY KEY (otl_alort, otl_plz, otl_schl, otl_status)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.ortsteil
    OWNER to myuser;
-- Index: idx_ortsteil___otl_alort__otl_schl__otl_plz__otl_status

-- DROP INDEX IF EXISTS public.idx_ortsteil___otl_alort__otl_schl__otl_plz__otl_status;

CREATE INDEX IF NOT EXISTS idx_ortsteil___otl_alort__otl_schl__otl_plz__otl_status
    ON public.ortsteil USING btree
        (otl_alort COLLATE pg_catalog."default" ASC NULLS LAST, otl_schl COLLATE pg_catalog."default" ASC NULLS LAST,
         otl_plz COLLATE pg_catalog."default" ASC NULLS LAST, otl_status COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

-- Table: public.plz

-- DROP TABLE IF EXISTS public.plz;

CREATE TABLE IF NOT EXISTS public.plz
(
    created_at                timestamp(6) without time zone,
    modified_at               timestamp(6) without time zone,
    version                   timestamp(6) without time zone,
    importing_file_identifier character varying(255) COLLATE pg_catalog."default",
    plz_alort                 character varying(255) COLLATE pg_catalog."default" NOT NULL,
    plz_art_auslierferung     character varying(255) COLLATE pg_catalog."default",
    "plz_art_kardinalität"    character varying(255) COLLATE pg_catalog."default",
    plz_art_ozusatz           character varying(255) COLLATE pg_catalog."default",
    plz_bz_nr                 character varying(255) COLLATE pg_catalog."default",
    plz_datum                 character varying(255) COLLATE pg_catalog."default",
    plz_fz_nr                 character varying(255) COLLATE pg_catalog."default",
    plz_kgs                   character varying(255) COLLATE pg_catalog."default",
    plz_la_alort              character varying(255) COLLATE pg_catalog."default",
    plz_la_brief              character varying(255) COLLATE pg_catalog."default",
    plz_leitcode_max          character varying(255) COLLATE pg_catalog."default",
    plz_oname                 character varying(255) COLLATE pg_catalog."default",
    plz_oname24               character varying(255) COLLATE pg_catalog."default",
    plz_ort_code              character varying(255) COLLATE pg_catalog."default",
    plz_ozusatz               character varying(255) COLLATE pg_catalog."default",
    plz_pfverz                character varying(255) COLLATE pg_catalog."default",
    plz_plz                   character varying(255) COLLATE pg_catalog."default" NOT NULL,
    plz_postlag               character varying(255) COLLATE pg_catalog."default",
    plz_rabatt_info_schwer    character varying(255) COLLATE pg_catalog."default",
    plz_reserve               character varying(255) COLLATE pg_catalog."default",
    plz_stverz                character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT plz_pkey PRIMARY KEY (plz_alort, plz_plz)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.plz
    OWNER to myuser;
-- Index: idx_plz___plz_plz__plz_alort

-- DROP INDEX IF EXISTS public.idx_plz___plz_plz__plz_alort;

CREATE INDEX IF NOT EXISTS idx_plz___plz_plz__plz_alort
    ON public.plz USING btree
        (plz_plz COLLATE pg_catalog."default" ASC NULLS LAST, plz_alort COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

-- Table: public.strasse

-- DROP TABLE IF EXISTS public.strasse;

CREATE TABLE IF NOT EXISTS public.strasse
(
    already_applied_at        timestamp(6) without time zone,
    created_at                timestamp(6) without time zone,
    modified_at               timestamp(6) without time zone,
    outdated_at               timestamp(6) without time zone,
    version                   timestamp(6) without time zone,
    importing_file_identifier character varying(255) COLLATE pg_catalog."default",
    str_alorgb                character varying(255) COLLATE pg_catalog."default",
    str_alort                 character varying(255) COLLATE pg_catalog."default" NOT NULL,
    str_alort_neu             character varying(255) COLLATE pg_catalog."default",
    str_bund_lfdnr            character varying(255) COLLATE pg_catalog."default" NOT NULL,
    str_bund_lfdnr_neu        character varying(255) COLLATE pg_catalog."default",
    str_code                  character varying(255) COLLATE pg_catalog."default",
    str_datum                 character varying(255) COLLATE pg_catalog."default",
    str_hnr1000               character varying(255) COLLATE pg_catalog."default" NOT NULL,
    str_hnr_typ               character varying(255) COLLATE pg_catalog."default",
    str_hnrbis                character varying(255) COLLATE pg_catalog."default" NOT NULL,
    str_hnrbis_neu            character varying(255) COLLATE pg_catalog."default",
    str_hnrvon                character varying(255) COLLATE pg_catalog."default" NOT NULL,
    str_hnrvon_neu            character varying(255) COLLATE pg_catalog."default",
    str_kgs                   character varying(255) COLLATE pg_catalog."default",
    str_name22                character varying(255) COLLATE pg_catalog."default",
    str_name46                character varying(255) COLLATE pg_catalog."default",
    str_name_sort             character varying(255) COLLATE pg_catalog."default",
    str_namen_schl            character varying(255) COLLATE pg_catalog."default" NOT NULL,
    str_namen_schl_neu        character varying(255) COLLATE pg_catalog."default",
    str_otl_schl              character varying(255) COLLATE pg_catalog."default",
    str_plz                   character varying(255) COLLATE pg_catalog."default",
    str_reserve               character varying(255) COLLATE pg_catalog."default",
    str_status                character varying(255) COLLATE pg_catalog."default" NOT NULL,
    str_stverz                character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT strasse_pkey PRIMARY KEY (str_alort, str_bund_lfdnr, str_hnr1000, str_hnrbis, str_hnrvon, str_namen_schl,
                                         str_status)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.strasse
    OWNER to myuser;
-- Index: idx_strasse___str_alort__str_namen_schl__str_bund_lfdnr__str_hn

-- DROP INDEX IF EXISTS public.idx_strasse___str_alort__str_namen_schl__str_bund_lfdnr__str_hn;

CREATE INDEX IF NOT EXISTS idx_strasse___str_alort__str_namen_schl__str_bund_lfdnr__str_hn
    ON public.strasse USING btree
        (str_alort COLLATE pg_catalog."default" ASC NULLS LAST, str_namen_schl COLLATE pg_catalog."default" ASC NULLS
         LAST, str_bund_lfdnr COLLATE pg_catalog."default" ASC NULLS LAST, str_hnrvon COLLATE pg_catalog."default" ASC
         NULLS LAST, str_hnrbis COLLATE pg_catalog."default" ASC NULLS LAST, str_status COLLATE pg_catalog."default" ASC
         NULLS LAST, str_hnr1000 COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;