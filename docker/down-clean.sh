#!/bin/bash

set -ex

docker-compose rm -fsv
rm -r ./db/postgres-data