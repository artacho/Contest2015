
public class Ballon implements Cloneable {
	private Coordinate position;
	private short[] moves;
	private int height;
	private int turnLost;
	private boolean lost;
	
	public Ballon () {
		this.position = new Coordinate(Problem.startingCell.getRow(), Problem.startingCell.getCol());
		this.moves = new short[Problem.T];
		this.height = 0;
		this.turnLost = Integer.MAX_VALUE;
		this.lost = false;
	}
	
	public boolean cover(Coordinate targetCell) {
		int r = this.position.getRow();
		int c = this.position.getCol();
		
		int u = targetCell.getRow();
		int v = targetCell.getCol();
		
		
		return (int) Math.pow((r - u),2) + (int) Math.pow(columndist(c,v),2) <= (int) Math.pow(Problem.V, 2);
	}
	
	public void applyWindForce (int turn) {
		int r = this.position.getRow();
		int c = this.position.getCol();
		
		int a = Problem.wind_grid[r][c][this.height-1].getRow();
		int b = Problem.wind_grid[r][c][this.height-1].getCol();
		
		if (r+a >= 0 && r+a < Problem.R) {
			this.position.setRow(r+a);
			this.position.setCol(Math.abs((c+b)%Problem.C));
		} else {
			this.turnLost = turn;
			this.lost = true;
		}
	}
	
	public int calculateCoveredCells (int move) throws CloneNotSupportedException {
		int coveredCells = 0;
		
		this.setHeight(move);
		this.applyWindForce(0);
		
		if (this.isLost()) {
			return coveredCells;
		} else {
			for (int target_cell = 0; target_cell < Problem.L; target_cell++) {
				if (this.cover(Problem.targetCell[target_cell])) coveredCells++;
			}
		}
		
		return coveredCells;
	}

	private int columndist(int c1, int c2) {
		return Math.min(Math.abs(c1-c2), Problem.C - Math.abs(c1-c2));
	}
	
	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	public short[] getMoves() {
		return moves;
	}
	
	public void setMoves(short[] moves) {
		this.moves = moves;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height += height;
	}
	
	public int getTurnLost() {
		return turnLost;
	}
	
	public void setTurnLost(int turnLost) {
		this.turnLost = turnLost;
	}

	public boolean isLost() {
		return lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}
	
	
}
