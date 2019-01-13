CREATE DATABASE f1_sys;

CREATE TABLE photos (
  id SERIAL PRIMARY KEY,
  path VARCHAR(255) NOT NULL,
  UNIQUE (path)
);

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  login VARCHAR(24) NOT NULL ,
  UNIQUE (login),
  password VARCHAR(32) NOT NULL ,
  spec user_spec NOT NULL ,
  photo_id INT REFERENCES photos(id),
  status accept_status NOT NULL ,
  comments VARCHAR(255),
  CHECK (photo_id > 0)
);

INSERT INTO users(login, password, spec, status, comments) VALUES
  ('asd','asd','RACER','ACCEPTED',NULL ),
  ('as','asd','RACER','ACCEPTED',NULL),
  ('a','asd','RACER','ACCEPTED',NULL),
  ('man','asd','MANAGER','ACCEPTED',NULL),
  ('man2','asd','MANAGER','ACCEPTED',NULL),
  ('mech','asd','MECHANIC','ACCEPTED',NULL),
  ('mech2','asd','MECHANIC','ACCEPTED',NULL),
  ('con','asd','CONSTRUCTOR','ACCEPTED',NULL),
  ('m','asd','CONSTRUCTOR','ON_REVIEW',NULL),
  ('itmo','asd','SPONSOR','ACCEPTED',NULL),
  ('bb','asd','SPONSOR','REFUSED','No bb in my system!'),
  ('N_and_A','root','ADMIN','ACCEPTED',NULL);

CREATE TABLE teams (
  id SERIAL PRIMARY KEY ,
  name VARCHAR(48) NOT NULL ,
  UNIQUE (name),
  budget BIGINT NOT NULL ,
  status accept_status NOT NULL ,
  comments TEXT,
  CHECK (budget >= 0)
);

INSERT INTO teams(name, budget, status, comments) VALUES
  ('GachiRacing',300,'ACCEPTED',NULL),
  ('RogoNemRacing',43530,'ACCEPTED','Best team ever'),
  ('ITMO',460,'REFUSED','no comments...'),
  ('RedBull',4120,'ON_REVIEW',NULL);

CREATE TABLE team_members (
  user_id INT REFERENCES users(id) PRIMARY KEY ,
  name VARCHAR(48) NOT NULL ,
  surname VARCHAR(48) NOT NULL ,
  can_buy BOOLEAN NOT NULL ,
  team_id INT REFERENCES teams(id),
  CHECK (team_id > 0),
  CHECK (user_id > 0)
);

INSERT INTO team_members (user_id, name, surname, can_buy, team_id) VALUES
  (1,'Nikita','Rogalenko',FALSE,2),
  (2,'Andrei','Nemov',FALSE,2),
  (3,'Gachi','Muchi',FALSE,1),
  (4,'Again Nikita',' Again Rogalenko',TRUE,2),
  (5,'Boy','Next door',TRUE,1),
  (6,'Ivan','Ivanov',FALSE,1),
  (7,'Rogo','RogoGit',FALSE,2),
  (8,'Ivan2','Ivanov2',FALSE,2);

CREATE TABLE sponsors (
   user_id INT REFERENCES users(id) PRIMARY KEY ,
   name VARCHAR(40) NOT NULL ,
   budget BIGINT NOT NULL ,
   CHECK (budget >= 0),
   CHECK (user_id > 0)
);

INSERT INTO sponsors(user_id, name, budget) VALUES
  (10,'GachiBank',909090);

CREATE TABLE sponsoring (
   id SERIAL PRIMARY KEY ,
   team_id INT REFERENCES teams(id) NOT NULL ,
   sponsor_id INT REFERENCES sponsors(user_id)  NOT NULL ,
   sp_money BIGINT NOT NULL ,
   date TIMESTAMP,
   CHECK (team_id > 0),
   CHECK (sponsor_id > 0),
   CHECK (sp_money >= 0),
   CHECK ( EXTRACT(YEAR FROM date) >= 1950)
);

INSERT INTO sponsoring(team_id, sponsor_id, sp_money, date) VALUES
  (1,10,200,'2019-01-10 03:01:20'),
  (2,10,20000,'2019-01-08 01:01:23');

CREATE TABLE seasons (
  year INT PRIMARY KEY,
  CHECK (year >= 1950)
);

INSERT INTO seasons(year) VALUES
  (2017),
  (2018),
  (2019);

CREATE TABLE world_cup_result (
  season_id INT REFERENCES seasons(year) NOT NULL ,
  racer_id INT REFERENCES users(id) NOT NULL ,
  PRIMARY KEY (season_id, racer_id),
  place INT NOT NULL ,
  points INT NOT NULL
  CHECK (season_id > 0),
  CHECK (racer_id > 0),
  CHECK (place > 0),
  CHECK (points >= 0)
);

INSERT INTO world_cup_result (season_id, racer_id, place, points) VALUES
  (2018,1,1,25),
  (2018,2,2,20),
  (2018,3,3,15);

INSERT INTO world_cup_result (season_id, racer_id, place, points) VALUES
  (2017,1,4,12),
  (2017,2,3,15),
  (2017,3,5,10);

CREATE TABLE constr_cup_result (
  season_id INT REFERENCES seasons(year) NOT NULL ,
  team_id INT REFERENCES teams(id) NOT NULL ,
  PRIMARY KEY (season_id, team_id),
  place INT NOT NULL ,
  points INT NOT NULL
  CHECK (season_id > 0),
  CHECK (team_id > 0),
  CHECK (place > 0),
  CHECK (points >= 0)
);

INSERT INTO constr_cup_result (season_id, team_id, place, points) VALUES
  (2018,1,2,15),
  (2018,2,1,45);

INSERT INTO constr_cup_result (season_id, team_id, place, points) VALUES
  (2017,1,5,10),
  (2017,2,4,27);

CREATE TABLE championships (
  id SERIAL PRIMARY KEY ,
  season_id INT REFERENCES seasons(year) NOT NULL ,
  name VARCHAR(25) NOT NULL ,
  country VARCHAR(30),
  stage_num INT NOT NULL ,
  CHECK (season_id > 0),
  CHECK (stage_num >= 0)
);

INSERT INTO championships (season_id, name, country, stage_num) VALUES
  (2017,'Gran-Pri Russia','Russia',1),
  (2017,'Gran-Pri Monako','Monako',2),
  (2017,'Gran-Pri China','China',3),
  (2018,'Gran-Pri Bachrein','Bachrein',1),
  (2018,'Gran-Pri Canada','Canada',2),
  (2018,'Gran-Pri France','France',3);

CREATE TABLE engine_storage (
  id SERIAL PRIMARY KEY ,
  team_id INT REFERENCES teams(id) NOT NULL ,
  model VARCHAR(30) NOT NULL ,
  cyclinders INT NOT NULL ,
  capacity REAL NOT NULL ,
  mass REAL,
  stroke REAL,
  condition component_condition NOT NULL ,
  price MONEY NOT NULL,
  status accept_status NOT NULL,
  CHECK (team_id > 0),
  CHECK (cyclinders > 0),
  CHECK (capacity > 0),
  CHECK (mass > 0),
  CHECK (stroke > 0),
  CHECK (price >= '0'::money)
);

INSERT INTO engine_storage (team_id, model, cyclinders, capacity, mass, stroke, condition, price, status) VALUES
  (2,'RS27',8,1.9,90.4,39.7,'GOOD','76743'::money,'ACCEPTED'),
  (2,'ER-75',8,2.3,96.8,38.7,'PERFECT','11237'::money,'ACCEPTED'),
  (1,'R7',8,2.1,80.7,40.73,'NORMAL','8234'::money,'ACCEPTED'),
  (1,'S2-T6',8,2.0,88.4,39.9,'AWFUL','7655'::money,'ACCEPTED'),
  (2,'AQ-W',16,2.8,70.45,39.8,'PERFECT','99999'::money,'ON_REVIEW');
INSERT INTO engine_storage (team_id, model, cyclinders, capacity, mass, stroke, condition, price, status) VALUES
  (2,'RS28',8,1.887,98.4,39.78,'GOOD','76743'::money,'ACCEPTED');

CREATE TABLE chassis_storage (
  id SERIAL PRIMARY KEY ,
  team_id INT REFERENCES teams(id) NOT NULL ,
  model VARCHAR(30) NOT NULL ,
  height REAL NOT NULL ,
  width REAL NOT NULL ,
  condition component_condition NOT NULL ,
  price MONEY NOT NULL ,
  status accept_status NOT NULL ,
  CHECK (team_id > 0),
  CHECK (height > 0),
  CHECK (width > 0),
  CHECK (price >= '0'::money)
);

INSERT INTO chassis_storage(team_id, model, height, width, condition, price, status) VALUES
  (2,'SH-X',300,100,'NORMAL','2344'::money,'ACCEPTED'),
  (2,'H-12',476,176,'GOOD','24764'::money,'ACCEPTED'),
  (1,'GachiCH',509,143,'PERFECT','62244'::money,'ON_REVIEW'),
  (2,'RG-sh',234,99,'NORMAL','76534'::money,'REFUSED'),
  (1,'ULTRA-c-h-12',199,198,'PERFECT','1422838'::money,'ACCEPTED');
INSERT INTO chassis_storage(team_id, model, height, width, condition, price, status) VALUES
  (2,'SH-X2',302,120,'NORMAL','232244'::money,'ACCEPTED');

CREATE TABLE carcase_storage (
  id SERIAL PRIMARY KEY ,
  team_id INT REFERENCES teams(id) NOT NULL ,
  material VARCHAR(25),
  rear_wing VARCHAR(25) NOT NULL ,
  safety_arcs VARCHAR(25) NOT NULL ,
  wings VARCHAR(40) NOT NULL ,
  condition component_condition NOT NULL ,
  price MONEY NOT NULL ,
  status accept_status NOT NULL ,
  CHECK (team_id > 0),
  CHECK (price >= '0'::money)
);

INSERT INTO carcase_storage (team_id, material, rear_wing, safety_arcs, wings, condition, price, status) VALUES
  (1,'carbon','XX-1','SEC-01','Q-typed W-2','GOOD','44555'::money,'ACCEPTED'),
  (1,'carbon','XX-2','SEC-02','R-typed W-3','NORMAL','6555'::money,'ON_REVIEW'),
  (2,'carbon','XX-3','SEC-01','U-typed W-2','GOOD','464355'::money,'ACCEPTED'),
  (2,'carbon','XX-4','SEC-01','Y-typed W-2','BAD','8885'::money,'ACCEPTED'),
  (2,'carbon','XX-5','SEC-00','T-typed W-0','AWFUL','999999'::money,'REFUSED');
INSERT INTO carcase_storage (team_id, material, rear_wing, safety_arcs, wings, condition, price, status) VALUES
  (2,'carbon','XX-6','SEC-01','Q-typed W-2','GOOD','44555'::money,'ACCEPTED');

CREATE TABLE electronics_storage (
  id SERIAL PRIMARY KEY ,
  team_id INT REFERENCES teams(id) NOT NULL ,
  telemetry VARCHAR(30) NOT NULL ,
  control_system VARCHAR(30),
  condition component_condition NOT NULL ,
  price MONEY NOT NULL ,
  status accept_status NOT NULL ,
  CHECK (team_id > 0),
  CHECK (price >= '0'::money)
);

INSERT INTO electronics_storage(team_id, telemetry, control_system, condition, price, status) VALUES
  (1,'type 1','GachiContr-3','GOOD','12455'::money,'ACCEPTED'),
  (1,'type 2','cs-12','NORMAL','55655'::money,'ON_REVIEW'),
  (2,'type 3','Rogogog','GOOD','3255'::money,'ACCEPTED'),
  (2,'type 4','Nem_electro','BAD','98885'::money,'ACCEPTED'),
  (2,'type 5','o-0','AWFUL','9999'::money,'REFUSED');
INSERT INTO electronics_storage(team_id, telemetry, control_system, condition, price, status) VALUES
  (2,'type 6', 'NEmo','PERFECT','74563'::money,'ACCEPTED');

CREATE TABLE cars (
  id SERIAL PRIMARY KEY ,
  label VARCHAR(25) NOT NULL ,
  model VARCHAR(25) NOT NULL ,
  creation_year INT,
  team_id INT REFERENCES teams(id),
  photo_id INT REFERENCES photos(id),
  is_ready BOOLEAN NOT NULL ,
  current_carcase_id INT REFERENCES carcase_storage(id) ,
  UNIQUE (current_carcase_id),
  current_engine_id INT REFERENCES engine_storage(id) ,
  UNIQUE (current_engine_id),
  current_chassis_id INT REFERENCES chassis_storage(id) ,
  UNIQUE (current_chassis_id),
  current_electronics_id INT REFERENCES electronics_storage(id) ,
  UNIQUE (current_electronics_id),
  CHECK (creation_year > 1900),
  CHECK (team_id > 0),
  CHECK (photo_id > 0),
  CHECK (current_carcase_id > 0),
  CHECK (current_chassis_id > 0),
  CHECK (current_electronics_id > 0),
  CHECK (current_engine_id > 0)
);

INSERT INTO cars(label, model, creation_year, team_id, photo_id, is_ready, current_carcase_id, current_engine_id, current_chassis_id, current_electronics_id) VALUES
  ('Ferrari','X-01',2015,2,NULL,TRUE,5,2,4,4),
  ('Ferrari','X-02',2015,2,NULL,TRUE,4,5,1,3),
  ('Toyota','T-02',2017,2,NULL,TRUE,3,1,2,5),
  ('MCLaren','Y-01',2014,1,NULL,TRUE,1,4,3,2),
  ('MCLaren','Y-02',2016,1,NULL,FALSE,2,3,5,1);

CREATE TABLE piloting (
  id SERIAL PRIMARY KEY,
  car_id INT REFERENCES cars(id) NOT NULL,
  racer_id INT REFERENCES team_members(user_id) NOT NULL,
  UNIQUE (car_id, racer_id),
  CHECK (car_id > 0),
  CHECK (racer_id > 0)
);

INSERT INTO piloting(car_id, racer_id) VALUES
  (4,3),
  (1,1),
  (3,2);

CREATE TABLE races (
  id SERIAL PRIMARY KEY ,
  champ_id INT REFERENCES championships(id) NOT NULL ,
  duration TIME,
  date_time TIMESTAMP NOT NULL ,
  laps INT,
  max_participants INT NOT NULL ,
  track VARCHAR(30) NOT NULL ,
  CHECK (champ_id > 0),
  CHECK (laps > 0),
  CHECK (max_participants > 0),
  CHECK (EXTRACT(YEAR FROM date_time) >= 1950),
  CHECK (duration >= '00:00:00')
);

INSERT INTO races(champ_id, duration, date_time, laps, max_participants, track) VALUES
  (1,'00:56:00','2018-11-11 09:01:20',20,20,'Dragon nest'),
  (2,'00:59:03','2018-01-12 19:01:20',20,20,'Dragon quest'),
  (3,'00:23:13','2018-11-12 12:01:20',20,20,'Dragon chest'),
  (4,'00:12:12','2018-01-01 09:41:20',20,20,'Dragon rest'),
  (5,'00:06:32','2018-10-03 10:41:21',20,20,'Dragon lace'),
  (6,'00:34:22','2018-11-11 23:11:20',20,20,'Dragon chase'),
  (1,'00:55:12','2018-10-11 19:01:20',15,20,'Dragon nails'),
  (2,'00:23:45','2018-10-12 19:01:20',15,20,'Dragon rails'),
  (3,'00:45:55','2018-10-12 12:01:20',15,20,'Dragon tails'),
  (4,'00:11:32','2018-11-01 09:41:20',15,20,'Dragon ways'),
  (5,'00:56:57','2018-02-03 20:41:21',15,20,'Dragon gays (happy)'),
  (6,'00:29:34','2018-09-08 13:11:20',15,20,'Dragon maze');

CREATE TABLE chats (
  id SERIAL PRIMARY KEY ,
  name VARCHAR(50) NOT NULL
);

INSERT INTO chats(name) VALUES
  ('Andrei and Nikita'),
  ('RogoNemRacing chat');
INSERT INTO chats(name) VALUES
  ('Permission test');

CREATE TABLE user_chat (
  id SERIAL PRIMARY KEY ,
  user_id INT REFERENCES users(id) NOT NULL ,
  chat_id INT REFERENCES chats(id) NOT NULL ,
  UNIQUE (user_id, chat_id),
  CHECK (user_id > 0),
  CHECK (chat_id > 0)
);

INSERT INTO user_chat(user_id, chat_id) VALUES
  (1,1),
  (1,2),
  (2,1),
  (2,2);

INSERT INTO user_chat(user_id, chat_id) VALUES
  (4,3),
  (7,3);

CREATE TABLE messages (
  id SERIAL PRIMARY KEY ,
  author_id INT REFERENCES users(id) NOT NULL ,
  chat_id INT REFERENCES chats(id) NOT NULL ,
  time TIMESTAMP NOT NULL ,
  is_read BOOLEAN NOT NULL ,
  mess_text TEXT NOT NULL ,
  type message_type NOT NULL ,
  result BOOLEAN,
  CHECK (author_id > 0),
  CHECK (chat_id > 0),
  CHECK (EXTRACT(YEAR FROM time) >= 2019)
);

INSERT INTO messages(author_id, chat_id, time, is_read, mess_text, type, result) VALUES
  (1,1,'2019-01-12 12:12:12',FALSE,'Привет, Андрей!','ORDINARY',NULL),
  (2,1,'2019-01-12 12:12:20',FALSE,'прив','ORDINARY',NULL),
  (4,3,'2019-01-12 11:11:11',FALSE ,'Hello','BUY_PERMISSION',FALSE );

CREATE TABLE component_changes (
  id SERIAL PRIMARY KEY ,
  car_id INT REFERENCES cars(id)  NOT NULL ,
  race_id INT REFERENCES races(id),
  current_carcase_old INT REFERENCES carcase_storage(id) ,
  current_carcase_new INT REFERENCES carcase_storage(id) ,
  current_chassis_old INT REFERENCES chassis_storage(id) ,
  current_chassis_new INT REFERENCES chassis_storage(id) ,
  current_engine_old INT REFERENCES engine_storage(id) ,
  current_engine_new INT REFERENCES engine_storage(id) ,
  current_electronics_old INT REFERENCES electronics_storage(id) ,
  current_electronics_new INT REFERENCES electronics_storage(id) ,
  reason text,
  date TIMESTAMP,
  CHECK (car_id > 0),
  CHECK (race_id > 0),
  CHECK (current_carcase_new > 0),
  CHECK (current_carcase_old > 0),
  CHECK (current_chassis_new > 0),
  CHECK (current_chassis_old > 0),
  CHECK (current_engine_new > 0),
  CHECK (current_engine_old > 0),
  CHECK (current_electronics_new > 0),
  CHECK (current_electronics_old > 0),
  CHECK (EXTRACT(YEAR FROM date) >= 1950),
  CHECK (
     CASE
     WHEN ((current_carcase_old IS NULL ) OR (current_carcase_new IS NULL )) THEN
       ((current_carcase_old IS NULL) AND (current_carcase_new IS NULL))
     WHEN ((current_engine_old IS NULL ) OR (current_engine_new IS NULL )) THEN
       ((current_engine_old IS NULL) AND (current_engine_new IS NULL))
     WHEN ((current_chassis_old IS NULL ) OR (current_chassis_new IS NULL )) THEN
       ((current_chassis_old IS NULL) AND (current_chassis_new IS NULL))
     WHEN ((current_electronics_old IS NULL ) OR (current_electronics_new IS NULL )) THEN
       ((current_electronics_old IS NULL) AND (current_electronics_new IS NULL))
     END
  )
);

INSERT INTO component_changes (car_id, race_id, current_carcase_old, current_carcase_new,
                               current_chassis_old, current_chassis_new, current_engine_old,
                               current_engine_new, current_electronics_old,
                               current_electronics_new, reason, date) VALUES
  (1,NULL,5,6,2,6,2,6,4,6,'because I said so!!','2019-01-12 12:12:12');

CREATE TABLE race_registration (
  team_id INT REFERENCES teams(id) NOT NULL ,
  race_id INT REFERENCES races(id) NOT NULL ,
  PRIMARY KEY (team_id,race_id),
  first_pilot INT REFERENCES team_members(user_id) NOT NULL ,
  first_car INT REFERENCES cars(id) NOT NULL ,
  second_pilot INT REFERENCES team_members(user_id) ,
  second_car INT REFERENCES cars(id) ,
  status accept_status NOT NULL ,
  comment TEXT,
  CHECK (first_pilot > 0),
  CHECK (first_car > 0),
  CHECK (second_pilot > 0),
  CHECK (second_car > 0),
  CHECK (
    CASE WHEN ((second_pilot ISNULL ) OR (second_car ISNULL )) THEN ((second_pilot IS NULL) AND (second_car IS NULL)) END
  )
);

INSERT INTO race_registration (team_id, race_id, first_pilot, first_car, second_pilot, second_car, status, comment) VALUES
  (2,1,1,1,2,2,'ACCEPTED','We will win'),
  (1,1,3,4,NULL,NULL,'ACCEPTED',NULL),
  (2,2,1,1,2,2,'ON_REVIEW','Still thinking'),
  (1,2,3,4,NULL,NULL,'REFUSED','No gachi right now');

CREATE TABLE race_results (
  car_id INT REFERENCES cars(id) NOT NULL ,
  race_id INT REFERENCES races(id) NOT NULL ,
  PRIMARY KEY (car_id,race_id),
  piloting_id INT REFERENCES piloting(id) NOT NULL ,
  place INT NOT NULL ,
  laps INT,
  points INT NOT NULL ,
  race_time TIME NOT NULL ,
  CHECK (car_id > 0),
  CHECK (race_id > 0),
  CHECK (piloting_id > 0),
  CHECK (race_time >= '00:00:00'),
  CHECK (laps >= 0),
  CHECK (points >= 0),
  CHECK (place > 0)
);

INSERT INTO race_results(car_id, race_id, piloting_id, place, laps, points, race_time) VALUES
  (1,1,2,1,20,25,'00:37:00'),
  (3,1,3,2,20,20,'00:37:01'),
  (4,1,1,4,20,16,'01:00:00');

CREATE TABLE pit_stop_places (
  id SERIAL PRIMARY KEY ,
  name VARCHAR(20) NOT NULL ,
  team_id INT REFERENCES teams(id) NOT NULL,
  tough INT NOT NULL ,
  soft INT NOT NULL ,
  fuel REAL NOT NULL ,
  CHECK (tough >= 0),
  CHECK (soft >= 0),
  CHECK (fuel >= 0),
  CHECK (team_id > 0)
);

INSERT INTO pit_stop_places (name, team_id, tough, soft, fuel) VALUES
  ('GachiStop A',1,8,5,300),
  ('GachiStop B',1,2,9,300),
  ('RogoNem A',2,0,1,5000),
  ('RogoNem B',2,20,32,0.5);

CREATE TABLE pit_stop_service (
  id SERIAL PRIMARY KEY ,
  race_id INT REFERENCES races(id) NOT NULL ,
  place_id INT REFERENCES pit_stop_places(id) NOT NULL ,
  car_id INT REFERENCES cars(id) NOT NULL ,
  laps INT NOT NULL ,
  fuel REAL,
  tires tire_types,
  status accept_status NOT NULL,
  CHECK (race_id > 0),
  CHECK (car_id > 0),
  CHECK (place_id > 0),
  CHECK (laps >= 0),
  CHECK (fuel >= 0)
);

INSERT INTO pit_stop_service(race_id, place_id, car_id, laps, fuel, tires, status) VALUES
  (1,1,4,3,20,'SOFT','ACCEPTED'),
  (1,1,4,9,NULL,'TOUGH','REFUSED'),
  (1,4,1,16,14,NULL,'ON_REVIEW'),
  (1,3,2,11,13,'SOFT','ACCEPTED');

CREATE TABLE pit_stop_repair (
  id SERIAL PRIMARY KEY ,
  race_id INT REFERENCES races(id) NOT NULL ,
  place_id INT REFERENCES pit_stop_places(id) NOT NULL ,
  car_id INT REFERENCES cars(id) NOT NULL ,
  comment TEXT,
  component component_types,
  status accept_status NOT NULL,
  CHECK (race_id > 0),
  CHECK (car_id > 0),
  CHECK (place_id > 0)
);

INSERT INTO pit_stop_repair (race_id, place_id, car_id, comment, component, status) VALUES
  (1,2,4,'engine is almost dead','ENGINE','REFUSED'),
  (1,3,1,'wings are destroyed','CARCASE','ON_REVIEW'),
  (1,1,4,'electronics broken','ELECTRONICS','ACCEPTED');

CREATE TABLE pilot_change (
  id SERIAL PRIMARY KEY ,
  race_id INT REFERENCES races(id) NOT NULL ,
  place_id INT REFERENCES pit_stop_places(id) NOT NULL ,
  car_id INT REFERENCES cars(id) NOT NULL ,
  pilot_id INT REFERENCES team_members(user_id)  NOT NULL ,
  comment TEXT NOT NULL ,
  status accept_status NOT NULL,
  CHECK (race_id > 0),
  CHECK (car_id > 0),
  CHECK (place_id > 0),
  CHECK (pilot_id > 0)
);

INSERT INTO pilot_change (race_id, place_id, car_id, pilot_id, comment, status) VALUES
  (1,3,1,2,'Something happened','REFUSED');

CREATE TABLE pit_stop_transfer (
  id SERIAL PRIMARY KEY ,
  race_id INT REFERENCES races(id) NOT NULL ,
  place_from_id INT REFERENCES pit_stop_places(id) NOT NULL ,
  place_to_id INT REFERENCES pit_stop_places(id) NOT NULL ,
  transfer transfers NOT NULL ,
  amount REAL NOT NULL ,
  status accept_status NOT NULL ,
  CHECK (race_id > 0),
  CHECK (place_from_id > 0),
  CHECK (place_to_id > 0),
  CHECK (amount > 0)
);

INSERT INTO pit_stop_transfer (race_id, place_from_id, place_to_id, transfer, amount, status) VALUES
  (1,1,2,'FUEL',10,'ACCEPTED'),
  (1,4,3,'SOFT',2,'ACCEPTED'),
  (1,2,1,'TOUGH',5,'REFUSED'),
  (1,3,4,'FUEL',34,'ON_REVIEW');

CREATE TYPE accept_status AS ENUM ('ACCEPTED','ON_REVIEW','REFUSED');
CREATE TYPE user_spec AS ENUM ('RACER','MANAGER','ADMIN','SPONSOR','CONSTRUCTOR','MECHANIC');
CREATE TYPE message_type AS ENUM ('ORDINARY','TEAM_INVITE','JOIN_REQUEST','TEAM_CREATE','BUY_PERMISSION');
CREATE TYPE component_condition AS ENUM ('PERFECT','GOOD','NORMAL','BAD','AWFUL');
CREATE TYPE component_types AS ENUM ('CARCASE','ENGINE','CHASSIS','ELECTRONICS');
CREATE TYPE tire_types AS ENUM ('TOUGH','SOFT');
CREATE TYPE transfers AS ENUM ('TOUGH','FUEL','SOFT');