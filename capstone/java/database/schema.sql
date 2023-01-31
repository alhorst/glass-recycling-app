BEGIN TRANSACTION;

--DROP TABLE IF EXISTS routes;
DROP TABLE IF EXISTS recycle_records;
DROP TABLE IF EXISTS pickup_information;
DROP TABLE IF EXISTS user_information;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
    is_driver boolean DEFAULT false,
	CONSTRAINT PK_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

CREATE TABLE user_information (
    -- current PK is username, is it better practice to have a Serial id for this table? as PK
    -- account_id here as pk?
	username varchar(50) NOT NULL,
	--full_name or first_name & last_name? What works best with our form data?
	full_name varchar(100) NOT NULL,
	street_address varchar(100) NOT NULL, -- 3001 Railroad St
	city varchar(50) NOT NULL, -- Pittsburgh
	state_abbreviation varchar(2) NOT NULL, -- PA
	zipcode varchar(5) NOT NULL, -- 15201
	phone_number varchar(11), -- example: 7032978845 (country code '1' optional)
	email_address varchar(100),
	CONSTRAINT PK_user_information PRIMARY KEY (username),
	CONSTRAINT FK_user_information_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE recycle_records (
	account_id SERIAL, -- added serial account_id as PK for records table
	username varchar(50) NOT NULL,
	total_pounds_recycled int DEFAULT 0,
	credit_points int DEFAULT 0,
	points_redeemed int DEFAULT 0,
	CONSTRAINT PK_recycle_records PRIMARY KEY (account_id),
	CONSTRAINT FK_recycle_records_user_information FOREIGN KEY (username) REFERENCES user_information (username)
);

CREATE TABLE pickup_information (
	pickup_id SERIAL,
	driver_id int,
	route_id int,
	username varchar(50) NOT NULL,
	place_id varchar(200) NOT NULL, -- for Route API use, is varchar(200) workable?
	pickup_date date NOT NULL,
	-- pickup_time time -- optional: unless we need time for scheduling, instead of just date (route is organized by efficiency)
	--Another option would be: pickup_date_time TIMESTAMP (NOT NULL), What works best? as the data will be coming from the schedule pickup form*/
	pickup_weight int NOT NULL, -- full weight * num_of_bins
	num_of_bins int NOT NULL, -- limit number to 3 bins.
	isPickedUp boolean DEFAULT false,
	CONSTRAINT PK_pickup_information PRIMARY KEY (pickup_id),
    CONSTRAINT FK_pickup_information_users FOREIGN KEY (driver_id) REFERENCES users (user_id),
    CONSTRAINT FK_pickup_information_user_information FOREIGN KEY (username) REFERENCES user_information (username),
	CONSTRAINT chk_num_of_bins CHECK (num_of_bins <= 3)
);


-- Thinking consolidation of pickups per route and driver assigned. Reflecting pickups assigned to driver
--CREATE TABLE routes (
--    route_id SERIAL,
--    driver_id int,
--    pickup_id int
--    CONSTRAINT PK_routes PRIMARY KEY (route_id),
--    CONSTRAINT FK_routes_pickup_information FOREIGN KEY (driver_id) REFERENCES pickup_information (driver_id),
--);



COMMIT TRANSACTION;

