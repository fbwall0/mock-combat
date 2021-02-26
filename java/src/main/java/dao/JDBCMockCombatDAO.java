package dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import model.Attack;
import model.Enemy;
import model.PlayerCharacter;
import model.Spell;

@Component
public class JDBCMockCombatDAO implements MockCombatDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCMockCombatDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long createPlayer(String name) {
		String sqlStmt = "INSERT INTO players (name) VALUES (?) RETURNING player_id";
		long output = jdbcTemplate.queryForObject(sqlStmt, long.class, new Object[] {name});
		return output;
	}

	@Override
	public List<PlayerCharacter> getPlayers() {
		String sqlStmt = "SELECT * FROM players";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt);
		List<PlayerCharacter> players = new ArrayList<>();
		while (results.next()) {
			PlayerCharacter player = mapRowToPlayer(results);
			players.add(player);
		}
		return players;
	}

	@Override
	public List<Spell> getAllSpells() {
		String sqlStmt = "SELECT * FROM spells";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt);
		List<Spell> spells = new ArrayList<>();
		while (results.next()) {
			Spell spell = mapRowToSpell(results);
			spells.add(spell);
		}
		return spells;
	}

	@Override
	public List<Attack> getAllAttacks() {
		String sqlStmt = "SELECT * FROM attacks";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt);
		List<Attack> attacks = new ArrayList<>();
		while (results.next()) {
			Attack attack = mapRowToAttack(results);
			attacks.add(attack);
		}
		return attacks;
	}
	
	@Override
	public List<Attack> getAllEnemyAttacks() {
		String sqlStmt = "SELECT * FROM enemy_attacks";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt);
		List<Attack> attacks = new ArrayList<>();
		while (results.next()) {
			Attack attack = mapRowToEnemyAttack(results);
			attacks.add(attack);
		}
		return attacks;
	}
	
	@Override
	public List<Spell> getUnknownSpells(long playerId) {
		String sqlStmt = "SELECT * FROM spells WHERE spell_id NOT IN (SELECT spell_id FROM grimoire WHERE player_id = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt, playerId);
		List<Spell> spells = new ArrayList<>();
		while (results.next()) {
			Spell spell = mapRowToSpell(results);
			spells.add(spell);
		}
		return spells;
	}

	@Override
	public List<Attack> getUnknownAttacks(long playerId) {
		String sqlStmt = "SELECT * FROM attacks WHERE attack_id NOT IN (SELECT attack_id FROM repertoire WHERE player_id = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt, playerId);
		List<Attack> attacks = new ArrayList<>();
		while (results.next()) {
			Attack attack = mapRowToAttack(results);
			attacks.add(attack);
		}
		return attacks;
	}

	@Override
	public List<Enemy> getAllEnemies() {
		String sqlStmt = "SELECT * FROM enemies";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt);
		List<Enemy> enemies = new ArrayList<>();
		while (results.next()) {
			Enemy enemy = mapRowToEnemy(results);
			enemies.add(enemy);
		}
		return enemies;
	}
	
	@Override
	public List<Spell> getUnknownEnemySpells(long enemyId) {
		String sqlStmt = "SELECT * FROM spells WHERE spell_id NOT IN (SELECT spell_id FROM enemy_grimoire WHERE enemy_id = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt, enemyId);
		List<Spell> spells = new ArrayList<>();
		while (results.next()) {
			Spell spell = mapRowToSpell(results);
			spells.add(spell);
		}
		return spells;
	}

	@Override
	public List<Attack> getUnknownEnemyAttacks(long enemyId) {
		String sqlStmt = "SELECT * FROM attacks WHERE attack_id NOT IN (SELECT attack_id FROM enemy_repertoire WHERE enemy_id = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt, enemyId);
		List<Attack> attacks = new ArrayList<>();
		while (results.next()) {
			Attack attack = mapRowToAttack(results);
			attacks.add(attack);
		}
		return attacks;
	}

	@Override
	public PlayerCharacter getPlayer(long playerId) {
		String sqlStmt = "SELECT * FROM players WHERE player_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlStmt, playerId);
		PlayerCharacter player = null;
		if (result.next()) {
			player = mapRowToPlayer(result);
		}
		return player;
	}

	@Override
	public Enemy getEnemy(long enemyId) {
		String sqlStmt = "SELECT * FROM enemies WHERE enemy_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlStmt, enemyId);
		Enemy enemy = null;
		if (result.next()) {
			enemy = mapRowToEnemy(result);
		}
		return enemy;
	}

	@Override
	public Spell getSpell(long spellId) {
		String sqlStmt = "SELECT * FROM spells WHERE spell_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlStmt, spellId);
		Spell spell = null;
		if (result.next()) {
			spell = mapRowToSpell(result);
		}
		return spell;
	}

	@Override
	public Attack getAttack(long attackId) {
		String sqlStmt = "SELECT * FROM attacks WHERE attack_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlStmt, attackId);
		Attack attack = null;
		if (result.next()) {
			attack = mapRowToAttack(result);
		}
		return attack;
	}
	
	@Override
	public Attack getEnemyAttack(long attackId) {
		String sqlStmt = "SELECT * FROM enemy_attacks WHERE attack_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlStmt, attackId);
		Attack attack = null;
		if (result.next()) {
			attack = mapRowToAttack(result);
		}
		return attack;
	}

	@Override
	public Enemy createEnemy(Enemy newEnemy) {
		String sqlStmt = "INSERT INTO enemies (enemy_name, max_health, armor, hit_bonus, max_action_points, description, health_regen) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING enemy_id";
		long enemyId = jdbcTemplate.queryForObject(sqlStmt, long.class, new Object[] {newEnemy.getName(), newEnemy.getHpMax(), newEnemy.getBaseAC(), newEnemy.getProfBonus(), newEnemy.getActionPointsMax(), newEnemy.getDescription(), newEnemy.getHpRegen()});
		return getEnemy(enemyId);
	}

	@Override
	public Attack createAttack(Attack newAttack) {
		String sqlStmt = "";
		long attackId = 0;
		if (!newAttack.isHasSecondDamage()) {
			sqlStmt = "INSERT INTO attacks (name, action_cost, damage_dice_1, die_size_1, bonus_damage_1, damage_type_id_1, is_magic) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING attack_id";
			attackId = jdbcTemplate.update(sqlStmt, long.class, new Object[] {newAttack.getName(), newAttack.getActionCost(), newAttack.getDamageDie1(), newAttack.getDieSize1(), newAttack.getBonusDamage1(), newAttack.getDamageType1(), newAttack.isMagic()});
		} else {
			sqlStmt = "INSERT INTO attacks (name, action_cost, damage_dice_1, die_size_1, bonus_damage_1, damage_type_id_1, is_magic, damage_dice_2, die_size_2, bonus_damage_2, damage_type_id_2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING attack_id";
			attackId = jdbcTemplate.update(sqlStmt, long.class, new Object[] {newAttack.getName(), newAttack.getActionCost(), newAttack.getDamageDie1(), newAttack.getDieSize1(), newAttack.getBonusDamage1(), newAttack.getDamageType1(), newAttack.isMagic(), newAttack.getDamageDie2(), newAttack.getDieSize2(), newAttack.getBonusDamage2(), newAttack.getDamageType2()});
		}
		return getAttack(attackId);
	}
	
	@Override
	public Attack createEnemyAttack(Attack newAttack) {
		String sqlStmt = "";
		long attackId = 0;
		if (!newAttack.isHasSecondDamage()) {
			sqlStmt = "INSERT INTO attacks (name, action_cost, damage_dice_1, die_size_1, bonus_damage_1, damage_type_id_1, is_magic) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING attack_id";
			attackId = jdbcTemplate.update(sqlStmt, long.class, new Object[] {newAttack.getName(), newAttack.getActionCost(), newAttack.getDamageDie1(), newAttack.getDieSize1(), newAttack.getBonusDamage1(), newAttack.getDamageType1(), newAttack.isMagic()});
		} else {
			sqlStmt = "INSERT INTO attacks (name, action_cost, damage_dice_1, die_size_1, bonus_damage_1, damage_type_id_1, is_magic, damage_dice_2, die_size_2, bonus_damage_2, damage_type_id_2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING attack_id";
			attackId = jdbcTemplate.update(sqlStmt, long.class, new Object[] {newAttack.getName(), newAttack.getActionCost(), newAttack.getDamageDie1(), newAttack.getDieSize1(), newAttack.getBonusDamage1(), newAttack.getDamageType1(), newAttack.isMagic(), newAttack.getDamageDie2(), newAttack.getDieSize2(), newAttack.getBonusDamage2(), newAttack.getDamageType2()});
		}
		newAttack.setAttackId(attackId);
		return newAttack;
	}

	@Override
	public Spell createSpell(Spell newSpell) {
		String sqlStmt = "";
		long spellId = 0;
		if (!newSpell.isHasSecondDamage()) {
			sqlStmt = "INSERT INTO attacks (name, action_cost, damage_dice_1, die_size_1, bonus_damage_1, damage_type_id_1, mana_cost, target_self, spell_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING spell_id";
			spellId = jdbcTemplate.update(sqlStmt, long.class, new Object[] {newSpell.getName(), newSpell.getActionCost(), newSpell.getDamageDie1(), newSpell.getDieSize1(), newSpell.getBonusDamage1(), newSpell.getDamageType1(), newSpell.getManaCost(), newSpell.isTargetSelf(), newSpell.getSpellType()});
		} else {
			sqlStmt = "INSERT INTO attacks (name, action_cost, damage_dice_1, die_size_1, bonus_damage_1, damage_type_id_1, mana_cost, target_self, spell_type, damage_dice_2, die_size_2, bonus_damage_2, damage_type_id_2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING spell_id";
			spellId = jdbcTemplate.update(sqlStmt, long.class, new Object[] {newSpell.getName(), newSpell.getActionCost(), newSpell.getDamageDie1(), newSpell.getDieSize1(), newSpell.getBonusDamage1(), newSpell.getDamageType1(), newSpell.getManaCost(), newSpell.isTargetSelf(), newSpell.getSpellType(), newSpell.getDamageDie2(), newSpell.getDieSize2(), newSpell.getBonusDamage2(), newSpell.getDamageType2()});
		}
		return getSpell(spellId);
	}

	@Override
	public void deletePlayer(long playerId) {
		String repertoireDeletion = "DELETE from repertoire WHERE player_id = ?";
		String grimoireDeletion = "DELETE from grimoire WHERE player_id = ?";
		String playerDeletion = "DELETE from players WHERE player_id = ?";
		jdbcTemplate.update(repertoireDeletion);
		jdbcTemplate.update(grimoireDeletion);
		jdbcTemplate.update(playerDeletion);
	}

	@Override
	public PlayerCharacter learnAttack(long playerId, long attackId) {
		String sqlStmt = "INSERT INTO repertoire (player_id, attack_id) VALUES (?, ?)";
		jdbcTemplate.update(sqlStmt, playerId, attackId);
		return getPlayer(playerId);
	}

	@Override
	public PlayerCharacter learnSpell(long playerId, long spellId) {
		String sqlStmt = "INSERT INTO grimoire (player_id, spell_id) VALUES (?, ?)";
		jdbcTemplate.update(sqlStmt, playerId, spellId);
		return getPlayer(playerId);
	}

	@Override
	public Enemy enemyLearnAttack(long enemyId, long attackId) {
		String sqlStmt = "INSERT INTO enemy_repertoire (enemy_id, attack_id) VALUES (?, ?)";
		jdbcTemplate.update(sqlStmt, enemyId, attackId);
		return getEnemy(enemyId);
	}

	@Override
	public Enemy enemyLearnSpell(long enemyId, long spellId) {
		String sqlStmt = "INSERT INTO enemy_grimoire (enemy_id, spell_id) VALUES (?, ?)";
		jdbcTemplate.update(sqlStmt, enemyId, spellId);
		return getEnemy(enemyId);
	}

	@Override
	public void updatePlayer(PlayerCharacter updatedPlayer) {
		String sqlStmt = "UPDATE players SET player_name = ?, experience = ?, spent_experience = ?, proficiency_bonus = ?, base_health = ?, base_mana = ?, base_armor = ?, health_regen = ?, mana_regen = ?, strength = ?, dexterity = ?, constitution = ?, magic = ? WHERE player_id = ?";
		jdbcTemplate.update(sqlStmt, updatedPlayer.getName(), updatedPlayer.getXp(), updatedPlayer.getSpentXp(), updatedPlayer.getProfBonus(), updatedPlayer.getHpMax(), updatedPlayer.getManaMax(), updatedPlayer.getBaseAC(), updatedPlayer.getHpRegen(), updatedPlayer.getManaRegen(), updatedPlayer.getStrength(), updatedPlayer.getDexterity(), updatedPlayer.getConstitution(), updatedPlayer.getMagic(), updatedPlayer.getPlayerId());
	}

	@Override
	public Enemy updateEnemy(Enemy updatedEnemy) {
		String sqlStmt = "UPDATE enemies SET enemy_name = ?, hit_bonus = ?, max_health = ?, armor = ?, health_regen = ?, max_action_points = ? , description = ? WHERE enemy_id = ?";
		jdbcTemplate.update(sqlStmt, updatedEnemy.getName(), updatedEnemy.getProfBonus(), updatedEnemy.getHpMax(), updatedEnemy.getBaseAC(), updatedEnemy.getHpRegen(), updatedEnemy.getActionPointsMax(), updatedEnemy.getDescription());
		return getEnemy(updatedEnemy.getEnemyId());
	}

	@Override
	public void deleteEnemy(long enemyId) {
		String repertoireDeletion = "DELETE from enemy_repertoire WHERE enemy_id = ?";
		String grimoireDeletion = "DELETE from enemy_grimoire WHERE enemy_id = ?";
		String enemyDeletion = "DELETE from enemies WHERE enemy_id = ?";
		jdbcTemplate.update(repertoireDeletion);
		jdbcTemplate.update(grimoireDeletion);
		jdbcTemplate.update(enemyDeletion);

	}

	private PlayerCharacter mapRowToPlayer(SqlRowSet results) {
		String name = results.getString("player_name");
		long playerId = results.getLong("player_id");
		int experience = results.getInt("experience");
		int hpMax = results.getInt("base_hp");
		int manaMax = results.getInt("base_mana");
		int baseAC = results.getInt("base_armor");
		int hpRegen = results.getInt("health_regen");
		int manaRegen = results.getInt("mana_regen");
		int profBonus = results.getInt("proficiency_bonus");
		int strength = results.getInt("strength");
		int dexterity = results.getInt("dexterity");
		int constitution = results.getInt("constitution");
		int magic = results.getInt("magic");
		int spentXp = results.getInt("spent_experience");
		PlayerCharacter player = new PlayerCharacter(name, playerId, experience, hpMax,  manaMax, baseAC, hpRegen, manaRegen,
			 profBonus, strength, dexterity, constitution, magic, spentXp);
		List<Attack> attacks = getPlayerAttacks(playerId);
		List<Spell> spells = getPlayerSpells(playerId);
		for (Attack attack : attacks) {
			player.addAttack(attack);
		}
		for (Spell spell : spells) {
			player.addSpell(spell);
		}
		return player;
	}

	@Override
	public List<Spell> getPlayerSpells(long playerId) {
		String sqlStmt = "SELECT * FROM spells WHERE spell_id IN (SELECT spell_id FROM grimoire WHERE player_id = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt, playerId);
		List<Spell> output = new ArrayList<>();
		while (results.next()) {
			Spell spell = mapRowToSpell(results);
			output.add(spell);
		}
		return output;
	}

	private Spell mapRowToSpell(SqlRowSet results) {
		Spell output;
		long spellId = results.getLong("spell_id");
		String name = results.getString("name");
		double actionCost = results.getDouble("action_cost");
		int xpCost = results.getInt("experience_cost");
		int damageType1Id = results.getInt("damage_type_id_1");
		int damageDie1 = results.getInt("damage_dice_1");
		int dieSize1 = results.getInt("die_size_1");
		int bonusDamage1 = results.getInt("bonus_damage_1");
		int spellType = results.getInt("spell_type");
		int manaCost = results.getInt("mana_cost");
		boolean targetSelf = results.getBoolean("target_self");
		int damageDie2 = results.getInt("damage_dice_2");
		int dieSize2 = results.getInt("die_size_2");
		int bonusDamage2 = results.getInt("bonus_damage_2");
		if (damageDie2 * dieSize2 > 0 || bonusDamage2 > 0) {
			Integer damageType2Id = results.getInt("damage_type_2");
			output = new Spell(name, spellId, actionCost, xpCost, damageType1Id, damageDie1, dieSize1, bonusDamage1, damageType2Id, damageDie2, dieSize2, bonusDamage2, spellType, manaCost, targetSelf);
		} else {
			output = new Spell(name, spellId, actionCost, xpCost, damageType1Id, damageDie1, dieSize1, bonusDamage1, spellType, manaCost, targetSelf);
		}
		return output;
	}

	@Override
	public String getDamageType(Integer damageType2Id) {
		String sqlStmt = "SELECT description FROM damage_types WHERE damage_type_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlStmt, damageType2Id);
		String output = "";
		if (result.next()) {
			output = result.getString("description");
		}
		return output;
	}

	@Override
	public List<Attack> getPlayerAttacks(long playerId) {
		String sqlStmt = "SELECT * FROM attacks WHERE attack_id IN (SELECT attack_id FROM repertoire WHERE player_id = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt, playerId);
		List<Attack> output = new ArrayList<>();
		while (results.next()) {
			Attack attack = mapRowToAttack(results);
			output.add(attack);
		}
		return output;
	}

	private Attack mapRowToAttack(SqlRowSet results) {
		Attack output;
		long attackId = results.getLong("spell_id");
		String name = results.getString("name");
		double actionCost = results.getDouble("action_cost");
		int xpCost = results.getInt("experience_cost");
		int damageType1Id = results.getInt("damage_type_id_1");
		int damageDie1 = results.getInt("damage_dice_1");
		int dieSize1 = results.getInt("die_size_1");
		int bonusDamage1 = results.getInt("bonus_damage_1");
		boolean magic = results.getBoolean("is_magic");
		int damageDie2 = results.getInt("damage_dice_2");
		int dieSize2 = results.getInt("die_size_2");
		int bonusDamage2 = results.getInt("bonus_damage_2");
		if (damageDie2 * dieSize2 > 0 || bonusDamage2 > 0) {
			Integer damageType2Id = results.getInt("damage_type_2");
			output = new Attack(name, attackId, actionCost, xpCost, damageType1Id, damageDie1, dieSize1, bonusDamage1, damageType2Id, damageDie2, dieSize2, bonusDamage2, magic);
		} else {
			output = new Attack(name, attackId, actionCost, xpCost, damageType1Id, damageDie1, dieSize1, bonusDamage1, magic);
		}
		return output;
	}
	
	private Attack mapRowToEnemyAttack(SqlRowSet results) {
		Attack output;
		long attackId = results.getLong("spell_id");
		String name = results.getString("name");
		double actionCost = results.getDouble("action_cost");
		int xpCost = results.getInt("experience_cost");
		int damageType1Id = results.getInt("damage_type_id_1");
		int damageDie1 = results.getInt("damage_dice_1");
		int dieSize1 = results.getInt("die_size_1");
		int bonusDamage1 = results.getInt("bonus_damage_1");
		boolean magic = results.getBoolean("is_magic");
		int damageDie2 = results.getInt("damage_dice_2");
		int dieSize2 = results.getInt("die_size_2");
		int bonusDamage2 = results.getInt("bonus_damage_2");
		if (damageDie2 * dieSize2 > 0 || bonusDamage2 > 0) {
			Integer damageType2Id = results.getInt("damage_type_2");
			output = new Attack(name, attackId, actionCost, damageType1Id, damageDie1, dieSize1, bonusDamage1, damageType2Id, damageDie2, dieSize2, bonusDamage2, magic);
		} else {
			output = new Attack(name, attackId, actionCost, damageType1Id, damageDie1, dieSize1, bonusDamage1, magic);
		}
		return output;
	}

	private Enemy mapRowToEnemy(SqlRowSet result) {
		Long enemyId = result.getLong("enemy_id");
		String name = result.getString("enemy_name");
		int experience = result.getInt("experience");
		int hpMax = result.getInt("max_health");
		int baseAC = result.getInt("armor");
		double challenge = result.getDouble("challenge_rating");
		int profBonus = result.getInt("hit_bonus");
		double maxActionPoints = result.getDouble("max_action_points");
		int healthRegen = result.getInt("health_regen");
		String description = result.getString("description");
		List<Attack> attacks = getEnemyAttacks(enemyId);
		List<Spell> spells = getEnemySpells(enemyId);
		Enemy enemy = new Enemy(enemyId, name, experience, hpMax, baseAC, maxActionPoints, healthRegen, profBonus, challenge, description, attacks, spells);
		return enemy;
	}
	
	@Override
	public List<Spell> getEnemySpells(long enemyId) {
		String sqlStmt = "SELECT * FROM spells WHERE spell_id IN (SELECT spell_id FROM enemy_grimoire WHERE enemy_id = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt, enemyId);
		List<Spell> output = new ArrayList<>();
		while (results.next()) {
			Spell spell = mapRowToSpell(results);
			output.add(spell);
		}
		return output;
	}

	@Override
	public List<Attack> getEnemyAttacks(long enemyId) {
		String sqlStmt = "SELECT * FROM enemy_attacks WHERE enemy_attack_id IN (SELECT enemy_attack_id FROM enemy_repertoire WHERE enemy_id = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStmt, enemyId);
		List<Attack> output = new ArrayList<>();
		while (results.next()) {
			Attack attack = mapRowToEnemyAttack(results);
			output.add(attack);
		}
		return output;
	}
}
