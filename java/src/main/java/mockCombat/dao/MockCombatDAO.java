package mockCombat.dao;

import java.util.List;

import mockCombat.model.Attack;
import mockCombat.model.Enemy;
import mockCombat.model.PlayerCharacter;
import mockCombat.model.Spell;

public interface MockCombatDAO {
	
	long createPlayer(String name);
	
	long createPlayer2();


	List<PlayerCharacter> getPlayers();
	
	List<Spell> getAllSpells();
	
	List<Attack> getAllAttacks();
	
	List<Enemy> getAllEnemies();
	
	PlayerCharacter getPlayer(long playerId);
	
	Enemy getEnemy(long enemyId);
	
	Spell getSpell(long spellId);
	
	Attack getAttack(long attackId);
	
	Enemy createEnemy(Enemy newEnemy);
	
	Attack createAttack(Attack newAttack);
	
	Spell createSpell(Spell newSpell);
	
	void deletePlayer(long playerId);
	
	PlayerCharacter learnAttack(long playerId, long attackId);
	
	PlayerCharacter learnSpell(long playerId, long spellId);
	
	Enemy enemyLearnAttack(long enemyId, long attackId);
	
	Enemy enemyLearnSpell(long enemyId, long spellId);
	
	void updatePlayer(PlayerCharacter updatedPlayer);
	
	Enemy updateEnemy(Enemy updatedEnemy);
	
	void deleteEnemy(long enemyId);
	
	List<Spell> getPlayerSpells(long playerId);

	List<Attack> getPlayerAttacks(long playerId);
	
	List<Spell> getEnemySpells(long enemyId);

	List<Attack> getEnemyAttacks(long enemyId);

	Attack getEnemyAttack(long attackId);

	List<Spell> getUnknownSpells(long playerId);

	List<Attack> getUnknownAttacks(long playerId);

	List<Spell> getUnknownEnemySpells(long enemyId);

	List<Attack> getUnknownEnemyAttacks(long enemyId);

	Attack createEnemyAttack(Attack newAttack);

	String getDamageType(Integer damageType2Id);

	List<Attack> getAllEnemyAttacks();
}
