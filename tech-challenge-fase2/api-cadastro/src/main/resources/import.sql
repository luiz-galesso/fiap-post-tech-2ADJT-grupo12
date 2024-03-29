--INSERCAO DE CONDUTORES--
INSERT INTO TB_CONDUTOR (EMAIL, NOME, CPF, NR_CELULAR, DESCRICAO, NUMERO, CEP, CIDADE, ESTADO, COMPLEMENTO, ATIVACAO_AUTOMATICA) VALUES ('john.wick@continental.com','John Wick','67082485069', '12999991111','Rua Alta Cupula', '87','09450220','São Paulo', 'SP', 'Cobertura', false);
INSERT INTO TB_CONDUTOR (EMAIL, NOME, CPF, NR_CELULAR, DESCRICAO, NUMERO, CEP, CIDADE, ESTADO, COMPLEMENTO, ATIVACAO_AUTOMATICA) VALUES ('max.rockatansky@wasteland.com','Max Rockatansky','98353850095', '11888882222','Rua Australia', '322','08450220','São Paulo', 'SP', null, false);
INSERT INTO TB_CONDUTOR (EMAIL, NOME, CPF, NR_CELULAR, DESCRICAO, NUMERO, CEP, CIDADE, ESTADO, COMPLEMENTO, ATIVACAO_AUTOMATICA) VALUES ('john.rambo@exercito.com','John Rambo','47881175095', '12777771111','Rua Vietnan', '87','09760320','São Paulo', 'SP', 'Subsolo', false);
INSERT INTO TB_CONDUTOR (EMAIL, NOME, CPF, NR_CELULAR, DESCRICAO, NUMERO, CEP, CIDADE, ESTADO, COMPLEMENTO, ATIVACAO_AUTOMATICA) VALUES ('indiana.jones@museum.com','Henry Walton Jones','48515000091', '19555551111','Rua Egito', '87','09380380','São Paulo', 'SP', 'Ap 1603', false);
--
--INSERCAO DE VEICULOS--
INSERT INTO TB_VEICULO (ID, PLACA, NOME, CONDUTOR_EMAIL) VALUES (NEXTVAL ('veiculo_sequence'), 'FWL3756','Ford Mustang Boss 502','john.wick@continental.com');
INSERT INTO TB_VEICULO (ID, PLACA, NOME, CONDUTOR_EMAIL) VALUES (NEXTVAL ('veiculo_sequence'), 'AFG7756','Ford Mustang Fastback 1968','john.wick@continental.com');
INSERT INTO TB_VEICULO (ID, PLACA, NOME, CONDUTOR_EMAIL) VALUES (NEXTVAL ('veiculo_sequence'), 'MAD7894','Ford XB Falcon GT 351','max.rockatansky@wasteland.com');
INSERT INTO TB_VEICULO (ID, PLACA, NOME, CONDUTOR_EMAIL) VALUES (NEXTVAL ('veiculo_sequence'), 'BLOOD01','Mercury Monterey 1950','john.rambo@exercito.com');
INSERT INTO TB_VEICULO (ID, PLACA, NOME, CONDUTOR_EMAIL) VALUES (NEXTVAL ('veiculo_sequence'), 'INDYJR1','Mercedes-Benz LG3000','indiana.jones@museum.com');
--
--INSERCAO DE MEIO DE PAGAMENTO--
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'john.wick@continental.com', 'PIX',null, null, true);
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'john.wick@continental.com', 'CREDITO',111111111111, '2028-11-01', false);
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'john.wick@continental.com', 'DEBITO',333333333333, '2025-07-01', false);
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'john.wick@continental.com', 'CREDITO',555555555555, '2021-11-01', false);
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'max.rockatansky@wasteland.com', 'PIX',null, null, true);
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'max.rockatansky@wasteland.com', 'CREDITO',543254325432, '2032-06-01', false);
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'max.rockatansky@wasteland.com', 'DEBITO',789678967896, '2029-09-01', false);
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'john.rambo@exercito.com', 'PIX',null, null, true);
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'john.rambo@exercito.com', 'CREDITO',234523452345, '2028-06-01', false);
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'john.rambo@exercito.com', 'DEBITO',909080805050, '2035-04-01', false);
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'indiana.jones@museum.com', 'PIX',null, null, true);
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'indiana.jones@museum.com', 'CREDITO',292934340606, '2034-07-01', false);
INSERT INTO TB_MEIO_PAGAMENTO_CONDUTOR (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO, NUMERO_CARTAO, VALIDADE_CARTAO, FAVORITO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'indiana.jones@museum.com', 'DEBITO',334488991122, '2026-04-01', false);
