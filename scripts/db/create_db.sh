#!/bin/bash

#docker run -i --rm --name ms_postgres \
docker run --detach \
  --name ms_postgres \
  -e POSTGRES_DB=users \
  -e POSTGRES_USER=pguser \
  -e POSTGRES_PASSWORD=pgpass \
  -e PGDATA=/var/lib/postgresql/data/ms_pgdata \
  -v ./postgres-data:/var/lib/postgresql/data \
  -p 5433:5432 \
  postgres:16.1

docker cp ./init.sql ms_postgres:/docker-entrypoint-initdb.d/init.sql
docker exec -u postgres ms_postgres psql users pguser -f docker-entrypoint-initdb.d/init.sql