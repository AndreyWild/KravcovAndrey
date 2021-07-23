-- -----------------------------------------------------
-- Inserting data into the Product table
-- ----------------------------------------------------- 
INSERT INTO SENLA_task10.product (maker, model, type) VALUES
('ASUS', 'ZenBook UX', 'Laptop'),
('ASUS','Vivo AIO','PC'),
('ASUS','Zen AIO','PC'),
('ASUS', 'VivoBook 15', 'Laptop'),
('OKI','MP-98','Printer'),
('Xiaomi', 'Mi Book Air', 'Laptop'),
('Xiaomi', 'Mi Book Pro', 'Laptop'),
('Apple','IMac 27','PC'),
('Apple','IMac Pro','PC'),
('Apple','IMac 21','PC'),
('Apple', 'MacBook Air 11', 'Laptop'),
('Apple', 'MacBook Air 13', 'Laptop'),
('MSI','Gaming 24','PC'),
('Brother','HL-1210','Printer'),
('Canon','PIXMA','Printer'),
('Lenovo','IdeaCentre 520','PC'),
('HP','Laserjet','Printer'),
('HP','Deskjet','Printer'),
('HP','Laserjet Color','Printer'),
('HP','ProOne','PC'),
('HP', 'Pavilion', 'Laptop'),
('HP', 'ProBook 4320', 'Laptop');

-- -----------------------------------------------------
-- Inserting data into the Laptop table
-- ----------------------------------------------------- 
INSERT INTO SENLA_task10.laptop (code, model, speed, ram, hd, screen, price) VALUES
(101,'MacBook Air 11',1800,4096,500,11,800),
(102,'MacBook Air 13',1800,8192,128,13,950),
(103,'ProBook 4320',1600,3072,1000,17,400),
(104,'Pavilion',2500,8192,1000,15,750),
(105,'VivoBook 15',2500,4096,500,15,650),
(106,'ZenBook UX',2500,8192,256,15,1240),
(107,'Mi Book Pro',1600,8192,256,15,1150),
(108,'Mi Book Air',2300,8192,256,13,275);

-- -----------------------------------------------------
-- Inserting data into the PC table
-- ----------------------------------------------------- 
INSERT INTO SENLA_task10.pc (code, model, speed, ram, hd, cd, price) VALUES
(201,'IMac 27',3800,8192,2000,'48x',2700),
(202,'Zen AIO',2400,8190,2000,'12x',1200),
(203,'IMac 21',2600,8192,1000,'24x',1000),
(204,'IdeaCentre 520',3400,4090,1000,'24x',750),
(205,'Vivo AIO',2500,4090,1000,'8x',650),
(206,'ProOne',3900,4090,128,'12x',490),
(207,'Gaming 24',2500,8190,256,'4x',375),
(208,'IMac Pro',3600,8190,1000,'48x',6300);

-- -----------------------------------------------------
-- Inserting data into the Printer table
-- ----------------------------------------------------- 
INSERT INTO SENLA_task10.printer (code, model, color, type, price) VALUES
(302,'PIXMA','y','Jet',187),
(303,'MP-98','n','Matrix',20),
(304,'Laserjet','n','Laser',120),
(301,'HL-1210','n','Laser',95),
(306,'Laserjet Color','y','Laser',350),
(305,'Deskjet','n','Jet',90);