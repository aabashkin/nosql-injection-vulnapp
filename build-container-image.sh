#!/bin/sh

mvn clean install
docker build --tag aabashkin/niva .