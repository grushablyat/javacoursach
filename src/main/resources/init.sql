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
	status INT NOT NULL DEFAULT 1,
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
	comment_date DATE DEFAULT CURRENT_DATE,
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
('Grigorij Tselousov', 'grusha@email.com', 'grushav', '6b5856cdc03e4b4fd67c58053b50b0c5f01cac7d3f796f8c2d38aa23c9c601ea', 1),
('Sergej Baranov', 'baranov.43@gmail.com', 'sergge43', '220a8149ca4581626893cdec7b04190c5c100a5d979c5e7f35021d33e1153df9', 2),
('Daria Tarasova', 'tarasova.alien@yandex.ru', 'cornstar', 'c685670a70d4af976c9507f0686683657ac402705519cb7efd04ef49be2c3599', 3),
('Semen Smirnov', 'semen.smi@gmail.com', 'semen300', '220a8149ca4581626893cdec7b04190c5c100a5d979c5e7f35021d33e1153df9', 2),
('Ilia Steinberg', 'ilia.stein@gmail.com', 'ilusha', 'c685670a70d4af976c9507f0686683657ac402705519cb7efd04ef49be2c3599', 3);

INSERT INTO request(client, description, status, request_date) VALUES
(3, 'u menya vse slomalos, pamagiti', 3, '2023-12-14'),
(5, 'set up my router', 2, '2023-12-15'),
(3, 'uronili polku na nout', 2, '2023-12-16'),
(5, 'computer crushed', 2, '2023-12-17'),
(3, 'phone drowned', 1, '2023-12-18'),
(5, 'set up VPN', 1, '2023-12-20');

INSERT INTO service(request, master) VALUES
(1, 2),
(2, 4),
(3, 2),
(4, 4),
(3, 4);

INSERT INTO comments(service, text, comment_date) VALUES
(1, 'vse pochinil', '2023-12-15'),
(2, 'Patch cord RJ-45 bought', '2023-12-15'),
(3, 'Need to change matrix', '2023-12-16'),
(4, 'Motherboard burned', '2023-12-17'),
(3, 'Matrix changed, new touchpad and keyboard required', '2023-12-18'),
(5, 'New touchpad set', '2023-12-19');

