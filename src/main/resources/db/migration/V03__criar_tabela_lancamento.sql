CREATE TABLE "lancamento"  ( 
	"codigo"          	serial NOT NULL,
	"descricao"       	varchar(50) NOT NULL,
	"data_vencimento" 	date NOT NULL,
	"data_pagamento"  	date NOT NULL,
	"valor"           	decimal(15,5) NOT NULL,
	"observacao"      	varchar(100) NULL,
	"tipo"            	varchar(20) NOT NULL,
	"codigo_categoria"	int4 NOT NULL,
	"codigo_pessoa"   	int4 NOT NULL,
	PRIMARY KEY("codigo")
);

ALTER TABLE "lancamento"
	ADD CONSTRAINT "categoria_lancamento"
	FOREIGN KEY("codigo_categoria")
	REFERENCES "categoria"("codigo")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION;

ALTER TABLE "lancamento"
	ADD CONSTRAINT "pessoa_lancamento"
	FOREIGN KEY("codigo_pessoa")
	REFERENCES "pessoa"("codigo")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION ;


INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor, observacao, tipo,codigo_categoria,codigo_pessoa) 
	   VALUES ('Salário Mensal', '2017-02-10', '2017-02-10', 100.32,  null, 'DESPESA',1,1);
INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor, observacao, tipo,codigo_categoria,codigo_pessoa) 
	   VALUES ('Salão beleza', '2017-03-10', '2017-03-12', 50.00,  null, 'RECEITA',2,2);
INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor, observacao, tipo,codigo_categoria,codigo_pessoa) 
	   VALUES ('Manutenção carro', '2017-05-01', '2017-05-01', 349.99,  null, 'DESPESA',1,2);
INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor, observacao, tipo,codigo_categoria,codigo_pessoa) 
	   VALUES ('Estorno compra', '2017-05-10', '2017-05-11', 599.90,  null, 'RECEITA',3,3);