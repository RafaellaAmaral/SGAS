-- Criar schema
CREATE SCHEMA IF NOT EXISTS sgas_db;
SET search_path TO sgas_db;

-- -----------------------------------------------------
-- Table usuario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
  id SERIAL PRIMARY KEY,
  email VARCHAR(80) NOT NULL UNIQUE,
  nome VARCHAR(80) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  tipo VARCHAR(2) NOT NULL
);

-- -----------------------------------------------------
-- Table animal
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS animal (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(80) NOT NULL,
  raca VARCHAR(80),
  data_nasc DATE,
  porte VARCHAR(1),
  descricao TEXT,
  imagem BYTEA,
  sexo VARCHAR(1)
);

-- -----------------------------------------------------
-- Table servico
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS servico (
  id SERIAL PRIMARY KEY,
  titulo VARCHAR(80),
  descricao VARCHAR(300),
  data DATE,
  horario VARCHAR(80),
  tipo VARCHAR(80),
  vagas INT
);

-- -----------------------------------------------------
-- Table tutor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tutor (
  usuario_id INT PRIMARY KEY,
  lista_favoritos JSON,
  CONSTRAINT fk_tutor_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

-- -----------------------------------------------------
-- Table voluntario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS voluntario (
  usuario_id INT PRIMARY KEY,
  CONSTRAINT fk_voluntario_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

-- -----------------------------------------------------
-- Table solicitacao_adocao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS solicitacao_adocao (
  animal_id INT NOT NULL,
  tutor_usuario_id INT NOT NULL,
  PRIMARY KEY (animal_id, tutor_usuario_id),
  CONSTRAINT fk_solicitacao_adocao_animal
    FOREIGN KEY (animal_id) REFERENCES animal (id),
  CONSTRAINT fk_solicitacao_adocao_tutor
    FOREIGN KEY (tutor_usuario_id) REFERENCES tutor (usuario_id)
);

-- -----------------------------------------------------
-- Table doacao_financeira
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS doacao_financeira (
  id SERIAL PRIMARY KEY,
  valor DOUBLE PRECISION,
  comprovante BYTEA,
  observacao VARCHAR(300),
  usuario_id INT,
  CONSTRAINT fk_doacao_financeira_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

-- -----------------------------------------------------
-- Table candidatura_servico
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS candidatura_servico (
  voluntario_usuario_id INT NOT NULL,
  servico_id INT NOT NULL,
  data_candidatura DATE,
  status VARCHAR(80),
  data_servico DATE,
  presenca BOOLEAN,
  funcionario_responsavel_id INT NOT NULL,
  PRIMARY KEY (voluntario_usuario_id, servico_id),
  CONSTRAINT fk_candidatura_voluntario
    FOREIGN KEY (voluntario_usuario_id) REFERENCES voluntario (usuario_id),
  CONSTRAINT fk_candidatura_servico
    FOREIGN KEY (servico_id) REFERENCES servico (id),
  CONSTRAINT fk_candidatura_usuario
    FOREIGN KEY (funcionario_responsavel_id) REFERENCES usuario (id)
);
