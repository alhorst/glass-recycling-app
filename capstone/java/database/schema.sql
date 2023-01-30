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
	username varchar(50) NOT NULL,
	--full_name or first_name & last_name? What works best with our form data?
	full_name varchar(100) NOT NULL,
	street_address varchar(100) NOT NULL,
	city varchar(50) NOT NULL,
	/* full state name or state abbreviation*/
	state_name varchar(15) NOT NULL,
	zipcode char(15) NOT NULL,
	phone_number char(10), -- example: 7032978845
	email_address varchar(100),
	CONSTRAINT PK_user_information PRIMARY KEY (username),
	CONSTRAINT FK_user_information_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE recycle_records (
	account_id SERIAL, -- added serial account_id as PK for records table
	username varchar(50) NOT NULL,
	total_pounds_recycled numeric(10) DEFAULT 0,
	credit_points numeric(10) DEFAULT 0,
	points_redeemed numeric(10) DEFAULT 0,
	CONSTRAINT PK_recycle_records PRIMARY KEY (account_id),
	CONSTRAINT FK_recycle_records_user_information FOREIGN KEY (username) REFERENCES user_information (username)
);

CREATE TABLE pickup_information (
	pickup_id SERIAL,
	driver_id int,
	route_id int,
	username varchar(50) NOT NULL,
	place_id varchar(200) NOT NULL, -- for Route API use, is varchar(200) workable?
	pickup_date date,
	pickup_time time,
	--Another option would be: pickup_date_time TIMESTAMP (NOT NULL), What works best? as the data will be coming from the schedule pickup form*/
	pickup_weight numeric(3), -- full weight * num_of_bins
	num_of_bins numeric(1), -- limit number to 3 bins.
	isPickedUp boolean DEFAULT false,
	CONSTRAINT PK_pickup_information PRIMARY KEY (pickup_id),
    CONSTRAINT FK_pickup_information_users FOREIGN KEY (driver_id) REFERENCES users (user_id),
    CONSTRAINT FK_pickup_information_user_information FOREIGN KEY (username) REFERENCES user_information (username),
	CONSTRAINT chk_num_of_bins CHECK (num_of_bins <= 3)
);


-- Thinking consolidation of pickups per route and driver assigned.
--CREATE TABLE routes (
--    route_id SERIAL,
--    driver_id int,
--    pickup_id int
--    CONSTRAINT PK_routes PRIMARY KEY (route_id),
--    CONSTRAINT FK_routes_pickup_information FOREIGN KEY (driver_id) REFERENCES pickup_information (driver_id),
--);



COMMIT TRANSACTION;

