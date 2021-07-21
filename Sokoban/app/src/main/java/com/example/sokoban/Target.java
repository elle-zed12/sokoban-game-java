package com.example.sokoban;

public class Target extends Enterable {
	
	public Target(int x, int y) {
		super(x, y, '+');
	}
	
	protected String getMovableString(Moveable theMovable ) {
		return theMovable.toString().toUpperCase();
	}
}