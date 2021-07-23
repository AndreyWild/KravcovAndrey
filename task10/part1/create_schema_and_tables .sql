-- -----------------------------------------------------
-- Creating schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS SENLA_task10 COLLATE = utf8_general_ci;
USE SENLA_task10;

-- -----------------------------------------------------
-- Creating Product table
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS SENLA_task10.product (
  maker VARCHAR(10) NOT NULL,
  model VARCHAR(50) NOT NULL,
  type VARCHAR(50) NOT NULL,
  PRIMARY KEY (model));

-- -----------------------------------------------------
-- Creating Laptop table
-- -----------------------------------------------------  
  CREATE TABLE IF NOT EXISTS SENLA_task10.laptop (
  code INT NOT NULL,
  model VARCHAR(50) NOT NULL,
  speed SMALLINT(4) NOT NULL,
  ram SMALLINT(4) NOT NULL,
  hd REAL NOT NULL,
  price INT NULL,
  screen TINYINT(2) NOT NULL,
  PRIMARY KEY (code),
  FOREIGN KEY (model) REFERENCES product (model));

-- -----------------------------------------------------
-- Creating PC table
-- -----------------------------------------------------
  CREATE TABLE IF NOT EXISTS SENLA_task10.pc (
  code INT NOT NULL,
  model VARCHAR(50) NOT NULL,
  speed SMALLINT(4) NOT NULL,
  ram SMALLINT(4) NOT NULL,
  hd REAL NOT NULL,
  cd VARCHAR(10) NOT NULL,
  price INT NULL,
  PRIMARY KEY (code),
  FOREIGN KEY (model) REFERENCES product (model));

-- -----------------------------------------------------
-- Creating Printer table
-- -----------------------------------------------------
  CREATE TABLE IF NOT EXISTS SENLA_task10.printer (
  code INT NOT NULL,
  model VARCHAR(50) NOT NULL,
  color CHAR(1) NOT NULL,
  type VARCHAR(10) NOT NULL,
  price INT NULL,
  PRIMARY KEY (code),
  FOREIGN KEY (model) REFERENCES product (model));
  
  