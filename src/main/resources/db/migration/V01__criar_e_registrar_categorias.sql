CREATE TABLE "categoria"  ( 
	"codigo"	serial NOT NULL,
	"nome"  	varchar(50) NULL,
	PRIMARY KEY("codigo")
);

INSERT INTO categoria (nome) VALUES ('Lazer');
INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('Supermercado');
INSERT INTO categoria (nome) VALUES ('Farmácia');
INSERT INTO categoria (nome) VALUES ('Outros');