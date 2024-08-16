DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'estudante') THEN
      PERFORM dblink_exec('dbname=' || current_database(), 'CREATE DATABASE estudante');
END IF;
END
    $$;

CREATE TABLE IF NOT EXISTS aluno (
                                     id SERIAL PRIMARY KEY,
                                     nome VARCHAR(255),
                                     idade INT,
                                     nome_professor VARCHAR(255),
                                     nota_primeiro_semestre FLOAT,
                                     nota_segundo_semestre FLOAT,
                                     numero_sala INT
);
