-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `theatre`.`auditorium`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theatre`.`auditorium` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `number_of_seats` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theatre`.`seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theatre`.`seat` (
  `number` INT NOT NULL,
  `auditorium_id` INT NOT NULL,
  PRIMARY KEY (`number`),
  INDEX `fk_seat_auditorium_idx` (`auditorium_id` ASC),
  CONSTRAINT `fk_seat_auditorium`
    FOREIGN KEY (`auditorium_id`)
    REFERENCES `theatre`.`auditorium` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theatre`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theatre`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(40) NOT NULL,
  `last_name` VARCHAR(40) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `birthday` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theatre`.`event_rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theatre`.`event_rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theatre`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theatre`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `basePrice` DECIMAL(8,2) NOT NULL,
  `rating_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_event_rating_idx` (`rating_id` ASC),
  CONSTRAINT `fk_event_event_rating`
    FOREIGN KEY (`rating_id`)
    REFERENCES `theatre`.`event_rating` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theatre`.`air_date`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theatre`.`air_date` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `auditorium_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `date_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_air_date_auditorium_idx` (`auditorium_id` ASC),
  INDEX `fk_air_date_event_idx` (`event_id` ASC),
  CONSTRAINT `fk_air_date_auditorium`
    FOREIGN KEY (`auditorium_id`)
    REFERENCES `theatre`.`auditorium` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_air_date_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `theatre`.`event` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theatre`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theatre`.`ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `purchase_date` INT NOT NULL,
  `seat` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_seat_ticket_idx` (`seat` ASC),
  INDEX `fk_ticket_user_idx` (`user_id` ASC),
  INDEX `fk_ticket_event_idx` (`event_id` ASC),
  CONSTRAINT `fk_ticket_seat`
    FOREIGN KEY (`seat`)
    REFERENCES `theatre`.`seat` (`number`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `theatre`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `theatre`.`event` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `theatre`.`event_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `theatre`;
INSERT INTO `theatre`.`event_rating` (`id`, `rating`) VALUES (1, 'LOW');
INSERT INTO `theatre`.`event_rating` (`id`, `rating`) VALUES (2, 'MIDDLE');
INSERT INTO `theatre`.`event_rating` (`id`, `rating`) VALUES (3, 'HIGH');

COMMIT;

