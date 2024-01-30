FROM openjdk:17-oracle

WORKDIR /app

COPY target/sua-TechChallengePaymentsApplication.jar /app/sua-TechChallengePaymentsApplication.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/sua-TechChallengePaymentsApplication.jar"]
