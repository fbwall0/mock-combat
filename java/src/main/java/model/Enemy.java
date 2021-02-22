package model;

import java.util.List;

public class Enemy extends Creature {
	
	private double challenge;

	public Enemy(String name, int xp, int hpMax, int baseAC, double actionPointsMax, int hpRegen, int profBonus, double challenge,
			List<Attack> attacks, List<Spell> spells) {
		super(name, xp, hpMax, baseAC, actionPointsMax, hpRegen, profBonus, attacks, spells);
		this.challenge = challenge;
	}

	public Enemy(String name, int xp, int hpMax, int baseAC, double actionPointsMax, int hpRegen, int profBonus, double challenge) {
		super(name, xp, hpMax, baseAC, actionPointsMax, hpRegen, profBonus);
		this.challenge = challenge;
	}

	public double getChallenge() {
		return challenge;
	}

	public void setChallenge(double challenge) {
		this.challenge = challenge;
	}
	
	

}
