package mockCombat.model;

import java.util.ArrayList;
import java.util.List;

public class Creature {
	
	private String name;
	private int xp;
	private int hpMax;
	private int hp;
 	private int baseAC;
	private double actionPointsMax;
	private double actionPoints;
	private int hpRegen;
	private int profBonus;
	private List<Attack> attacks = new ArrayList<>();
	private List<Spell> spells = new ArrayList<>();

	public Creature(String name, int xp, int hpMax, int baseAC, double actionPointsMax, int hpRegen, int profBonus,
			List<Attack> attacks, List<Spell> spells) {
		this.name = name;
		this.xp = xp;
		this.hpMax = hpMax;
		this.baseAC = baseAC;
		this.actionPointsMax = actionPointsMax;
		this.hpRegen = hpRegen;
		this.profBonus = profBonus;
		this.attacks = attacks;
		this.hp = hpMax;
		this.actionPoints = actionPointsMax;
		this.attacks = attacks;
		this.spells = spells;
	}
	
	public Creature(String name, int xp, int hpMax, int baseAC, double actionPointsMax, int hpRegen, int profBonus) {
		this.name = name;
		this.xp = xp;
		this.hpMax = hpMax;
		this.baseAC = baseAC;
		this.actionPointsMax = actionPointsMax;
		this.hpRegen = hpRegen;
		this.profBonus = profBonus;
		this.hp = hpMax;
		this.actionPoints = actionPointsMax;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getBaseAC() {
		return baseAC;
	}

	public void setBaseAC(int baseAC) {
		this.baseAC = baseAC;
	}

	public double getActionPointsMax() {
		return actionPointsMax;
	}

	public void setActionPointsMax(double actionPointsMax) {
		this.actionPointsMax = actionPointsMax;
	}

	public double getActionPoints() {
		return actionPoints;
	}

	public void setActionPoints(double actionPoints) {
		this.actionPoints = actionPoints;
	}

	public int getHpRegen() {
		return hpRegen;
	}

	public void setHpRegen(int hpRegen) {
		this.hpRegen = hpRegen;
	}

	public int getProfBonus() {
		return profBonus;
	}

	public void setProfBonus(int profBonus) {
		this.profBonus = profBonus;
	}

	public List<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(List<Attack> attacks) {
		this.attacks = attacks;
	}

	public List<Spell> getSpells() {
		return spells;
	}

	public void setSpells(List<Spell> spells) {
		this.spells = spells;
	}
	
	
	
	
	
	public void addAttack(Attack attack) {
		if (!this.attacks.contains(attack)) {
			this.attacks.add(attack);			
		}
	}
	
	public void addSpell(Spell spell) {
		if (!this.spells.contains(spell)) {
			this.spells.add(spell);			
		}
	}
	
	public void regenHp() {
		this.hp += this.hpRegen;
		if (this.hp > this.hpMax) {
			this.hp = this.hpMax;
		}
	}
	
	public void resetActionPoints() {
		this.actionPoints = this.actionPointsMax;
	}

	public void resetHp() {
		this.hp = this.hpMax;
	}
	
	
	public void takeDamage(int damageTaken) {
		this.hp -= damageTaken;
	}
	
	public int getAttackBonus() {
		return this.profBonus; 
	}
	
	public int getSpellBonus() {
		return this.profBonus; 
	}
	
}
