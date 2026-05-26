FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw && ./mvnw -DskipTests package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /workflow
RUN apt-get update \
    && apt-get install -y --no-install-recommends python3 python3-pip python-is-python3 \
    && rm -rf /var/lib/apt/lists/*
COPY render-workflow/requirements.txt ./requirements.txt
RUN pip install --no-cache-dir -r requirements.txt
COPY render-workflow/main.py ./main.py
COPY --from=build /app/target/*.jar ./app.jar
CMD ["python", "main.py"]
