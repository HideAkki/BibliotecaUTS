FROM openjdk:17
COPY "./target/BibliotecaUTS-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8031
ENTRYPOINT [ "java", "-jar", "app.jar" ]