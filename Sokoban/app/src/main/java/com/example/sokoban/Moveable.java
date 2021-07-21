package com.example.sokoban;

public abstract class Moveable extends Placeable {
	public Moveable(int x, int y, char symbol) {
		super(x,  y, symbol);
	}
	public void set( Point newLocation) {
		this.location.set(newLocation);
	}
}
