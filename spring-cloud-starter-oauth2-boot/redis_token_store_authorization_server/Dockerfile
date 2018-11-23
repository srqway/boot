FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/redis_token_store_authorization_server-1.0.0.jar redis_token_store_authorization_server.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /redis_token_store_authorization_server.jar