#!/bin/sh

if [ -z "$1" ]
then
    echo "Missing tag version"
    exit
fi 

docker tag aabashkin/niva aabashkin/niva:$1

docker push aabashkin/niva
docker push aabashkin/niva:$1
