# Kouign

## Dev

```bash
mvn spring-boot:run
```

## Test

```bash
docker run -it --net=host -v ${PWD}:/data ghcr.io/anweber/httpyac:latest --all kouign.http
```

## Regular

### Build

```bash
mvn package
```

### Start

```bash
java -jar target/kouign-*-regular.jar
```

## Regular + docker

### Build

```bash
mvn spring-boot:build-image
```

### Start

```bash
docker run -p 9090:9090 kouign-regular:0.0.1-SNAPSHOT
```

## CDS / AOT Cache

### Build

```bash
mvn package -Pcds
```

### Training

```bash
java -XX:AOTMode=record -XX:AOTConfiguration=target/app.aotconf -Dspring.context.exit=onRefresh -jar target/kouign-*-cds.jar
java -XX:AOTMode=create -XX:AOTConfiguration=target/app.aotconf -XX:AOTCache=target/app.aot -jar target/kouign-*-cds.jar
```

### Start

```bash
java -XX:AOTCache=target/app.aot -jar target/kouign-*-cds.jar
```

## CDS / AOT Cache + docker

### Build

```bash
mvn spring-boot:build-image -Pcds
```

### Start

```bash
docker run -p 9090:9090 kouign-cds:0.0.1-SNAPSHOT
```

## AOT Native

### Build

```bash
mvn native:compile -Pnative
```

### Build

```bash
./target/kouign
```

## AOT Native + docker

### Build

```bash
mvn spring-boot:build-image -Pnative
```

### Start

```bash
docker run \
  -p 9090:9090 \
  -p 5005:5005 \
  -p 9010:9010 \
  -e JAVA_TOOL_OPTIONS="-Dcom.sun.management.jmxremote \
    -Dcom.sun.management.jmxremote.port=9010 \
    -Dcom.sun.management.jmxremote.rmi.port=9010 \
    -Dcom.sun.management.jmxremote.local.only=false \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Dcom.sun.management.jmxremote.ssl=false \
    -Djava.rmi.server.hostname=localhost \
    -Dcom.sun.management.jmxremote.registry.ssl=false \
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" \
  docker.io/library/kouign:0.0.1-SNAPSHOT
```
