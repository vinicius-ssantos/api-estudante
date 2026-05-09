create table if not exists aluno (
    id bigserial primary key,
    nome varchar(100) not null,
    idade integer not null,
    nome_professor varchar(100),
    numero_sala integer,
    nota_primeiro_semestre real,
    nota_segundo_semestre real
);
