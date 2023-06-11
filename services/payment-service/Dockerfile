# using multistage docker build
# ref: https://docs.docker.com/develop/develop-images/multistage-build/
# temp container to build using gradle
FROM openjdk:17-slim as TEMP_BUILD_IMAGE

ENV APP_HOME=/user/app

# Set the working directory to /user/app
WORKDIR ${APP_HOME}

# Copy from host to image filesystem
COPY . .

# Give permissions to Gradle
RUN chmod +x ./gradlew

# build project
RUN ./gradlew clean build -x test
RUN ./gradlew assemble


# Set base image to OpenJDK 17 of actual container
FROM openjdk:17-slim

LABEL MAINTAINER = "@edenilsonpineda"
LABEL VERSION = "1.0"
LABEL SERVICE="payment"

ENV ARTIFACT_NAME=payment-service-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/user/app
ENV APP_PORT=9085

# Set the working directory to /user/app
WORKDIR ${APP_HOME}

COPY --from=TEMP_BUILD_IMAGE ${APP_HOME}/build/libs/${ARTIFACT_NAME} .

#EXPOSE ${APP_PORT}:${APP_PORT}

ENTRYPOINT exec java -jar ${ARTIFACT_NAME}