FROM java:8-jdk-alpine
COPY ./target/Rasputin.jar /usr/app/
WORKDIR /usr/app
EXPOSE 25565
ENTRYPOINT ["java", "-jar", "Rasputin.jar"]