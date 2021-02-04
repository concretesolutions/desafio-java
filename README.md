# Desafio Java Concrete Solutions

Crie uma aplicação que exponha uma API RESTful de criação de usuários e login.

O desafio consiste em uma jornada de usuário, onde:
1. O usuário deverá se cadastrar
2. Uma vez cadastrado, o usuário deverá conseguir fazer login
3. Ao fazer login, o usuário deverá ter um token e poderá utilizar esse token para acessar o seu perfil

## Requisitos

* Utilizar JSON como media type em todos os endpoints, inclusive os de erro.
* Banco de dados em memória, pode ser HSQLDB, H2 etc...
* Persistência com Hibernate.
* Framework Spring Boot.
* Prazo de 4 dias corridos.
* Entregar um repo público (github ou bitbucket) com o código fonte.
* Java 7+.

Todas as mensagens de erro devem ter o formato:
>
>```json
>    {"mensagem": "mensagem de erro"}
>```

## Requisitos desejáveis

* JWT como token.
* Testes unitários.
* Utilizar processo de build via gradle.
* Criptogafia não reversível (hash) na senha e no token.
* Entregar a API rodando em algum host (Heroku, AWS, etc) na porta 80 ou 443.

## Cadastro
Este endpoint deverá ser utilizado para cadastrar um novo usuário na base, seguindo as especificações abaixo:

* Esse endpoint deverá receber no body um usuário com os campos "nome", "email", "senha", mais uma lista de objetos "telefone", seguindo o formato abaixo:

Exemplo:

>```json
>   {
>        "name": "João da Silva",
>        "email": "joao@silva.org",
>        "password": "hunter2",
>        "phones": [
>            {
>                "number": "987654321",
>                "ddd": "21"
>            }
>        ]
>    }
>```

* Responder o código de status HTTP apropriado.
  
* Em caso de sucesso, retorne no body de resposta, o usuário, mais os campos:
    * `id`: id do usuário (pode ser o próprio gerado pelo banco, porém seria interessante se fosse um UUID)
    * `created`: data da criação do usuário
    * `modified`: data da última atualização do usuário
    * `last_login`: data do último login (no caso da criação, será a mesma data que a  data de criação)
    * `token`: token de acesso para o endpoint de perfil (pode ser um UUID ou um JWT)

Exemplo:

>```json
>    {   
>        "id": " 00c6de58-6582-11eb-ae93-0242ac130002",
>         "name": "João da Silva",
>         "email": "joao@silva.org",
>         "password": "hunter2",
>         "phones": [
>             {
>                 "number": "987654321",
>                 "ddd": "21"
>             }
>         ],
>         "created": "2020-10-03T19:30:00",
>         "modified": "2020-10-03T19:30:00",
>         "last_login": "2020-10-03T19:30:00",
>         "token": "eyJhbGciOiJIUzUxMiJ9.eyJwcm9ncmFtQ29kZSI6ImRhMjhiNjk4MDM0M2I3ZjE3ODUwMDgyNzlmNzI0MGJiNWNmZDAyNjYiLCJ1c2VySWQiOiI1ZjkyZGI3Y2M3MDgxYjliOTZmNGNlNDkiLCJwZXJzb25JZCI6IjVmOTJkYjdjYzcwODFiOWI5NmY0Y2U0OSIsInVzZXJUeXBlIjoiQUNDT1VOVCIsInNlc3Npb25JZCI6Ijc1NWM0MTcyLWYyYjgtNDRiYS1hMzgzLTBlZGI2NzdlYTZiYyIsInJvbGVzIjoiIiwic3ViIjoiNjk0MjA2NjMwMzUiLCJhdWQiOiJ1bmtub3duIiwiaWF0IjoxNjA3NTM0MzU1LCJleHAiOjE2MDc1MzQ1MzV9.3GNRIE4ND_NSbe7cDYoVRUMMXj-_sZmwE_oX-u6Ju7xnUYipEjKz1A2m7mUfPa08BY3USe5zau220u0Zij3LEA"
>     }
> ```

* Caso o e-mail já exista, deverá retornar erro com a mensagem "E-mail já existente".
  
* O token deverá ser persistido junto com o usuário.

## Login 

Este endpoint deverá ser utilizado para que o usuário, utilizando um e-mail e senha cadastrados, realize um login, ao fazer login o token deverá ser atualizado.

* Utilizar o exemplo abaixo para o body:

> ```json
>     {
>         "email": "joao@silva.org",
>         "password": "hunter2"
>     }
> ```

* Caso o e-mail e a senha correspondam a um usuário existente, retornar com o status apropriado e conforme o exemplo abaixo:
  
>```json
>    {   
>        "id": " 00c6de58-6582-11eb-ae93-0242ac130002",
>         "name": "João da Silva",
>         "email": "joao@silva.org",
>         "password": "hunter2",
>         "phones": [
>             {
>                 "number": "987654321",
>                 "ddd": "21"
>             }
>         ],
>         "created": "2020-10-03T19:30:00",
>         "modified": "2020-10-03T19:30:00",
>         "last_login": "2020-10-03T19:30:00",
>         "token": "eyJhbGciOiJIUzUxMiJ9.eyJwcm9ncmFtQ29kZSI6ImRhMjhiNjk4MDM0M2I3ZjE3ODUwMDgyNzlmNzI0MGJiNWNmZDAyNjYiLCJ1c2VySWQiOiI1ZjkyZGI3Y2M3MDgxYjliOTZmNGNlNDkiLCJwZXJzb25JZCI6IjVmOTJkYjdjYzcwODFiOWI5NmY0Y2U0OSIsInVzZXJUeXBlIjoiQUNDT1VOVCIsInNlc3Npb25JZCI6Ijc1NWM0MTcyLWYyYjgtNDRiYS1hMzgzLTBlZGI2NzdlYTZiYyIsInJvbGVzIjoiIiwic3ViIjoiNjk0MjA2NjMwMzUiLCJhdWQiOiJ1bmtub3duIiwiaWF0IjoxNjA3NTM0MzU1LCJleHAiOjE2MDc1MzQ1MzV9.3GNRIE4ND_NSbe7cDYoVRUMMXj-_sZmwE_oX-u6Ju7xnUYipEjKz1A2m7mUfPa08BY3USe5zau220u0Zij3LEA"
>     }
> ```

* Caso o e-mail não exista, retornar status apropriado e utilizar o formato de mensagem de erro com a mensagem "Usuário e/ou senha inválidos".

* Caso o e-mail exista mas a senha não bata, retornar o status 401 e utilizar o formato de mensagem de erro com a mensagem "Usuário e/ou senha inválidos".

## Perfil do Usuário

Este endpoint deverá receber no header um token (jwt ou uuid), e um id de usuário no path, considerar os cenários a seguir:

* Caso o token não seja passado no header, deverá retornar erro com status apropriado e com a mensagem "Não autorizado".

* Caso o token seja diferente do persistido, retornar erro com status apropriado e com a mensagem "Não autorizado".
  
* Caso o token exista, e seja o mesmo persistido, buscar o usuário pelo `id` passado no path.

* Caso o usuário não seja encontrado pelo id, retornar com status e mensagem de erro apropriados.

* Verificar se o último login foi há MENOS de 30 minutos atrás. 
     * Caso não seja há MENOS de 30 minutos atrás, retornar erro com status apropriado e com a mensagem "Sessão inválida".
  
* Caso tudo esteja ok, retornar conforme o exemplo abaixo:
  
>```json
>    {   
>        "id": " 00c6de58-6582-11eb-ae93-0242ac130002",
>        "name": "João da Silva",
>        "email": "joao@silva.org",
>        "password": "hunter2",
>        "phones": [
>            {
>                "number": "987654321",
>                "ddd": "21"
>            }
>        ],
>        "created": "2020-10-03T19:30:00",
>        "modified": "2020-10-03T19:30:00",
>        "last_login": "2020-10-03T19:30:00",
>        "token": "eyJhbGciOiJIUzUxMiJ9.eyJwcm9ncmFtQ29kZSI6ImRhMjhiNjk4MDM0M2I3ZjE3ODUwMDgyNzlmNzI0MGJiNWNmZDAyNjYiLCJ1c2VySWQiOiI1ZjkyZGI3Y2M3MDgxYjliOTZmNGNlNDkiLCJwZXJzb25JZCI6IjVmOTJkYjdjYzcwODFiOWI5NmY0Y2U0OSIsInVzZXJUeXBlIjoiQUNDT1VOVCIsInNlc3Npb25JZCI6Ijc1NWM0MTcyLWYyYjgtNDRiYS1hMzgzLTBlZGI2NzdlYTZiYyIsInJvbGVzIjoiIiwic3ViIjoiNjk0MjA2NjMwMzUiLCJhdWQiOiJ1bmtub3duIiwiaWF0IjoxNjA3NTM0MzU1LCJleHAiOjE2MDc1MzQ1MzV9.3GNRIE4ND_NSbe7cDYoVRUMMXj-_sZmwE_oX-u6Ju7xnUYipEjKz1A2m7mUfPa08BY3USe5zau220u0Zij3LEA"
>    }
>```
