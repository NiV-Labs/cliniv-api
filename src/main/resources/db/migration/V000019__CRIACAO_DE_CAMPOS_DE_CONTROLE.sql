ALTER TABLE `PRESCRICAO`
	ADD COLUMN `PRESCRICAO_ESPECIAL` VARCHAR(1) NULL DEFAULT 'N' AFTER `DATA_FIM`,
	ADD COLUMN `INSERE_EVENTO` VARCHAR(1) NULL DEFAULT 'N' AFTER `PRESCRICAO_ESPECIAL`;
