-- EXTENSOES
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
CREATE EXTENSION IF NOT EXISTS "pg_trgm";

-- TABELAS
CREATE TABLE usuario (
                         id         UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
                         nome       VARCHAR(100) NOT NULL,
                         email      VARCHAR(150) NOT NULL UNIQUE,
                         telefone   VARCHAR(20),
                         cpf_hash   VARCHAR(64)  NOT NULL UNIQUE,
                         senha_hash VARCHAR(255) NOT NULL,
                         foto_url   TEXT,
                         ativo      BOOLEAN      NOT NULL DEFAULT TRUE,
                         criado_em  TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE local_evento (
                              id        UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
                              nome      VARCHAR(150) NOT NULL,
                              endereco  VARCHAR(255) NOT NULL,
                              bairro    VARCHAR(100),
                              cidade    VARCHAR(100) NOT NULL DEFAULT 'Porto Alegre',
                              criado_em TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE post (
                      id        UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
                      autor_id  UUID         NOT NULL REFERENCES usuario(id)      ON DELETE CASCADE,
                      local_id  UUID                  REFERENCES local_evento(id) ON DELETE SET NULL,
                      titulo    VARCHAR(200) NOT NULL,
                      descricao TEXT         NOT NULL,
                      foto_url  TEXT,
                      score     INTEGER      NOT NULL DEFAULT 0,
                      criado_em TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE comentario (
                            id        UUID      PRIMARY KEY DEFAULT gen_random_uuid(),
                            post_id   UUID      NOT NULL REFERENCES post(id)    ON DELETE CASCADE,
                            autor_id  UUID      NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
                            conteudo  TEXT      NOT NULL,
                            criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE voto (
                      usuario_id UUID      NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
                      post_id    UUID      NOT NULL REFERENCES post(id)    ON DELETE CASCADE,
                      criado_em  TIMESTAMP NOT NULL DEFAULT NOW(),
                      PRIMARY KEY (usuario_id, post_id)
);

CREATE TABLE repost (
                        id               UUID      PRIMARY KEY DEFAULT gen_random_uuid(),
                        usuario_id       UUID      NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
                        post_original_id UUID      NOT NULL REFERENCES post(id)    ON DELETE CASCADE,
                        criado_em        TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE seguidor (
                          seguidor_id UUID      NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
                          seguido_id  UUID      NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
                          criado_em   TIMESTAMP NOT NULL DEFAULT NOW(),
                          PRIMARY KEY (seguidor_id, seguido_id),
                          CONSTRAINT nao_auto_follow CHECK (seguidor_id != seguido_id)
    );

CREATE TABLE chamado_suporte (
                                 id         UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
                                 usuario_id UUID         NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
                                 titulo     VARCHAR(200) NOT NULL,
                                 descricao  TEXT         NOT NULL,
                                 protocolo  VARCHAR(50)  NOT NULL UNIQUE,
                                 status     VARCHAR(30)  NOT NULL DEFAULT 'ABERTO',
                                 anexos     TEXT[],
                                 criado_em  TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE chamado_suporte_anexos (
                                        chamado_id UUID NOT NULL REFERENCES chamado_suporte(id) ON DELETE CASCADE,
                                        url        TEXT NOT NULL
);

-- INDICES
CREATE INDEX idx_post_criado_em      ON post         (criado_em DESC);
CREATE INDEX idx_post_autor_data     ON post         (autor_id, criado_em DESC);
CREATE INDEX idx_post_local          ON post         (local_id);
CREATE INDEX idx_post_titulo_trgm    ON post         USING GIN (titulo gin_trgm_ops);
CREATE INDEX idx_post_descricao_trgm ON post         USING GIN (descricao gin_trgm_ops);
CREATE INDEX idx_usuario_nome_trgm   ON usuario      USING GIN (nome gin_trgm_ops);
CREATE INDEX idx_local_nome_trgm     ON local_evento USING GIN (nome gin_trgm_ops);
CREATE INDEX idx_local_bairro_trgm   ON local_evento USING GIN (bairro gin_trgm_ops);

-- DADOS DE EXEMPLO
INSERT INTO usuario (id, nome, email, telefone, cpf_hash, senha_hash)
VALUES
    (
        'a1b2c3d4-0001-0001-0001-000000000001',
        'Gabriel Lacerda',
        'gabriel@poanoite.com',
        '(51) 99999-0001',
        encode(sha256('12345678901'::bytea), 'hex'),
        encode(sha256('Senha123'::bytea), 'hex')
    ),
    (
        'a1b2c3d4-0002-0002-0002-000000000002',
        'Polyana Santos',
        'polyana@poanoite.com',
        '(51) 99999-0002',
        encode(sha256('98765432100'::bytea), 'hex'),
        encode(sha256('Senha123'::bytea), 'hex')
    );

INSERT INTO local_evento (id, nome, endereco, bairro)
VALUES
    (
        'b1b2c3d4-0001-0001-0001-000000000001',
        'Opiniao',
        'Rua Jose Bonifacio, 417',
        'Cidade Baixa'
    ),
    (
        'b1b2c3d4-0002-0002-0002-000000000002',
        'Dado Bier',
        'Rua Olavo Barreto Viana, 325',
        'Moinhos de Vento'
    ),
    (
        'b1b2c3d4-0003-0003-0003-000000000003',
        'Ocidente',
        'Av. Osvaldo Aranha, 960',
        'Bom Fim'
    );

INSERT INTO post (autor_id, local_id, titulo, descricao, score)
VALUES
    (
        'a1b2c3d4-0001-0001-0001-000000000001',
        'b1b2c3d4-0001-0001-0001-000000000001',
        'Show de Rock no Opiniao',
        'Noite com bandas locais. Abertura as 22h, ingressos na entrada.',
        5
    ),
    (
        'a1b2c3d4-0001-0001-0001-000000000001',
        'b1b2c3d4-0002-0002-0002-000000000002',
        'Happy Hour Dado Bier',
        'Chopp artesanal 30% off das 18h as 21h. Petiscos inclusos.',
        3
    ),
    (
        'a1b2c3d4-0002-0002-0002-000000000002',
        'b1b2c3d4-0003-0003-0003-000000000003',
        'Festa Universitaria no Ocidente',
        'A melhor festa da Cidade Baixa. Open bar das 23h a meia-noite.',
        7
    );