
USE [$(DatabaseName)];

INSERT INTO Products (Name, Description, Price, Image) 
 SELECT 'Big Moby', 'Moby', 25, BulkColumn FROM OPENROWSET(BULK 'C:\init\SeedData\images\moby_art.png', SINGLE_BLOB) rs;

INSERT INTO Products (Name, Description, Price, Image) 
 SELECT 'On the dock', 'Working together', 25, BulkColumn FROM OPENROWSET(BULK 'C:\init\SeedData\images\on_the_dock.jpg', SINGLE_BLOB) rs;

INSERT INTO Products (Name, Description, Price, Image) 
 SELECT 'Compose', 'Compose', 25, BulkColumn FROM OPENROWSET(BULK 'C:\init\SeedData\images\Compose.png', SINGLE_BLOB) rs;

INSERT INTO Products (Name, Description, Price, Image) 
 SELECT 'Gordon', 'Gordon the Turtle', 25, BulkColumn FROM OPENROWSET(BULK 'C:\init\SeedData\images\gordon.png', SINGLE_BLOB) rs;

INSERT INTO Products (Name, Description, Price, Image) 
 SELECT 'Containerd', 'Containerd for the people', 25, BulkColumn FROM OPENROWSET(BULK 'C:\init\SeedData\images\containerd.png', SINGLE_BLOB) rs;

INSERT INTO Products (Name, Description, Price, Image) 
 SELECT 'Registry', 'Where to put your containers', 25, BulkColumn FROM OPENROWSET(BULK 'C:\init\SeedData\images\Registry.png', SINGLE_BLOB) rs;

INSERT INTO Products (Name, Description, Price, Image) 
 SELECT 'DockerMachine', 'Working like a well oiled machine', 25, BulkColumn FROM OPENROWSET(BULK 'C:\init\SeedData\images\Docker_machine.png', SINGLE_BLOB) rs;

INSERT INTO Products (Name, Description, Price, Image) 
 SELECT 'Swarm', 'Orchestrating work loads', 25, BulkColumn FROM OPENROWSET(BULK 'C:\init\SeedData\images\swarm.png', SINGLE_BLOB) rs;

INSERT INTO Products (Name, Description, Price, Image) 
 SELECT 'Trusted Registry', 'Keeping it safe and secure', 25, BulkColumn FROM OPENROWSET(BULK 'C:\init\SeedData\images\trusted_registry.jpg', SINGLE_BLOB) rs;