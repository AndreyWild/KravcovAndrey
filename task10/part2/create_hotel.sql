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
CREATE SCHEMA IF NOT EXISTS `Hotel` DEFAULT CHARACTER SET utf8 ;
USE `Hotel` ;

-- -----------------------------------------------------
-- Table `Hotel`.`maintenance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`maintenance` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel`.`busy_date`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`busy_date` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`room` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `capacity` INT NOT NULL DEFAULT 5,
  `status` ENUM('OPEN', 'CLOSED', 'REPAIR') NOT NULL DEFAULT 'OPEN',
  `price` DECIMAL(10,2) NOT NULL DEFAULT 0.0,
  `numberOfStars` INT NOT NULL DEFAULT 5,
  `guest_id` INT NOT NULL,
  `id_guests_history` INT NULL,
  `busy_dates_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `guest_idx` (`guest_id` ASC, `id_guests_history` ASC) VISIBLE,
  INDEX `fk_room_busy_date1_idx` (`busy_dates_id` ASC) VISIBLE,
  CONSTRAINT `guest`
    FOREIGN KEY (`guest_id` , `id_guests_history`)
    REFERENCES `Hotel`.`guest` (`id` , `id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_room_busy_date1`
    FOREIGN KEY (`busy_dates_id`)
    REFERENCES `Hotel`.`busy_date` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel`.`guest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`guest` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `in` DATE NULL,
  `out` DATE NULL,
  `guest_status` ENUM('CHECKED', 'NOT_CHECKED') NOT NULL DEFAULT 'CHECKED',
  `maintenance_id` INT NOT NULL,
  `room_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `maintenance_idx` (`maintenance_id` ASC) VISIBLE,
  INDEX `fk_guest_room1_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `maintenance`
    FOREIGN KEY (`maintenance_id`)
    REFERENCES `Hotel`.`maintenance` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_guest_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `Hotel`.`room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
