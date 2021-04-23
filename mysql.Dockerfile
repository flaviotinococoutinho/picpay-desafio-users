FROM alpine:3.8 as dwnldr

RUN apk --update add wget \
     gzip

RUN wget "https://s3.amazonaws.com/careers-picpay/users.csv.gz" -P /tmp/
RUN gunzip /tmp/users.csv.gz

FROM mysql:5.5
ENV MYSQL_DATABASE=picpay_teste \
    MYSQL_ROOT_PASSWORD=1qwertyu

RUN echo 'secure_file_priv="/tmp"' >> /etc/mysql/my.cnf

COPY --from=dwnldr /tmp/users.csv /tmp/users.csv

RUN chmod -R 777 /tmp/users.csv

ADD ./scripts/db-init.sql /docker-entrypoint-initdb.d/
EXPOSE 3306
