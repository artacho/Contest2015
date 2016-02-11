import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Solution {
	private Ballon[] ballons;
	private Random rnd;
	private double probability;
	
	public Solution () {
		this.ballons = new Ballon[Problem.B];
		for (int ballon = 0; ballon < Problem.B; ballon++) {
			this.ballons[ballon] = new Ballon();			
		}
		rnd = new Random();
	}
	
	public void generateSolution () throws CloneNotSupportedException {
		for (int turn = 0; turn < Problem.T; turn++) {
			for (int ballon = 0; ballon < Problem.B; ballon++) {
				int move = bestMoveHeuristic(ballons[ballon]);
				ballons[ballon].setHeight(move);
				if (!ballons[ballon].isLost()) {
					ballons[ballon].applyWindForce(turn);
					ballons[ballon].getMoves()[turn] = (short) move;
				} else {
					ballons[ballon].getMoves()[turn] = (short) 0;
				}
			}
		}
	}
	
	private int bestMoveHeuristic(Ballon ballon) throws CloneNotSupportedException {
		
		Ballon clonedBallon = new Ballon();
		clonedBallon.setHeight(ballon.getHeight());
		clonedBallon.setLost(ballon.isLost());
		clonedBallon.setPosition(new Coordinate(ballon.getPosition().getRow(), ballon.getPosition().getCol()));
		
		probability = rnd.nextDouble();
		
		if (clonedBallon.isLost()) {
			return 0;
		} else {
			if (clonedBallon.getHeight() == 0) {
				return 1;				
			} else if (clonedBallon.getHeight() < Problem.A && clonedBallon.getHeight() > 1) {
				//possible moves 0 1 -1 
				int a =  clonedBallon.calculateCoveredCells(0);
				int b = clonedBallon.calculateCoveredCells(1);
				int c = clonedBallon.calculateCoveredCells(-1);
				if (probability < .85) {
					if (a >= b && a >= c ) return 0;
					else if (b > a && b > c) return 1;
					else return -1;
				} else {
					probability = rnd.nextDouble();
					if (probability < 0.4) return 0;
					else if (probability < 0.4) return -1;
					else return 1;
				}
				
				
			} else if (clonedBallon.getHeight() == Problem.A) {
				//possible move 0 -1
				int a = clonedBallon.calculateCoveredCells(0);
				int b = clonedBallon.calculateCoveredCells(-1);
				
				if (probability < 0.75) {
					if (a > b) return 0;
					else return -1;
				} else {
					if (a < b) return 0;
					else return -1;
				}
				
				
				
			} else if (clonedBallon.getHeight() == 1) {
				//possible move 0 1
				
				int a = clonedBallon.calculateCoveredCells(0);
				int b = clonedBallon.calculateCoveredCells(1);
								
				if (probability < 0.75) {
					if (a > b) return 0;
					else return 1;
				} else {
					if (a > b) return 0;
					else return 1;
				}
				
			}
		}
		
		return 0;
	}

	public int quality () {
		int quality = 0;
		for (int turn = 0; turn < Problem.T; turn++) {
			for (int target_cell = 0; target_cell < Problem.L; target_cell++) {
				for (int ballon = 0; ballon < Problem.B; ballon++) {
					if (turn < ballons[ballon].getTurnLost()) {
						if (ballons[ballon].cover(Problem.targetCell[target_cell])) {
							quality++;
							break;
						}
						
					}
				}
			}
		}
		return quality;
	}
	
	@Override
	public String toString() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("output.out"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int turn = 0; turn < Problem.T; turn++) {
			for (int ballon = 0; ballon < Problem.B; ballon++) {
				pw.print(ballons[ballon].getMoves()[turn]+ " ");
				
			}
			pw.println();
		}
		pw.close();
		return null;
	}
}
