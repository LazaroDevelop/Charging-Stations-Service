FROM codenvy/alpine_jdk8
ARG JAR_FILE
COPY target/${JAR_FILE}.jar charging-stations-service-1.0.0.jar
VOLUME /tmp
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "charging-stations-service-1.0.0.jar"]