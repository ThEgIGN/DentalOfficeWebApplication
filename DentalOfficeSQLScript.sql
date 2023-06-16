-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dental_office
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dental_office
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dental_office` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `dental_office` ;

-- -----------------------------------------------------
-- Table `dental_office`.`dentists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dental_office`.`dentists` (
  `dentist_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(20) NULL DEFAULT NULL,
  `jmbg` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`dentist_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dental_office`.`patients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dental_office`.`patients` (
  `patient_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(20) NULL DEFAULT NULL,
  `jmbg` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`patient_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dental_office`.`appointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dental_office`.`appointments` (
  `appointment_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `time_start` TIME NOT NULL,
  `time_end` TIME NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `length` SMALLINT UNSIGNED NOT NULL,
  `patient_id` INT UNSIGNED NULL DEFAULT NULL,
  `dentist_id` INT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`appointment_id`),
  INDEX `idx_fk_patient_id` (`patient_id` ASC) VISIBLE,
  INDEX `idx_fk_dentist_id` (`dentist_id` ASC) VISIBLE,
  CONSTRAINT `fk_appointment_dentist`
    FOREIGN KEY (`dentist_id`)
    REFERENCES `dental_office`.`dentists` (`dentist_id`),
  CONSTRAINT `fk_appointment_patient`
    FOREIGN KEY (`patient_id`)
    REFERENCES `dental_office`.`patients` (`patient_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
