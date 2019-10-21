create sequence hibernate_sequence start 1 increment 1;
create table comment (id int8 not null, text varchar(255), user_id int8, note_id int8, primary key (id));
create table disease (id int8 not null, name varchar(255), primary key (id));
create table drug (id int8 not null, name varchar(255), primary key (id));
create table mylike (user_id int8 not null, note_id int8 not null, primary key (user_id, note_id));
create table note (id int8 not null, comment varchar(255), name varchar(255), user_id int8, primary key (id));
create table note_disease (note_id int8 not null, disease_id int8 not null, primary key (note_id, disease_id));
create table note_drug (note_id int8 not null, drug_id int8 not null, primary key (note_id, drug_id));
create table note_symptom (note_id int8 not null, symptom_id int8 not null, primary key (note_id, symptom_id));
create table symptom (id int8 not null, name varchar(255), primary key (id));
create table user_role (user_id int8 not null, roles varchar(255));
create table usr (id int8 not null, active boolean not null, password varchar(255), username varchar(255), primary key (id));