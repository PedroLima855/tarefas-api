<h1 align="center">
<br>
  <img src="https://i.morioh.com/200628/59d6bda2.jpg" alt="tarefas-api" width="120">
<br>
<br>
tarefas-api
</h1>

<p align="center">API de cadastros de tarefas</p>

<p align="center">
  <a href="https://opensource.org/licenses/MIT">
    <img src="https://img.shields.io/badge/License-MIT-blue.svg" alt="License MIT">
  </a>
</p>

## Características
[//]: #
Essa api apresenta algumas ferramentas e práticas mais recentes em desenvolvimento backend!

-  **Spring Boot** — Um framework open source para desenvolvimento web
-  **FlyWay** — uma ferramenta open source de migração de banco de dados
-  **PostgreSQL** — um sistema gerenciador de banco de dados objeto relacional

## Funcionalidades

Foi criado os endpoints de salvar, atualizar, remover e consultar tarefas, o recurso
é acessado atravez de login e senha e autenticação por token JWT, para isso foi
implementado o OAuth2, um protocolo de autorização e autenticação.

A consulta pode ser passado cada paramentro individualmente ou todos para ser obtido
um resultado da base.

## Getting started

Primeiro é preciso clonar o projeto na pasta de sua preferencia, logo depois crie uma
base de teste no PostgreSQL, depois de criado acesse o arquivo application.properties dentro da pasta
src/main/resources do projeto
e troque as configurações de acesso do banco de dados e do FlyWay.

Na pasta src/main/resources/db/migration contem o login e senha que é adicionado assim que configurado a base de dados
e compilado o projeto.

Para testar os endpoints, faça uma chamada na uri /oauth/token passando usuario e senha no corpo(form-data) se estiver no postman, copie o token gerado
e adicione na aba Authorization que é sucesso.