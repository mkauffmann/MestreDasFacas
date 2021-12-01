-- tabela gênero
INSERT INTO GENERO (DESCRICAO_GENERO) VALUES ('FEMININO');
INSERT INTO GENERO (DESCRICAO_GENERO) VALUES ('MASCULINO');
INSERT INTO GENERO (DESCRICAO_GENERO) VALUES ('NÃO-BINÁRIO');
INSERT INTO GENERO (DESCRICAO_GENERO) VALUES ('OUTROS');

-- tabela uf
INSERT INTO UF VALUES ('AC', 'ACRE');
INSERT INTO UF VALUES ('AL', 'ALAGOAS');
INSERT INTO UF VALUES ('AM', 'AMAZONAS');
INSERT INTO UF VALUES ('AP', 'AMAPÁ');
INSERT INTO UF VALUES ('BA', 'BAHIA');
INSERT INTO UF VALUES ('CE', 'CEARÁ');
INSERT INTO UF VALUES ('DF', 'DISTRITO FEDERAL');
INSERT INTO UF VALUES ('ES', 'ESPÍRITO SANTO');
INSERT INTO UF VALUES ('GO', 'GOIÁS');
INSERT INTO UF VALUES ('MA', 'MARANHÃO');
INSERT INTO UF VALUES ('MG', 'MINAS GERAIS');
INSERT INTO UF VALUES ('MS', 'MATO GROSSO DO SUL');
INSERT INTO UF VALUES ('MT', 'MATO GROSSO');
INSERT INTO UF VALUES ('PA', 'PARÁ');
INSERT INTO UF VALUES ('PB', 'PARAÍBA');
INSERT INTO UF VALUES ('PE', 'PERNAMBUCO');
INSERT INTO UF VALUES ('PI', 'PIAUÍ');
INSERT INTO UF VALUES ('PR', 'PARANÁ');
INSERT INTO UF VALUES ('RJ', 'RIO DE JANEIRO');
INSERT INTO UF VALUES ('RN', 'RIO GRANDE DO NORTE');
INSERT INTO UF VALUES ('RO', 'RONDÔNIA');
INSERT INTO UF VALUES ('RR', 'RORAIMA');
INSERT INTO UF VALUES ('RS', 'RIO GRANDE DO SUL');
INSERT INTO UF VALUES ('SC', 'SANTA CATARINA');
INSERT INTO UF VALUES ('SE', 'SERGIPE');
INSERT INTO UF VALUES ('SP', 'SÃO PAULO');
INSERT INTO UF VALUES ('TO', 'TOCANTINS');

-- tabela bandeira cartao
INSERT INTO BANDEIRA (DESCRICAO_BANDEIRA) VALUES ('VISA');
INSERT INTO BANDEIRA (DESCRICAO_BANDEIRA) VALUES ('MASTERCARD');
INSERT INTO BANDEIRA (DESCRICAO_BANDEIRA) VALUES ('ELO');
INSERT INTO BANDEIRA (DESCRICAO_BANDEIRA) VALUES ('HIPERCARD');
INSERT INTO BANDEIRA (DESCRICAO_BANDEIRA) VALUES ('AMERICAN EXPRESS');
INSERT INTO BANDEIRA (DESCRICAO_BANDEIRA) VALUES ('DINERS CLUB');

-- tabela categorias

insert into categoria (descricao_categoria) values ('Churrasco');
insert into categoria (descricao_categoria) values ('Cozinha');
insert into categoria (descricao_categoria) values ('Facas Especiais');
insert into categoria (descricao_categoria) values ('Réplicas');

-- tabela marca:

insert into marca (descricao_marca) values ('ToyShow');
insert into marca (descricao_marca) values ('Tramontina');
insert into marca (descricao_marca) values ('Zakharov');
insert into marca (descricao_marca) values ('Brinox');
insert into marca (descricao_marca) values ('J.A. Henckels');

-- tabela preco_produto:

insert into preco_produto (valor_preco) values (79.99);
insert into preco_produto (valor_preco) values (89.99);
insert into preco_produto (valor_preco) values (109.99);
insert into preco_produto (valor_preco) values (127.99);
insert into preco_produto (valor_preco) values (169.99);
insert into preco_produto (valor_preco) values (200.99);
insert into preco_produto (valor_preco) values (419.99);
insert into preco_produto (valor_preco) values (559.99);
insert into preco_produto (valor_preco) values (729.99);
insert into preco_produto (valor_preco) values (1099.99);
insert into preco_produto (valor_preco) values (1399.99);

-- tabela produtos

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Cutelo especializada em cortes pesados e profundos, em qualquer tipo de alimento', 2, 
'https://ibb.co/4JN0rZS', 30, 'Faca Cutelo Tramontina Cabo Amarelo 30cm', 270, 8, 2, 2, 3, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca de mesa com dentes polidos, os quais trazem mais conforto nos cortes', 2, 
'https://ibb.co/nmgF5fG', 20, 'Faca de Mesa Tramontina Aço Inox Continental', 35, 1.5, 2, 2, 1, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca de chef especializada e eficiente para qualquer alimento ', 5, 
'https://ibb.co/6tzDy72', 30, 'Faca Chef Tramontina com Cabo Branco Master', 127, 2, 2, 2, 3, false, false);

-- 4

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca de churrasco com cabo longo, para manuseio seguro e confortável ', 2, 
'https://ibb.co/N7tKjhW', 20, 'Faca Churrasco Brinox com Cabo longo', 30, 2, 4, 1, 2, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca para sobremesa com corte preciso e perfeito', 1.3, 
'https://ibb.co/WGz1PQW', 18, 'Faca para Sobremesa Brinox ', 36, 1.5, 4, 2, 1, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca corta asas com qualidade e durabilidade para qualquer ingrediente', 2.5, 
'https://ibb.co/bWqdq0y', 28, 'Faca Corte de Asa Tramontina Cabo Amarelo ', 100, 4.5, 2, 2, 4, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca de chef especializada, resistente e eficiente para qualquer alimento', 2, 
'https://ibb.co/MgqM5BD', 32, 'Faca Chef Tramontina com Cabo Madeira Marrom', 238, 4, 2, 2, 6, false, false);

-- 8

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca para churrasco eficiente, resistente e segura', 2, 
'https://ibb.co/Mf25h62', 22, 'Faca para Churrasco Tramontina Century', 200, 4, 2, 1, 5, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca especializada com precição e alta performance para culinária oriental', 2.3, 
'https://ibb.co/TY84CLk', 30, 'Faca Nakiri Tramontina Cabo Preto', 190, 5, 2, 1, 7, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Réplica das lâminas do caos, usado por Kratos no game God Of War', 3, 
'https://ibb.co/qxtpF5f', 62, 'Lâminas Kratos God of War', 500, 12, 1, 4, 8, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca de chef especializada, resistente e eficiente para qualquer tipo de alimento', 2, 
'https://ibb.co/kGwJTr0', 32, 'Faca Chef Tramontina com Cabo Vermelho', 238, 4, 2, 2, 6, false, false);

-- 12

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca dessosa com corte perfeito para separação da carne dos ossos', 2.5, 
'https://ibb.co/Y079Lvc', 28, 'Faca Dessosa Tramontina Cabo Azul ', 100, 4.5, 2, 2, 4, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Cutelo especializada em cortes pesados e profundos, em qualquer tipo de alimento', 2, 
'https://ibb.co/VYXG4q5', 28, 'Faca Cutelo Zakharov Cabo Madeira', 250, 8, 3, 2, 3, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca de pão resistentes, confortáveis e seguras', 2.5, 
'https://ibb.co/zNtVWVj', 33, 'Faca de Pão Brinox Cabo Branco', 197, 38, 4, 2, 3, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca dessosa com corte perfeito para separação da carne dos ossos', 3.6, 
'https://ibb.co/J7kMM2Y', 27.2, 'Faca Dessosa Tramontina Cabo Madeira', 89, 1.8, 2, 2, 2, false, false);

-- 16

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca corta asas com qualidade e durabilidade para qualquer ingrediente', 2.2, 
'https://ibb.co/zn7pDfN', 26, 'Faca Corte de Asa Tramontina Cabo Azul ', 93, 4.3, 2, 2, 1, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca dessosa com corte perfeito para separação da carne dos ossos', 2.5, 
'https://ibb.co/HGKQQqf', 28, 'Faca Dessosa Tramontina Cabo Vermelho ', 100, 4.5, 2, 2, 4, false, false);

-- 18

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Réplica da Kunai do anime Naruto', 23, 
'https://ibb.co/WB3KtMT', 22, 'Kunai Naruto', 12 , 12, 1, 4, 1, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca de chef especializada, resistente e eficiente para qualquer tipo de alimento', 2, 
'https://ibb.co/TrBX61H', 32, 'Faca Chef J.A. Henckels Cabo Branco', 180, 4.5, 5, 2, 5, false, false);

-- 20 - FEITA:

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca preparada para todas as situações, desde cozinha até caça / pesca', 2.2, 
'https://ibb.co/JqnFr3K', 24, 'Faca Esportiva Hunter Zakharov', 185, 3.5, 3, 3, 9, false, false);

-- 21 - FEITA:

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca especial preparada para churrasco e cozinha', 2.8, 
'https://ibb.co/Pxc4GHR', 38, 'Faca Churrasco e Cozinha Zakharov Premium', 260, 3.8, 3, 3, 7, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Réplica da espada Frostmourne do game World Of Warcraft', 25, 
'https://ibb.co/4RZpyxY', 107, 'Frostmourne World Of Warcraft', 300 , 25, 1, 4, 11, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca perfeita e prática para o corte suave do tomate', 2.4, 
'https://ibb.co/6H1gg3h', 50, 'Faca para Tomate Brinox', 35, 23, 4, 3, 1, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Réplica da espada Master Sword do game Zelda', 89, 
'https://ibb.co/VBBrMPf', 89, 'Espada Master Sword Zelda', 100 , 25, 1, 4, 10, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca para frutas e legumes, corte perfeito e com bastante qualidade.', 1.4, 
'https://ibb.co/4jHFp9g', 20.7, 'Faca de Legume Tramontina Cabo Branco ', 40, 2.3, 2, 2, 1, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca prática e resistente, perfeita para ervas e temperos.', 1.5, 
'https://ibb.co/v4X44MR', 25, 'Mezzaluna Tramontina Polywood com Lâmina em Aço', 320, 15, 2, 2, 3, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca perfeita para um almoço em família, ao trazer qualidade e destaque a mesa.', 0.6, 
'https://ibb.co/0y8TgjM', 23, 'Faca Churrasco Tramontina Aço Inox', 234, 9, 2, 1, 1, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Réplica da espada Sandai Kitetsu do personagem Zoro do anime One Piece', 10, 
'https://ibb.co/hyS0VFG', 110, 'Espada Zoro One Piece', 1200, 10, 1, 4, 7, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Faca preparada para todas as situações, desde cozinha até caça / pesca', 12.5, 
'https://ibb.co/sC5gcfg', 31, 'Faca De Caça/Churrasco Zakharov', 150, 18, 3, 3, 2, false, false);

insert into produto (descricao_produto, altura, imagem, comprimento, nome_produto, 
peso, largura, id_marca, id_categoria, id_preco_produto, destaque, novidade)
values ('Espada Katana decorativa, perfeita para fantasias e/ou cosplays', 8, 
'https://ibb.co/VtWTX73', 105, 'Espada Katana Decorativa', 736, 8, 1, 4, 7, false, false);



-- tabela estoque

insert into estoque (id_produto, quantidade_estoque) values (1, 30);
insert into estoque (id_produto, quantidade_estoque) values (2, 30);
insert into estoque (id_produto, quantidade_estoque) values (3, 30);
insert into estoque (id_produto, quantidade_estoque) values (4, 30);
insert into estoque (id_produto, quantidade_estoque) values (5, 30);
insert into estoque (id_produto, quantidade_estoque) values (6, 30);
insert into estoque (id_produto, quantidade_estoque) values (7, 30);
insert into estoque (id_produto, quantidade_estoque) values (8, 30);
insert into estoque (id_produto, quantidade_estoque) values (9, 30);
insert into estoque (id_produto, quantidade_estoque) values (10, 30);
insert into estoque (id_produto, quantidade_estoque) values (11, 30);
insert into estoque (id_produto, quantidade_estoque) values (12, 30);
insert into estoque (id_produto, quantidade_estoque) values (13, 30);
insert into estoque (id_produto, quantidade_estoque) values (14, 30);
insert into estoque (id_produto, quantidade_estoque) values (15, 30);
insert into estoque (id_produto, quantidade_estoque) values (16, 30);
insert into estoque (id_produto, quantidade_estoque) values (17, 30);
insert into estoque (id_produto, quantidade_estoque) values (18, 30);
insert into estoque (id_produto, quantidade_estoque) values (19, 30);
insert into estoque (id_produto, quantidade_estoque) values (20, 30);
insert into estoque (id_produto, quantidade_estoque) values (21, 30);
insert into estoque (id_produto, quantidade_estoque) values (22, 30);
insert into estoque (id_produto, quantidade_estoque) values (23, 30);
insert into estoque (id_produto, quantidade_estoque) values (24, 30);
insert into estoque (id_produto, quantidade_estoque) values (25, 30);
insert into estoque (id_produto, quantidade_estoque) values (26, 30);
insert into estoque (id_produto, quantidade_estoque) values (27, 30);
insert into estoque (id_produto, quantidade_estoque) values (28, 30);
insert into estoque (id_produto, quantidade_estoque) values (29, 30);
insert into estoque (id_produto, quantidade_estoque) values (30, 30);


-- tabela tipo_pagamento

insert into tipo_pagamento (descricao_tipo_pagamento) values ('Cartão de Crédito');
insert into tipo_pagamento (descricao_tipo_pagamento) values ('Boleto');
insert into tipo_pagamento (descricao_tipo_pagamento) values ('PIX');

-- tabela status_pedido 

insert into status_pedido (descricao_status_pedido) values ('PEDIDO EFETUADO');
insert into status_pedido (descricao_status_pedido) values ('AGUARDANDO APROVAÇÃO PAGAMENTO');
insert into status_pedido (descricao_status_pedido) values ('PAGAMENTO APROVADO');
insert into status_pedido (descricao_status_pedido) values ('CONCLUIDO');
insert into status_pedido (descricao_status_pedido) values ('CANCELADO');

