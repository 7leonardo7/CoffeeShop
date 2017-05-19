CREATE TABLE `coffeeshop`.`coffeeorder` (
  
`id` INT NOT NULL AUTO_INCREMENT,
  
`coffeeKind` VARCHAR(45) NOT NULL,
  
`quantity` DOUBLE NOT NULL,
  
`cost` DOUBLE NOT NULL,
  
`deliveryTimeFrom` DATETIME NULL,
  
`deliveryTimeTo` DATETIME NULL,
  
PRIMARY KEY (`id`));