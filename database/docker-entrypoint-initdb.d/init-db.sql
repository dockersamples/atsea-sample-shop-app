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

ALTER ROLE gordonuser CONNECTION LIMIT -1;

-- add product data
-- note: images are pulled from the public folder at atsea/app/react-app/public
INSERT INTO product (name, description, image, price) VALUES ('Unusable Security', 'Unusuable security is not security', '/images/1.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Valentine''s Day', 'Love is meant to be shared', '/images/2.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Docker Tooling', 'Docker provides a whole suite of tools', '/images/3.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Docker Presents', 'Giving gifts every day', '/images/4.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Valentine''s Day', 'Love is in the air', '/images/5.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Docker Babies', 'For those with a cute little whale', '/images/6.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Experimental', 'Trying the latest', '/images/7.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('Docker for Developers', 'Escape the App Dependency Matrix', '/images/8.png', 25);
INSERT INTO product (name, description, image, price) VALUES ('DockerCon Copenhagen', 'DockerCon returns to Europe', '/images/9.png', 25);


