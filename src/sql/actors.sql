-- run via
--   psql postgres
--   \i actors.sql;

create database actors with owner psc;
\connect actors psc;
create table actors(id serial primary key, name text, url text);
insert into actors(name) values ('Requester');
insert into actors(name, url) values ('Responder', 'akka://localhost:5153/rest_of_url');
select * from actors;
\q