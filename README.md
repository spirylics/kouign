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

```bash
docker run -it --net=host -v ${PWD}:/data ghcr.io/anweber/httpyac:latest --all kouign.http
```