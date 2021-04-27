# Desafio PicPay

### Java Jr. Backend - Busca de Usuários - Otimizado -

O desafio foi criar uma API REST para busca de usuarios pelo nome e/ou username a partir de uma palavra chave. 

EXEMPLO DOS DADOS

ID | Nome | Username
| --- | --- | --- |
065d8403-8a8f-484d-b602-9138ff7dedcf | Wadson marcia | wadson.marcia
5761be9e-3e27-4be8-87bc-5455db08408 | Kylton Saura | kylton.saura
ef735189-105d-4784-8e2d-c8abb07e72d3 | Edmundo Cassemiro |edmundo.cassemiro
aaa40f4e-da26-42ee-b707-cb81e00610d5 |Raimundira M|raimundiram
51ba0961-8d5b-47be-bcb4-54633a567a99 | Pricila Kilder|pricilakilderitaliani
  
 A carga de dados foi feito pelo banco durante a build da imagem na url:
 [Arquivo com a Carga CSV de Usuários](https://s3.amazonaws.com/careers-picpay/users.csv.gz "Arquivo CSV")

Faça o download do arquivo users.csv.gz que contém o banco de dados que
deve ser usado na busca. Ele contém os IDs, nomes e usernames dos usuários

## Diferenciais do Desafio Exigidos
  - Ter um desempenho elevado num conjunto de dados muito grande
  - Utilizar o Docker
  - Toda vez que fizer uma pesquisa, disparar em uma fila com a string que foi
digitada na pesquisa, e um listener salvará essa pesquisa no banco.

## Instalação e Execução
```
docker-compose up --build
```
> - O processo para build e up das imagens é um pouco demorado, o backend irá depender de todas os serviços executando.

## Tecnologias Utilizadas
  - Spring Boot 2.1.4
  - Maven
  - Spring Data JPA com MySQL e ElasticSearch
  - RabbitMQ
  - Documentação com Swagger 2
  - Docker e Docker Composer

## Funcionalidades
  - Base de usuários pré-cadastrados, pré-sincronismo com elasticsearch agendado, a busca é feita sobre o ES;
  - Recursos de API de usuários para testes de carga;
  - Mensageria que persiste as querys de buscas de forma assíncrona lançando-as na fila para ser persistida.
  - Swagger via web no domínio local porta 8080 para testes dos recursos;

## TODO:
  - Usar o Logstash para sincronizar o banco relacional mysql com o elasticsearch;
  - Usar Mysql compatível com UUID(id universal) para imports pelo arquivo local;

### Autor
* Flávio Tinoco Coutinho - flaviotinoco@gmail.com