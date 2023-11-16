DROP database if exists egglog;
CREATE database if NOT EXISTS `egglog`;
use `egglog`;
-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `gender` varchar(40) NOT NULL,
  `birth` DATE NOT NULL,
  `nickname` varchar(40) NOT NULL,
--   `phonenum` varchar(40) NOT NULL,
--   `public status` boolean NOT NULL,
  `status_message` varchar(40) DEFAULT NULL,
  `profile_picture` varchar(100) default NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;

insert INTO `user`
VALUES("1", "2", "123", "2000/10/10","123","123","123");



-- -----------------------------------------------------
-- Table `diary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diary` (
  `user_id` varchar(40) NOT NULL,
  `title` VARCHAR(40) NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `diary_date` date NOT NULL,
  `	` varchar(100) default NULL,
  `location` varchar(40) default NULL,
	PRIMARY KEY (`diary_date`),
  	FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
  ) ENGINE = InnoDB;
  
  insert INTO `diary`
VALUES("1", "2", "123", "2000/10/10","123","123");
  
  
  
-- -----------------------------------------------------
-- Table `request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `request` (
  `from` VARCHAR(40) NOT NULL,
  `to` varchar(40) NOT NULL,
  PRIMARY KEY (`from`,`to`)
  ) ENGINE = InnoDB;
  
insert INTO `request`
VALUES("1", "2");
  
-- -----------------------------------------------------
-- Table `friends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `friends` (
  `my_id` VARCHAR(40) NOT NULL,
  `friend_id` varchar(40) NOT NULL,
  PRIMARY KEY (`my_id`,`friend_id`)
  ) ENGINE = InnoDB;
  
  insert INTO `friends`
VALUES("2", "3");
  
select * from user;
select * from diary;
select * from request;
select * from friends;