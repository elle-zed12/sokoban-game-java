package com.example.sokoban;

public class Level {
	protected final String name;
	protected final int width;
	protected final int height;
	protected final Placeable[][] allPlaceables ;
	protected Worker myWorker;
	protected int moveCount = 0;
	protected int completedCount = 0;
	public int targetCount = 0;
	public int crateOnTarget = 0;
	protected final String startingData;
	Placeable workerOnTarget;
	
	public Level(String name, int height, int width, String data){
		this.name = name;
		this.width = width;
		this.height = height;
		this.startingData = data;
		this.allPlaceables = new Placeable[height][width];
		this.restart();
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCompletedCount() {
		return this.completedCount;
	}
	
	public int getMoveCount() {
		return this.moveCount;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void addedTarget() {
		this.targetCount ++;
	}
	
	protected Placeable makePlaceable(int x, int y, char symbol) {

		Placeable newPlaceable = new Empty(x, y);
		
		switch (symbol) {

			case '#': {
				newPlaceable = new Wall(x, y);
				break;
			}
			case '+': { 
				newPlaceable = new Target(x, y);
				this.addedTarget();
				break;
			}
			case 'x': { 
				Enterable empty = new Empty(x, y);
				Crate aCrate = new Crate(x, y);
				empty.addCrate(aCrate);
				newPlaceable = empty;
				break;
			}
			case 'w': {
				this.myWorker = new Worker(x, y);
				Enterable empty = new Empty(x, y);
				empty.addWorker(this.myWorker);
				newPlaceable = empty;
				break;
			}
		}
		return newPlaceable;
	}

	
	protected void restart() {
		this.moveCount = 0;
		this.completedCount = 0;
		this.targetCount = 0;
		int index = 0;
		for (int x = 0; x < height; x ++) {
			for (int y = 0; y < width; y ++) {
				char symbol = this.startingData.charAt(index);
				Placeable newTile = this.makePlaceable(x, y, symbol);
				this.allPlaceables[x][y] = newTile;
				index ++;
			}
		}
	}
	
	public Placeable whoIsAt(Point where){
		return  this.allPlaceables[where.getX()][where.getY()];
	}
	
	public void move(Direction direction) {
		Point workerCurrentPoint = this.myWorker.where();
		Placeable workerCurrentPlace = this.whoIsAt(workerCurrentPoint);
		
		Point workerDestinationPoint = direction.adjust(this.myWorker.where());
		Placeable workerDestinationPlace = this.whoIsAt(workerDestinationPoint);
		this.workerOnTarget = workerDestinationPlace;
		
		if (workerDestinationPlace.isEmpty()) {
			Enterable currentEnterable = (Enterable) workerCurrentPlace;
			currentEnterable.removeWorker();
			
			Enterable destinationEnterable = (Enterable) workerDestinationPlace;
			destinationEnterable.addWorker(this.myWorker);
		}
		
		if (workerDestinationPlace.hasCrate()) {
			Point crateDestinationPoint = direction.adjust(workerDestinationPoint);
			Placeable crateDestinationPlace = this.whoIsAt(crateDestinationPoint);
			Enterable crateEnterable = (Enterable) workerDestinationPlace;
			Crate crate = crateEnterable.getCrate();
			
			if(crateDestinationPlace.isEmpty()) {
				Enterable cratePlace = (Enterable) workerDestinationPlace;
				cratePlace.removeCrate();
				
				Enterable crateDestinationEnterable = (Enterable) crateDestinationPlace;
				crateDestinationEnterable.addCrate(crate);
				
				Enterable currentEnterable = (Enterable) workerCurrentPlace;
				currentEnterable.removeWorker();
				
				Enterable destinationEnterable = (Enterable) workerDestinationPlace;
				destinationEnterable.addWorker(this.myWorker);
				this.workerOnTarget = destinationEnterable;

				if (crateDestinationEnterable.hasCrate() && crateDestinationEnterable instanceof Target){
					crateOnTarget++;
				}
				if (workerDestinationPlace instanceof Target){
					crateOnTarget--;
				}
			}

		}
		this.moveCount ++;
	}
	
	public String toString() {
		String result = "";
		String grid = "";
		for (int x = 0; x < height; x ++) {
			String row = "";
			for (int y = 0; y < width; y++) {
				row += "" + this.allPlaceables[x][y];
			}
			grid += row + "\n";
		}
		result += grid;
//		result += "move " + this.moveCount + "\n";
//		result += "completed " + this.completedCount + " of " + this.targetCount + "\n";
		return result;
	}
	

}
