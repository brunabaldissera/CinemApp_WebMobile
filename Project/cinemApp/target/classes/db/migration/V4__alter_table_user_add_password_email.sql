ALTER TABLE `user`
ADD COLUMN senha varchar(255) NOT NULL,
ADD COLUMN email varchar(255) NOT NULL UNIQUE,
ADD COLUMN role varchar(10) DEFAULT "USER";