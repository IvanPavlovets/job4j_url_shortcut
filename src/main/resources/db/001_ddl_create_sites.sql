create table if not exists sites (
    id serial primary key,
    login varchar(200) NOT NULL unique,
    password varchar(200) NOT NULL,
    registration boolean default true
);