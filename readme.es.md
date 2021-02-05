# Desafío Java Concrete Solutions

Cree una aplicación que exponga una API RESTful para crear usuarios e iniciar sesión.

El desafío consiste en un viaje del usuario, donde:
1. El usuario debe registrarse
2. Una vez registrado, el usuario debería poder iniciar sesión
3. Al iniciar sesión, el usuario debe tener un token y puede usar ese token para acceder a su perfil.

## Requisitos

* Utilice JSON como tipo de medio en todos los endpoints, inclusive al para los mensajes de error
* Base de datos en memoria, puede ser HSQLDB, H2, etc.
* Persistencia con Hibernate.
* Marco de Spring Boot.
* Plazo de 4 días naturales.
* Entregar un repositorio público (github o bitbucket) con el código fuente.
* Java 7+.

Todos los mensajes de error deben tener el siguiente formato:
>
> ```json
> {"mensaje": "mensaje de error"}
> ```

## Requisitos deseables

* JWT cómo token.
* Pruebas de unidad.
* Utilice el proceso de build via gradle.
* Criptografía no reversible (hash) en la contraseña y el token.
* Entregue la API que se ejecuta en un host (Heroku, AWS, etc.) en el puerto 80 o 443.

## Registrarse
Este endpoint debe usarse para registrar un nuevo usuario en la base de datos, siguiendo las especificaciones a continuación:

* Este endpoint debe recibir en el body un usuario con los campos "nombre", "correo", "contraseña", más una lista de objetos "teléfono", siguiendo el siguiente formato:

Ejemplo:

>```json
>   {
>        "nombre": "Juan Rodriguez",
>        "correo": "juan@rodriguez.org",
>        "contrasena": "hunter2",
>        "telefono": [
>            {
>                "numero": "987654321",
>                "citycode": "1",
>                "countrycode": "57"
>            }
>        ]
>    }
>```

* Responda al código de status HTTP adecuado.
  
* En caso de éxito, devuelva el usuario, más los campos:
    * `id`: id de usuario (puede ser el generado por el banco, pero sería  más deseable un UUID)
    * `creado`: fecha de creación del usuario
    * `modificado`: fecha de la última actualización del usuario
    * `ultimo_acceso`: fecha del último ingreso (en caso de nuevo usuario, va a coincidir con la fecha de creación)
    * `token`: token de acceso para el endpint del perfil (puede ser un UUID o un JWT)

Ejemplo:

>```json
>   {
>        "id": " 00c6de58-6582-11eb-ae93-0242ac130002",
>        "nombre": "Juan Rodriguez",
>        "correo": "juan@rodriguez.org",
>        "contrasena": "hunter2",
>        "telefono": [
>            {
>                "numero": "987654321",
>                "citycode": "1",
>                "countrycode": "57"
>            }
>        ],
>         "creado": "2020-10-03T19:30:00",
>         "modificado": "2020-10-03T19:30:00",
>         "ultimo_acceso": "2020-10-03T19:30:00",
>         "token": "eyJhbGciOiJIUzUxMiJ9.eyJwcm9ncmFtQ29kZSI6ImRhMjhiNjk4MDM0M2I3ZjE3ODUwMDgyNzlmNzI0MGJiNWNmZDAyNjYiLCJ1c2VySWQiOiI1ZjkyZGI3Y2M3MDgxYjliOTZmNGNlNDkiLCJwZXJzb25JZCI6IjVmOTJkYjdjYzcwODFiOWI5NmY0Y2U0OSIsInVzZXJUeXBlIjoiQUNDT1VOVCIsInNlc3Npb25JZCI6Ijc1NWM0MTcyLWYyYjgtNDRiYS1hMzgzLTBlZGI2NzdlYTZiYyIsInJvbGVzIjoiIiwic3ViIjoiNjk0MjA2NjMwMzUiLCJhdWQiOiJ1bmtub3duIiwiaWF0IjoxNjA3NTM0MzU1LCJleHAiOjE2MDc1MzQ1MzV9.3GNRIE4ND_NSbe7cDYoVRUMMXj-_sZmwE_oX-u6Ju7xnUYipEjKz1A2m7mUfPa08BY3USe5zau220u0Zij3LEA"
>     }
> ```

* Si el correo electrónico conste en la base de datos, debe devolver un error con el mensaje "El correo electrónico ya existe".
  
* El token debe ser persistido con el usuario.

## Login

Este endpoint debe usarse para que el usuario, utilizando un correo electrónico y una contraseña registrados, realice un inicio de sesión, al iniciar sesión el token debe actualizarse.

* Utilice el siguiente ejemplo para el body:

> ```json
>     {
>         "correo": "juan@rodriguez.org",
>         "contrasena": "hunter2"
>     }
> ```

* Si el correo electrónico y la contraseña corresponden a un usuario existente, regrese con el http status adecuado y de acuerdo con el siguiente ejemplo:

>```json
>   {
>        "id": " 00c6de58-6582-11eb-ae93-0242ac130002",
>        "nombre": "Juan Rodriguez",
>        "correo": "juan@rodriguez.org",
>        "contrasena": "hunter2",
>        "telefono": [
>            {
>                "numero": "987654321",
>                "citycode": "1",
>                "countrycode": "57"
>            }
>        ],
>         "creado": "2020-10-03T19:30:00",
>         "modificado": "2020-10-03T19:30:00",
>         "ultimo_acceso": "2020-10-03T19:30:00",
>         "token": "eyJhbGciOiJIUzUxMiJ9.eyJwcm9ncmFtQ29kZSI6ImRhMjhiNjk4MDM0M2I3ZjE3ODUwMDgyNzlmNzI0MGJiNWNmZDAyNjYiLCJ1c2VySWQiOiI1ZjkyZGI3Y2M3MDgxYjliOTZmNGNlNDkiLCJwZXJzb25JZCI6IjVmOTJkYjdjYzcwODFiOWI5NmY0Y2U0OSIsInVzZXJUeXBlIjoiQUNDT1VOVCIsInNlc3Npb25JZCI6Ijc1NWM0MTcyLWYyYjgtNDRiYS1hMzgzLTBlZGI2NzdlYTZiYyIsInJvbGVzIjoiIiwic3ViIjoiNjk0MjA2NjMwMzUiLCJhdWQiOiJ1bmtub3duIiwiaWF0IjoxNjA3NTM0MzU1LCJleHAiOjE2MDc1MzQ1MzV9.3GNRIE4ND_NSbe7cDYoVRUMMXj-_sZmwE_oX-u6Ju7xnUYipEjKz1A2m7mUfPa08BY3USe5zau220u0Zij3LEA"
>     }
> ```

* Si el correo electrónico no existe, devuelva el http status adecuado y use el formato de mensaje de error con el mensaje "Usuario y / o contraseña no válidos".

* Si el correo electrónico existe pero la contraseña no coincide, devuelva el http status 401 y use el formato de mensaje de error con el mensaje "Usuario y / o contraseña no válidos".

## Perfil del usuario

Este endpoint debe recibir un token (jwt o uuid) en el encabezado y una identificación de usuario en la ruta, considerando los siguientes escenarios:

* Si el token no se pasa en el header, debería devolver un error con el http status adecuado y con el mensaje "No autorizado".

* Si el token es diferente al persistente, devuelve un error con el http status adecuado y con el mensaje "No autorizado".
  
* Si el token existe, y persiste el mismo, busque al usuario por el `id` pasado en la url.

* Si el ID no encuentra al usuario, regrese con el http status adecuado y el mensaje de error.

* Compruebe si el último inicio de sesión fue hace MENOS de 30 minutos.
     * Si no hace menos de 30 minutos, devuelva el error con el estado apropiado y con el mensaje "Sesión no válida".
  
* Si todo está bien, regrese de acuerdo con el siguiente ejemplo:


>```json
>   {
>        "id": " 00c6de58-6582-11eb-ae93-0242ac130002",
>        "nombre": "Juan Rodriguez",
>        "correo": "juan@rodriguez.org",
>        "contrasena": "hunter2",
>        "telefono": [
>            {
>                "numero": "987654321",
>                "citycode": "1",
>                "countrycode": "57"
>            }
>        ],
>         "creado": "2020-10-03T19:30:00",
>         "modificado": "2020-10-03T19:30:00",
>         "ultimo_acceso": "2020-10-03T19:30:00",
>         "token": "eyJhbGciOiJIUzUxMiJ9.eyJwcm9ncmFtQ29kZSI6ImRhMjhiNjk4MDM0M2I3ZjE3ODUwMDgyNzlmNzI0MGJiNWNmZDAyNjYiLCJ1c2VySWQiOiI1ZjkyZGI3Y2M3MDgxYjliOTZmNGNlNDkiLCJwZXJzb25JZCI6IjVmOTJkYjdjYzcwODFiOWI5NmY0Y2U0OSIsInVzZXJUeXBlIjoiQUNDT1VOVCIsInNlc3Npb25JZCI6Ijc1NWM0MTcyLWYyYjgtNDRiYS1hMzgzLTBlZGI2NzdlYTZiYyIsInJvbGVzIjoiIiwic3ViIjoiNjk0MjA2NjMwMzUiLCJhdWQiOiJ1bmtub3duIiwiaWF0IjoxNjA3NTM0MzU1LCJleHAiOjE2MDc1MzQ1MzV9.3GNRIE4ND_NSbe7cDYoVRUMMXj-_sZmwE_oX-u6Ju7xnUYipEjKz1A2m7mUfPa08BY3USe5zau220u0Zij3LEA"
>     }
> ```
