
SET TimeZone TO 'America/Sao_Paulo'
;

INSERT INTO usuario (id, email, nome, is_administrador, senha) 
VALUES 
(1,'admin@gmail.com','admin',true, ENCODE(sha512('123'), 'hex')),
(2,'caio@gmail.com','caio',false,  ENCODE(sha512('123'), 'hex')),
(3,'joao@gmail.com','joao',false,  ENCODE(sha512('123'), 'hex')),
(4,'maria@gmail.com','maria',false,  ENCODE(sha512('123'), 'hex')),
(5,'ana@gmail.com','ana',false,  ENCODE(sha512('123'), 'hex'))
;



INSERT INTO animal (id, porte, sexo, data_nascimento,  descricao, imagem_url, nome, raca) 
VALUES 
(1,'P','M','2025-06-01','Muito novo','url1','tico','pincher'),
(2,'M','F','2024-11-21','Muito arisco','url2','diana','vira-lata'),
(3,'G','M','2025-02-14','Muito esperto','url3','lampiao','labrador'),
(4,'M','F','2024-08-25','Muito chata','url2','lola','indefinida')
;


INSERT INTO servico (id, vagas, data, descricao, tipo, titulo, turno) 
VALUES 
(1,2,'2025-12-18','dar banho','limpeza','Banho','manhã'),
(2,5,'2026-01-05','tosar animais','cuidado','Tosa','tarde'),
(3,5,'2025-12-15','gerenciar finanças','financeiro','caixa','tarde'),
(4,5,'2025-12-25','cortar unhas dos animais','cuidado','Cortar unhas','manhã')
;

INSERT INTO candidatura_servico (usuario_id, servico_id, data_candidatura, status) 
VALUES 
(5, 1,'2025-12-10','pendente'),
(2, 1,'2025-11-11','aprovada'),
(3, 4,'2025-10-07','recusada'),
(4, 3,'2025-12-06','pendente')
;

INSERT INTO doacao_financeira (id, valor, usuario_id, observacao) 
VALUES 
(1, 30.0, 2,' '),
(2, 10.0, 3,'para comprar produtos de higiene'),
(3, 15.0, 2,'gastei a toa'),
(4, 5.0, 4,'para comprar ração')
;

INSERT INTO solicitacao_adocao (id, usuario_id, animal_id, data_solicitacao, status) 
VALUES 
(1, 5, 1,'2025-12-10','pendente'),
(2, 2, 1,'2025-11-11','aprovada'),
(3, 3, 4,'2025-10-07','recusada'),
(4, 4, 3,'2025-12-06','pendente')
;

INSERT INTO usario_fav_animal (animal_id, usuario_id) 
VALUES 
(4, 2),
(2, 2),
(3, 4),
(4, 3)
;