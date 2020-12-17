CREATE TABLE user (
 `user_id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
 `email` VARCHAR(45) NOT NULL,
 `phone_no` VARCHAR(45) NOT NULL,
 `street_address` VARCHAR(45) NOT NULL,
 `zipcode` INT NOT NULL,
  PRIMARY KEY (`username`));

INSERT INTO user 
(username, password, first_name, last_name, email, phone_no, street_address, zipcode)
 VALUES
(amckeow3, KaiLeon1!, Adrianna, Mckeown, amckeow3@uncc.edu, 704-989-1797, 5 May St, 28104);

INSERT INTO user 
(username, password, first_name, last_name, email, phone_no, street_address, zipcode)
 VALUES
(jdoe123, 1q2w3e, Jane, Doe, jane_doe@hotmail.com, 980-999-3452, 3005 Johnson Dr, 28104);


CREATE TABLE appliance (
  `appliance_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `appliance_name` VARCHAR(150) NOT NULL,
  `appliance_desc` VARCHAR(200) NOT NULL,
 `available_from_dt` DATE NOT NULL,
 `available_to_dt` DATE NOT NULL,
 `price_per_day` DOUBLE NOT NULL,
  PRIMARY KEY (`appliance_id`),
  CONSTRAINT `appliance_ibfk_1`
  FOREIGN KEY (`username`)
  REFERENCES `user` (`username`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

INSERT INTO appliance 
(username, appliance_name, appliance_desc, available_from_dt, available_to_dt, price_per_day)
 VALUES
(amckeow3, Lawn Mower, brand new, 2020-12-20, 2020-12-30, 10);



CREATE TABLE  notification (
  `notification_id` INT NOT NULL AUTO_INCREMENT,
  `appliance_id` INT NOT NULL,
  `lender_username` VARCHAR(45) NOT NULL,
  `borrower_username` VARCHAR(45) NOT NULL,
 `created_on` DATE NOT NULL,
  PRIMARY KEY (`notification_id`),
  CONSTRAINT `notification_ibfk_1`
  FOREIGN KEY (`appliance_id`)
  REFERENCES `appliance` (`appliance_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT `notification_ibfk_2`
  FOREIGN KEY (`lender_username`)
  REFERENCES `user` (`username`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT `notification_ibfk_3`
  FOREIGN KEY (`borrower_username`)
  REFERENCES ‘user’ (`username`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);
 
INSERT INTO notification 
(appliance_id, lender_username, borrower_username, created_on)
 VALUES
(1, amckeow3, jdoe123, 2020-12-15);


