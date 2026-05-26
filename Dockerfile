FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw && ./mvnw -DskipTests package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
RUN apt-get update \
    && apt-get install -y --no-install-recommends python3 python3-pip python-is-python3 \
    && rm -rf /var/lib/apt/lists/*
COPY render-workflow/requirements.txt ./render-workflow/requirements.txt
RUN pip install --no-cache-dir -r ./render-workflow/requirements.txt
COPY --from=build /app/target/*.jar app.jar
COPY render-workflow/main.py ./render-workflow/main.py
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
