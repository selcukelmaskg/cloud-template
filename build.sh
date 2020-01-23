#!/bin/bash
#printf "Please enter service name(s)"
#read serviceName

#serviceName="api-documentation api-gateway asset-service catalog-service customer-service eureka-server logger-service order-service"
serviceName="api-documentation api-gateway asset-service catalog-service customer-service eureka-server logger-service order-service"
LC_ALL=C
serviceName=$(echo "$serviceName" | sed 's/ /,/g')
echo ${serviceName}

mvn -pl ${serviceName},shared -am clean install -DskipTests -Pdocker

serviceNames=$(echo ${serviceName} | tr "," "\n")

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

docker-compose up --build -d
#for name in ${serviceNames}
#do
#    cd ${DIR}
#    echo "> [$name]"
#    cd ${name}
#    docker build -t ${name} .
#done
# api-documentation api-gateway asset-service catalog-service customer-service eureka-server logger-service order-service
