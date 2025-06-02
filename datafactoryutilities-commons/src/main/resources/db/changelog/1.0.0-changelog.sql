-- liquibase formatted sql

-- changeset dusch:1748864099729-1
CREATE SEQUENCE IF NOT EXISTS import_result_seq START WITH 1 INCREMENT BY 50;

-- changeset dusch:1748864099729-2
CREATE TABLE ags
(
    importing_file_identifier VARCHAR(255),
    created_at                TIMESTAMP WITHOUT TIME ZONE,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    version                   TIMESTAMP WITHOUT TIME ZONE,
    kg_datum                  VARCHAR(255),
    kg_name                   VARCHAR(255),
    kg_schluessel             VARCHAR(255) NOT NULL,
    kg_satzart                VARCHAR(255) NOT NULL,
    CONSTRAINT pk_ags PRIMARY KEY (kg_schluessel, kg_satzart)
);

-- changeset dusch:1748864099729-3
CREATE TABLE import_result
(
    id                BIGINT  NOT NULL,
    file_name         VARCHAR(255),
    import_date       TIMESTAMP WITHOUT TIME ZONE,
    import_successful BOOLEAN NOT NULL,
    duration          BIGINT,
    CONSTRAINT pk_importresult PRIMARY KEY (id)
);

-- changeset dusch:1748864099729-4
CREATE TABLE ort
(
    importing_file_identifier VARCHAR(255),
    created_at                TIMESTAMP WITHOUT TIME ZONE,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    version                   TIMESTAMP WITHOUT TIME ZONE,
    ort_datum                 VARCHAR(255),
    ort_oname                 VARCHAR(255),
    ort_oname_post            VARCHAR(255),
    ort_ozusatz               VARCHAR(255),
    ort_art_ozusatz           VARCHAR(255),
    ort_oname24               VARCHAR(255),
    ort_kgs                   VARCHAR(255),
    ort_alort_neu             VARCHAR(255),
    outdated_at               TIMESTAMP WITHOUT TIME ZONE,
    already_applied_at        TIMESTAMP WITHOUT TIME ZONE,
    ort_alort                 VARCHAR(255) NOT NULL,
    ort_status                VARCHAR(255) NOT NULL,
    CONSTRAINT pk_ort PRIMARY KEY (ort_alort, ort_status)
);

-- changeset dusch:1748864099729-5
CREATE TABLE ortsteil
(
    importing_file_identifier VARCHAR(255),
    created_at                TIMESTAMP WITHOUT TIME ZONE,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    version                   TIMESTAMP WITHOUT TIME ZONE,
    otl_datum                 VARCHAR(255),
    otl_stverz                VARCHAR(255),
    otl_name                  VARCHAR(255),
    otl_kgs                   VARCHAR(255),
    outdated_at               TIMESTAMP WITHOUT TIME ZONE,
    already_applied_at        TIMESTAMP WITHOUT TIME ZONE,
    otl_alort                 VARCHAR(255) NOT NULL,
    otl_schl                  VARCHAR(255) NOT NULL,
    otl_plz                   VARCHAR(255) NOT NULL,
    otl_status                VARCHAR(255) NOT NULL,
    CONSTRAINT pk_ortsteil PRIMARY KEY (otl_alort, otl_schl, otl_plz, otl_status)
);

-- changeset dusch:1748864099729-6
CREATE TABLE plz
(
    importing_file_identifier VARCHAR(255),
    created_at                TIMESTAMP WITHOUT TIME ZONE,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    version                   TIMESTAMP WITHOUT TIME ZONE,
    plz_datum                 VARCHAR(255),
    "plz_art_kardinalität"    VARCHAR(255),
    plz_art_auslierferung     VARCHAR(255),
    plz_stverz                VARCHAR(255),
    plz_pfverz                VARCHAR(255),
    plz_oname                 VARCHAR(255),
    plz_ozusatz               VARCHAR(255),
    plz_art_ozusatz           VARCHAR(255),
    plz_oname24               VARCHAR(255),
    plz_postlag               VARCHAR(255),
    plz_la_brief              VARCHAR(255),
    plz_la_alort              VARCHAR(255),
    plz_kgs                   VARCHAR(255),
    plz_ort_code              VARCHAR(255),
    plz_leitcode_max          VARCHAR(255),
    plz_rabatt_info_schwer    VARCHAR(255),
    plz_reserve               VARCHAR(255),
    plz_fz_nr                 VARCHAR(255),
    plz_bz_nr                 VARCHAR(255),
    plz_plz                   VARCHAR(255) NOT NULL,
    plz_alort                 VARCHAR(255) NOT NULL,
    CONSTRAINT pk_plz PRIMARY KEY (plz_plz, plz_alort)
);

-- changeset dusch:1748864099729-7
CREATE TABLE strasse
(
    importing_file_identifier VARCHAR(255),
    created_at                TIMESTAMP WITHOUT TIME ZONE,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    version                   TIMESTAMP WITHOUT TIME ZONE,
    str_datum                 VARCHAR(255),
    str_stverz                VARCHAR(255),
    str_name_sort             VARCHAR(255),
    str_name46                VARCHAR(255),
    str_name22                VARCHAR(255),
    str_reserve               VARCHAR(255),
    str_hnr_typ               VARCHAR(255),
    str_plz                   VARCHAR(255),
    str_code                  VARCHAR(255),
    str_otl_schl              VARCHAR(255),
    str_alorgb                VARCHAR(255),
    str_kgs                   VARCHAR(255),
    str_alort_neu             VARCHAR(255),
    str_namen_schl_neu        VARCHAR(255),
    str_bund_lfdnr_neu        VARCHAR(255),
    str_hnrvon_neu            VARCHAR(255),
    str_hnrbis_neu            VARCHAR(255),
    outdated_at               TIMESTAMP WITHOUT TIME ZONE,
    already_applied_at        TIMESTAMP WITHOUT TIME ZONE,
    str_alort                 VARCHAR(255) NOT NULL,
    str_namen_schl            VARCHAR(255) NOT NULL,
    str_bund_lfdnr            VARCHAR(255) NOT NULL,
    str_hnrvon                VARCHAR(255) NOT NULL,
    str_hnrbis                VARCHAR(255) NOT NULL,
    str_status                VARCHAR(255) NOT NULL,
    str_hnr1000               VARCHAR(255) NOT NULL,
    CONSTRAINT pk_strasse PRIMARY KEY (str_alort, str_namen_schl, str_bund_lfdnr, str_hnrvon, str_hnrbis, str_status,
                                       str_hnr1000)
);

