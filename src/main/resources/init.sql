DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS request;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS status;

CREATE TABLE status (
	id SERIAL PRIMARY KEY,
	name VARCHAR NOT NULL
);

CREATE TABLE role (
	id SERIAL PRIMARY KEY,
	name VARCHAR NOT NULL
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	name VARCHAR NOT NULL,
	email VARCHAR NOT NULL,
	login VARCHAR NOT NULL,
	password VARCHAR NOT NULL,
	role INT NOT NULL,
	FOREIGN KEY (role) REFERENCES role (id)
);

CREATE TABLE request (
	id SERIAL PRIMARY KEY,
	client INT NOT NULL,
	request_date DATE DEFAULT CURRENT_DATE,
	description TEXT,
	status INT NOT NULL,
	FOREIGN KEY (client) REFERENCES users (id),
	FOREIGN KEY (status) REFERENCES status (id)

);

CREATE TABLE service (
	id SERIAL PRIMARY KEY,
	request INT NOT NULL,
	master INT,
	FOREIGN KEY (request) REFERENCES request (id),
	FOREIGN KEY (master) REFERENCES users (id)
);

CREATE TABLE comments (
	id SERIAL PRIMARY KEY,
	service INT NOT NULL,
	text TEXT,
	FOREIGN KEY (service) REFERENCES service (id)
);

INSERT INTO status(name) VALUES
('Opened'),
('In process'),
('Closed');

INSERT INTO role(name) VALUES
('Admin'),
('Master'),
('Client');

INSERT INTO users(name, email, login, password, role) VALUES
('Grusha Grusha', 'grushablsja@email.com', 'grushav', 'p_1234321', 1),
('Sergej Baranov', 'baranov.43@gmail.com', 'sergge43', '300bucks$', 2),
('Daria Tarasova', 'tarasova.alien@yandex.ru', 'cornstar', 'moy_parol69', 3),
('Ilia Steinberg', 'ilia.stein@gmail.com', 'ilusha', 'pass34&pass', 3);

INSERT INTO request(client, description, status) VALUES
(3, 'u menya vse slomalos, pamagiti', 1),
(4, 'set router', 2);

INSERT INTO service(request, master) VALUES
(1, 1),
(2, 2);

INSERT INTO comments(service, text) VALUES
(1, 'vse pochinili, zaebumba'),
(2, 'nifiga ne poluchilos');

