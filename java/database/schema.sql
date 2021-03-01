--Table Setup
BEGIN TRANSACTION;

DROP TABLE IF EXISTS grimoire;
DROP TABLE IF EXISTS repertoire;
DROP TABLE IF EXISTS enemy_grimoire;
DROP TABLE IF EXISTS enemy_repertoire;
DROP TABLE IF EXISTS players;
DROP TABLE IF EXISTS enemies;
DROP TABLE IF EXISTS spells;
DROP TABLE IF EXISTS spell_types;
DROP TABLE IF EXISTS attacks;
DROP TABLE IF EXISTS enemy_attacks;
DROP TABLE IF EXISTS damage_types;

DROP SEQUENCE IF EXISTS seq_player_id;
DROP SEQUENCE IF EXISTS seq_enemy_id;
DROP SEQUENCE IF EXISTS seq_spell_id;
DROP SEQUENCE IF EXISTS seq_spell_type_id;
DROP SEQUENCE IF EXISTS seq_attack_id;
DROP SEQUENCE IF EXISTS seq_damage_type_id;
DROP SEQUENCE IF EXISTS seq_enemy_attack_id;


CREATE SEQUENCE seq_player_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_enemy_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_spell_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_attack_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_damage_type_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_enemy_attack_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_spell_type_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE players (
  player_id int DEFAULT nextval('seq_player_id'::regclass) NOT NULL,
  player_name varchar(45) NOT NULL,
  experience int DEFAULT 300 NOT NULL,
  spent_experience int DEFAULT 0 NOT NULL,
  proficiency_bonus int DEFAULT 1 NOT NULL,
  base_health int DEFAULT 15 NOT NULL,
  base_mana int DEFAULT 15 NOT NULL,
  base_armor int DEFAULT 10 NOT NULL,
  health_regen int DEFAULT 0 NOT NULL,
  mana_regen int DEFAULT 0 NOT NULL,
  strength int DEFAULT 10 NOT NULL,
  dexerity int DEFAULT 10 NOT NULL,
  constitution int DEFAULT 10 NOT NULL,
  magic int DEFAULT 10 NOT NULL,
  
  CONSTRAINT PK_player_id PRIMARY KEY (player_id)
);

CREATE TABLE enemies (
  enemy_id int DEFAULT nextval('seq_enemy_id'::regclass) NOT NULL,
  enemy_name varchar(45) NOT NULL,
  experience int,
  max_health int NOT NULL,
  armor int NOT NULL,
  challenge_rating numeric,
  hit_bonus int NOT NULL,
  max_action_points int NOT NULL,
  description varchar(200) NOT NULL,
  health_regen int DEFAULT 0,
  
  CONSTRAINT PK_enemy_id PRIMARY KEY (enemy_id)
);

CREATE TABLE damage_types (
  damage_type_id int DEFAULT nextval('seq_damage_type_id'::regclass) NOT NULL,
  description varchar(45) NOT NULL,
  
  CONSTRAINT PK_damage_type_id PRIMARY KEY (damage_type_id)
);

CREATE TABLE attacks (
  attack_id int DEFAULT nextval('seq_attack_id'::regclass) NOT NULL,
  name varchar(45) NOT NULL,
  action_cost numeric NOT NULL,
  damage_dice_1 int NOT NULL,
  die_size_1 int NOT NULL,
  bonus_damage_1 int NOT NULL,
  has_second_damage boolean DEFAULT false,
  damage_dice_2 int DEFAULT 0,
  die_size_2 int DEFAULT 0,
  bonus_damage_2 int DEFAULT 0,
  is_magic boolean DEFAULT false NOT NULL,
  experience_cost int,
  damage_type_id_1 int NOT NULL,
  damage_type_id_2 int DEFAULT NULL,
  
  
  CONSTRAINT PK_attack_id PRIMARY KEY (attack_id),
  CONSTRAINT FK_damage_type_id_1 FOREIGN KEY (damage_type_id_1) REFERENCES damage_types (damage_type_id),
  CONSTRAINT FK_damage_type_id_2 FOREIGN KEY (damage_type_id_2) REFERENCES damage_types (damage_type_id)
);

CREATE TABLE enemy_attacks (
  enemy_attack_id int DEFAULT nextval('seq_enemy_attack_id'::regclass) NOT NULL,
  name varchar(45) NOT NULL,
  action_cost numeric NOT NULL,
  damage_dice_1 int NOT NULL,
  die_size_1 int NOT NULL,
  bonus_damage_1 int NOT NULL,
  has_second_damage boolean DEFAULT false,
  damage_dice_2 int DEFAULT 0,
  die_size_2 int DEFAULT 0,
  bonus_damage_2 int DEFAULT 0,
  is_magic boolean DEFAULT false NOT NULL,
  damage_type_id_1 int NOT NULL,
  damage_type_id_2 int DEFAULT NULL,
  
  CONSTRAINT PK_enemy_attack_id PRIMARY KEY (enemy_attack_id),
  CONSTRAINT FK_damage_type_id_1 FOREIGN KEY (damage_type_id_1) REFERENCES damage_types (damage_type_id),
  CONSTRAINT FK_damage_type_id_2 FOREIGN KEY (damage_type_id_2) REFERENCES damage_types (damage_type_id)
);

CREATE TABLE spell_types (
  spell_type_id int DEFAULT nextval('seq_spell_type_id'::regclass) NOT NULL,
  description varchar(45) NOT NULL,
  
  CONSTRAINT PK_spell_type_id PRIMARY KEY (spell_type_id)
);

CREATE TABLE spells (
  spell_id int DEFAULT nextval('seq_spell_id'::regclass) NOT NULL,
  name varchar(45) NOT NULL,
  action_cost numeric NOT NULL,
  damage_dice_1 int NOT NULL,
  die_size_1 int NOT NULL,
  bonus_damage_1 int NOT NULL,
  has_second_damage bool DEFAULT false,
  damage_dice_2 int DEFAULT 0,
  die_size_2 int DEFAULT 0,
  bonus_damage_2 int DEFAULT 0,
  mana_cost int,
  target_self boolean NOT NULL,
  experience_cost int,
  damage_type_id_1 int NOT NULL,
  damage_type_id_2 int DEFAULT NULL,
  spell_type int DEFAULT 1,

  
  CONSTRAINT PK_spell_id PRIMARY KEY (spell_id),
  CONSTRAINT FK_damage_type_id_1 FOREIGN KEY (damage_type_id_1) REFERENCES damage_types (damage_type_id),
  CONSTRAINT FK_damage_type_id_2 FOREIGN KEY (damage_type_id_2) REFERENCES damage_types (damage_type_id),
  CONSTRAINT FK_spell_type FOREIGN KEY (spell_type) REFERENCES spell_types (spell_type_id)
);

CREATE TABLE grimoire (
  player_id int NOT NULL,
  spell_id int NOT NULL,
  
  CONSTRAINT PK_grimoire PRIMARY KEY (player_id, spell_id),
  CONSTRAINT FK_grimoire_player_id FOREIGN KEY (player_id) REFERENCES players (player_id),
  CONSTRAINT FK_grimoire_spell_id FOREIGN KEY (spell_id) REFERENCES spells (spell_id)
  
);

CREATE TABLE repertoire (
  player_id int NOT NULL,
  attack_id int NOT NULL,
  
  CONSTRAINT PK_repertoire PRIMARY KEY (player_id, attack_id),
  CONSTRAINT FK_repertoire_player_id FOREIGN KEY (player_id) REFERENCES players (player_id),
  CONSTRAINT FK_repertoire_attack_id FOREIGN KEY (attack_id) REFERENCES attacks (attack_id)
  
);

CREATE TABLE enemy_grimoire (
  enemy_id int NOT NULL,
  spell_id int NOT NULL,
  
  CONSTRAINT PK_enemy_grimoire PRIMARY KEY (enemy_id, spell_id),
  CONSTRAINT FK_enemy_grimoire_enemy_id FOREIGN KEY (enemy_id) REFERENCES enemies (enemy_id),
  CONSTRAINT FK_enemy_grimoire_spell_id FOREIGN KEY (spell_id) REFERENCES spells (spell_id)
  
);

CREATE TABLE enemy_repertoire (
  enemy_id int NOT NULL,
  enemy_attack_id int NOT NULL,
  
  CONSTRAINT PK_enemy_repertoire PRIMARY KEY (enemy_id, enemy_attack_id),
  CONSTRAINT FK_enemy_repertoire_enemy_id FOREIGN KEY (enemy_id) REFERENCES enemies (enemy_id),
  CONSTRAINT FK_enemy_repertoire_attack_id FOREIGN KEY (enemy_attack_id) REFERENCES enemy_attacks (enemy_attack_id)
  
);


COMMIT TRANSACTION;

--Trigger Setup
BEGIN TRANSACTION;
CREATE OR REPLACE FUNCTION update_enemy() RETURNS TRIGGER AS '
  BEGIN
    UPDATE enemies
    SET experience = (((max_health * armor * hit_bonus * max_action_points + (health_regen + (SELECT COUNT(*) FROM enemy_repertoire WHERE enemy_repertoire.enemy_id = NEW.enemy_id) + (SELECT COUNT(*) FROM enemy_grimoire WHERE enemy_grimoire.enemy_id = NEW.enemy_id)) * 100) / 300 + 1) :: int) * 100,
    challenge_rating = SQRT(((max_health * armor * hit_bonus * max_action_points + (health_regen + (SELECT COUNT(*) FROM enemy_repertoire WHERE enemy_repertoire.enemy_id = NEW.enemy_id) + (SELECT COUNT(*) FROM enemy_grimoire WHERE enemy_grimoire.enemy_id = NEW.enemy_id)) * 100) / 300 + 1) :: numeric) / 2
    WHERE enemies.enemy_id = NEW.enemy_id;    
    
    RETURN NEW;
  END;
' LANGUAGE plpgsql;

CREATE TRIGGER new_enemy_attack AFTER INSERT ON enemy_repertoire
    FOR EACH ROW EXECUTE FUNCTION update_enemy();

CREATE TRIGGER new_enemy_spell AFTER INSERT ON enemy_grimoire
    FOR EACH ROW EXECUTE FUNCTION update_enemy();

CREATE OR REPLACE FUNCTION update_created_enemy() RETURNS TRIGGER AS '
  BEGIN
    IF NEW.hit_bonus < 1 THEN
      NEW.hit_bonus = 1;
    END IF;
    IF NEW.health_regen IS NULL OR NEW.health_regen < 0 THEN
      NEW.health_regen = 0;
    END IF;
    NEW.experience = (((NEW.max_health * NEW.armor * NEW.hit_bonus * NEW.max_action_points + (NEW.health_regen + (SELECT COUNT(*) FROM enemy_repertoire WHERE enemy_repertoire.enemy_id = NEW.enemy_id) + (SELECT COUNT(*) FROM enemy_grimoire WHERE enemy_grimoire.enemy_id = NEW.enemy_id)) * 100) / 300 + 1) :: int) * 100;
    NEW.challenge_rating = SQRT(((NEW.max_health * NEW.armor * NEW.hit_bonus * NEW.max_action_points + (NEW.health_regen + (SELECT COUNT(*) FROM enemy_repertoire WHERE enemy_repertoire.enemy_id = NEW.enemy_id) + (SELECT COUNT(*) FROM enemy_grimoire WHERE enemy_grimoire.enemy_id = NEW.enemy_id)) * 100) / 300 + 1) :: numeric) / 2;
    RETURN NEW;
  END;
' LANGUAGE plpgsql;


CREATE TRIGGER update_created_enemy BEFORE INSERT OR UPDATE ON enemies
    FOR EACH ROW EXECUTE FUNCTION update_created_enemy();
    


CREATE OR REPLACE FUNCTION update_spell() RETURNS TRIGGER AS '
  BEGIN
    IF NEW.spell_type IS NULL OR NEW.spell_type < 1 OR NEW.spell_type > 3 THEN
      NEW.spell_type = 1;
    END IF;
    IF NEW.damage_dice_1 < 0 THEN
        NEW.damage_dice_1 = 0;
    END IF;
    IF NEW.die_size_1 <= 0 THEN
        NEW.damage_dice_1 = 1;
    END IF;

    IF (NEW.damage_dice_2 * NEW.die_size_2 = 0 AND NEW.bonus_damage_2 = 0) OR NEW.damage_type_id_2 IS NULL THEN
        NEW.has_second_damage = false;
    ELSE
        NEW.has_second_damage = true;
    END IF;
    
    IF (NEW.has_second_damage = false) THEN
        NEW.mana_cost = ((NEW.damage_dice_1 * NEW.die_size_1 + NEW.bonus_damage_1) / (NEW.action_cost * 2) :: int);
        NEW.experience_cost = ((NEW.damage_dice_1 * NEW.die_size_1 + NEW.bonus_damage_1) * 50 / NEW.action_cost :: int);
    ELSE
        NEW.mana_cost = ((NEW.damage_dice_1 * NEW.die_size_1 + NEW.bonus_damage_1 + NEW.damage_dice_2 * NEW.die_size_2 + NEW.bonus_damage_2) / (NEW.action_cost * 2) :: int);
        NEW.experience_cost = ((NEW.damage_dice_1 * NEW.die_size_1 + NEW.bonus_damage_1 + NEW.damage_dice_2 * NEW.die_size_2 + NEW.bonus_damage_2) * 50 / NEW.action_cost :: int);
    END IF;
    RETURN NEW;
  END;
' LANGUAGE plpgsql;

CREATE TRIGGER update_spell BEFORE INSERT OR UPDATE ON spells
    FOR EACH ROW EXECUTE FUNCTION update_spell();

    
    
CREATE OR REPLACE FUNCTION update_attack() RETURNS TRIGGER AS '
  BEGIN
    IF NEW.is_magic IS NULL THEN
        NEW.is_magic = false;
    END IF;
   
    IF NEW.damage_dice_1 < 0 THEN
        NEW.damage_dice_1 = 0;
    END IF;
    IF NEW.die_size_1 <= 0 THEN
        NEW.damage_dice_1 = 1;
    END IF;
    
    IF (NEW.damage_dice_2 * NEW.die_size_2 = 0 AND NEW.bonus_damage_2 = 0) OR NEW.damage_type_id_2 IS NULL THEN
        NEW.has_second_damage = false;
    ELSE
        NEW.has_second_damage = true;
    END IF;
    
    IF (NEW.has_second_damage = false) THEN
        NEW.experience_cost = ((NEW.damage_dice_1 * NEW.damage_dice_1 + NEW.bonus_damage_1) * 200 / NEW.action_cost :: int);
    ELSE
        NEW.experience_cost = ((NEW.damage_dice_1 * NEW.die_size_1 + NEW.bonus_damage_1 + NEW.damage_dice_2 * NEW.die_size_2 + NEW.bonus_damage_2) * 200 / NEW.action_cost :: int);
    END IF;
    RETURN NEW;
  END;
' LANGUAGE plpgsql;

CREATE TRIGGER update_attack BEFORE INSERT OR UPDATE ON attacks
    FOR EACH ROW EXECUTE FUNCTION update_attack();
    
CREATE OR REPLACE FUNCTION update_enemy_attack() RETURNS TRIGGER AS '
  BEGIN
    IF NEW.is_magic IS NULL THEN
        NEW.is_magic = false;
    END IF;
    
    IF NEW.damage_dice_1 < 0 THEN
        NEW.damage_dice_1 = 0;
    END IF;
    IF NEW.die_size_1 <= 0 THEN
        NEW.damage_dice_1 = 1;
    END IF;
    
    IF (NEW.damage_dice_2 * NEW.die_size_2 = 0 AND NEW.bonus_damage_2 = 0) OR NEW.damage_type_id_2 IS NULL THEN
        NEW.has_second_damage = false;
    ELSE
        NEW.has_second_damage = true;
    END IF;
    
    RETURN NEW;
  END;
' LANGUAGE plpgsql;

CREATE TRIGGER update_enemy_attack BEFORE INSERT OR UPDATE ON enemy_attacks
    FOR EACH ROW EXECUTE FUNCTION update_enemy_attack();
    
    
CREATE OR REPLACE FUNCTION new_player_default_abilities() RETURNS TRIGGER AS '
  BEGIN
    INSERT INTO repertoire (repertoire.player_id, attack_id) 
        VALUES (NEW.player_id, 1), (NEW.player_id, 2);
    INSERT INTO grimoire (grimoire.player_id, spell_id) 
        VALUES (NEW.player_id, 1), (NEW.player_id, 2);
    RETURN NULL;
  END;
' LANGUAGE plpgsql;

CREATE TRIGGER new_player_default_abilities AFTER INSERT ON players
    FOR EACH ROW EXECUTE FUNCTION new_player_default_abilities();
    

COMMIT TRANSACTION;

--Initial Value Setup
BEGIN TRANSACTION;

INSERT INTO spell_types (description)
  VALUES ('Combat'), ('Healing'), ('Utility');

INSERT INTO damage_types (description)
  VALUES ('Bludgeoning'), ('Piercing'), ('Slashing'), ('Radiant'), ('Necrotic'), ('Fire'), ('Cold'), ('Lightning'), ('Thunder'), ('Force'), ('Psychic'), ('Poison'), ('Acid');

INSERT INTO spells (name, action_cost, damage_dice_1, die_size_1, bonus_damage_1, target_self, damage_type_id_1, spell_type)
  VALUES ('Lesser Heal', 1, 1, 8, 3, true, 4, 2), ('Lesser Flare', 0.5, 1, 6, 0, false, 6, 1);
  
INSERT INTO attacks (name, action_cost, damage_dice_1, die_size_1, bonus_damage_1, damage_type_id_1)
  VALUES ('Punch', 0.5, 1, 4, 0, 1), ('Kick', 1, 1, 8, 0, 1);
  
INSERT INTO enemy_attacks (name, action_cost, damage_dice_1, die_size_1, bonus_damage_1, damage_type_id_1)
  VALUES ('Claw', 0.5, 1, 4, 1, 3), ('Bite', 1, 1, 6, 2, 2), ('Spear', 1, 1, 8, 1, 2), ('Dagger', 0.5, 1, 4, 2, 2), ('Sword', 1, 1, 8, 1, 3), ('Sucker Punch', 1.5, 2, 46, 1, 1);
  
--Insert enemies
INSERT INTO enemies (enemy_name, max_health, armor, hit_bonus, max_action_points, description)
  VALUES ('Goblin', 15, 12, 1, 1, 'A small green humanoid'), ('Kobold', 20, 13, 1.5, 2, 'A small lizard-like humanoid'), ('Bandit', 30, 14, 2, 3, 'A human with a bandana covering their face');

INSERT INTO enemy_repertoire (enemy_id, enemy_attack_id)
  VALUES (1, 4), (2, 1), (2, 2), (2, 3), (3, 4), (3, 5), (3, 6);

COMMIT TRANSACTION;

--Creating user Login
BEGIN TRANSACTION;

CREATE USER mock_combat_user
WITH PASSWORD 'mockcombat';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO mock_combat_user;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO mock_combat_user;

COMMIT TRANSACTION;