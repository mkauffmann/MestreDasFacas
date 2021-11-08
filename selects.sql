desc cliente ;
desc genero ;
desc telefone ;
desc cliente_telefone ;
desc bandeira;


-- CARTAO
SELECT * FROM bandeira ;
SELECT * FROM cartao_credito cc ;
select * from cliente_cartao_credito;

-- ENDERECO
SELECT * FROM CIDADE;
SELECT * FROM UF;
SELECT * FROM endereco;
SELECT * FROM cliente_endereco ce ;

-- TELEFONE
SELECT * FROM telefone t ;
SELECT * FROM cliente_telefone ct ;

SELECT * FROM GENERO;
SELECT * FROM cliente c ;