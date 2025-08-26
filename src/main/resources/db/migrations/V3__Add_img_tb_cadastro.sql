-- V3: Migration para adicionar a coluna de IMG na tabela de cadastros

ALTER TABLE tb_cadastro
ADD COLUMN imgUrl VARCHAR(255);