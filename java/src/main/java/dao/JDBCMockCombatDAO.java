package dao;

import java.util.List;

import model.Attack;
import model.Enemy;
import model.PlayerCharacter;
import model.Spell;

public class JDBCMockCombatDAO implements MockCombatDAO {

	@Override
	public PlayerCharacter createPlayer(PlayerCharacter newPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayerCharacter> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Spell> getAllSpells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attack> getAllAttacks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enemy> getAllEnemies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerCharacter getPlayer(long playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enemy getEnemy(long enemyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spell getSpell(long spellId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attack getAttack(long attackId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enemy createEmeny(Enemy newEnemy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attack createAttack(Attack newAttack) {
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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
