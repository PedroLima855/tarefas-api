create table usuario(

    id int PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL


);

CREATE TABLE permissao (

	id int PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL

);

CREATE TABLE usuario_permissao (

	usuario_id int NOT NULL,
	permissao_id int NOT NULL,
	PRIMARY KEY (usuario_id, permissao_id),
	FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	FOREIGN KEY (permissao_id) REFERENCES permissao(id)
);

INSERT INTO usuario (id, nome, email, senha) values (1, 'Administrador', 'esig@master.com', '$2a$10$M1dY9RVuBmqIO.05qW5WKe8IfFt81IbWWQHSmck9kQKtJgVfalaeq');
INSERT INTO usuario (id, nome, email, senha) values (2, 'Usuario', 'user@user.com', '$2a$10$h8A7NXbDa1lxD2BfePBi7er4xC2.jIsM7iNC4FD5PRe8gDaIlIjBi');

INSERT INTO permissao (id, descricao) values (1, 'MASTER');
INSERT INTO permissao (id, descricao) values (2, 'USER');

-- admin
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 1);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 2);

-- user
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (2, 2);
