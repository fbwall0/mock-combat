package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.Attack;

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

	
	public Creature(String name, int xp, int hpMax, int baseAC, double actionPointsMax, int hpRegen, int profBonus,
			List<Attack> attacks) {
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
	
	
}
