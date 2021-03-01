package mockCombat.model;

import java.util.List;

public class Enemy extends Creature {
	
	private long enemyId;
	private double challenge;
	private String description = "";
	private List<Spell> spells;
	private List<Attack> attacks;
	

	public Enemy(long enemyId, String name, int xp, int hpMax, int baseAC, double actionPointsMax, int hpRegen, int profBonus, double challenge, String description,
			List<Attack> attacks, List<Spell> spells) {
		super(name, xp, hpMax, baseAC, actionPointsMax, hpRegen, profBonus, attacks, spells);
		this.enemyId= enemyId;
		this.challenge = challenge;
		this.description = description;
		this.attacks = attacks;
		this.spells = spells;
	}

	public Enemy(long enemyId, String name, int xp, int hpMax, int baseAC, double actionPointsMax, int hpRegen, int profBonus, double challenge, String description) {
		super(name, xp, hpMax, baseAC, actionPointsMax, hpRegen, profBonus);
		this.enemyId = enemyId;
		this.challenge = challenge;
		this.description = description;
	}

	public double getChallenge() {
		return challenge;
	}

	public void setChallenge(double challenge) {
		this.challenge = challenge;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Spell> getSpells() {
		return spells;
	}

	public void setSpells(List<Spell> spells) {
		this.spells = spells;
	}

	public List<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(List<Attack> attacks) {
		this.attacks = attacks;
	}

	public long getEnemyId() {
		return enemyId;
	}

	public void setEnemyId(long enemyId) {
		this.enemyId = enemyId;
	}
	
	

}
