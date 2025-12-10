# Kouign

```bash
mvn clean package
```

```bash
mvn spring-boot:run
```

```bash
mvn native:compile -Pnative
```

```bash
mvn spring-boot:build-image -Pnative
```

```bash
docker run -p 9090:9090 -t docker.io/library/kouign:0.0.1-SNAPSHOT
```

```bash
docker run -it --net=host -v ${PWD}:/data ghcr.io/anweber/httpyac:latest --all kouign.http
```