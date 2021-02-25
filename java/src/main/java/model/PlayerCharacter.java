package model;

import java.util.List;

public class PlayerCharacter extends Creature {
	
	private long playerId;
	private int manaMax = 15;
	private int mana = 15;
	private int strength = 10;
	private int dexterity = 10;
	private int constitution = 10;
	private int magic = 10;
	private int spentXp = 0;
	private int manaRegen = 1;
	private String ancestry;
	

	public void setManaMax(int manaMax) {
		this.manaMax = manaMax;
	}

	public PlayerCharacter(String name, long playerId, int xp, int hpMax, int manaMax, int baseAC, int hpRegen, int manaRegen,
			int profBonus, int strength, int dexterity, int constitution, int magic, int spentXp, List<Attack> attacks, List<Spell> spells) {
		super(name, xp, hpMax, baseAC, 1.5, hpRegen, profBonus, attacks, spells);
		this.playerId = playerId;
		this.strength = strength;
		this.dexterity = dexterity;
		this.constitution = constitution;
		this.magic = magic;
		this.spentXp = spentXp;
		this.manaRegen = manaRegen;
	}

	public PlayerCharacter(String name, long playerId, int xp, int hpMax,  int manaMax, int baseAC, int hpRegen, int manaRegen,
			int profBonus, int strength, int dexterity, int constitution, int magic, int spentXp) {
		super(name, xp, hpMax, baseAC, 1.5, hpRegen, profBonus);
		this.playerId = playerId;
		this.strength = strength;
		this.dexterity = dexterity;
		this.constitution = constitution;
		this.magic = magic;
		this.spentXp = spentXp;
		this.manaRegen = manaRegen;
	}
	
	public PlayerCharacter(String name) {
		super(name, 300, 15, 10, 1.5, 1, 1);
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public int getSpentXp() {
		return spentXp;
	}

	public void setSpentXp(int spentXp) {
		this.spentXp = spentXp;
	}

	public int getManaRegen() {
		return manaRegen;
	}

	public void setManaRegen(int manaRegen) {
		this.manaRegen = manaRegen;
	}
	
	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public String getAncestry() {
		return ancestry;
	}

	public void setAncestry(String ancestry) {
		this.ancestry = ancestry;
	}
	
	public int getMaxHp() {
		return this.getHpMax() + (this.constitution - 10) * 5 + this.getProfBonus() * 5;
	}
	
	public int getMaxMana() {
		return this.manaMax + (this.magic - 10) * 5  + this.getProfBonus() * 5;
	}
	
	public int getArmor() {
		return this.getBaseAC() + (this.dexterity - 10) / 2 + this.getProfBonus();
	}
	
	@Override
	public int getAttackBonus() {
		return this.getProfBonus() + (this.strength - 10) / 2; 
	}
	
	@Override
	public int getSpellBonus() {
		return this.getProfBonus() + (this.magic - 10) / 2;
	}
	
	public double getMaxActionPoints() {
		return this.getActionPointsMax() + ((double) ((this.getProfBonus() - 1) / 2) / 2);
	}
	
	public int getStrengthMod() {
		return (this.strength - 10) / 2;
	}
	
	public int getConstitutionMod() {
		return (this.constitution - 10) / 2;
	}
	
	public int getDexterityMod() {
		return (this.dexterity - 10) / 2;
	}
	
	public int getMagicMod() {
		return (this.magic - 10) / 2;
	}

	public void regenMana() {
		this.mana += this.manaRegen;
		if (this.mana > this.manaMax) {
			this.mana = this.manaMax;
		}
	}
	
	public void resetHp() {
		this.setHp(this.getHpMax());
	}
	
	public void resetMana() {
		this.setMana(this.manaMax);
	}
	
	public void resetActionPoints() {
		this.setActionPoints(this.getMaxActionPoints());
	}

	public int getManaMax() {
		return manaMax;
	}
	
	

}
