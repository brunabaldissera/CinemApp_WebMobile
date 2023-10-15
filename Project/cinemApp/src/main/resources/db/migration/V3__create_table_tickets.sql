CREATE TABLE `tickets` (
 `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `data` datetime(6) NOT NULL,
 `sessao_id` bigint NOT NULL,
 `usuario_id` bigint NOT NULL,
FOREIGN KEY (`sessao_id`) REFERENCES `sessions` (`id`),
FOREIGN KEY (`usuario_id`) REFERENCES `user` (`id`)
);