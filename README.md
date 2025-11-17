# Kouign

```bash
mvn clean package
```

```bash
mvn spring-boot:run
```

```bash
sdk use java 25-graal
mvn native:compile -Pnative
```

```bash
sdk use java 25-graal
mvn spring-boot:build-image -Pnative
```

```bash
docker run -p 9090:9090 -t docker.io/library/kouign:0.0.1-SNAPSHOT
```