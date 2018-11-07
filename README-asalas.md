Desafio Java  Alphé Salas

Realizado con los framework y api siguientes
 
Spring Boot Web 2.0.1
Spring Security
Spring Test
Spring  Data (JPA)
JWT
HSQLDB
Graddle
Spring Tool Suit 4.1 for Eclipse

USO: Applicacion desplegada en HEROKU.com

Insertar un usuario

curl -H "Content-Type: application/json" -X POST -d '{ "name": "alphe salas", "email": "asalas@test.org", "password": "clave1234", "phones": [ { "countrycode": "56", "citycode": "9", "number": "2113112" }] }'  https://restloginjwt.herokuapp.com/api/registro

Consultar el login
curl -i -H "Content-Type: application/json" -X POST -d '{ "email": "asalas@test.org", "password": "clave124" }' https://restloginjwt.herokuapp.com/api/login


