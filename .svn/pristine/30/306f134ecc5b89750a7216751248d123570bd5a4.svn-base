SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `db_sistema_rlf` DEFAULT CHARACTER SET utf8 ;
USE `db_sistema_rlf` ;

-- -----------------------------------------------------
-- Table `db_sistema_rlf`.`caracteristica`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_sistema_rlf`.`caracteristica` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(255) NULL DEFAULT NULL ,
  `pai_codigo` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`codigo`) ,
  INDEX `FK_b9534d220c5f48f09a43b2ff7f5` (`pai_codigo` ASC) ,
  CONSTRAINT `FK_b9534d220c5f48f09a43b2ff7f5`
    FOREIGN KEY (`pai_codigo` )
    REFERENCES `db_sistema_rlf`.`caracteristica` (`codigo` ))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_sistema_rlf`.`produto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_sistema_rlf`.`produto` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT ,
  `dataInclusao` DATETIME NULL DEFAULT NULL ,
  `descricao` VARCHAR(255) NULL DEFAULT NULL ,
  `valorCusto` DOUBLE NULL DEFAULT NULL ,
  `valorVenda` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`codigo`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_sistema_rlf`.`caracteristica_produto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_sistema_rlf`.`caracteristica_produto` (
  `produto` INT(11) NOT NULL ,
  `caracteristica` INT(11) NOT NULL ,
  INDEX `FK_76fe2682fc5643719bc124f276b` (`caracteristica` ASC) ,
  INDEX `FK_daa98428d637484087d6ff03324` (`produto` ASC) ,
  CONSTRAINT `FK_daa98428d637484087d6ff03324`
    FOREIGN KEY (`produto` )
    REFERENCES `db_sistema_rlf`.`produto` (`codigo` ),
  CONSTRAINT `FK_76fe2682fc5643719bc124f276b`
    FOREIGN KEY (`caracteristica` )
    REFERENCES `db_sistema_rlf`.`caracteristica` (`codigo` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_sistema_rlf`.`cliente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_sistema_rlf`.`cliente` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT ,
  `cpfCnpj` VARCHAR(255) NULL DEFAULT NULL ,
  `ehPJ` TINYINT(1) NULL DEFAULT NULL ,
  `email` VARCHAR(255) NULL DEFAULT NULL ,
  `nomeContato` VARCHAR(255) NULL DEFAULT NULL ,
  `nomeRazaoSocial` VARCHAR(255) NULL DEFAULT NULL ,
  `telefone` VARCHAR(255) NULL DEFAULT NULL ,
  `telefoneAlternativo` VARCHAR(255) NULL DEFAULT NULL ,
  `telefoneCelular` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`codigo`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_sistema_rlf`.`pedido`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_sistema_rlf`.`pedido` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT ,
  `dataFechamento` DATETIME NULL DEFAULT NULL ,
  `dataInclusao` DATETIME NULL DEFAULT NULL ,
  `situacao` CHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`codigo`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_sistema_rlf`.`item`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_sistema_rlf`.`item` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT ,
  `quantidade` INT(11) NULL DEFAULT NULL ,
  `situacao` CHAR(1) NULL DEFAULT NULL ,
  `valor` DOUBLE NULL DEFAULT NULL ,
  `cor_codigo` INT(11) NULL DEFAULT NULL ,
  `pedido_codigo` INT(11) NULL DEFAULT NULL ,
  `produto_codigo` INT(11) NULL DEFAULT NULL ,
  `tamanho_codigo` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`codigo`) ,
  INDEX `FK_ca2c586ef99b46d9b9809b789d2` (`cor_codigo` ASC) ,
  INDEX `FK_956d0dfabf2c478e982c31459b3` (`pedido_codigo` ASC) ,
  INDEX `FK_9d8c162fd3a24646ac3bec63622` (`produto_codigo` ASC) ,
  INDEX `FK_04ca47a046f84127a706606a9ab` (`tamanho_codigo` ASC) ,
  CONSTRAINT `FK_04ca47a046f84127a706606a9ab`
    FOREIGN KEY (`tamanho_codigo` )
    REFERENCES `db_sistema_rlf`.`caracteristica` (`codigo` ),
  CONSTRAINT `FK_956d0dfabf2c478e982c31459b3`
    FOREIGN KEY (`pedido_codigo` )
    REFERENCES `db_sistema_rlf`.`pedido` (`codigo` ),
  CONSTRAINT `FK_9d8c162fd3a24646ac3bec63622`
    FOREIGN KEY (`produto_codigo` )
    REFERENCES `db_sistema_rlf`.`produto` (`codigo` ),
  CONSTRAINT `FK_ca2c586ef99b46d9b9809b789d2`
    FOREIGN KEY (`cor_codigo` )
    REFERENCES `db_sistema_rlf`.`caracteristica` (`codigo` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_sistema_rlf`.`ordemproducao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_sistema_rlf`.`ordemproducao` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT ,
  `dataFechamento` DATETIME NULL DEFAULT NULL ,
  `dataInclusao` DATETIME NULL DEFAULT NULL ,
  `situacao` CHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`codigo`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_sistema_rlf`.`item_ordemproducao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_sistema_rlf`.`item_ordemproducao` (
  `item` INT(11) NOT NULL ,
  `ordem_producao` INT(11) NOT NULL ,
  UNIQUE INDEX `UK_d6649770f90f46c5bbd37043269` (`ordem_producao` ASC) ,
  UNIQUE INDEX `UK_d1ad345d26bd4e1795cfcb4f224` (`ordem_producao` ASC) ,
  UNIQUE INDEX `UK_e5ab20148e3f4177879b00d4e18` (`ordem_producao` ASC) ,
  UNIQUE INDEX `UK_bbb186db96d7433b80ec3da3065` (`ordem_producao` ASC) ,
  INDEX `FK_2fee6fd98cc54dd1ba0284c7d7b` (`ordem_producao` ASC) ,
  INDEX `FK_154c73a96774442985e0441fe92` (`item` ASC) ,
  CONSTRAINT `FK_154c73a96774442985e0441fe92`
    FOREIGN KEY (`item` )
    REFERENCES `db_sistema_rlf`.`ordemproducao` (`codigo` ),
  CONSTRAINT `FK_2fee6fd98cc54dd1ba0284c7d7b`
    FOREIGN KEY (`ordem_producao` )
    REFERENCES `db_sistema_rlf`.`item` (`codigo` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_sistema_rlf`.`pedido_cliente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_sistema_rlf`.`pedido_cliente` (
  `cliente` INT(11) NULL DEFAULT NULL ,
  `pedido` INT(11) NOT NULL ,
  PRIMARY KEY (`pedido`) ,
  INDEX `FK_7c2047526ba444fa9fc2f988303` (`cliente` ASC) ,
  INDEX `FK_46a4ab996fa449e2ab51bbb0faf` (`pedido` ASC) ,
  CONSTRAINT `FK_46a4ab996fa449e2ab51bbb0faf`
    FOREIGN KEY (`pedido` )
    REFERENCES `db_sistema_rlf`.`pedido` (`codigo` ),
  CONSTRAINT `FK_7c2047526ba444fa9fc2f988303`
    FOREIGN KEY (`cliente` )
    REFERENCES `db_sistema_rlf`.`cliente` (`codigo` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `db_sistema_rlf` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
