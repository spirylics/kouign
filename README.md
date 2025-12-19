# Kouign

## Dev

```bash
mvn spring-boot:run
```

## Test

```bash
mvn verify
```

## Perf

```bash
mvn gatling:test -Dkouign.during=PT1M -Dkouign.users=10000
```

## Deploy
### Regular

#### Build

```bash
mvn package
```

#### Start

```bash
java -jar target/kouign-*-regular.jar
```

### Regular + docker

#### Build

```bash
mvn spring-boot:build-image
```

#### Start

```bash
docker run -p 9090:9090 kouign-regular:latest
```

### AOT cache

#### Build

```bash
mvn package -Paot-cache
```

#### Training

```bash
java -XX:AOTMode=record -XX:AOTConfiguration=target/app.aotconf -Dspring.context.exit=onRefresh -jar target/kouign-*-aot-cache.jar
java -XX:AOTMode=create -XX:AOTConfiguration=target/app.aotconf -XX:AOTCache=target/app.aot -jar target/kouign-*-aot-cache.jar
```


#### Start

```bash
java -XX:AOTCache=target/app.aot -jar target/kouign-*-aot-cache.jar
```

### AOT cache + docker

#### Build

```bash
mvn docker:build -Paot-cache
```

#### Start

```bash
docker run -p 9090:9090 kouign-aot-cache:latest
```

### AOT Native

#### Build

```bash
mvn native:compile -Pnative
```

#### Start

```bash
./target/kouign
```

### AOT Native + docker

#### Build

```bash
mvn spring-boot:build-image -Pnative
```

#### Start

```bash
docker run -p 9090:9090 kouign-aot-native:latest
```
