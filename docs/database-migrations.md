# Database migrations

This app uses Flyway for database schema changes.

Runtime behavior:

- Flyway applies SQL migrations from `src/main/resources/db/migration`.
- Hibernate validates the entity mapping with `spring.jpa.hibernate.ddl-auto=validate`.
- Schema changes should be added as new Flyway migration files instead of relying on Hibernate `update`.

Initial migration:

```text
src/main/resources/db/migration/V1__create_aluno_table.sql
```

For staging/production, run the application or a controlled migration task against the target database with the same `DATABASE_URL`, `DATABASE_USERNAME`, and `DATABASE_PASSWORD` used by the app.
