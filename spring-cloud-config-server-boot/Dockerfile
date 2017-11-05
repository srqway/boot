FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN apk update
RUN apk upgrade
RUN apk add curl
RUN cd /tmp
RUN curl -k -LO "http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip" -H 'Cookie: oraclelicense=accept-securebackup-cookie'
RUN unzip jce_policy-8.zip
RUN rm -f jce_policy-8.zip
RUN cp UnlimitedJCEPolicyJDK8/*.jar $JAVA_HOME/jre/lib/security/
ADD target/spring-cloud-config-server-boot-1.0.0.jar spring-cloud-config-server-boot.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /spring-cloud-config-server-boot.jar