CREATE TABLE pessoa (
	codigo		BIGINT(20) 	PRIMARY KEY AUTO_INCREMENT,
	nome 		VARCHAR(50) 	NOT NULL,
	ativo 		BOOLEAN 	NOT NULL,
	logradouro  	VARCHAR(60),
	numero		VARCHAR(10),
	complemento	VARCHAR(50),
	bairro		VARCHAR(50),
	cep		VARCHAR(10),
	cidade		VARCHAR(50),
	estado		VARCHAR(2)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
	   VALUES ('Chuck Norris', true, 'Brooklin street win', '07', null, 'Brooklin','05555-777','Nova York','ex');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
	   VALUES ('Dercy Gonçalves', false, 'Rua da zueira s/n conjunto z lote', '01', 'Zueiropolis', 'Rio de janeiro','05555-777','Rio de Janeiro','rj');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
	   VALUES ('Marcos Sousa', true, 'SQS 211 Bloco B apt', '301', null, 'Asa Sul','05555-777','Brasilia','df');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
	   VALUES ('Zeze Polessa', true, 'Avenida das palmeiras conunto w casa', '01', 'Bonfinopolis', 'Bonfinopolis','05555-777','Rio de Janeiro','rj');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
	   VALUES ('Ayrton Senna', true, 'Avenida dos campeões Washington Luiz casa', '12', null, 'Interlagos','05555-777','São Paulo','sp');
