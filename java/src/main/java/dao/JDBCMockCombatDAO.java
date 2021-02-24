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
		String sqlStmt = "INSERT INTO attacks (name, action_cost, damage_dice_1, die_size_1, bonus_damage_1, damage_type_id_1)";
		
		return null;
	}
	
	@Override
	public Attack createEnemyAttack(Attack newAttack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spell createSpell(Spell newSpell) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerCharacter learnSpell(long playerId, long spellId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enemy enemyLearnAttack(long enemyId, long attackId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enemy enemyLearnSpell(long enemyId, long spellId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePlayer(PlayerCharacter updatedPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEnemy(Enemy updatedEnemy) {
		// TODO Auto-generated method stub

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
		Enemy enemy = new Enemy(name, experience, hpMax, baseAC, maxActionPoints, healthRegen, profBonus, challenge, description, attacks, spells);
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
			Attack attack = mapRowToAttack(results);
			output.add(attack);
		}
		return output;
	}
}
