CREATE TABLE IF NOT EXISTS ags
(
    created_at                timestamp(6) without time zone,
    modified_at               timestamp(6) without time zone,
    version                   timestamp(6) without time zone,
    importing_file_identifier character varying(255),
    kg_datum                  character varying(255),
    kg_name                   character varying(255),
    kg_satzart                character varying(255) NOT NULL,
    kg_schluessel             character varying(255) NOT NULL,
    CONSTRAINT ags_pkey PRIMARY KEY (kg_satzart, kg_schluessel)
);

CREATE TABLE IF NOT EXISTS import_result
(
    import_successful boolean NOT NULL,
    duration          bigint,
    id                bigint  NOT NULL,
    import_date       timestamp(6) without time zone,
    file_name character varying(255),
    CONSTRAINT import_result_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ort
(
    already_applied_at        timestamp(6) without time zone,
    created_at                timestamp(6) without time zone,
    modified_at               timestamp(6) without time zone,
    outdated_at               timestamp(6) without time zone,
    version                   timestamp(6) without time zone,
    importing_file_identifier character varying(255),
    ort_alort                 character varying(255) NOT NULL,
    ort_alort_neu             character varying(255),
    ort_art_ozusatz           character varying(255),
    ort_datum                 character varying(255),
    ort_kgs                   character varying(255),
    ort_oname                 character varying(255),
    ort_oname24               character varying(255),
    ort_oname_post            character varying(255),
    ort_ozusatz               character varying(255),
    ort_status                character varying(255) NOT NULL,
    CONSTRAINT ort_pkey PRIMARY KEY (ort_alort, ort_status)
);

CREATE TABLE IF NOT EXISTS ortsteil
(
    already_applied_at        timestamp(6) without time zone,
    created_at                timestamp(6) without time zone,
    modified_at               timestamp(6) without time zone,
    outdated_at               timestamp(6) without time zone,
    version                   timestamp(6) without time zone,
    importing_file_identifier character varying(255),
    otl_alort                 character varying(255) NOT NULL,
    otl_datum                 character varying(255),
    otl_kgs                   character varying(255),
    otl_name                  character varying(255),
    otl_plz                   character varying(255) NOT NULL,
    otl_schl                  character varying(255) NOT NULL,
    otl_status                character varying(255) NOT NULL,
    otl_stverz                character varying(255),
    CONSTRAINT ortsteil_pkey PRIMARY KEY (otl_alort, otl_plz, otl_schl, otl_status)
);

CREATE TABLE IF NOT EXISTS plz
(
    created_at                timestamp(6) without time zone,
    modified_at               timestamp(6) without time zone,
    version                   timestamp(6) without time zone,
    importing_file_identifier character varying(255),
    plz_alort                 character varying(255) NOT NULL,
    plz_art_auslierferung     character varying(255),
    "plz_art_kardinalität"    character varying(255),
    plz_art_ozusatz           character varying(255),
    plz_bz_nr                 character varying(255),
    plz_datum                 character varying(255),
    plz_fz_nr                 character varying(255),
    plz_kgs                   character varying(255),
    plz_la_alort              character varying(255),
    plz_la_brief              character varying(255),
    plz_leitcode_max          character varying(255),
    plz_oname                 character varying(255),
    plz_oname24               character varying(255),
    plz_ort_code              character varying(255),
    plz_ozusatz               character varying(255),
    plz_pfverz                character varying(255),
    plz_plz                   character varying(255) NOT NULL,
    plz_postlag               character varying(255),
    plz_rabatt_info_schwer    character varying(255),
    plz_reserve               character varying(255),
    plz_stverz                character varying(255),
    CONSTRAINT plz_pkey PRIMARY KEY (plz_alort, plz_plz)
);

CREATE TABLE IF NOT EXISTS strasse
(
    already_applied_at        timestamp(6) without time zone,
    created_at                timestamp(6) without time zone,
    modified_at               timestamp(6) without time zone,
    outdated_at               timestamp(6) without time zone,
    version                   timestamp(6) without time zone,
    importing_file_identifier character varying(255),
    str_alorgb                character varying(255),
    str_alort                 character varying(255) NOT NULL,
    str_alort_neu             character varying(255),
    str_bund_lfdnr            character varying(255) NOT NULL,
    str_bund_lfdnr_neu        character varying(255),
    str_code                  character varying(255),
    str_datum                 character varying(255),
    str_hnr1000               character varying(255) NOT NULL,
    str_hnr_typ               character varying(255),
    str_hnrbis                character varying(255) NOT NULL,
    str_hnrbis_neu            character varying(255),
    str_hnrvon                character varying(255) NOT NULL,
    str_hnrvon_neu            character varying(255),
    str_kgs                   character varying(255),
    str_name22                character varying(255),
    str_name46                character varying(255),
    str_name_sort             character varying(255),
    str_namen_schl            character varying(255) NOT NULL,
    str_namen_schl_neu        character varying(255),
    str_otl_schl              character varying(255),
    str_plz                   character varying(255),
    str_reserve               character varying(255),
    str_status                character varying(255) NOT NULL,
    str_stverz                character varying(255),
    CONSTRAINT strasse_pkey PRIMARY KEY (str_alort, str_bund_lfdnr, str_hnr1000, str_hnrbis, str_hnrvon, str_namen_schl,
                                         str_status)
);