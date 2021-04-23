SET GLOBAL local_infile = 1;

CREATE DATABASE IF NOT EXISTS picpay_teste;
USE picpay_teste;

CREATE TABLE IF NOT EXISTS user (
	id INTEGER NOT NULL AUTO_INCREMENT,
    uuid VARCHAR(40) NOT NULL,
    username VARCHAR(40) NOT NULL,
    nome VARCHAR(80) NOT NULL,
	PRIMARY KEY (id)
);

LOAD DATA LOCAL INFILE 'tmp/users.csv' INTO TABLE user
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
(@uuid, @nome, @username)
set
  uuid = @uuid,
  username = @username,
  nome = @nome;
  