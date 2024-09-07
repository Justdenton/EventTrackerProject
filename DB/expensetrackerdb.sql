-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema expensetrackerdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `expensetrackerdb` ;

-- -----------------------------------------------------
-- Schema expensetrackerdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `expensetrackerdb` DEFAULT CHARACTER SET utf8 ;
USE `expensetrackerdb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `username` VARCHAR(16) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `email` VARCHAR(255) NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(300) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payment_method`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `payment_method` ;

CREATE TABLE IF NOT EXISTS `payment_method` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `method_name` VARCHAR(45) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `expense`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `expense` ;

CREATE TABLE IF NOT EXISTS `expense` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(8,2) NOT NULL,
  `description` VARCHAR(300) NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `payment_method_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,
  INDEX `payment_method_id_idx` (`payment_method_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `category_id`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `payment_method_id`
    FOREIGN KEY (`payment_method_id`)
    REFERENCES `payment_method` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS expensetrackeradmin@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'expensetrackeradmin'@'localhost' IDENTIFIED BY 'expensetrackeradmin';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'expensetrackeradmin'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `expensetrackerdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `create_time`, `update_time`, `active`) VALUES (1, 'Test', 'Tester', 'test', 'test', 'tt@ex.com', '2024-09-05 19:00:00', NULL, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `create_time`, `update_time`, `active`) VALUES (2, 'Justin', 'Denton', 'jdent', 'jdent', 'jd@ex.com', '2024-09-05 19:05:00', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `expensetrackerdb`;
INSERT INTO `category` (`id`, `name`, `description`) VALUES (1, 'Food', 'Expenses related to food.');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (2, 'Grocery', 'Expenses related to grocery.');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (3, 'Transportation', 'Expenses related to commuting or travel.');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (4, 'Entertainment', 'Expenses related to entertainment.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `payment_method`
-- -----------------------------------------------------
START TRANSACTION;
USE `expensetrackerdb`;
INSERT INTO `payment_method` (`id`, `method_name`, `create_time`) VALUES (1, 'Cash', '2024-09-05 19:00:00');
INSERT INTO `payment_method` (`id`, `method_name`, `create_time`) VALUES (2, 'Check', '2024-09-05 19:00:00');
INSERT INTO `payment_method` (`id`, `method_name`, `create_time`) VALUES (3, 'Credit Card', '2024-09-05 19:00:00');
INSERT INTO `payment_method` (`id`, `method_name`, `create_time`) VALUES (4, 'Debit Card', '2024-09-05 19:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `expense`
-- -----------------------------------------------------
START TRANSACTION;
USE `expensetrackerdb`;
INSERT INTO `expense` (`id`, `amount`, `description`, `create_time`, `update_time`, `user_id`, `category_id`, `payment_method_id`) VALUES (1, 10.10, 'Walmart - grocery and shoes', '2024-09-05 19:10:00', NULL, 1, 2, 1);
INSERT INTO `expense` (`id`, `amount`, `description`, `create_time`, `update_time`, `user_id`, `category_id`, `payment_method_id`) VALUES (2, 20.20, 'Movies - paid for friend', '2024-09-05 19:00:00', NULL, 1, 4, 3);
INSERT INTO `expense` (`id`, `amount`, `description`, `create_time`, `update_time`, `user_id`, `category_id`, `payment_method_id`) VALUES (3, 30.30, 'Caseys - fill up Jeep', '2024-09-05 19:00:00', NULL, 2, 3, 3);
INSERT INTO `expense` (`id`, `amount`, `description`, `create_time`, `update_time`, `user_id`, `category_id`, `payment_method_id`) VALUES (4, 40.40, 'Lots of Taco Bell', '2024-09-05 19:00:00', NULL, 2, 1, 1);

COMMIT;

