DROP database if exists egglog;
CREATE database if NOT EXISTS `egglog`;
use `egglog`;
-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `gender` varchar(40) NOT NULL,
  `birth` DATE NOT NULL,
  `reg_date` DATE,
  `nickname` varchar(40) NOT NULL,
--   `phonenum` varchar(40) NOT NULL,
--   `public status` boolean NOT NULL,
  `status_message` varchar(40) DEFAULT NULL,
  `profile_picture` varchar(100) default NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;

insert INTO `user`
VALUES("1", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjIifQ.9x1bQAqJlzWdD4n5LHi2DOD493wmufsrPmm31HisU6Y", "123", "2000/10/10", "2023/11/20", "페이커","우승달달하다","123"),
       ("2", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjMifQ.TXzi4wGEPaE8SksUKSBRRWbf5BtuhRDu8IablFv3iOY", "234", "2000/11/11", "2023/10/20", "페이커친구", "그만잘해 페이커", "234"),
       ("3", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjMifQ.TXzi4wGEPaE8SksUKSBRRWbf5BtuhRDu8IablFv3iOY", "345", "2000/12/12", "2023/9/20", "페이커친구의친구", "그만잘해 페이커친구", "345"),
       ("4", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjMifQ.TXzi4wGEPaE8SksUKSBRRWbf5BtuhRDu8IablFv3iOY", "456", "2000/2/2", "2022/10/20", "페이커친구의친구의친구", "그만잘해 페이커친구의친구", "456");



-- -----------------------------------------------------
-- Table `diary`board
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diary` (
  `user_id` varchar(40) NOT NULL,
  `title` VARCHAR(40) NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `diary_date` date NOT NULL,
  `hashtag` varchar(100) default NULL,
  `location` varchar(40) default NULL,
    PRIMARY KEY (`diary_date`, `user_id`)
  ) ENGINE = InnoDB;
  
  insert INTO `diary`
VALUES("1", "2", "123", "2000/10/10","상체 하체 복근 전신 ","123");
  
  
  
-- -----------------------------------------------------
-- Table `request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `request` (
  `from` VARCHAR(40) NOT NULL,
  `to` varchar(40) NOT NULL,
  PRIMARY KEY (`from`,`to`)
  ) ENGINE = InnoDB;
  
insert INTO `request`
VALUES("4", "2"),
        ("2", "3");
  
-- -----------------------------------------------------
-- Table `friends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `friends` (
  `my_id` VARCHAR(40) NOT NULL,
  `friend_id` varchar(40) NOT NULL,
  PRIMARY KEY (`my_id`,`friend_id`)
  ) ENGINE = InnoDB;
  
  insert INTO `friends`
VALUES("1", "2"),
        ("2", "1"),
        ("1", "3"),
        ("3", "1"),
        ("2", "3"),
        ("3", "2");
  
select * from user;
select * from diary;
select * from request;
select * from friends;