docker exec -it postgres psql -U postgres
CREATE DATABASE dentist;
CREATE USER dentistuser WITH PASSWORD 'dentist';