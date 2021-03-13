#!/bin/bash

printf "====== Executing Docker Compose with Build to start the application ======\n"
printf "\n"

printf "=========== Building local api-client Project with local Maven ===========\n"
printf "\n"
cd api-client/
./mvnw package
cd ..
printf "\n"
printf "============ api-client Project builded successfully by Maven ============\n"
printf "\n"

printf "=========== Building local mock-server Project with local Maven ==========\n"
printf "\n"
cd mock-server/
./mvnw package
cd ..
printf "\n"
printf "============ mock-server Project builded successfully by Maven ===========\n"
printf "\n"

function ctrl_c() {
printf "\n"
printf '==========================================================================\n'
printf '============= EXECUTIN DOCKER COMPOSE DOWN AFTER CTRL+C ==================\n'
printf '==========================================================================\n'
printf "\n"
docker-compose -f ./docker-compose-mock.yml down
exit
}
trap ctrl_c INT

docker-compose -f ./docker-compose-mock.yml up --build

ctrl_c