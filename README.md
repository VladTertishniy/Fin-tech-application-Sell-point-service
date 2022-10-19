<h1 align="center"> Persons service </h1> <br>

<p align="center">
  Service have the the ability to create the sell points.
The sell point contain information about the address, name, mark, whether it is an online or offline store.
</p>


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [API](#requirements)
- [Acknowledgements](#acknowledgements)




## Introduction

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e91606af4a364076a7058c5ea1c006a8)](https://www.codacy.com/app/joneubank/microservice-template-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=overture-stack/microservice-template-java&amp;utm_campaign=Badge_Grade)
[![CircleCI](https://circleci.com/gh/overture-stack/microservice-template-java/tree/master.svg?style=shield)](https://circleci.com/gh/overture-stack/microservice-template-java/tree/master)

## Features
This service based on Spring Boot 2.7.4 and java 17. 
Use Feign Client for integration with other services, AWS RDS as DB, Hibernate as ORM, Lombok annotations, Mapstruct for entity mapping,
WebSocket and eureka


## Requirements
The application can be run locally or in a docker container, the requirements for each setup are listed below.


### Local
* [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/download.cgi)
* [AWS RDS](https://us-east-1.console.aws.amazon.com/rds/home?region=us-east-1#)


### Docker
* [Docker](https://www.docker.com/get-docker)
* [AWS RDS](https://us-east-1.console.aws.amazon.com/rds/home?region=us-east-1#)


## Quick Start

Before service starting need create AWS RDS Postgres instance
In __application.properties__ need fill below properties
`spring.datasource.url`
`spring.datasource.username`
`spring.datasource.password`

### Run Local
```bash
$ mvn spring-boot:run
```

Application will run by default on port `9082`
Configure the port by changing `server.port` in __application.properties__


### Run Docker

First build the image:
```bash
$ docker-compose build
```

When ready, run it:
```bash
$ docker-compose up
```

## API
### Sell point
For Sell point creation
**Post** {host}:{port}/sellPoints/create
<details><summary>Body example</summary>
{
    "address":"address",
    "name":"name",
    "mark":"4.5",
    "offlineShop":"false"
}
</details>

For Sell point updating
**Post** {host}:{port}/sellPoints/update/{id}
<details><summary>Body example</summary>
{
    "address":"address",
    "name":"name",
    "mark":"4.5",
    "offlineShop":"false"
}
</details>

For Sell point deleting
**Get** {host}:{port}/sellPoints/delete/{id}

For Sell point get by id
**Get** {host}:{port}/sellPoints/getById/{id}

For Sell point get all
**Get** {host}:{port}/sellPoints/getAll

For Sell point get all on page
**Get** {host}:{port}/sellPoints/getAllOnPage

For Sell point is sell point exist
**Get** {host}:{port}/sellPoints/isSellPointExist/{id}

## Acknowledgements
Acknowledgements for Yevhenii Khudolii for interesting trainig project and support =)
