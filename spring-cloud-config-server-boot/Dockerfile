FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/spring-cloud-config-server-boot-1.0.0.jar spring-cloud-config-server-boot.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /spring-cloud-config-server-boot.jar