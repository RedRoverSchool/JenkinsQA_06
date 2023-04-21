#!/bin/bash

set -e

times=15
while ! curl -sSL 'http://localhost:8080/login?from=%2F' 2>&1 \
             | grep '<html' >/dev/null; do
    echo 'Waiting for the Jenkins'
    sleep 10
    times=$(($times - 1))

    if [ $times -le 0 ]; then
        echo 'Time out'
        exit 1
    fi
done

echo 'The Jenkins is up'