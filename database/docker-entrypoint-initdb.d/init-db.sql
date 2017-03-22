-- CREATE DATABASE mobystore
--   WITH OWNER = gordonuser
--        ENCODING = 'UTF8'
--        TABLESPACE = pg_default
--        LC_COLLATE = 'en_US.utf8'
--        LC_CTYPE = 'en_US.utf8'
--        CONNECTION LIMIT = -1;
-- GRANT CONNECT, TEMPORARY ON DATABASE mobystore TO public;
-- GRANT ALL ON DATABASE mobystore TO gordonuser;


-- CONNECT `mobystore`;

CREATE TABLE product
(
  productid serial UNIQUE PRIMARY KEY,
  description character varying(10485760) NOT NULL,
  image oid,
  name character varying(255) NOT NULL,
  price double precision NOT NULL
);

ALTER TABLE product
  OWNER TO gordonuser;

CREATE TABLE customer
(
  customerid serial UNIQUE PRIMARY KEY,
  address character varying(512) NOT NULL,
  email character varying(128) NOT NULL,
  name character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  phone character varying(32) NOT NULL,
  username character varying(255) NOT NULL,
  enabled boolean DEFAUTL true,
  role character varying(20) DEFAULT 'USER'
);


ALTER TABLE customer
  OWNER TO gordonuser;


 CREATE TABLE order
 (
   orderid bigint PRIMARY KEY,
   orderdate date NOT NULL,
   ordernum integer NOT NULL,
   productid integer,
   FOREIGN KEY (customerid) REFERENCES customer(customerid)
 );

 ALTER TABLE order
   OWNER TO gordonuser;


-- add product data
INSERT INTO product (name, description, image, price) VALUES ('Big Moby', 'Moby', lo_import('/images/moby_art.png'), 25);
INSERT INTO product (name, description, image, price) VALUES ('On the dock', 'Working together', lo_import('/images/on_the_dock.jpg'), 25);
INSERT INTO product (name, description, image, price) VALUES ('Compose', 'Compose', lo_import('/images/Compose.png'), 25);
INSERT INTO product (name, description, image, price) VALUES ('Gordon', 'Gordon the Turle', lo_import('/images/gordon.png'), 25);
INSERT INTO product (name, description, image, price) VALUES ('Containerd', 'Containerd for the people', lo_import('/images/containerd.png'), 25);
INSERT INTO product (name, description, image, price) VALUES ('Registry', 'Where to put your containers', lo_import('/images/Registry.png'), 25);
INSERT INTO product (name, description, image, price) VALUES ('DockerMachine', 'Working like a well oiled machine', lo_import('/images/Docker_machine.png'), 25);
INSERT INTO product (name, description, image, price) VALUES ('Swarm', 'Orchestrating work loads', lo_import('/images/swarm.png'), 25);
INSERT INTO product (name, description, image, price) VALUES ('Trusted Registry', 'Keeping it safe and secure', lo_import('/images/trusted_registry.jpg'), 25);


-- reset lo ownership of lo (images) to gordonuser
do $$
declare r record;
begin
for r in select loid from pg_catalog.pg_largeobject loop
execute 'ALTER LARGE OBJECT ' || r.loid || ' OWNER TO gordonuser';
end loop;
end$$;
