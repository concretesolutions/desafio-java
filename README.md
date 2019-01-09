# Desafio Java Concrete Solutions

Crie uma aplicação que exponha uma API RESTful de criação de usuários e login.

Todos os endpoints devem aceitar e responder somente JSON, inclusive ao responder mensagens de erro.

Todas as mensagens de erro devem ter o formato:

```json
    {"mensagem": "mensagem de erro"}
```

## Cadastro

* Esse endpoint deverá receber um usuário com os campos "nome", "email", "senha", mais uma lista de objetos "telefone", seguindo o formato abaixo:

```json
    {
        "name": "João da Silva",
        "email": "joao@silva.org",
        "password": "hunter2",
        "phones": [
            {
                "number": "987654321",
                "ddd": "21"
            }
        ]
    }
```

* Responder o código de status HTTP apropriado
* Em caso de sucesso, retorne o usuário, mais os campos:
    * `id`: id do usuário (pode ser o próprio gerado pelo banco, porém seria interessante se fosse um UUID)
    * `created`: data da criação do usuário
    * `modified`: data da última atualização do usuário
    * `last_login`: data do último login (no caso da criação, será a mesma que a criação)
    * `token`: token de acesso da API (pode ser um UUID ou um JWT)

* Caso o e-mail já exista, deverá retornar erro com a mensagem "E-mail já existente".
* O token deverá ser persistido junto com o usuário

## Login

* Este endpoint irá receber um objeto com e-mail e senha.
* Caso o e-mail e a senha correspondam a um usuário existente, retornar igual ao endpoint de Criação.
* Caso o e-mail não exista, retornar erro com status apropriado mais a mensagem "Usuário e/ou senha inválidos"
* Caso o e-mail exista mas a senha não bata, retornar o status apropriado 401 mais a mensagem "Usuário e/ou senha inválidos"

## Perfil do Usuário
* Caso o token não exista, retornar erro com status apropriado com a mensagem "Não autorizado".
* Caso o token exista, buscar o usuário pelo `id` passado no path e comparar se o token no modelo é igual ao token passado no header.
* Caso não seja o mesmo token, retornar erro com status apropriado e mensagem "Não autorizado"
* Caso seja o mesmo token, verificar se o último login foi a MENOS que 30 minutos atrás. Caso não seja a MENOS que 30 minutos atrás, retornar erro com status apropriado com mensagem "Sessão inválida".
* Caso tudo esteja ok, retornar o usuário no mesmo formato do retorno do Login.

## Requisitos
* Banco de dados em memória, como HSQLDB.
* Processo de build via Gradle.
* Persistência com Hibernate.
* Framework Spring.
* Prazo de 4 dias corridos.
* Entregar um repo público (github ou bitbucket) com o código fonte.
* Entregar a API rodando em algum host (Heroku, AWS, etc).
* Servidor Tomcat ou Jetty Embedded 
* Java 7+


## Requisitos desejáveis
* JWT como token
* Testes unitários
* Criptogafia não reversível (hash) na senha e no token
