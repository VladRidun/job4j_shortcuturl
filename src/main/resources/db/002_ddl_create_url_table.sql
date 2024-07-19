CREATE TABLE url
(
    id         serial primary key not null,
    longUrl      varchar(2000) unique,
    shortUrl   varchar(2000),
    count int,
    site_id int REFERENCES site(id)
);