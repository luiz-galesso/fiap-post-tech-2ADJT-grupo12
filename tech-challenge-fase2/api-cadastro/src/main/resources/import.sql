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
INSERT INTO TB_MEIO_PAGAMENTO (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'john.wick@continental.com', 'CREDITO');
INSERT INTO TB_MEIO_PAGAMENTO (ID, CONDUTOR_EMAIL, TIPO_MEIO_PAGAMENTO) VALUES (NEXTVAL ('meiopagamento_sequence'), 'john.wick@continental.com', 'DEBITO');
