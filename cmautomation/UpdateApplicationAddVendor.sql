
use cm_automation;

ALTER TABLE `cm_automation`.`applications` 
ADD COLUMN `vendor_Id` INT(11) AFTER `description`,
ADD INDEX `vendor_Id_idx` (`vendor_Id` ASC);
ALTER TABLE `cm_automation`.`applications` 
ADD CONSTRAINT `vendor_Id`
  FOREIGN KEY (`vendor_Id`)
  REFERENCES `cm_automation`.`vendor` (`vendor_Id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
UPDATE `cm_automation`.`applications` SET `vendor_Id`='1' WHERE `vendor_Id` is null;

ALTER TABLE `cm_automation`.`applications` 
DROP FOREIGN KEY `vendor_Id`;
ALTER TABLE `cm_automation`.`applications` 
CHANGE COLUMN `vendor_Id` `vendor_Id` INT(11) NOT NULL ;
ALTER TABLE `cm_automation`.`applications` 
ADD CONSTRAINT `vendor_Id`
  FOREIGN KEY (`vendor_Id`)
  REFERENCES `cm_automation`.`vendor` (`vendor_Id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

