-- run via
--   psql postgres
--   \i drop.sql;

\connect actors psc;
drop table actors;
\connect postgres;
drop database actors;
\q
