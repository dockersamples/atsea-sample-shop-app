-- create table for product

CREATE TABLE product
(
  productid serial UNIQUE PRIMARY KEY,
  description character varying(10485760) NOT NULL,
  image character varying(255) NOT NULL,
  name character varying(255) NOT NULL,
  price double precision NOT NULL
);

ALTER TABLE product
  OWNER TO gordonuser;


-- add product data
-- note: images are pulled from the public folder at atsea/app/react-app/public
INSERT INTO product (name, description, image, price) VALUES ('Big Moby', 'Moby', '/images/1.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('On the dock', 'Working together', '/images/2.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Compose', 'Compose', '/images/3.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Gordon', 'Gordon the Turle', '/images/4.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Containerd', 'Containerd for the people', '/images/5.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Registry', 'Where to put your containers', '/images/6.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('DockerMachine', 'Working like a well oiled machine', '/images/7.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Swarm', 'Orchestrating work loads', '/images/8.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Trusted Registry', 'Keeping it safe and secure', '/images/9.png', 25);


