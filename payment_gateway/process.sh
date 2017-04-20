#!/bin/sh

while true
do
	case "$(cat /run/secrets/payment_token)" in
	staging)
		echo "$(date '+%s') | OK | Authenticated in staging mode, waiting for transactions..."
		;;
	production)
		echo "$(date '+%s') | OK | PRODUCTION MODE! WE'RE READY TO ROCK AND ROLL!"
		;;
	*)
		RED='\033[0;31m'
		NC='\033[0m'
		echo -e "${RED}$(date '+%s') | ERROR | Payment token WRONG!!!!!!! :(${NC}"
		exit 1
		;;
	esac
	sleep 1
done
