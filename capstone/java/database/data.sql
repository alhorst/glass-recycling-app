BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role, is_driver) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', false);
INSERT INTO users (username,password_hash,role, is_driver) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', false);

--INSERT INTO driver_details (employee_id,username,home_office_address) VALUES ('1','larryglassmore','3001 Railroad St, Pittsburgh, PA 15201');


COMMIT TRANSACTION;
