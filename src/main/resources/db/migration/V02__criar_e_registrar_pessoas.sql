CREATE TABLE "pessoa"  ( 
	"codigo"     	serial NOT NULL,
	"nome"       	varchar(50) NOT NULL,
	"ativo"      	bool NOT NULL,
	"logradouro" 	varchar(60) NULL,
	"numero"     	varchar(10) NULL,
	"complemento"	varchar(50) NULL,
	"bairro"     	varchar(50) NULL,
	"cep"        	varchar(10) NULL,
	"cidade"     	varchar(50) NULL,
	"estado"     	varchar(2) NULL,
	PRIMARY KEY("codigo")
);

INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
	   VALUES ('Chuck Norris', true, 'Brooklin street win', '07', null, 'Brooklin','05.555-777','Nova York','ex');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
	   VALUES ('Dercy Gonçalves', false, 'Rua da zueira s/n conjunto z lote', '01', 'Zueiropolis', 'Rio de janeiro','05.555-777','Rio de Janeiro','rj');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
	   VALUES ('Marcos Sousa', true, 'SQS 211 Bloco B apt', '301', null, 'Asa Sul','05.555-777','Brasilia','df');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
	   VALUES ('Zeze Polessa', true, 'Avenida das palmeiras conunto w casa', '01', 'Bonfinopolis', 'Bonfinopolis','05.555-777','Rio de Janeiro','rj');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
	   VALUES ('Ayrton Senna', true, 'Avenida dos campeões Washington Luiz casa', '12', null, 'Interlagos','72.000-710','São Paulo','sp');
