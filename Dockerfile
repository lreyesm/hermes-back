#FROM artifactory-ekide.eroski.es/eroski-docker/library/eclipse-temurin:17-jdk-alpine
FROM artifactory-ekide.eroski.es/eroski-docker-release-local/base/jdk17-opentelemetry:1.0.0
ARG ARTIFACT_ID
ARG VERSION

COPY target/$ARTIFACT_ID-$VERSION.jar /home/app/app.jar

ENTRYPOINT [ "/bin/sh", "start.sh" ]
