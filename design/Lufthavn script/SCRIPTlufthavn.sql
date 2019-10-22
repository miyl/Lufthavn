-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema lufthavn
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lufthavn
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lufthavn` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `lufthavn` ;

-- -----------------------------------------------------
-- Table `lufthavn`.`Adgange`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lufthavn`.`Adgange` (
  `afgange_id` INT(10) NOT NULL AUTO_INCREMENT,
  `til_dest_id` INT(10) NOT NULL,
  `landingstidpunkt` VARCHAR(50) NULL DEFAULT NULL,
  `fly_id` INT(10) NOT NULL,
  PRIMARY KEY (`afgange_id`),
  INDEX `FKAdgange855821` (`til_dest_id` ASC) VISIBLE,
  INDEX `FKAdgange406199` (`fly_id` ASC) VISIBLE,
  CONSTRAINT `FKAdgange406199`
    FOREIGN KEY (`fly_id`)
    REFERENCES `lufthavn`.`fly` (`fly_id`),
  CONSTRAINT `FKAdgange855821`
    FOREIGN KEY (`til_dest_id`)
    REFERENCES `lufthavn`.`destinationer` (`dest_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lufthavn`.`Ankomster`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lufthavn`.`Ankomster` (
  `ankomst_id` INT(10) NOT NULL AUTO_INCREMENT,
  `fra_dest_id` INT(10) NOT NULL,
  `landingstidpunkt` VARCHAR(50) NULL DEFAULT NULL,
  `fly_id` INT(10) NOT NULL,
  PRIMARY KEY (`ankomst_id`),
  INDEX `FKAnkomster226332` (`fra_dest_id` ASC) VISIBLE,
  INDEX `FKAnkomster800644` (`fly_id` ASC) VISIBLE,
  CONSTRAINT `FKAnkomster226332`
    FOREIGN KEY (`fra_dest_id`)
    REFERENCES `lufthavn`.`destinationer` (`dest_id`),
  CONSTRAINT `FKAnkomster800644`
    FOREIGN KEY (`fly_id`)
    REFERENCES `lufthavn`.`fly` (`fly_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lufthavn`.`Destinationer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lufthavn`.`Destinationer` (
  `dest_id` INT(10) NOT NULL AUTO_INCREMENT,
  `navn` VARCHAR(50) NULL DEFAULT NULL,
  `land` VARCHAR(50) NULL DEFAULT NULL,
  `afstand` INT(10) NULL DEFAULT NULL,
  PRIMARY KEY (`dest_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lufthavn`.`Fly`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lufthavn`.`Fly` (
  `fly_id` INT(10) NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(100) NULL DEFAULT NULL,
  `capacitet` INT(10) NULL DEFAULT NULL,
  `hastighed` INT(10) NULL DEFAULT NULL,
  `landingsplads_id` INT(10) NOT NULL,
  `størrelse` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`fly_id`),
  INDEX `FKFly722180` (`landingsplads_id` ASC) VISIBLE,
  CONSTRAINT `FKFly722180`
    FOREIGN KEY (`landingsplads_id`)
    REFERENCES `lufthavn`.`standpladser` (`standplads_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lufthavn`.`Flyopgaver`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lufthavn`.`Flyopgaver` (
  `fly_id` INT(10) NOT NULL,
  `opgave_id` INT(10) NOT NULL,
  `hold_id` INT(10) NULL DEFAULT NULL,
  `done` BINARY(255) NULL DEFAULT NULL,
  INDEX `FKFlyopgaver936654` (`opgave_id` ASC) VISIBLE,
  INDEX `FKFlyopgaver122714` (`fly_id` ASC) VISIBLE,
  CONSTRAINT `FKFlyopgaver122714`
    FOREIGN KEY (`fly_id`)
    REFERENCES `lufthavn`.`fly` (`fly_id`),
  CONSTRAINT `FKFlyopgaver936654`
    FOREIGN KEY (`opgave_id`)
    REFERENCES `lufthavn`.`opgave` (`opgave_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lufthavn`.`Opgave`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lufthavn`.`Opgave` (
  `opgave_id` INT(10) NOT NULL AUTO_INCREMENT,
  `opgavetype_id` INT(10) NOT NULL,
  `arbejdsminutter` INT(10) NULL DEFAULT NULL,
  PRIMARY KEY (`opgave_id`),
  INDEX `FKOpgave451579` (`opgavetype_id` ASC) VISIBLE,
  CONSTRAINT `FKOpgave451579`
    FOREIGN KEY (`opgavetype_id`)
    REFERENCES `lufthavn`.`opgavetyper` (`opgavetype_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lufthavn`.`Opgavetyper`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lufthavn`.`Opgavetyper` (
  `opgavetype_id` INT(10) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`opgavetype_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lufthavn`.`Personale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lufthavn`.`Personale` (
  `hold_id` INT(10) NOT NULL AUTO_INCREMENT,
  `opgavetype_id` INT(10) NOT NULL,
  `standplads_id` INT(10) NULL DEFAULT NULL,
  PRIMARY KEY (`hold_id`),
  INDEX `FKPersonale306463` (`opgavetype_id` ASC) VISIBLE,
  CONSTRAINT `FKPersonale306463`
    FOREIGN KEY (`opgavetype_id`)
    REFERENCES `lufthavn`.`opgavetyper` (`opgavetype_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lufthavn`.`Personale_login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lufthavn`.`Personale_login` (
  `login_id` INT(10) NOT NULL AUTO_INCREMENT,
  `brugernavn` VARCHAR(50) NULL DEFAULT NULL,
  `kodeord` VARCHAR(50) NULL DEFAULT NULL,
  `e-mail` VARCHAR(100) NULL DEFAULT NULL,
  `hold_id` INT(10) NOT NULL,
  PRIMARY KEY (`login_id`),
  INDEX `FKPersonale_634738` (`hold_id` ASC) VISIBLE,
  CONSTRAINT `FKPersonale_634738`
    FOREIGN KEY (`hold_id`)
    REFERENCES `lufthavn`.`personale` (`hold_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lufthavn`.`Standplads_nabo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lufthavn`.`Standplads_nabo` (
  `standpladsA_id` INT(10) NOT NULL,
  `standpladsB_id` INT(10) NOT NULL,
  INDEX `FKStandplads921931` (`standpladsA_id` ASC) VISIBLE,
  INDEX `FKStandplads892140` (`standpladsB_id` ASC) VISIBLE,
  CONSTRAINT `FKStandplads892140`
    FOREIGN KEY (`standpladsB_id`)
    REFERENCES `lufthavn`.`standpladser` (`standplads_id`),
  CONSTRAINT `FKStandplads921931`
    FOREIGN KEY (`standpladsA_id`)
    REFERENCES `lufthavn`.`standpladser` (`standplads_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lufthavn`.`Standpladser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lufthavn`.`Standpladser` (
  `standplads_id` INT(10) NOT NULL AUTO_INCREMENT,
  `terminal_id` INT(10) NOT NULL,
  PRIMARY KEY (`standplads_id`),
  INDEX `FKStandplads320695` (`terminal_id` ASC) VISIBLE,
  CONSTRAINT `FKStandplads320695`
    FOREIGN KEY (`terminal_id`)
    REFERENCES `lufthavn`.`terminaler` (`terminal_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lufthavn`.`Terminaler`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lufthavn`.`Terminaler` (
  `terminal_id` INT(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`terminal_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
