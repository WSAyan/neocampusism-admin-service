# [WIP] NeoCampusism Admin Service 

This is the core rest api service with admin db.

## Prerequisite
- intellij idea (communiy/version)
- MySql database server

## Steps
- Start `MySql` Server (make sure its running on localhost:3306)
- Open the project in `intellij idea`
- Add mysql server `USER`, `PASSWORD` in `ExposeUtils.kt` file
- Run project using `intellij idea`
- It will automatically create a schema named `nc_admin` on mysql server
- Swagger `http://127.0.0.1:8080/swagger`
