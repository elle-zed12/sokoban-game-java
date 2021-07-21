package com.example.sokoban;

public abstract class Placeable {

	public final Point location;
	protected final char symbol;
	
	public Placeable(int x, int y, char symbol) {
		this.location = new Point( x, y);
		this.symbol = symbol;
	}
	
	public Point where() {
		return this.location;
	}
	public int x() {
		return this.location.getX();
	}
	public int y() {
		return this.location.getY();
	}
	
	public String toString () {
		return "" + this.symbol;
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public boolean hasCrate() {
		return false;
	}


}
