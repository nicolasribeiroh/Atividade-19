-- Executar no pgAdmin (Query Tool) conectado ao servidor local
-- 1) Criar o banco (se ainda nao existir)
CREATE DATABASE projeto3aapi;

-- 2) Conecte-se ao banco projeto3aapi e (opcional) crie a tabela.
--    O Hibernate (ddl-auto=update) ja cria sozinho se ela nao existir.
CREATE TABLE IF NOT EXISTS produto (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DOUBLE PRECISION NOT NULL
);

-- 3) Conferir os dados (use para os prints de evidencia)
SELECT * FROM produto ORDER BY id;
