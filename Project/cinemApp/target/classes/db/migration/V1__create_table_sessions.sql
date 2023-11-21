CREATE TABLE `sessions` (
 `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `data` date,
 `description` varchar(255) DEFAULT NULL,
 `filmname` varchar(255),
 `status` enum('ABERTO','CANCELADO','ENCERRADO','PREVISTO') DEFAULT NULL
);