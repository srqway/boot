FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/spring-cloud-starter-zuul-boot-1.0.0.jar spring-cloud-starter-zuul-boot.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /spring-cloud-starter-zuul-boot.jar