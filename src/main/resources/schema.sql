-- Disable foreign key checks to prevent errors during table drops
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `energyreading`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `meter`;
DROP TABLE IF EXISTS `line`;

SET FOREIGN_KEY_CHECKS = 1;

-- Table: line
CREATE TABLE `line` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table: meter
CREATE TABLE `meter` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `line_id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `meter_ibfk_1` FOREIGN KEY (`line_id`) REFERENCES `line` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table: users
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `approved` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `line_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_unique` (`username`),
  CONSTRAINT `FK_user_line` FOREIGN KEY (`line_id`) REFERENCES `line` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table: energyreading
CREATE TABLE `energyreading` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `meter_id` bigint NOT NULL,
  `kwh` double DEFAULT NULL,
  `ts` datetime NOT NULL,
  `k_wh` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_energy_meter` FOREIGN KEY (`meter_id`) REFERENCES `meter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;