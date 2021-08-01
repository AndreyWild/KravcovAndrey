-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Hotel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Hotel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Hotel` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci ;
-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci ;
USE `Hotel` ;

-- -----------------------------------------------------
-- Table `Hotel`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`room` (
  `id` BIGINT(4) NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `capacity` INT(2) NOT NULL,
  `roomStatus` ENUM('OPEN', 'CLOSED', 'REPAIR') NOT NULL DEFAULT 'OPEN',
  `price` DOUBLE NOT NULL,
  `stars` ENUM('ONE', 'TWO', 'THREE', 'FOUR', 'FIVE') NOT NULL DEFAULT 'ONE',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel`.`guest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`guest` (
  `id` BIGINT(4) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT(3) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`order` (
  `id` BIGINT(4) NOT NULL AUTO_INCREMENT,
  `id_guest` BIGINT(4) NOT NULL,
  `id_room` BIGINT(4) NOT NULL,
  `checkIn` DATE NOT NULL,
  `checkOut` DATE NOT NULL,
  `status` ENUM('CHECKIN', 'CHECKOUT') NOT NULL DEFAULT 'CHECKIN',
  PRIMARY KEY (`id`),
  INDEX `fk_order_room_idx` (`id_guest` ASC) VISIBLE,
  INDEX `fk_order_guest1_idx` (`id_room` ASC) VISIBLE,
  CONSTRAINT `fk_order_room`
    FOREIGN KEY (`id_guest`)
    REFERENCES `Hotel`.`room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_guest1`
    FOREIGN KEY (`id_room`)
    REFERENCES `Hotel`.`guest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel`.`maintenance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`maintenance` (
  `id` BIGINT(4) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel`.`ord_maint`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`ord_maint` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_order` BIGINT(4) NOT NULL,
  `id_maintenance` BIGINT(4) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ord_maint_maintenance1_idx` (`id_maintenance` ASC) VISIBLE,
  INDEX `fk_ord_maint_order1_idx` (`id_order` ASC) VISIBLE,
  CONSTRAINT `fk_ord_maint_maintenance1`
    FOREIGN KEY (`id_maintenance`)
    REFERENCES `Hotel`.`maintenance` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ord_maint_order1`
    FOREIGN KEY (`id_order`)
    REFERENCES `Hotel`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `hotel` ;

-- -----------------------------------------------------
-- Table `hotel`.`guest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`guest` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_mysql500_ci;


-- -----------------------------------------------------
-- Table `hotel`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`room` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `capacity` INT NOT NULL,
  `roomStatus` ENUM('OPEN', 'CLOSED', 'REPAIR') NOT NULL DEFAULT 'OPEN',
  `price` DOUBLE NOT NULL,
  `stars` ENUM('ONE', 'TWO', 'THREE', 'FOUR', 'FIVE') NOT NULL DEFAULT 'ONE',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_mysql500_ci;


-- -----------------------------------------------------
-- Table `hotel`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_guest` BIGINT NOT NULL,
  `id_room` BIGINT NULL DEFAULT NULL,
  `checkIn` DATE NULL DEFAULT NULL,
  `checkOut` DATE NULL DEFAULT NULL,
  `status` ENUM('CHECKIN', 'CHECKOUT') NOT NULL DEFAULT 'CHECKIN',
  PRIMARY KEY (`id`),
  INDEX `fk_order_guest_idx` (`id_guest` ASC) VISIBLE,
  INDEX `fk_order_room1_idx` (`id_room` ASC) VISIBLE,
  CONSTRAINT `fk_order_guest`
    FOREIGN KEY (`id_guest`)
    REFERENCES `hotel`.`guest` (`id`),
  CONSTRAINT `fk_order_room1`
    FOREIGN KEY (`id_room`)
    REFERENCES `hotel`.`room` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_mysql500_ci;


-- -----------------------------------------------------
-- Table `hotel`.`maintenance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`maintenance` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `id_order` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_maintenance_order1_idx` (`id_order` ASC) VISIBLE,
  CONSTRAINT `fk_maintenance_order1`
    FOREIGN KEY (`id_order`)
    REFERENCES `hotel`.`order` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_mysql500_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
