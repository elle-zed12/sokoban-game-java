package com.example.sokoban;

public enum Direction {
	UP(-1, 0),
	RIGHT(0,1),
	DOWN(1,0),
	LEFT(0,-1);
	
	public final int xAdjust;
	public final int yAdjust;
		
	Direction(int x, int y){
		xAdjust = x;
		yAdjust = y;
	}
	
	public Point adjust(Point where){
		return new Point( this.xAdjust + where.getX(), this.yAdjust + where.getY());
	}

}