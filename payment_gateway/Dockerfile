FROM alpine:3.15.0

RUN adduser -D payment
USER payment
COPY process.sh /home/payment/process.sh
CMD ["/home/payment/process.sh"]
