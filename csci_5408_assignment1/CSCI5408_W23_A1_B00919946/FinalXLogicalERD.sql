-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Dalhousie_Logical
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Dalhousie_Logical
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Dalhousie_Logical` ;
USE `Dalhousie_Logical` ;

-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`Dalhousie University`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`Dalhousie University` (
  `Dalhousie_id` INT NOT NULL,
  `Dalhousie_Address` VARCHAR(45) NULL,
  `Dalhousie_Contact` VARCHAR(45) NULL,
  `Dalhousie_Dean` VARCHAR(45) NULL,
  PRIMARY KEY (`Dalhousie_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`Libraries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`Libraries` (
  `Library_id` INT NOT NULL,
  `Library_name` VARCHAR(45) NULL,
  `Library_address` VARCHAR(45) NULL,
  `Library_Hours` VARCHAR(45) NULL,
  `DalhousieLibraryId` INT NULL,
  PRIMARY KEY (`Library_id`),
  INDEX `DalhousieLibraryId_idx` (`DalhousieLibraryId` ASC) VISIBLE,
  CONSTRAINT `DalhousieLibraryId`
    FOREIGN KEY (`DalhousieLibraryId`)
    REFERENCES `Dalhousie_Logical`.`Dalhousie University` (`Dalhousie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`Programs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`Programs` (
  `Program_id` INT NOT NULL,
  `Program_name` VARCHAR(45) NULL,
  `Program_requirements` VARCHAR(45) NULL,
  `Program_acceptance_rate` INT NULL,
  `Program_Scholarship` VARCHAR(45) NULL,
  `DalhousieProgramId` INT NULL,
  PRIMARY KEY (`Program_id`),
  INDEX `DalhousieProgramId_idx` (`DalhousieProgramId` ASC) VISIBLE,
  CONSTRAINT `DalhousieProgramId`
    FOREIGN KEY (`DalhousieProgramId`)
    REFERENCES `Dalhousie_Logical`.`Dalhousie University` (`Dalhousie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`Faculty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`Faculty` (
  `Faculty_id` INT NOT NULL,
  `Faculty_name` VARCHAR(45) NULL,
  `Faculty_contact` VARCHAR(45) NULL,
  `Faculty_Expertise` VARCHAR(45) NULL,
  `DalhousieFacultyId` INT NULL,
  PRIMARY KEY (`Faculty_id`),
  INDEX `DalhousieFacultyId_idx` (`DalhousieFacultyId` ASC) VISIBLE,
  CONSTRAINT `DalhousieFacultyId`
    FOREIGN KEY (`DalhousieFacultyId`)
    REFERENCES `Dalhousie_Logical`.`Dalhousie University` (`Dalhousie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`Campus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`Campus` (
  `Campus_id` INT NOT NULL,
  `Campus_name` VARCHAR(45) NULL,
  `Campus_size` INT NULL,
  `Campus_location` VARCHAR(45) NULL,
  `Campus_Facility` VARCHAR(45) NULL,
  `DalhousieCampusId` INT NULL,
  PRIMARY KEY (`Campus_id`),
  INDEX `DalhousieCampusId_idx` (`DalhousieCampusId` ASC) VISIBLE,
  CONSTRAINT `DalhousieCampusId`
    FOREIGN KEY (`DalhousieCampusId`)
    REFERENCES `Dalhousie_Logical`.`Dalhousie University` (`Dalhousie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`Academic_Course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`Academic_Course` (
  `Academic_Course_Id` INT NOT NULL AUTO_INCREMENT,
  `Acdaemic_Course_Faculty_Id` VARCHAR(45) NULL,
  `Academic_Course_Curriculum` VARCHAR(45) NULL,
  `Academic_Course_Requirements` VARCHAR(45) NULL,
  `DalhousieAcademicId` INT NULL,
  PRIMARY KEY (`Academic_Course_Id`),
  INDEX `DalhousieAcademicId_idx` (`DalhousieAcademicId` ASC) VISIBLE,
  CONSTRAINT `DalhousieAcademicId`
    FOREIGN KEY (`DalhousieAcademicId`)
    REFERENCES `Dalhousie_Logical`.`Dalhousie University` (`Dalhousie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`Research`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`Research` (
  `Research_Id` INT NOT NULL,
  `Research_Areas` VARCHAR(45) NULL,
  `Research_Funding` INT NULL,
  `DalhousieResearchId` INT NULL,
  PRIMARY KEY (`Research_Id`),
  INDEX `DalhousieResearchId_idx` (`DalhousieResearchId` ASC) VISIBLE,
  CONSTRAINT `DalhousieResearchId`
    FOREIGN KEY (`DalhousieResearchId`)
    REFERENCES `Dalhousie_Logical`.`Dalhousie University` (`Dalhousie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`Contact Us`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`Contact Us` (
  `ContactUs_Id` INT NOT NULL,
  `ContactUs_PhoneNumber` VARCHAR(45) NULL,
  `ContactUs_EmailAddress` VARCHAR(45) NULL,
  `ContactUs_FeedbackForm` VARCHAR(45) NULL,
  `ContactUs_CampusMap` VARCHAR(45) NULL,
  `DalhousieContactId` INT NULL,
  PRIMARY KEY (`ContactUs_Id`),
  INDEX `DalhousieContactId_idx` (`DalhousieContactId` ASC) VISIBLE,
  CONSTRAINT `DalhousieContactId`
    FOREIGN KEY (`DalhousieContactId`)
    REFERENCES `Dalhousie_Logical`.`Dalhousie University` (`Dalhousie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`Events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`Events` (
  `Event_id` INT NOT NULL,
  `Event_name` VARCHAR(45) NULL,
  `Event_SocialMedia` VARCHAR(45) NULL,
  `Event_NewsLetters` VARCHAR(45) NULL,
  `Event_Date` VARCHAR(45) NULL,
  `DalhousieEventId` INT NULL,
  PRIMARY KEY (`Event_id`),
  INDEX `DalhousieEventId_idx` (`DalhousieEventId` ASC) VISIBLE,
  CONSTRAINT `DalhousieEventId`
    FOREIGN KEY (`DalhousieEventId`)
    REFERENCES `Dalhousie_Logical`.`Dalhousie University` (`Dalhousie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`Laptop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`Laptop` (
  `Laptop_id` INT NOT NULL,
  `Laptop_Name` VARCHAR(45) NULL,
  `Laptop_StartDate` VARCHAR(45) NULL,
  `LibraryLaptopId` INT NULL,
  PRIMARY KEY (`Laptop_id`),
  INDEX `LibraryLaptopId_idx` (`LibraryLaptopId` ASC) VISIBLE,
  CONSTRAINT `LibraryLaptopId`
    FOREIGN KEY (`LibraryLaptopId`)
    REFERENCES `Dalhousie_Logical`.`Libraries` (`Library_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`Online Resources`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`Online Resources` (
  `Online_Computer_Id` INT NOT NULL,
  `Online_Computer_Department` VARCHAR(45) NULL,
  `LibraryOnlineId` INT NULL,
  PRIMARY KEY (`Online_Computer_Id`),
  INDEX `LibraryOnlineId_idx` (`LibraryOnlineId` ASC) VISIBLE,
  CONSTRAINT `LibraryOnlineId`
    FOREIGN KEY (`LibraryOnlineId`)
    REFERENCES `Dalhousie_Logical`.`Libraries` (`Library_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`Book` (
  `Book_Id` INT NOT NULL,
  `Book_Name` VARCHAR(45) NULL,
  `Book_Author` VARCHAR(45) NULL,
  `Book_Version` VARCHAR(45) NULL,
  `LibraryBookId` INT NULL,
  PRIMARY KEY (`Book_Id`),
  INDEX `LibraryBookId_idx` (`LibraryBookId` ASC) VISIBLE,
  CONSTRAINT `LibraryBookId`
    FOREIGN KEY (`LibraryBookId`)
    REFERENCES `Dalhousie_Logical`.`Libraries` (`Library_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dalhousie_Logical`.`AlumniBoard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dalhousie_Logical`.`AlumniBoard` (
  `AlumniBoardId` INT NOT NULL,
  `AlumniBoardName` VARCHAR(45) NULL,
  `AlumniBoardPosition` VARCHAR(45) NULL,
  `AlumniBoardQualification` VARCHAR(45) NULL,
  `AlumniBoardProgramId` INT NOT NULL,
  PRIMARY KEY (`AlumniBoardId`),
  INDEX `AlumniBoardProgramId_idx` (`AlumniBoardProgramId` ASC) VISIBLE,
  CONSTRAINT `AlumniBoardProgramId`
    FOREIGN KEY (`AlumniBoardProgramId`)
    REFERENCES `Dalhousie_Logical`.`Programs` (`Program_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
