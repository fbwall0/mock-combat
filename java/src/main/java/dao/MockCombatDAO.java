package dao;

import java.util.List;

import model.Attack;
import model.Enemy;
import model.PlayerCharacter;
import model.Spell;

public interface MockCombatDAO {
	
	long createPlayer(String name);
	
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
	
	void updateEnemy(Enemy updatedEnemy);
	
	void deleteEnemy(long enemyId);
	
	List<Spell> getPlayerSpells(long playerId);

	List<Attack> getPlayerAttacks(long playerId);
	
	List<Spell> getEnemySpells(long enemyId);

	List<Attack> getEnemyAttacks(long enemyId);

	Attack getEnemyAttack(long attackId);
}
