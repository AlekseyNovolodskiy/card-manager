create table if not exists user_info
(
    id int8 primary key,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    email varchar NOT NULL  UNIQUE,
    password varchar NOT NULL,
    role varchar NOT NULL

);

create sequence user_info_sequence start 3 increment 1;

insert into user_info(id, first_name, last_name, email, password, role)
values(1,'firstname1','lastname1','greathybi4@gmail.com','cGFzc3dvcmQ=','USER'),
    (2,'firstname2','lastname2','greathybi3@gmail.com','cGFzc3dvcmQ=','USER')
