create table tarefa(

    id SERIAL PRIMARY KEY,
    titulo varchar(80) not null,
    descricao varchar(255) not null,
    responsavel varchar(80) not null,
    deadline Date not null,
    prioridade varchar(20) not null,
    situacao varchar(20) not null

);