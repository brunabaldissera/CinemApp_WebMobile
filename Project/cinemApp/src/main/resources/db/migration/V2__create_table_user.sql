CREATE TABLE `user` (
 `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `cidade` varchar(255) DEFAULT NULL,
 `nome` varchar(255) NOT NULL,
 `telefone` varchar(255) DEFAULT NULL,
 `cpf` varchar(14) DEFAULT NULL
);