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
  `enabled` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payment_method`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `payment_method` ;

CREATE TABLE IF NOT EXISTS `payment_method` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `method_name` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recurring_transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recurring_transaction` ;

CREATE TABLE IF NOT EXISTS `recurring_transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `recur_period` VARCHAR(45) NOT NULL,
  `next_recur_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `enabled` TINYINT NULL DEFAULT 1,
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
  `enabled` TINYINT NULL DEFAULT 1,
  `user_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `payment_method_id` INT NOT NULL,
  `recurring_transaction_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,
  INDEX `payment_method_id_idx` (`payment_method_id` ASC) VISIBLE,
  INDEX `fk_expense_recurring_transaction1_idx` (`recurring_transaction_id` ASC) VISIBLE,
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
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_expense_recurring_transaction1`
    FOREIGN KEY (`recurring_transaction_id`)
    REFERENCES `recurring_transaction` (`id`)
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
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `create_time`, `update_time`, `active`) VALUES (3, 'John', 'Doe', 'jdoe', 'test', 'jdoe@gmail.com', '2024-09-10 17:01:00', NULL, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `create_time`, `update_time`, `active`) VALUES (4, 'Jane', 'Doe', 'janedoe', 'tester', 'janedoe1@hotmail.com', '2024-09-10 17:04:32', NULL, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `create_time`, `update_time`, `active`) VALUES (5, 'John', 'Smith', 'jsmith', 'jsmith', 'smithj@aol.com', '2024-09-10 17:05:00', NULL, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `create_time`, `update_time`, `active`) VALUES (6, 'Jane', 'Smith', 'janesmith', 'test', 'jane1@gmail.com', '2024-09-10 17:06:31', NULL, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `create_time`, `update_time`, `active`) VALUES (7, 'Jake', 'Doe', 'jakesmith', 'test', 'jake123@gmail.com', '2024-09-10 17:08:17', NULL, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `create_time`, `update_time`, `active`) VALUES (8, 'Justin', 'Doe', 'jdoe56', 'test', 'jdoe56@gmail.com', '2024-09-10 17:09:37', NULL, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `create_time`, `update_time`, `active`) VALUES (9, 'Bob', 'Doe', 'bob', 'test', 'bob5000@aol.com', '2024-09-10 17:11:12', NULL, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `create_time`, `update_time`, `active`) VALUES (10, 'Edward', 'Doe', 'ed1956', 'test', 'ed346@hotmail.com', '2024-09-10 17:13:42', NULL, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `create_time`, `update_time`, `active`) VALUES (11, 'Emily', 'Jackson', 'emjackson87', 'test', 'emjackson87@gmail.com', '2024-09-10 17:17:17', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `expensetrackerdb`;
INSERT INTO `category` (`id`, `name`, `description`, `enabled`) VALUES (1, 'Food', 'Expenses related to food.', NULL);
INSERT INTO `category` (`id`, `name`, `description`, `enabled`) VALUES (2, 'Grocery', 'Expenses related to grocery.', NULL);
INSERT INTO `category` (`id`, `name`, `description`, `enabled`) VALUES (3, 'Transportation', 'Expenses related to commuting or travel.', NULL);
INSERT INTO `category` (`id`, `name`, `description`, `enabled`) VALUES (4, 'Entertainment', 'Expenses related to entertainment.', NULL);
INSERT INTO `category` (`id`, `name`, `description`, `enabled`) VALUES (5, 'Utility', 'Expenses related to home utilities.', NULL);
INSERT INTO `category` (`id`, `name`, `description`, `enabled`) VALUES (6, 'Health', 'Expenses related to health and fitness.', NULL);
INSERT INTO `category` (`id`, `name`, `description`, `enabled`) VALUES (7, 'Insurance', 'Expenses related to insurance payments.', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `payment_method`
-- -----------------------------------------------------
START TRANSACTION;
USE `expensetrackerdb`;
INSERT INTO `payment_method` (`id`, `method_name`, `enabled`) VALUES (1, 'Cash', NULL);
INSERT INTO `payment_method` (`id`, `method_name`, `enabled`) VALUES (2, 'Check', NULL);
INSERT INTO `payment_method` (`id`, `method_name`, `enabled`) VALUES (3, 'Credit Card', NULL);
INSERT INTO `payment_method` (`id`, `method_name`, `enabled`) VALUES (4, 'Debit Card', NULL);
INSERT INTO `payment_method` (`id`, `method_name`, `enabled`) VALUES (5, 'Gift Card', NULL);
INSERT INTO `payment_method` (`id`, `method_name`, `enabled`) VALUES (6, 'Venmo', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `recurring_transaction`
-- -----------------------------------------------------
START TRANSACTION;
USE `expensetrackerdb`;
INSERT INTO `recurring_transaction` (`id`, `start_date`, `end_date`, `recur_period`, `next_recur_date`, `enabled`) VALUES (1, '2024-09-10 16:00:00', NULL, 'Monthly', '2024-10-10 16:00:00', 1);
INSERT INTO `recurring_transaction` (`id`, `start_date`, `end_date`, `recur_period`, `next_recur_date`, `enabled`) VALUES (2, '2024-09-15 16:00:00', NULL, 'Monthly', '2024-10-15 16:00:00', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `expense`
-- -----------------------------------------------------
START TRANSACTION;
USE `expensetrackerdb`;
INSERT INTO `expense` (`id`, `amount`, `description`, `create_time`, `update_time`, `enabled`, `user_id`, `category_id`, `payment_method_id`, `recurring_transaction_id`) VALUES (1, 10.10, 'Walmart - grocery and shoes', '2024-09-05 19:10:00', NULL, 1, 1, 2, 1, NULL);
INSERT INTO `expense` (`id`, `amount`, `description`, `create_time`, `update_time`, `enabled`, `user_id`, `category_id`, `payment_method_id`, `recurring_transaction_id`) VALUES (2, 20.20, 'Movies - paid for friend', '2024-09-05 19:00:00', NULL, 1, 1, 4, 3, NULL);
INSERT INTO `expense` (`id`, `amount`, `description`, `create_time`, `update_time`, `enabled`, `user_id`, `category_id`, `payment_method_id`, `recurring_transaction_id`) VALUES (3, 30.30, 'Caseys - fill up Jeep', '2024-09-05 19:00:00', NULL, 1, 2, 3, 3, NULL);
INSERT INTO `expense` (`id`, `amount`, `description`, `create_time`, `update_time`, `enabled`, `user_id`, `category_id`, `payment_method_id`, `recurring_transaction_id`) VALUES (4, 40.40, 'Lots of Taco Bell', '2024-09-05 19:00:00', NULL, 1, 2, 1, 1, NULL);
INSERT INTO `expense` (`id`, `amount`, `description`, `create_time`, `update_time`, `enabled`, `user_id`, `category_id`, `payment_method_id`, `recurring_transaction_id`) VALUES (5, 50.50, 'Family museum trip', '2024-09-10 18:30:00', NULL, 1, 1, 4, 2, NULL);
INSERT INTO `expense` (`id`, `amount`, `description`, `create_time`, `update_time`, `enabled`, `user_id`, `category_id`, `payment_method_id`, `recurring_transaction_id`) VALUES (6, 80.00, 'Monthly gym membership for Emily and I', '2024-09-10 17:00:00', NULL, 1, 2, 6, 4, NULL);
INSERT INTO `expense` (`id`, `amount`, `description`, `create_time`, `update_time`, `enabled`, `user_id`, `category_id`, `payment_method_id`, `recurring_transaction_id`) VALUES (7, 120.25, 'Car Insurance (State Farm)', '2024-09-10 17:06:00', NULL, 1, 2, 7, 4, NULL);

COMMIT;

