CREATE TABLE `ENDERECO_PF` (
  `ID` BIGINT NOT NULL,
  `ID_PESSOA` BIGINT NOT NULL,
  `LOGRADOURO` VARCHAR(200) NOT NULL,
  `NUMERO` VARCHAR(10) NOT NULL,
  `COMPLEMENTO` VARCHAR(50) NULL,
  `CODIGO_POSTAL` VARCHAR(8) NULL,
  `ESTADO` VARCHAR(2) NOT NULL,
  `BAIRRO` VARCHAR(100) NOT NULL,
  `CIDADE` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_ENDERECO_PESSOA_idx` (`ID_PESSOA` ASC),
  CONSTRAINT `FK_ENDERECO_PESSOA`
    FOREIGN KEY (`ID_PESSOA`)
    REFERENCES `PESSOA_FISICA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
