package model;

public class Action {
	
	private String name;
	private double actionCost;
	private int xpCost;
	//private String description;
	
	public Action() {
		
	}
	
	public Action(String name, double actionCost, int xpCost) {
		this.name = name;
		this.actionCost = actionCost;
		this.xpCost = xpCost;
		
	}


	public String getName() {
		return name;
	}

	public double getActionCost() {
		return actionCost;
	}


	public int getXpCost() {
		return xpCost;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setActionCost(double actionCost) {
		this.actionCost = actionCost;
	}

	public void setXpCost(int xpCost) {
		this.xpCost = xpCost;
	}
	
	

}
