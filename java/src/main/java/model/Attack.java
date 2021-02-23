package model;

public class Attack extends Action {

	private long attackId;
	private String damageType1;
	private String damageType2 = "";
	private int damageDie1;
	private int damageDie2;
	private int dieSize1;
	private int dieSize2;
	private int bonusDamage1;
	private int bonusDamage2;
	private boolean hasSecondDamage;
	private boolean magic;
	
	public boolean isMagic() {
		return magic;
	}

	public void setMagic(boolean magic) {
		this.magic = magic;
	}

	public Attack() {
		
	}
	
	public Attack(String name, long attackId, double actionCost, int xpCost, String damageType1, int damageDie1, int dieSize1, int bonusDamage1, String damageType2, int damageDie2, int dieSize2, int bonusDamage2, boolean magic) {
		super(name, actionCost, xpCost);
		this.attackId = attackId;
		this.damageType1 = damageType1;
		this.damageDie1 = damageDie1;
		this.dieSize1 = dieSize1;
		this.bonusDamage1 = bonusDamage1;
		this.damageType2 = damageType2;
		this.damageDie2 = damageDie2;
		this.dieSize2 = dieSize2;
		this.bonusDamage2 = bonusDamage2;
		hasSecondDamage = true;
		this.magic = magic;
	}
	
	public Attack(String name, long attackId, double actionCost, int xpCost, String damageType1, int damageDie1, int dieSize1, int bonusDamage1, boolean magic) {
		super(name, actionCost, xpCost);
		this.attackId = attackId;
		this.damageType1 = damageType1;
		this.damageDie1 = damageDie1;
		this.dieSize1 = dieSize1;
		this.bonusDamage1 = bonusDamage1;
		hasSecondDamage = false;
		this.magic = magic;
	}

	public String getDamageType1() {
		return damageType1;
	}

	public void setDamageType1(String damageType1) {
		this.damageType1 = damageType1;
	}

	public String getDamageType2() {
		return damageType2;
	}

	public void setDamageType2(String damageType2) {
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

	public long getAttackId() {
		return attackId;
	}

	public void setAttackId(long attackId) {
		this.attackId = attackId;
	}
	
	
}
