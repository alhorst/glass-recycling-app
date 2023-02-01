BEGIN TRANSACTION;

DROP TABLE IF EXISTS routes, pickup_details, driver_details, user_details, users CASCADE;

DROP SEQUENCE IF EXISTS seq_account_id, seq_employee_id;

-- user/password information for users who recycle, drivers, and admins
-- Drivers (ROLE_USER && is_driver = true)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	is_driver boolean DEFAULT false,
	CONSTRAINT PK_users PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- User information for those registered to recycle with us
-- Sequence below --> recyclers have account_id starting with 1001
CREATE SEQUENCE seq_account_id
    INCREMENT BY 1
    START WITH 1001
    NO MAXVALUE;

CREATE TABLE user_details (
    account_id int NOT NULL DEFAULT nextval ('seq_account_id'),
	username varchar(50) NOT NULL,
	--full_name or first_name & last_name? does one or the other benefit us when user inputs data on the front end?
	full_name varchar(100) NOT NULL,
	street_address varchar(100) NOT NULL, -- 3001 Railroad St
	city varchar(50) NOT NULL, -- Pittsburgh
	state_abbreviation varchar(2) NOT NULL, -- PA
	zipcode varchar(5) NOT NULL, -- 15201
	phone_number varchar(11), -- example: 7032978845 (country code '1' optional)
	email_address varchar(100),
	total_pounds_recycled int DEFAULT 0,
    credits_balance int DEFAULT 0, -- 1pt per pound
    credits_redeemed int DEFAULT 0,
	CONSTRAINT PK_user_details PRIMARY KEY (username),
	CONSTRAINT FK_user_details_users FOREIGN KEY (username) REFERENCES users (username)
);

-- Driver information
-- Sequence below --> Drivers have employee_id, starting with 3001
CREATE SEQUENCE seq_employee_id
    INCREMENT BY 1
    START WITH 3001
    NO MAXVALUE;

CREATE TABLE driver_details (
    employee_id int NOT NULL DEFAULT nextval ('seq_employee_id'),
    username varchar(50) NOT NULL,
    home_office_address varchar(200) DEFAULT ('3001 Railroad St, Pittsburgh, PA 15201'), -- default to home base address, same for all drivers -- full address could avoid concatenation when feeding into API
    CONSTRAINT PK_driver_details PRIMARY KEY (employee_id),
    CONSTRAINT FK_driver_details_users FOREIGN KEY (username) REFERENCES users (username)
);

-- Route table - unique routeID, route Date, and driver assigned to that route
-- when pickups are assigned to a route, in the same method, we can update the route_id in pickup_details for that specific route
-- keeping in mind limit of pickups per route, per day
CREATE TABLE routes (
    route_id SERIAL,
    route_date date NOT NULL,
    driver_id int NOT NULL,
    CONSTRAINT PK_routes PRIMARY KEY (route_id),
    CONSTRAINT FK_routes_driver_details FOREIGN KEY (driver_id) REFERENCES driver_details (employee_id)
);

-- pickup information
-- route_id will default to 0 == unassigned
CREATE TABLE pickup_details (
	pickup_id SERIAL,
	route_id int DEFAULT 0,
	requesting_username varchar(50) NOT NULL,
	pickup_date date NOT NULL,
	pickup_weight int NOT NULL, -- 60lbs * num_of_bins
	num_of_bins int NOT NULL, -- User selection of 1, 2, or 3(max) per pickup
	is_Picked_Up boolean DEFAULT false,
	CONSTRAINT PK_pickup_details PRIMARY KEY (pickup_id),
    CONSTRAINT FK_pickup_details_user_details FOREIGN KEY (requesting_username) REFERENCES user_details (username),
    CONSTRAINT FK_pickup_details_routes FOREIGN KEY (route_id) REFERENCES routes (route_id),
	CONSTRAINT chk_num_of_bins CHECK (num_of_bins > 0 AND num_of_bins <= 3)
);







-- Brought up idea of a credits table to track pounds of glass, credits, along with account_id
-- Andy said it can live on the user_details table in our case, assuming one user account has one credit account which is true
-- credits table
--- user_id (connect user table)
--- pounds recycled
--- credits balance
--- * ask product owner - about need for credits redeemed

COMMIT TRANSACTION;

