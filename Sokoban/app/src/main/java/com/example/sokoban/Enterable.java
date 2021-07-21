package com.example.sokoban;

public abstract class Enterable extends Immoveable {
	
	protected Moveable myWorker;
	protected Moveable myCrate;
	protected boolean isEmpty = true;
	
	public Enterable(int x, int y, char symbol) {
		super(x,y, symbol);
	}

	public Crate getCrate() {
		return (Crate) this.myCrate;
	}
	
	public void removeWorker() {
		this.myWorker = null;
		this.isEmpty = true;
	}
	
	public void addWorker(Moveable theWorker) {
		this.myWorker = theWorker;
		theWorker.set(this.location);
		this.isEmpty = false;
	}
	
	public void removeCrate() {
		this.myCrate = null;
		this.isEmpty = true;
	}
	
	public void addCrate(Crate aCrate) {
		this.myCrate = aCrate;
		this.isEmpty = false;
	}
	
	@Override 
	public boolean isEmpty() {
		return this.isEmpty;  // replace with test on content exists
	}

	public boolean hasCrate() {
		return this.myCrate != null;
	}

	// override to modify a 'stacked' moveable's string
	protected String getMovableString(Moveable theMovable ) {
		return theMovable.toString();
	} 
	
	public String toString() {
		String symbol = "" + this.symbol;
		if (this.myWorker != null) {
			symbol = this.getMovableString(myWorker);
		}
		if (this.myCrate != null) {
			symbol = this.getMovableString(myCrate);
		}
		return symbol;
	}
}


