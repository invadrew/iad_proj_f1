CREATE OR REPLACE FUNCTION user_type_control() RETURNS TRIGGER AS $$
DECLARE
  user_type user_spec;
BEGIN
  user_type:=(SELECT spec FROM users WHERE (users.id = NEW.user_id));
  IF (( (user_type = 'ADMIN') OR (user_type = 'SPONSOR')))
  THEN RAISE EXCEPTION 'admins and sponsors are not team members';
  RETURN NULL;
  END IF;
  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER user_check
  BEFORE INSERT OR UPDATE ON team_members
  FOR EACH ROW EXECUTE PROCEDURE user_type_control();

CREATE OR REPLACE FUNCTION non_team_type_control() RETURNS TRIGGER AS $$
DECLARE
  user_type user_spec;
BEGIN
  user_type:=(SELECT spec FROM users WHERE (users.id = NEW.user_id));
  IF (NOT(user_type = 'SPONSOR'))
  THEN RAISE EXCEPTION 'only sponsors allowed here';
  RETURN NULL;
  END IF;
  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER non_team_control
  BEFORE INSERT OR UPDATE ON sponsors
  FOR EACH ROW EXECUTE PROCEDURE non_team_type_control();

CREATE OR REPLACE FUNCTION check_buy_ability() RETURNS TRIGGER AS $$
DECLARE
  user_type user_spec;
  can_b BOOLEAN;
BEGIN
  user_type:=(SELECT spec FROM users WHERE (users.id = NEW.user_id));
  can_b:=(SELECT can_buy FROM team_members WHERE team_members.user_id = NEW.user_id);
  IF ((user_type = 'MANAGER') AND (can_b = FALSE ))
  THEN RAISE EXCEPTION 'Manager should have component adding permission';
  RETURN NULL;
  END IF;
  IF (NOT((user_type = 'MANAGER') OR (user_type = 'MECHANIC')) AND (can_b = TRUE ))
  THEN RAISE EXCEPTION 'This member cannot have adding permission';
  RETURN NULL;
  END IF;
  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER check_can_buy
  BEFORE INSERT OR UPDATE ON team_members
  FOR EACH ROW EXECUTE PROCEDURE check_buy_ability();

CREATE OR REPLACE FUNCTION check_team_accepted() RETURNS TRIGGER AS $$
DECLARE
  accept accept_status;
  team_key INT;
BEGIN
  team_key:=(SELECT team_id FROM team_members WHERE (team_members.user_id = NEW.user_id));
  accept:=(SELECT status FROM teams WHERE (teams.id = team_key));
  IF (NOT(accept = 'ACCEPTED'))
  THEN RAISE EXCEPTION 'Team is not accepted yet';
  RETURN NULL;
  END IF;
  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER check_team_accept
  BEFORE INSERT OR UPDATE ON team_members
  FOR EACH ROW EXECUTE PROCEDURE check_team_accepted();

CREATE OR REPLACE FUNCTION money_sponsor_control() RETURNS TRIGGER AS $$
DECLARE
  money BIGINT;
BEGIN
  money:=(SELECT budget FROM sponsors WHERE (sponsors.user_id = NEW.sponsor_id));
  IF (money < (SELECT SUM(sponsoring.sp_money) FROM sponsoring WHERE sponsoring.sponsor_id = NEW.sponsor_id))
  THEN RAISE EXCEPTION 'Sponsor does not have enough money for this';
  RETURN NULL;
  END IF;
  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER sponsor_money_check
  BEFORE INSERT OR UPDATE ON sponsoring
  FOR EACH ROW EXECUTE PROCEDURE money_sponsor_control();

CREATE OR REPLACE FUNCTION racers_in_world_cup_control() RETURNS TRIGGER AS $$
DECLARE
  person_id INT;
  us_spec user_spec;
BEGIN
  person_id:=(SELECT racer_id FROM world_cup_result WHERE (world_cup_result.racer_id = NEW.racer_id));
  us_spec:=(SELECT spec FROM users WHERE (users.id = person_id));
  IF (NOT(us_spec = 'RACER'))
  THEN RAISE EXCEPTION 'Only racers can be in world cup results';
  RETURN NULL;
  END IF;
  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER racers_in_cup_check
  BEFORE INSERT OR UPDATE ON world_cup_result
  FOR EACH ROW EXECUTE PROCEDURE racers_in_world_cup_control();

CREATE OR REPLACE FUNCTION piloting_control() RETURNS TRIGGER AS $$
DECLARE
  person_id INT;
  carr_id INT;
  t_p INT;
  t_c INT;
  us_spec user_spec;
BEGIN
  person_id:=(SELECT racer_id FROM piloting WHERE (piloting.id = NEW.id));
  carr_id:=(SELECT car_id FROM piloting WHERE (piloting.id = NEW.id));
  t_p:=(SELECT team_id FROM team_members WHERE (team_members.user_id = person_id));
  t_c:=(SELECT team_id FROM cars WHERE (cars.id = carr_id));
  us_spec:=(SELECT spec FROM users WHERE (users.id = person_id));
  IF (NOT(us_spec = 'RACER'))
  THEN RAISE EXCEPTION 'Only racers can drive cars';
  RETURN NULL;
  END IF;
  IF (NOT(t_p = t_c))
  THEN RAISE EXCEPTION 'car and racer must be in one team';
  RETURN NULL;
  END IF;
  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER piloting_check
  BEFORE INSERT OR UPDATE ON piloting
  FOR EACH ROW EXECUTE PROCEDURE piloting_control();

CREATE OR REPLACE FUNCTION race_result_check() RETURNS TRIGGER AS $$
DECLARE
  pil_id INT;
  carr_id INT;
BEGIN
  pil_id:=(SELECT piloting_id FROM race_results WHERE ((race_results.car_id = NEW.car_id) AND (race_results.race_id = NEW.race_id)));
  carr_id:=(SELECT car_id FROM race_results WHERE (race_results.car_id = NEW.car_id));
  IF (NOT(carr_id = (SELECT car_id FROM piloting WHERE (piloting.id = pil_id))))
  THEN RAISE EXCEPTION 'No such piloting found';
  RETURN NULL;
  END IF;
  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER races_result_checker
  BEFORE INSERT OR UPDATE ON race_results
  FOR EACH ROW EXECUTE PROCEDURE race_result_check();

CREATE OR REPLACE FUNCTION pit_stop_transfer_check() RETURNS TRIGGER AS $$
DECLARE
BEGIN
  IF (NOT(
      (SELECT team_id FROM pit_stop_places WHERE (pit_stop_places.id = NEW.place_from_id)) =
      (SELECT team_id FROM pit_stop_places WHERE (pit_stop_places.id = NEW.place_to_id))
    ))
  THEN RAISE EXCEPTION 'Places must be own by one team';
  RETURN NULL;
  END IF;
  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER pit_stop_transfer
  BEFORE INSERT OR UPDATE ON pit_stop_transfer
  FOR EACH ROW EXECUTE PROCEDURE pit_stop_transfer_check();

CREATE OR REPLACE FUNCTION pit_stop_repair_check() RETURNS TRIGGER AS $$
DECLARE
BEGIN
  IF (NOT(
      (SELECT team_id FROM pit_stop_places WHERE (pit_stop_places.id = NEW.place_id)) =
      (SELECT team_id FROM cars WHERE (cars.id = NEW.car_id))
    ))
  THEN RAISE EXCEPTION 'Place and car must be in one team';
  RETURN NULL;
  END IF;
  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER pit_stop_repairs
  BEFORE INSERT OR UPDATE ON pit_stop_repair
  FOR EACH ROW EXECUTE PROCEDURE pit_stop_repair_check();
CREATE TRIGGER pit_stop_serv
  BEFORE INSERT OR UPDATE ON pit_stop_service
  FOR EACH ROW EXECUTE PROCEDURE pit_stop_repair_check();

CREATE OR REPLACE FUNCTION pilot_change_check() RETURNS TRIGGER AS $$
DECLARE
BEGIN
  IF (NOT(
      (SELECT team_id FROM pit_stop_places WHERE (pit_stop_places.id = NEW.place_id)) =
      (SELECT team_id FROM cars WHERE (cars.id = NEW.car_id))))
  THEN RAISE EXCEPTION 'Place and car must be in one team';
  RETURN NULL;
  END IF;
  IF (NOT(
      (SELECT team_id FROM pit_stop_places WHERE (pit_stop_places.id = NEW.place_id)) =
      (SELECT team_id FROM team_members WHERE (team_members.user_id = NEW.pilot_id))))
  THEN RAISE EXCEPTION 'Pilot and car must be in one team';
  RETURN NULL;
  END IF;
  IF (NOT((SELECT spec FROM users WHERE (users.id = NEW.pilot_id)) = 'RACER'))
  THEN RAISE EXCEPTION 'Not a pilot';
  RETURN NULL;
  END IF;
  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER pit_stop_changings
  BEFORE INSERT OR UPDATE ON pilot_change
  FOR EACH ROW EXECUTE PROCEDURE pilot_change_check();

CREATE OR REPLACE FUNCTION storage_check() RETURNS TRIGGER AS $$
DECLARE
BEGIN
  IF (NOT(NEW.team_id = (SELECT team_id FROM engine_storage WHERE (engine_storage.id = NEW.current_engine_id)) ))
  THEN RAISE EXCEPTION 'engine and car must belong to one team';
  RETURN NULL;
  END IF;
  IF (NOT(NEW.team_id = (SELECT team_id FROM chassis_storage WHERE (chassis_storage.id = NEW.current_chassis_id)) ))
  THEN RAISE EXCEPTION 'chassis and car must belong to one team';
  RETURN NULL;
  END IF;
  IF (NOT(NEW.team_id = (SELECT team_id FROM carcase_storage WHERE (carcase_storage.id = NEW.current_carcase_id)) ))
  THEN RAISE EXCEPTION 'carcase and car must belong to one team';
  RETURN NULL;
  END IF;
  IF (NOT(NEW.team_id = (SELECT team_id FROM electronics_storage WHERE (electronics_storage.id = NEW.current_electronics_id)) ))
  THEN RAISE EXCEPTION 'electronics and car must belong to one team';
  RETURN NULL;
  END IF;
  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER storage_contr
  BEFORE INSERT OR UPDATE ON cars
  FOR EACH ROW EXECUTE PROCEDURE storage_check();

CREATE OR REPLACE FUNCTION components_changing_check() RETURNS TRIGGER AS $$
DECLARE
  team INT;
BEGIN
  team := (SELECT team_id FROM cars WHERE (cars.id = NEW.car_id));
  IF ((NEW.current_carcase_old IS NOT NULL) AND (NEW.current_carcase_new IS NOT NULL)) THEN
    IF (NOT(team = (SELECT team_id FROM carcase_storage WHERE (carcase_storage.id = NEW.current_carcase_old))))
    THEN RAISE EXCEPTION 'old carcase and car must belong to one team';
    RETURN NULL;
    END IF;
    IF (NOT(team = (SELECT team_id FROM carcase_storage WHERE (carcase_storage.id = NEW.current_carcase_new))))
    THEN RAISE EXCEPTION 'new carcase and car must belong to one team';
    RETURN NULL;
    END IF;
  END IF;

  IF ((NEW.current_engine_old IS NOT NULL) AND (NEW.current_engine_new IS NOT NULL)) THEN
    IF (NOT(team = (SELECT team_id FROM engine_storage WHERE (engine_storage.id = NEW.current_engine_old))))
    THEN RAISE EXCEPTION 'old engine and car must belong to one team';
    RETURN NULL;
    END IF;
    IF (NOT(team = (SELECT team_id FROM engine_storage WHERE (engine_storage.id = NEW.current_engine_new))))
    THEN RAISE EXCEPTION 'new engine and car must belong to one team';
    RETURN NULL;
    END IF;
  END IF;

  IF ((NEW.current_chassis_old IS NOT NULL) AND (NEW.current_chassis_new IS NOT NULL)) THEN
    IF (NOT(team = (SELECT team_id FROM chassis_storage WHERE (chassis_storage.id = NEW.current_chassis_old))))
    THEN RAISE EXCEPTION 'old chassis and car must belong to one team';
    RETURN NULL;
    END IF;
    IF (NOT(team = (SELECT team_id FROM chassis_storage WHERE (chassis_storage.id = NEW.current_chassis_new))))
    THEN RAISE EXCEPTION 'new chassis and car must belong to one team';
    RETURN NULL;
    END IF;
  END IF;

  IF ((NEW.current_electronics_old IS NOT NULL) AND (NEW.current_electronics_new IS NOT NULL)) THEN
    IF (NOT(team = (SELECT team_id FROM electronics_storage WHERE (electronics_storage.id = NEW.current_electronics_old))))
    THEN RAISE EXCEPTION 'old electronics and car must belong to one team';
    RETURN NULL;
    END IF;
    IF (NOT(team = (SELECT team_id FROM electronics_storage WHERE (electronics_storage.id = NEW.current_electronics_new))))
    THEN RAISE EXCEPTION 'new electronics and car must belong to one team';
    RETURN NULL;
    END IF;
  END IF;


  RETURN NEW;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER components_change_control
  BEFORE INSERT OR UPDATE ON component_changes
  FOR EACH ROW EXECUTE PROCEDURE components_changing_check();