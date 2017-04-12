#!/bin/sh

while true
do
	if [ "$(cat /run/secrets/payment_token)" = "verysecret" ]
	then
		echo "OK | Processing credit card transaction..."
	else
		echo "ERROR | Payment token wrong"
		exit 1
	fi
	sleep 5
done
