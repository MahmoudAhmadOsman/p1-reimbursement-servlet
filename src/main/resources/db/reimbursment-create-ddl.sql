 drop table if exists ers_reimbursements cascade;
drop table if exists ers_reimbursement_types cascade;
drop table if exists ers_reimbursement_statuses cascade;
--drop table if exists ers_users cascade;
drop table if exists users cascade;
drop table if exists ers_user_roles cascade;



--======== ers_user_roles TABLE =========

CREATE TABLE ers_user_roles(
  role_id varchar NOT NULL,
  role varchar NOT NULL,

  --PRIMARY KEY for this table
	constraint pk_ers_user_roles
		primary key (role_id)
);



--======== users TABLE =========

CREATE TABLE users (
	user_id varchar NOT NULL,
	username varchar NOT null UNIQUE,
	email varchar not NULL UNIQUE,
	password varchar NOT NULL,
	given_name varchar NOT NULL,
	surname varchar NOT NULL,
	is_active boolean,
	
	
	
	
	
	--PRIMARY KEY for this table 
	 constraint pk_users
		primary key (user_id),	
		
	
--The referrencing tbale's foreign key
-- the table that this table is referencing must be create first
	role_id varchar not null,	
	
	constraint fk_role_id
		foreign key (role_id) references ers_user_roles (role_id)	
		
		
	
);


--======== ers_reimbursement_statuses TABLE =========
CREATE TABLE ers_reimbursement_statuses (
	status_id varchar,
	status varchar UNIQUE,
	
	--PRIMARY KEY for this table
	constraint pk_ers_reimbursement_statuses
		primary key (status_id)

);



--======== ers_reimbursement_types TABLE =========

CREATE TABLE ers_reimbursement_types(
	type_id varchar,
	type varchar UNIQUE,
	
	--PRIMARY KEY for this table
	constraint pk_ers_reimbursement_types_id
		primary key (type_id)

);






--======== ers_reimbusements TABLE =========

CREATE TABLE ers_reimbursements(
	reim_id varchar,
	amount int NOT NULL,
	submitted timestamp NOT NULL,
	resolved timestamp,
	description varchar NOT NULL,
	receipt BYTEA,
	payment_id varchar,
	
	--author_id is referencing on ers_users, colomn(user_id)
	author_id varchar NOT NULL,
	resolver_id varchar,
	status_id varchar NOT NULL,
	type_id varchar not null,
	
	
	--PRIMARY KEY for this table
	constraint pk_ers_reimbursements
		primary key (reim_id),
	
	
	
	--for users foreign key
	constraint fk_author_id
		foreign key (author_id) references users (user_id),
		
	
	--for resolver foreign key
	constraint fk_resolver_id
		foreign key (resolver_id) references users (user_id),
		
		
	--for status foreign key
	constraint fk_status_id
		foreign key (status_id) references ers_reimbursement_statuses (status_id),
		
		
	--for types foreign key
	constraint fk_reimbursement_type_id
		foreign key (type_id) references ers_reimbursement_types (type_id)		
		
		
);



