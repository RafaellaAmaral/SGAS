TRUNCATE TABLE sgas_db.solicitacao_adocao CASCADE;
TRUNCATE TABLE sgas_db.candidatura_servico CASCADE;
TRUNCATE TABLE sgas_db.tutor CASCADE;
TRUNCATE TABLE sgas_db.voluntario CASCADE;
TRUNCATE TABLE sgas_db.animal CASCADE;
TRUNCATE TABLE sgas_db.servico CASCADE;
TRUNCATE TABLE sgas_db.usuario CASCADE;


TRUNCATE TABLE sgas_db.doacao_financeira CASCADE;



INSERT INTO sgas_db.animal (id, nome, raca, data_nasc, porte, descricao, imagem, sexo) 
VALUES 
(1, 'Espertinho', 'Labrador', '2024-10-28', 'G', 'Muito Esperto', null, 'M'),
(2, 'Fofinha', 'Pincher', '2025-06-28', 'P', 'Muito fofa', null, 'F'),
(3, 'Barulhentinho', 'Vira Lata', '2025-01-15', 'M', 'Muito Barulhento', null, 'M');

INSERT INTO sgas_db.servico (id, titulo, descricao, data, horario, tipo, vagas) 
VALUES 
(1, 'Banho', 'Dar banho nos animais', null, 'Tarde', 'Cuidado', 10),
(2, 'Tosa', 'Tosar os animais', null, 'Tarde', 'Cuidado', 5),
(3, 'Limpeza dos canis', 'Limpar os canis', null, 'Manhã', 'Limpeza', 3);

INSERT INTO sgas_db.usuario (id, email, nome, senha, role) 
VALUES 
(1, 'caio@gmail.com', 'Caio', '123', 'Administrador'),
(2, 'rayenne@gmail.com', 'Rayenne', '123', 'Tutor'),
(3, 'rafa@gmail.com', 'Rafaella', '123', 'Voluntario');

INSERT INTO sgas_db.voluntario (usuario_id) 
VALUES 
(3);

INSERT INTO sgas_db.tutor (usuario_id, lista_favoritos) 
VALUES 
(2, null);

INSERT INTO sgas_db.solicitacao_adocao (animal_id, tutor_usuario_id) 
VALUES 
(1, 2);

INSERT INTO sgas_db.candidatura_servico (voluntario_usuario_id, servico_id, data_candidatura,
status, data_servico, presenca, funcionario_responsavel_id) 
VALUES 
(3, 2, '2025-10-21', 'Em analise', '2025-10-29', null, 1),
(3, 1, '2025-10-21', 'Aprovada', '2025-10-30', null, 1),
(3, 3, '2025-10-21', 'Negada', '2025-10-31', null, 1);
