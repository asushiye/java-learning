create table aus_test_head
(
    id number,
    name varchar2(30),
    age int,
    create_date date
);

create table aus_test_item
(
    id number,
    ath_id number,
    name varchar2(30),
    age int,
    create_date date
);

alter table aus_test_head add constraint pk_aus_test_head primary key (ID);
alter table aus_test_item add constraint pk_aus_test_item primary key (ID);

create sequence aus_test_head_s;
create sequence aus_test_item_s;