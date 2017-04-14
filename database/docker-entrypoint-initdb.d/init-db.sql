-- CREATE DATABASE atsea
--   WITH OWNER = gordonuser
--        ENCODING = 'UTF8'
--        TABLESPACE = pg_default
--        LC_COLLATE = 'en_US.utf8'
--        LC_CTYPE = 'en_US.utf8'
--        CONNECTION LIMIT = -1;
-- GRANT CONNECT, TEMPORARY ON DATABASE atsea TO public;
-- GRANT ALL ON DATABASE atsea TO gordonuser;


-- CONNECT `atsea`;

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

-- CREATE TABLE customer
-- (
--   customerid serial UNIQUE PRIMARY KEY,
--   address character varying(512) NOT NULL,
--   email character varying(128) NOT NULL,
--   name character varying(255) NOT NULL,
--   password character varying(255) NOT NULL,
--   phone character varying(32) NOT NULL,
--   username character varying(255) NOT NULL,
--   enabled boolean DEFAUTL true,
--   role character varying(20) DEFAULT 'USER'
-- );


-- ALTER TABLE customer
--   OWNER TO gordonuser;


--  CREATE TABLE order
--  (
--    orderid bigint PRIMARY KEY,
--    orderdate date NOT NULL,
--    ordernum integer NOT NULL,
--    productid integer,
--    FOREIGN KEY (customerid) REFERENCES customer(customerid)
--  );

--  ALTER TABLE order
--    OWNER TO gordonuser;


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

-- reset lo ownership of lo (images) to gordonuser
do $$
declare r record;
begin
for r in select loid from pg_catalog.pg_largeobject loop
execute 'ALTER LARGE OBJECT ' || r.loid || ' OWNER TO gordonuser';
end loop;
end$$;
