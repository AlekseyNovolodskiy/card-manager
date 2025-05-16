create table if not exists card_info
(   id           BIGINT PRIMARY KEY,
    card_number  VARCHAR(255)   NOT NULL,
    card_status  VARCHAR(20)    NOT NULL,
    expired_date DATE           NOT NULL,
    balance      DECIMAL(10, 2) NOT NULL,
    user_id      BIGINT         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_info (id)
);

create sequence card_info_sequence start 3 increment 1;

insert into card_info(id, card_number, card_status, expired_date, balance, user_id)

values (1,'5555888899996666','BLOCKED','2027-07-10',22.01,2),
       (2,'5555888877776666','ACTIVE','2030-07-10',222222.01,2)

