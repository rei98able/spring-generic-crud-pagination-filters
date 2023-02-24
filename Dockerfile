FROM maven:3.8.5-openjdk-18-slim as build-deps
WORKDIR /usr/scr/app
COPY . ./
RUN mvn clean package

FROM openjdk:18-slim-buster as base
ENV TZ=Europe/Moscow
RUN apt-get update && apt-get install -yy tzdata
RUN cp /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

FROM base as app
ARG JAR_FILE=/usr/scr/app/target/*.jar
COPY --from=build-deps ${JAR_FILE} app.jar
CMD ["java","-jar","/app.jar", "-Dfile.encoding=UTF-8"]