<h1 align="center">Project compilation & local docker mount</h1>

```bash
 mvn clean install jib:dockerBuild -Plocal
```

<h1 align="center">Docker deployment</h1>

```bash
docker compose up
```

<br>

<hr>

<h1 align="center">URLs</h1>

|  Service   |                                  LOCAL                                  |                                                                                                                                                                   Credentials                                                                                                                                                                    |
|:----------:|:-----------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;        |                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                           | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; |
|  OpenApi   | [http://localhost:27017/](http://localhost:27017/swagger-ui/index.html) |                                                                                                                                                                        -                                                                                                                                                                         |
| Prometheus |            [http://localhost:9090/](http://localhost:9090/)             |                                                                                                                                                                        -                                                                                                                                                                         |
|  Grafana   |            [http://localhost:3000/](http://localhost:3000/)             |                                                                                                                                                           login: admin / pass: secret                                                                                                                                                            |
|   Zipkin   |            [http://localhost:9411/](http://localhost:9411/)             |                                                                                                                                                                        -                                                                                                                                                                         |
|  pgAdmin   |            [http://localhost:5050/](http://localhost:5050/)             |                                                                                                                                                                pass: dontgotosql                                                                                                                                                                 |
<br>
<hr>

<h1 align="center">API Objectives</h1>
<br>
<p align="center">
  <img src="https://i.postimg.cc/7h5rf5Xy/task.png">
</p>

