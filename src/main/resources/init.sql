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
('Grusha Grusha', 'grushablsja@email.com', 'grushav', 'aded4c27d439580a396842e466e7716e2a655a6c9ee745bfc414b40cae91cb84', 1),
('Sergej Baranov', 'baranov.43@gmail.com', 'sergge43', 'df1d4b95e3a1695598563d1a5372a166e5d56370abbee6d15c7681745e67fc58', 2),
('Daria Tarasova', 'tarasova.alien@yandex.ru', 'cornstar', '9b854b069aee5852c9c396667bf119d94ce61103ed373cd70360c66d02e41391', 3),
('Semen Smirnov', 'semen.smi@gmail.com', 'semen300', '88026fb4001e0be84e231f9e2612d117a2f148c7ba16fdb1da34e11b4802453a', 2),
('Ilia Steinberg', 'ilia.stein@gmail.com', 'ilusha', '7549aaf0082b79a6f1eb694ebc494b95be06b57753358b302b208daf94f23532', 3);

INSERT INTO request(client, description, status) VALUES
(3, 'u menya vse slomalos, pamagiti', 3),
(5, 'set router', 2),
(3, 'computer crushed', 1);

INSERT INTO service(request, master) VALUES
(1, 1),
(2, 2);

INSERT INTO comments(service, text) VALUES
(1, 'vse pochinili, zaebumba'),
(2, 'nifiga ne poluchilos');

