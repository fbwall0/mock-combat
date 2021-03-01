package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.MockCombatDAO;
import model.Attack;
import model.Enemy;
import model.PlayerCharacter;
import model.Spell;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CombatController {
	
	@Autowired
	MockCombatDAO combat;
	
	@GetMapping(path = "/players")
	public List<PlayerCharacter> getAllPlayers() {
		List<PlayerCharacter> players = combat.getPlayers();
		
		return players;
	}
	
	@GetMapping("/enemies")
	public List<Enemy> getAllEnemies() {
		List<Enemy> enemies = combat.getAllEnemies();
		
		return enemies;
	}
	
	@GetMapping(path = "/spells")
	public List<Spell> getAllSpells() {
		List<Spell> spells = combat.getAllSpells();
		
		return spells;
	}
	
	@GetMapping(path = "/attacks") 
	public List<Attack> getAllAttacks() {
		List<Attack> attacks = combat.getAllAttacks();
		
		return attacks;
	}

	@GetMapping(path = "/attacksE") 
	public List<Attack> getAllEnemyAttacks() {
		List<Attack> attacks = combat.getAllEnemyAttacks();
		
		return attacks;
	}
	
	@GetMapping(path = "/player/{id}")
	public PlayerCharacter getPlayer(@PathVariable long id) {
		PlayerCharacter player = combat.getPlayer(id);
		
		return player;
	}
	
	@GetMapping(path = "/enemy/{id}")
	public Enemy getEnemy(@PathVariable long id) {
		Enemy enemy = combat.getEnemy(id);
		
		return enemy;
	}
	
	@GetMapping(path = "/spell/{id}") 
	public Spell getSpell(@PathVariable long id) {
		Spell spell = combat.getSpell(id);
		
		return spell;
	}
	
	@GetMapping(path = "/attack/{id}")
	public Attack getAttack(@PathVariable long id) {
		Attack attack = combat.getAttack(id);
		
		return attack;
	}
	
	@GetMapping(path = "/attackE/{id}")
	public Attack getEnemyAttack(@PathVariable long id) {
		Attack attack = combat.getEnemyAttack(id);
		
		return attack;
	}
	
	@GetMapping(path = "/player/{id}/learn/spells")
	public List<Spell> getUnknownSpells(@PathVariable long id) {
		List<Spell> spells = combat.getUnknownSpells(id);
		
		return spells;
	}
	
	@GetMapping(path = "/player/{id}/learn/attacks")
	public List<Attack> getUnknwonAttacks(@PathVariable long id) {
		List<Attack> attacks = combat.getUnknownAttacks(id);
		
		return attacks;
	}
	
	@GetMapping(path = "/enemy/{id}/learn/spells")
	public List<Spell> getUnknownEnemySpells(@PathVariable long id) {
		List<Spell> spells = combat.getUnknownEnemySpells(id);
		
		return spells;
	}
	
	@GetMapping(path = "/enemy/{id}/learn/attacks")
	public List<Attack> getUnknownEnemyAttacks(@PathVariable long id) {
		List<Attack> attacks = combat.getUnknownEnemyAttacks(id);
		
		return attacks;
	}
	
	@PostMapping(path = "/enemy")
	public Enemy createEnemy(@RequestBody Enemy newEnemy) {
		Enemy enemy = combat.createEnemy(newEnemy);
		
		return enemy;
	}
	
	@PostMapping(path = "/attack")
	public Attack createAttack(@RequestBody Attack newAttack) {
		Attack attack = combat.createAttack(newAttack);
		
		return attack;
	}
	
	@PostMapping(path = "/spell")
	public Spell createSpell(@RequestBody Spell newSpell) {
		Spell spell = combat.createSpell(newSpell);
		
		return spell;
	}
	
	@PostMapping(path = "/attackE")
	public Attack createEnemyAttack(@RequestBody Attack newAttack) {
		Attack attack = combat.createEnemyAttack(newAttack);
		
		return attack;
	}
	
	@DeleteMapping(path = "/player/{id}")
	public void deletePlayer(@PathVariable long id) {
		combat.deletePlayer(id);
	}
	
	@DeleteMapping(path = "/enemy/{id}")
	public void deleteEnemy(@PathVariable long id) {
		combat.deleteEnemy(id);
	}
	
	@PostMapping(path = "/player/{id}/learn/spell/{spellId}")
	public PlayerCharacter learnSpell(@PathVariable long id, @PathVariable long spellId) {
		PlayerCharacter player = combat.learnSpell(id, spellId);
		
		return player;
	}
	
	@PostMapping(path = "/player/{id}/learn/attack/{attackId}")
	public PlayerCharacter learnAttack(@PathVariable long id, @PathVariable long attackId) {
		PlayerCharacter player = combat.learnAttack(id, attackId);
		
		return player;
	}
	
	@PostMapping(path = "/enemy/{id}/learn/spell/{spellId}")
	public Enemy enemyLearnSpell(@PathVariable long id, @PathVariable long spellId) {
		Enemy enemy = combat.enemyLearnSpell(id, spellId);
		
		return enemy;
	}
	
	@PostMapping(path = "/enemy/{id}/learn/attack/{attackId}")
	public Enemy enemyLearnAttack(@PathVariable long id, @PathVariable long attackId) {
		Enemy enemy = combat.enemyLearnAttack(id, attackId);
		
		return enemy;
	}
	
	@PutMapping(path = "/player/{id}")
	public void updatePlayer(@RequestBody PlayerCharacter updatedPlayer) {
		combat.updatePlayer(updatedPlayer);
	}
	
	@PutMapping(path = "/enemy/{id}")
	public Enemy updateEnemy(@RequestBody Enemy updatedEnemy) {
		Enemy enemy = combat.updateEnemy(updatedEnemy);
		
		return enemy;
	}
	
	@GetMapping(path = "/player/{id}/spells")
	public List<Spell> getPlayerSpells(@PathVariable long id) {
		List<Spell> spells = combat.getPlayerSpells(id);
		
		return spells;
	}
	
	@GetMapping(path = "/player/{id}/attacks")
	public List<Attack> getPlayerAttacks(@PathVariable long id) {
		List<Attack> attacks = combat.getPlayerAttacks(id);
		
		return attacks;
	}
	
	@GetMapping(path = "/enemy/{id}/spells")
	public List<Spell> getEnemySpells(@PathVariable long id) {
		List<Spell> spells = combat.getEnemySpells(id);
		
		return spells;
	}
	
	@GetMapping(path = "/enemy/{id}/attacks")
	public List<Attack> getEnemyAttacks(@PathVariable long id) {
		List<Attack> attacks = combat.getEnemyAttacks(id);
		
		return attacks;
	}
	
	@GetMapping(path = "/damageType/{id}")
	public String getDamageType(@PathVariable Integer id) {
		String description = combat.getDamageType(id);
		
		return description;
	}
	
}
