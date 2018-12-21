CREATE TABLE resume
(
  uuid      VARCHAR(36) PRIMARY KEY NOT NULL,
  full_name TEXT                    NOT NULL
);

CREATE TABLE contact
(
  id          SERIAL,
  resume_uuid VARCHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
  type        TEXT        NOT NULL,
  value       TEXT        NOT NULL
);

CREATE TABLE section
(
  id            SERIAL      NOT NULL,
  type_section  TEXT        NOT NULL,
  value_section TEXT        NOT NULL,
  resume_uuid   VARCHAR(36) NOT NULL
    CONSTRAINT section_name_resume__fk
      REFERENCES resume
      ON UPDATE RESTRICT ON DELETE CASCADE
);

ALTER TABLE section
  OWNER TO postgres;



ALTER TABLE section
  OWNER TO postgres;

CREATE UNIQUE INDEX section_type_uindex
  ON section (type_section);



CREATE UNIQUE INDEX contact_uuid_type_index
  ON contact (resume_uuid, type);