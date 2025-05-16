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
values(1,'firstadminname','lastadminname1','adminemail','$2a$10$1fnktPcyCD8aqmWaan/qz.VK0eZbZpR5LisdpMnW5PzHWF1agx2wy','ADMIN'),
    (2,'firstusername2','lastusername2','useremail','$2a$10$1fnktPcyCD8aqmWaan/qz.VK0eZbZpR5LisdpMnW5PzHWF1agx2wy','USER')
