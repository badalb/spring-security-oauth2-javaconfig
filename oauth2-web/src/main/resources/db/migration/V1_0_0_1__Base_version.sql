SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `oauth` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

USE `test`;

CREATE  TABLE IF NOT EXISTS `tbluser` (
  `user_id` CHAR(38) NOT NULL ,
  `user_name` VARCHAR(50) NOT NULL ,
  `first_name` VARCHAR(50) NOT NULL ,
  `last_name` VARCHAR(50) NULL DEFAULT NULL ,
  `is_inactive` CHAR(1) NULL DEFAULT NULL ,
  `is_locked` CHAR(1) NULL DEFAULT NULL ,
  `password` VARCHAR(256) NULL DEFAULT NULL ,
  `created_date` DATETIME NOT NULL ,
  `last_login_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `created_by` CHAR(38) NULL DEFAULT NULL ,
  `last_modified_by` CHAR(38) NULL DEFAULT NULL ,
  `is_deleted` CHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`user_id`) ,
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `tblrole` (
  `role_id` CHAR(38) NOT NULL ,
  `name` VARCHAR(50) NULL DEFAULT NULL ,
  `description` VARCHAR(100) NULL DEFAULT NULL ,
  `is_inactive` CHAR(1) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `created_by` CHAR(38) NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_by` CHAR(38) NULL DEFAULT NULL ,
  `is_deleted` CHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`role_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `tbluser_role` (
  `user_role_id` CHAR(38) NOT NULL ,
  `user_id` CHAR(38) NOT NULL ,
  `role_id` CHAR(38) NOT NULL ,
  `is_default` CHAR(1) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `created_by` CHAR(38) NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_by` CHAR(38) NULL DEFAULT NULL ,
  `is_deleted` CHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`user_role_id`, `user_id`, `role_id`) ,
  INDEX `fk_tblUserRole_tblUser1_idx` (`user_id` ASC) ,
  INDEX `fk_tblUserRole_tblRole1_idx` (`role_id` ASC) ,
  UNIQUE INDEX `un_tblUserRole` (`user_id` ASC, `role_id` ASC) ,
  CONSTRAINT `fk_tblUserRole_tblUser1`
    FOREIGN KEY (`user_id` )
    REFERENCES `tbluser` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblUserRole_tblRole1`
    FOREIGN KEY (`role_id` )
    REFERENCES `tblrole` (`role_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `tblpermission` (
  `permission_id` CHAR(38) NOT NULL ,
  `name` VARCHAR(50) NOT NULL ,
  `key_id` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(255) NULL DEFAULT NULL ,
  `is_inactive` CHAR(1) NULL DEFAULT NULL ,
  `created_date` DATETIME NOT NULL ,
  `created_by` CHAR(38) NULL DEFAULT NULL ,
  `last_modified_by` CHAR(38) NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `is_deleted` CHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`permission_id`) ,
  UNIQUE INDEX `key_id_UNIQUE` (`key_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `tblrole_permission` (
  `role_permission_id` CHAR(38) NOT NULL ,
  `permission_id` CHAR(38) NOT NULL ,
  `role_id` CHAR(38) NOT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `is_inactive` CHAR(1) NULL DEFAULT NULL ,
  `created_by` CHAR(38) NULL DEFAULT NULL ,
  `last_modified_by` CHAR(38) NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `is_deleted` CHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`role_permission_id`, `permission_id`, `role_id`) ,
  INDEX `fk_tblRolePermission_tblPermission1_idx` (`permission_id` ASC) ,
  INDEX `fk_tblRolePermission_tblRole1_idx` (`role_id` ASC) ,
  CONSTRAINT `fk_tblRolePermission_tblPermission1`
    FOREIGN KEY (`permission_id` )
    REFERENCES `tblpermission` (`permission_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblRolePermission_tblRole1`
    FOREIGN KEY (`role_id` )
    REFERENCES `tblrole` (`role_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


insert into tblrole values ('r1111', 'Solution Specialist', 'Embrace Solution Specialist', 'N', now(), 'system', now(), 'system', 'N');
insert into tblrole values ('r1112', 'ILM', 'Embrace ILM', 'N', now(), 'system', now(), 'system', 'N');
insert into tblrole values ('r1113', 'Asset Manager', 'Embrace Asset Manager', 'N', now(), 'system', now(), 'system', 'N');
insert into tblrole values ('r1114', 'Property Manager', 'Embrace  Property Manager', 'N', now(), 'system', now(), 'system', 'N');
insert into tblrole values ('r1115', 'Administrator', 'Administrator', 'N', now(), 'system', now(), 'system', 'N');

insert into tblpermission values ('p11111', ' VendorManagement', 'VendorManagement', 'Embrace  Vendor Management','N', now(), 'system','system', now(), 'N' );
insert into tblpermission values ('p11112', ' VendorSearch', 'VendorSearch', 'Embrace  Vendor Management','N', now(), 'system','system', now(), 'N' );


insert into tblrole_permission values ('rp0001','p11111','r1115', now(), 'N', 'system', 'system', now(), 'N');
insert into tblrole_permission values ('rp0002','p11112','r1115', now(), 'N', 'system', 'system', now(), 'N');

insert into tblrole_permission values ('rp0003','p11111','r1113', now(), 'N', 'system', 'system', now(), 'N');
insert into tblrole_permission values ('rp0004','p11112','r1113', now(), 'N', 'system', 'system', now(), 'N');

insert  into `tbluser`(`user_id`,`user_name`,`first_name`,`last_name`,`is_inactive`,`is_locked`,`password`,`created_date`,`last_login_date`,`last_modified_date`,`created_by`,`last_modified_by`,`is_deleted`) 
values ('sysusr1','admin','Embrace','Admin','N','N','$2a$10$kNaOXaLegCdBA6ShYaGeQueBA0hPJwqnQC9xtRTWFpI0tnLy8OS2a','2014-10-10 10:10:05',NULL,NULL,NULL,NULL,'N');

insert  into `tbluser`(`user_id`,`user_name`,`first_name`,`last_name`,`is_inactive`,`is_locked`,`password`,`created_date`,`last_login_date`,`last_modified_date`,`created_by`,`last_modified_by`,`is_deleted`) 
values ('sysusr2','assetmanager','Asset','Manager','N','N','$2a$10$kNaOXaLegCdBA6ShYaGeQueBA0hPJwqnQC9xtRTWFpI0tnLy8OS2a','2014-10-10 10:10:05',NULL,NULL,NULL,NULL,'N');


insert  into `tbluser_role`(`user_role_id`,`user_id`,`role_id`,`is_default`,`created_date`,`created_by`,`last_modified_date`,`last_modified_by`,`is_deleted`) 
values ('usrr1','sysusr1','r1115','N','2014-10-10 10:10:10','system','2014-10-10 10:10:10','system','N');

insert  into `tbluser_role`(`user_role_id`,`user_id`,`role_id`,`is_default`,`created_date`,`created_by`,`last_modified_date`,`last_modified_by`,`is_deleted`) 
values ('usrr2','sysusr2','r1113','N','2014-10-10 10:10:10','system','2014-10-10 10:10:10','system','N');