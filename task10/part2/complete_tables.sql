-- -----------------------------------------------------
-- Filling Guests
-- -----------------------------------------------------
INSERT INTO Hotel.guest (name, age) VALUES
('Eddard Stark',50),
('Catelyn Stark',46),
('Robb Stark',26),
('Sansa Stark',20),
('Arya Stark',17),
('Bran Stark',16),
('Rickon Stark',10),
('Jon Snow',25),
('Benjen Stark',64),
('Lyanna Stark', 52);

-- -----------------------------------------------------
-- Filling Rooms
-- -----------------------------------------------------
INSERT INTO Hotel.room (number, capacity, price, numberOfStars) VALUES
(101, 1, 250.0, 5),
(102, 2, 200.0, 4),
(103, 3, 150.0, 3),
(104, 4, 100.0, 2),
(105, 1, 350.0, 5),
(106, 2, 300.0, 4),
(107, 3, 250.0, 3),
(108, 4, 200.0, 2),
(109, 1, 500.0, 5),
(110, 2, 450.0, 4);

-- -----------------------------------------------------
-- Filling Maintenances
-- -----------------------------------------------------
INSERT INTO Hotel.maintenance (name, price) VALUES
('Room cleaning',50.0),
('Cleaning clothes',10.0),
('Shoe shine',5.0),
('Food ordering',75.0),
('Call order',3.0),
('Intercity call order',10.0),
('Cable TV order',30.0),
('Pay channel order',3.0),
('Morning Wake Order',2.0),
('Mini bar', 120.0);



