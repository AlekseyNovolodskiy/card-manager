create table if not exists role(
    id bigint primary key not null ,
    name varchar (256) not null
);
create sequence role_sequence start 3 increment 1;
insert into role(id, name)
VALUES (1,'USER'),
       (2,'ADMIN');