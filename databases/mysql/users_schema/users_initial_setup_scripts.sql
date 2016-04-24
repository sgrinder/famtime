-- AGRINDER
-- 4/21/2016

CREATE USER 'famtime'@'localhost' IDENTIFIED BY 'ER@SER321';

-- REVOKING
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'famtime'@'localhost';

-- GRANTING

-- REQUIRED FOR HIBERNATE ACCESS
GRANT ALL ON hibernate_sequence TO 'famtime'@'localhost';
GRANT ALL ON accounts TO 'famtime'@'localhost';
GRANT ALL ON roles TO 'famtime'@'localhost';

-- STORED PROCEDURE GRANTS
GRANT EXECUTE ON PROCEDURE users_development.validateUserNameAndPassword TO 'famtime'@'localhost';

-- TABLES
CREATE TABLE `users_development`.`accounts` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `account_name` VARCHAR(18) NOT NULL COMMENT 'account names will not be displayed in the user interface (UI)',
  `password` CHAR(32) NOT NULL,
  `status_id` INT NOT NULL DEFAULT 0,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`account_id`),
  UNIQUE INDEX `account_name_UNIQUE` (`account_name` ASC),
  UNIQUE INDEX `account_id_UNIQUE` (`account_id` ASC),
  FULLTEXT INDEX `account_password` (`password` ASC))
COMMENT = 'Holds user accounts for users of the application.';

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(22) NOT NULL,
  `status_id` int(11) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id_UNIQUE` (`role_id`),
  FULLTEXT KEY `description_SEARCH` (`description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- TRIGGERS

CREATE DEFINER = CURRENT_USER TRIGGER `users_development`.`accounts_BEFORE_INSERT` BEFORE INSERT ON `accounts` FOR EACH ROW
BEGIN
	SET NEW.password = MD5(NEW.password);
END


CREATE DEFINER = CURRENT_USER TRIGGER 'users_development'.'accounts_BEFORE_INSERT_DATES' BEFORE INSERT ON 'accounts' FOR EACH ROW
BEGIN
	SET NEW.created_date = CURRENT_TIMESTAMP;
    SET NEW.modified_date = CURRENT_TIMESTAMP;
END


-- STORED PROCEDURE CONTENT

CREATE DEFINER=`root`@`localhost` PROCEDURE `validateUserNameAndPassword`(
IN inUserName VARCHAR(18),
IN inPassword VARCHAR(32),
OUT outAnswer INT
)
BEGIN

	SELECT
		COUNT(*) INTO outAnswer
	FROM
		accounts
	WHERE
		account_name = inAccountName
        AND password = MD5(inPassword)
	;

    SELECT outAnswer;
END