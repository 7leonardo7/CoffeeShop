CREATE SCHEMA `coffeeshop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `coffeeshop`.`coffeeorder` (
  
`id` INT NOT NULL AUTO_INCREMENT,
  
`coffeeKind` VARCHAR(45) NOT NULL,
  
`quantity` DOUBLE NOT NULL,
  
`cost` DOUBLE NOT NULL,
  
`deliveryTimeFrom` DATETIME NULL,
  
`deliveryTimeTo` DATETIME NULL,
  
PRIMARY KEY (`id`));


INSERT INTO `coffeeshop`.`coffeeorder` (`coffeeKind`, `quantity`, `cost`) VALUES ('Espresso', '155', '1550');

INSERT INTO `coffeeshop`.`coffeeorder` (`coffeeKind`, `quantity`, `cost`, `deliveryTimeFrom`, `deliveryTimeTo`) 
VALUES ('Lungo', '112', '1143.4', '2017-05-24 17:00', '2017-05-24 18:00');

INSERT INTO `coffeeshop`.`coffeeorder` (`coffeeKind`, `quantity`, `cost`) VALUES ('3 in 1', '100', '550');

INSERT INTO `coffeeshop`.`coffeeorder` (`coffeeKind`, `quantity`, `cost`, `deliveryTimeFrom`, `deliveryTimeTo`) 
VALUES ('Macchiato', '220', '2751', '2017-05-25 12:10', '2017-05-25 16:30');