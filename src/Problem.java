import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Problem {
	
	protected static int R; // denotes the number of rows in the grid
	protected static int C; // denotes the number of columns in the grid
	protected static int A; // denotes the number of different altitudes
	
	protected static int L; // denotes the number of target cells;
	protected static int V; //denotes the coverage radius;
	protected static int B; //denotes the number of ballons;
	protected static int T; //denotes the number of turns;
	
	protected static Coordinate startingCell;
	protected static Coordinate[] targetCell;
	
	protected static Coordinate[][][] wind_grid;
	
	public Problem () {
		
	}
	
	public void readInputFile (String name) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(name);
			bufferedReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			System.out.println("Error reading input file");
			e.printStackTrace();
		}
		
		String readLine = null;
		
		try {
			String[] data = null;
			
			// Read first line
			readLine = bufferedReader.readLine();
			data = readLine.split(" ");
			this.R = Integer.parseInt(data[0]);
			this.C = Integer.parseInt(data[1]);
			this.A = Integer.parseInt(data[2]);
			//System.out.println(this.R + " rows, " + this.C + " columns, " + this.A + "altitudes");
			
			//Read second line
			readLine = bufferedReader.readLine();
			data = readLine.split(" ");
			this.L = Integer.parseInt(data[0]);
			this.V = Integer.parseInt(data[1]);
			this.B = Integer.parseInt(data[2]);
			this.T = Integer.parseInt(data[3]);
			//System.out.println(this.L + " target cells, coverage radius of " + this.V + ", " + this.B + " ballon, " + this.T + " turns");
			
			//Read third line
			readLine = bufferedReader.readLine();
			data = readLine.split(" ");
			this.startingCell = new Coordinate (Integer.parseInt(data[0]),Integer.parseInt(data[1]));
			System.out.println("Starting cell: row " + this.startingCell.getRow() + " column " + this.startingCell.getCol());
			
			//Read L lines describing target cells
			this.targetCell = new Coordinate[this.L];
			for (int targetCell = 0; targetCell < this.L; targetCell++) {
				readLine = bufferedReader.readLine();
				data = readLine.split(" ");
				this.targetCell[targetCell] = new Coordinate (Integer.parseInt(data[0]),Integer.parseInt(data[1]));
				//System.out.println("Target cell in row " + this.targetCell[targetCell].getRow() + " column " + this.targetCell[targetCell].getCol() );
			}
			
			//Read R lines of wind vectors
			this.wind_grid = new Coordinate[this.R][this.C][this.A];
			for (int altitud = 0; altitud < this.A; altitud++) {
				for (int fila = 0; fila < this.R; fila++) {
					readLine = bufferedReader.readLine();
					data = readLine.split(" ");
					for (int col = 0; col < this.C; col++) {
						wind_grid[fila][col][altitud] = new Coordinate (Integer.parseInt(data[2*col]),Integer.parseInt(data[2*col+1]));
					}
					
				}
			}
			
		} catch (IOException e) {
			System.out.println("Error while reading input file");
			e.printStackTrace();
		}
	}	
}
