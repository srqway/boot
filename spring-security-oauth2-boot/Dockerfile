FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/spring-security-oauth2-boot-1.0.0.jar spring-security-oauth2-boot.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /spring-security-oauth2-boot.jar