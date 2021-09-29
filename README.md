# Product-ms - A Product Microservice

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://github.com/ricardofrosty45/product-ms)

Product é um microservice escrito em Java, usando tecnologias como Spring boot com integração com banco de dados não relacional MongoDb

## Features

- Banco de dados MongoDb implementado
- Todos os endpoints feitos e testados
- API Documentation
- Aplicado CRUD
- Aplicado Clean Code
- Unit tests foram implementados

## Tecnologias Usadas
- [Java] - Linguagem usada no projeto
- [SpringBoot] - Framework
- [MongoDb] - Banco de dados não relacional orientada a documentos
- [Lombok]
- [OpenApi] - Biblioteca focada em documentar a sua API

## How to run it
Foi feito uma pipeline em uma conta pessoal minha onde você poderá acessar a api.
URL abaixo:

```sh
https://product-project-compasso.herokuapp.com/v1/healthcheck
```

Documentação da api...

```sh
https://product-project-compasso.herokuapp.com/swagger-ui/index.html?configUrl=/docs.json/swagger-config
```

Como foi informado, caso for rodar em um script automatizado, a porta esta configurada para 9999

```sh
http://localhost:9999/v1/healthcheck
```

## Links

| Links |  |
| ------ | ------ |
| Repository | [MASTER][Repo] |
| API Documentation | [Documentation][Doc] |

## Anotações do DEV

A unica anotação que deixarei aqui é sobre o endpoint **v1/products/search**
Já que estou usando o mongo, poderia fazer um @Query para poder ja filtrar em uma consulta só o max e min e com consulta com o name e description
usando algumas funções do mongodb chamada **Projections e Aggregations**, mas não obtive sucesso.
Então o que sobrou foi fazer um filtro na mão mesmo com ranges informados.
O projeto localmente esta configurado para a porta 9999 como foi dito



   [Java]: <https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html>
   [SpringBoot]: <https://spring.io/projects/spring-boot>
   [MongoDb]: <https://spring.io/guides/gs/accessing-data-mongodb/>
   [Lombok]: <https://projectlombok.org/>
   [OpenApi]: <https://swagger.io/specification/>

   [Repo]: <https://github.com/ricardofrosty45/product-ms>
   [Doc]: <https://product-project-compasso.herokuapp.com/swagger-ui/index.html?configUrl=/docs.json/swagger-config>
