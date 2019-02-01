DROP TABLE orders IF EXISTS;
DROP TABLE mechanics IF EXISTS;
DROP TABLE clients IF EXISTS;

CREATE TABLE mechanics (
	mechanicID BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY, 
	firstName VARCHAR(20), 
	lastName VARCHAR(30), 
	patronymic VARCHAR(20), 
	hourlyRate VARCHAR(20));

CREATE TABLE clients (
	clientID BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY, 
	firstName VARCHAR(20), 
	lastName VARCHAR(30), 
	patronymic VARCHAR(20), 
	phoneNumber VARCHAR(16));

CREATE TABLE orders (
	orderID BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY, 
	clientID BIGINT,
	mechanicID BIGINT,
	description VARCHAR(1000),  
	dateStart DATE, 
	dateEnd DATE, 
	cost VARCHAR(20), 
	status INT CHECK (status>=0 AND status <=2),
	FOREIGN KEY (clientID) REFERENCES clients(clientID) ON DELETE RESTRICT, 
	FOREIGN KEY (mechanicID) REFERENCES mechanics(mechanicID) ON DELETE RESTRICT);

INSERT INTO clients (firstName, lastName, patronymic, phoneNumber) VALUES (
	'Иванов0', 'Иван0', 'Иванович0', '88000000000');
INSERT INTO clients (firstName, lastName, patronymic, phoneNumber) VALUES (
	'Иванов1', 'Иван1', 'Иванович1', '88001111111');
INSERT INTO clients (firstName, lastName, patronymic, phoneNumber) VALUES (
	'Иванов2', 'Иван2', 'Иванович2', '88002222222');

INSERT INTO mechanics (firstName, lastName, patronymic, hourlyRate) VALUES (
	'Петр0', 'Петров0', 'Петрович0', '111.11');
INSERT INTO mechanics (firstName, lastName, patronymic, hourlyRate) VALUES (
	'Петр1', 'Петров1', 'Петрович1', '222.22');
INSERT INTO mechanics (firstName, lastName, patronymic, hourlyRate) VALUES (
	'Петр2', 'Петров2', 'Петрович2', '333.33');
COMMIT;

INSERT INTO orders (clientID, mechanicID, description, dateStart, dateEnd, cost, status) VALUES (
	1, 1, 'Тестовое описание 1', '2019-01-22', '2019-01-23', '5555.55', 1);
INSERT INTO orders (clientID, mechanicID, description, dateStart, dateEnd, cost, status) VALUES (
	2, 2, 'Тестовое описание 2', '2019-01-23', '2019-01-24', '5555.55', 1);