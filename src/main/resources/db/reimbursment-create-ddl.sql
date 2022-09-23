DROP TABLE IF EXISTS ers_reimbursements CASCADE;

DROP TABLE IF EXISTS ers_reimbursement_types CASCADE;

DROP TABLE IF EXISTS ers_reimbursement_statuses CASCADE;

--drop table if exists ers_users cascade;
DROP TABLE IF EXISTS users CASCADE;

DROP TABLE IF EXISTS ers_user_roles CASCADE;




--======== ers_user_roles TABLE =========
CREATE TABLE ers_user_roles
  (
     role_id VARCHAR NOT NULL,
     role    VARCHAR NOT NULL,
     --PRIMARY KEY for this table
     CONSTRAINT pk_ers_user_roles PRIMARY KEY (role_id)
  );

--======== users TABLE =========
CREATE TABLE users
  (
     user_id    VARCHAR NOT NULL,
     username   VARCHAR NOT NULL UNIQUE,
     email      VARCHAR NOT NULL UNIQUE,
     password   VARCHAR NOT NULL,
     given_name VARCHAR NOT NULL,
     surname    VARCHAR NOT NULL,
     is_active  BOOLEAN,
          --PRIMARY KEY for this table 
          CONSTRAINT pk_users PRIMARY KEY (user_id),
          --The referrencing tbale's foreign key
          -- the table that this table is referencing must be create first
          role_id    VARCHAR NOT NULL,
     CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES ers_user_roles (
          role_id)
  );

--======== ers_reimbursement_statuses TABLE =========
CREATE TABLE ers_reimbursement_statuses
  (
     status_id VARCHAR,
     status    VARCHAR UNIQUE,
     --PRIMARY KEY for this table
     CONSTRAINT pk_ers_reimbursement_statuses PRIMARY KEY (status_id)
  );

--======== ers_reimbursement_types TABLE =========
CREATE TABLE ers_reimbursement_types
  (
     type_id VARCHAR,
     type    VARCHAR UNIQUE,
     --PRIMARY KEY for this table
     CONSTRAINT pk_ers_reimbursement_types_id PRIMARY KEY (type_id)
  );

--======== ers_reimbusements TABLE =========
CREATE TABLE ers_reimbursements
  (
     reim_id     VARCHAR,
     amount      INT NOT NULL,
     submitted   TIMESTAMP NOT NULL,
     resolved    TIMESTAMP,
     description VARCHAR NOT NULL,
     receipt     BYTEA,
     payment_id  VARCHAR,
     --author_id is referencing on ers_users, colomn(user_id)
     author_id   VARCHAR NOT NULL,
     resolver_id VARCHAR,
     status_id   VARCHAR NOT NULL,
     type_id     VARCHAR NOT NULL,
     --PRIMARY KEY for this table
     CONSTRAINT pk_ers_reimbursements PRIMARY KEY (reim_id),
     --for users foreign key
     CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES users (user_id),
     --for resolver foreign key
     CONSTRAINT fk_resolver_id FOREIGN KEY (resolver_id) REFERENCES users (
     user_id),
     --for status foreign key
     CONSTRAINT fk_status_id FOREIGN KEY (status_id) REFERENCES
     ers_reimbursement_statuses (status_id),
     --for types foreign key
     CONSTRAINT fk_reimbursement_type_id FOREIGN KEY (type_id) REFERENCES
     ers_reimbursement_types (type_id)
  ); 
