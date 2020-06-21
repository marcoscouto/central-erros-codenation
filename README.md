# Central de Erros - Codenation
> Aluno: Marcos Vinicius Giacon Martinelli Couto

___

**Trabalho de conclusão do curso AceleraDev Java Online.**

**Objetivo:** Implementar uma API Rest para centralizar registros de erros de aplicações.

___

### Como testar?

> Para testar os diferentes ambientes disponíveis é necessário alterar o profile no arquivo application.properties.

#### Ambiente de Testes
O ambiente de testes não necessita de pré-configurações, pois roda em banco de dados temporário (H2).

#### Ambiente de Desenvolvimento

O código acompanha um arquivo docker-compose.yml com uma instância do banco de dados Postgres. 
Antes de iniciar o programa, se você tem o docker instalado, dê o comando: 

```docker-compose up -d```

#### Ambiente de Produção:

[Deploy no Heroku](https://central-erros-codenation.herokuapp.com/)

___

### Endpoints

> Os endpoints também podem ser encontrados na documentação do Swagger através do endpoint /swagger-ui.html

#### Login

**/login** (POST) - Retorna um Token para acesso ao sistema

#### Users

**/users** (GET) - Busca todos os usuários

**/users** (POST) - Salva um novo usuário

**/users/{id}** (GET) - Buscar um usuário por ID

**/users/{id}** (PUT) - Atualiza um usuário por ID

**/users/{id}** (DELETE) - Deleta um usuário por ID

#### Errors

**/errors** (GET) - Busca todos de erros

**/errors** (POST) - Salva um novo erro

**/errors/{id}** (GET) - Busca um erro por ID

**/errors/{id}** (PUT) - Atualiza um erro por ID

**/errors/{id}** (DELETE) - Deleta um erro por ID

**/errors/log/{id}** (GET) - Busca somente a informação de log de erro por ID

**/errors/page/{page}** (GET) - Busca todos de erros paginados, com a informação da página





