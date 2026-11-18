CREATE TABLE bairros (
 id BIGSERIAL PRIMARY KEY, nome VARCHAR(120) NOT NULL, cidade VARCHAR(120) NOT NULL,
 CONSTRAINT uk_bairro_nome_cidade UNIQUE(nome,cidade)
);
CREATE TABLE categorias (
 id BIGSERIAL PRIMARY KEY, nome VARCHAR(80) NOT NULL UNIQUE, descricao VARCHAR(255)
);
CREATE TABLE ocorrencias (
 id BIGSERIAL PRIMARY KEY, titulo VARCHAR(160) NOT NULL, descricao TEXT,
 endereco VARCHAR(255) NOT NULL, latitude DOUBLE PRECISION, longitude DOUBLE PRECISION,
 status VARCHAR(30) NOT NULL, data_criacao TIMESTAMP NOT NULL,
 bairro_id BIGINT NOT NULL REFERENCES bairros(id),
 categoria_id BIGINT NOT NULL REFERENCES categorias(id),
 morador_id VARCHAR(100) NOT NULL
);
CREATE TABLE confirmacoes (
 id BIGSERIAL PRIMARY KEY, ocorrencia_id BIGINT NOT NULL REFERENCES ocorrencias(id) ON DELETE CASCADE,
 morador_id VARCHAR(100) NOT NULL, data_confirmacao TIMESTAMP NOT NULL,
 CONSTRAINT uk_confirmacao_morador_ocorrencia UNIQUE(ocorrencia_id,morador_id)
);
CREATE TABLE atualizacoes (
 id BIGSERIAL PRIMARY KEY, ocorrencia_id BIGINT NOT NULL REFERENCES ocorrencias(id) ON DELETE CASCADE,
 status VARCHAR(30) NOT NULL, comentario VARCHAR(500), data_atualizacao TIMESTAMP NOT NULL
);
CREATE INDEX idx_ocorrencia_bairro ON ocorrencias(bairro_id);
CREATE INDEX idx_ocorrencia_status ON ocorrencias(status);
CREATE INDEX idx_atualizacao_ocorrencia ON atualizacoes(ocorrencia_id);
