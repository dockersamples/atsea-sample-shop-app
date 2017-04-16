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
INSERT INTO product (name, description, image, price) VALUES ('Work Life Balance', '', '/images/work_life_balance.png', 19.99);
INSERT INTO product (name, description, image, price) VALUES ('Teamwork', '', '/images/teamwork.png', 19.99);
INSERT INTO product (name, description, image, price) VALUES ('Synergy', '', '/images/synergy.png', 19.99);
INSERT INTO product (name, description, image, price) VALUES ('Integrate', '', '/images/integrate.png', 19.99);
INSERT INTO product (name, description, image, price) VALUES ('Growth', '', '/images/growth.png', 19.99);
INSERT INTO product (name, description, image, price) VALUES ('Enterprise', '', '/images/enterprise.png', 19.99);
INSERT INTO product (name, description, image, price) VALUES ('Empowerment', '', '/images/empowerment.png', 19.99);
INSERT INTO product (name, description, image, price) VALUES ('Collaborate', '', '/images/collaborate.png', 19.99);

