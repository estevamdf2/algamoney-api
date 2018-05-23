
CREATE TABLE "usuario"  ( 
	"codigo"	serial NOT NULL,
	"nome"  	varchar(50) NOT NULL,
	"email" 	varchar(50) NOT NULL,
	"senha" 	varchar(150) NOT NULL,
	PRIMARY KEY("codigo")
);


CREATE TABLE "permissao"  ( 
	"codigo"   	serial NOT NULL,
	"descricao"	varchar(50) NOT NULL,
	PRIMARY KEY("codigo")
);

CREATE TABLE "usuario_permissao"  ( 
	"codigo_usuario"  	int4 NULL,
	"codigo_permissao"	int4 NULL 
);

ALTER TABLE "usuario_permissao"
	ADD CONSTRAINT "permissao_usuario_permissao"
	FOREIGN KEY("codigo_permissao")
	REFERENCES "permissao"("codigo")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION ;

ALTER TABLE "usuario_permissao"
	ADD CONSTRAINT "usuario_usuario_permissao"
	FOREIGN KEY("codigo_usuario")
	REFERENCES "usuario"("codigo")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION ;

INSERT INTO usuario (codigo, nome, email, senha) values (1, 'Administrador', 'admin@algamoney.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (codigo, nome, email, senha) values (2, 'Marcos Sousa', 'marcos@algamoney.com', '$2a$10$IOMCkZdpaxca4WruuYpHP.jDfOmqV/7ML.0jvGI0f8BHAUB.7stEK');

-- Permissões de categoria
INSERT INTO permissao (codigo, descricao) values (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (codigo, descricao) values (2, 'ROLE_PESQUISAR_CATEGORIA');

-- Permissões de pessoa
INSERT INTO permissao (codigo, descricao) values (3, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (codigo, descricao) values (4, 'ROLE_REMOVER_PESSOA');
INSERT INTO permissao (codigo, descricao) values (5, 'ROLE_PESQUISAR_PESSOA');

-- Permissões de lancamento
INSERT INTO permissao (codigo, descricao) values (6, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao (codigo, descricao) values (7, 'ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissao (codigo, descricao) values (8, 'ROLE_PESQUISAR_LANCAMENTO');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 8);

-- maria
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 8);