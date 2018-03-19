FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/zipkin-server-boot-1.0.0.jar zipkin-server-boot.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /zipkin-server-boot.jar