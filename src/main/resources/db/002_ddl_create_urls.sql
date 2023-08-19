create table if not exists sites (
    id serial primary key,
    url varchar(800) NOT NULL unique,
    site_id int NOT NULL references sites(id),
    code varchar(200) NOT NULL unique,
    total int default 0
);