# docker image for db tests
FROM postgres:9.6

# https://stackoverflow.com/questions/26598738/how-to-create-user-database-in-script-for-docker-postgres
COPY sql/ddl_init.sql /docker-entrypoint-initdb.d/
COPY sql/triggers_init.sql /docker-entrypoint-initdb.d/

ENV POSTGRES_PASSWORD=password