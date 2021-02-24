package model;

public class Spell extends Action{

	private long spellId;
	private int damageType1;
	private int damageType2 = 0;
	private int damageDie1;
	private int damageDie2;
	private int dieSize1;
	private int dieSize2;
	private int bonusDamage1;
	private int bonusDamage2;
	private boolean hasSecondDamage;
	private int spellType;
	private int manaCost;
	private boolean targetSelf;
	
	
	public Spell() {
		
	}
	
	public Spell(String name, long spellId, double actionCost, int xpCost, int damageType1, int damageDie1, int dieSize1, int bonusDamage1, int damageType2, int damageDie2, int dieSize2, int bonusDamage2, int spellType, int manaCost, boolean targetSelf) {
		super(name, actionCost, xpCost);
		this.spellId = spellId;
		this.damageType1 = damageType1;
		this.damageDie1 = damageDie1;
		this.dieSize1 = dieSize1;
		this.bonusDamage1 = bonusDamage1;
		this.damageType2 = damageType2;
		this.damageDie2 = damageDie2;
		this.dieSize2 = dieSize2;
		this.bonusDamage2 = bonusDamage2;
		hasSecondDamage = true;
		this.spellType = spellType;
		this.manaCost = manaCost;
		this.targetSelf = targetSelf;
	}
	
	public Spell(String name, long spellId, double actionCost, int xpCost, int damageType1, int damageDie1, int dieSize1, int bonusDamage1, int spellType, int manaCost, boolean targetSelf) {
		super(name, actionCost, xpCost);
		this.spellId = spellId;
		this.damageType1 = damageType1;
		this.damageDie1 = damageDie1;
		this.dieSize1 = dieSize1;
		this.bonusDamage1 = bonusDamage1;
		hasSecondDamage = false;
		this.spellType = spellType;
		this.manaCost = manaCost;
		this.targetSelf = targetSelf;
	}

	public int getDamageType1() {
		return damageType1;
	}

	public void setDamageType1(int damageType1) {
		this.damageType1 = damageType1;
	}

	public int getDamageType2() {
		return damageType2;
	}

	public void setDamageType2(int damageType2) {
		this.damageType2 = damageType2;
	}

	public int getDamageDie1() {
		return damageDie1;
	}

	public void setDamageDie1(int damageDie1) {
		this.damageDie1 = damageDie1;
	}

	public int getDamageDie2() {
		return damageDie2;
	}

	public void setDamageDie2(int damageDie2) {
		this.damageDie2 = damageDie2;
	}

	public int getDieSize1() {
		return dieSize1;
	}

	public void setDieSize1(int dieSize1) {
		this.dieSize1 = dieSize1;
	}

	public int getDieSize2() {
		return dieSize2;
	}

	public void setDieSize2(int dieSize2) {
		this.dieSize2 = dieSize2;
	}

	public int getBonusDamage1() {
		return bonusDamage1;
	}

	public void setBonusDamage1(int bonusDamage1) {
		this.bonusDamage1 = bonusDamage1;
	}

	public int getBonusDamage2() {
		return bonusDamage2;
	}

	public void setBonusDamage2(int bonusDamage2) {
		this.bonusDamage2 = bonusDamage2;
	}

	public boolean isHasSecondDamage() {
		return hasSecondDamage;
	}

	public void setHasSecondDamage(boolean hasSecondDamage) {
		this.hasSecondDamage = hasSecondDamage;
	}

	public int getSpellType() {
		return spellType;
	}

	public void setSpellType(int spellType) {
		this.spellType = spellType;
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}

	public boolean isTargetSelf() {
		return targetSelf;
	}

	public void setTargetSelf(boolean targetSelf) {
		this.targetSelf = targetSelf;
	}

	public long getSpellId() {
		return spellId;
	}

	public void setSpellId(long spellId) {
		this.spellId = spellId;
	}
	
	
	
}
