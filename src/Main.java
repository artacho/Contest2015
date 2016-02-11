
public class Main {
	public static void main (String[] args) throws CloneNotSupportedException {
		Problem problem = new Problem();
		problem.readInputFile("input.in");
		Solution solution = new Solution();
		solution.generateSolution();
		System.out.println(solution.quality());
		System.out.println(solution);
	}
	
	
	
}
