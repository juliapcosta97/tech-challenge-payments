FROM openjdk:17-oracle

WORKDIR /app

COPY target/TechChallengePaymentsApplication.jar /app/TechChallengePaymentsApplication.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/TechChallengePaymentsApplication.jar"]
