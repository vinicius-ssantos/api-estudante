FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw && ./mvnw -DskipTests package

FROM python:3.11-slim
WORKDIR /workflow
RUN apt-get update \
    && apt-get install -y --no-install-recommends openjdk-17-jre-headless \
    && rm -rf /var/lib/apt/lists/*
COPY render-workflow/requirements.txt ./requirements.txt
RUN pip install --no-cache-dir -r requirements.txt
COPY render-workflow/main.py ./main.py
COPY --from=build /app/target/*.jar ./app.jar
CMD ["python", "main.py"]
