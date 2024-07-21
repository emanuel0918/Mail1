
FROM openjdk:8-jre-alpine
#FROM openjdk:8u212-jre-alpine3.9
#Version  Java ARM v7
#FROM alpine:latest@sha256:ec299a7ba3c670e38642b0b62a0c779d84b249a3c889757e2b6f841433b4c6fe



# Bash Dependency
RUN apk update
RUN apk add --no-cache openjdk8-jre


RUN mkdir -p /opt/app

RUN mkdir -p /opt/app/images

# 2.1 - Wrapper
COPY "target/DemoApplication*.jar" /opt/app/DemoApplication.jar
COPY "target/lib/" /opt/app/
# 3 - Workdir
WORKDIR /opt/app

# Expose
EXPOSE 9091

# Volumen
VOLUME ["/opt/app/"]

CMD [ "java", "-jar", "DemoApplication.jar" ]
