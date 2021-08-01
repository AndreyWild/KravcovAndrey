-- -----------------------------------------------------
-- Filling Guests
-- -----------------------------------------------------
INSERT INTO hotel.guest (name, age) VALUES
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
INSERT INTO hotel.room (number, capacity, price, stars) VALUES
(101, 1, 250.0, 'ONE'),
(102, 2, 200.0, 'FOUR'),
(103, 3, 150.0, 'THREE'),
(104, 4, 100.0, 'TWO'),
(105, 1, 350.0, 'FIVE'),
(106, 2, 300.0, 'FOUR'),
(107, 3, 250.0, 'THREE'),
(108, 4, 200.0, 'TWO'),
(109, 1, 500.0, 'FIVE'),
(110, 2, 450.0, 'FOUR');

-- -----------------------------------------------------
-- Filling Maintenances
-- -----------------------------------------------------
INSERT INTO hotel.maintenance (name, price) VALUES
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