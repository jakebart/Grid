
//This class will be used for user and library will hold most of the methods w/ graphics
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class control {
	
	//grid that gets randomized with values in num_array
	public int [] grid = new int[9];
	int[] num_array = {1,2,3,4,5,6,7,8,9};	
	
	Random rand = new Random();
	private int rand_int = 0;
	private ArrayList<Integer> used_num = new ArrayList<Integer>();

	//method exists for console play
	public void printGrid() {
		//Yeah I know a loop would've been better but I wrote this at 2am
		System.out.print("#######" + "\n" +
		"#"+ grid[0] + "#" + grid[1] + "#" + grid[2] + "#" + "\n" +
		"#######" + "\n" +
		"#"+ grid[3] + "#" + grid[4] + "#" + grid[5] + "#" + "\n" +
		"#######" + "\n" +
		"#"+ grid[6] + "#" + grid[7] + "#" + grid[8] + "#" + "\n" +
		"#######" + "\n");
		
	}
	//Generates random numbers, 9 is hard-coded for now
	private int generator() {
		rand_int = rand.nextInt(9);
		return rand_int;
	}
	//Sets the array with the index of rand_int with a int from num_array
	public void setGrid() {
		
		for (int i = 0; i < grid.length; i++) {	
						
			boolean random = true;
			while(random) {
				if (used_num.contains(rand_int)) {
					this.generator();
					random = true;
				} else {
					used_num.add(rand_int);
					grid[i] = num_array[rand_int];
					random = false;
					
				}
			}
		}
	}
	//Again for playing in this class in the console
	public void setup() {
		setGrid();
		printGrid();
	}
	//Method finds the index of selected number then swaps the value there to other index
	//Might make playing in console different than intended
	public void change(int[] array, int in, int in1) {
		//ArrayList is used to find the index, could be changed later with creativity
		ArrayList<Integer> arrayc = new ArrayList<Integer>();
		for(int i = 0; i < array.length; i++) {
			arrayc.add(array[i]);
		}

		int spot = arrayc.indexOf((in));
		int spot1 = arrayc.indexOf((in1));
		int temp = arrayc.get(spot);
		int temp1 = arrayc.get(spot1);

		arrayc.remove(spot);
		arrayc.add(spot, temp1);
		arrayc.remove(spot1);
		arrayc.add(spot1, temp);
		
		for(int i = 0; i < array.length; i++) {
			array[i] = arrayc.get(i);
		}		
	}
	//checks to see if the array given is in the same order of num_array
	public boolean check(int[] array) {
		boolean game = true;
		
		while (game) {
			int count = 0;
			boolean check = true;
			while (check) {
				if (array[count] != num_array[count]) {
					check = false;
					count = 0;
					return game;
				} else {
					count++;
					if (count == 8) {
						check = false;
						System.out.println("WIN!");
					}
				}
				
			}
			game = false;

		}
		return game;
	}
	//For playing in console
	public void game() {
		Scanner user_spot = new Scanner(System.in);
		
		while (check(grid)) {
			change(grid, user_spot.nextInt(), user_spot.nextInt());
			printGrid();
			check(grid);
		}
		user_spot.close();
	}
	
	public static void main(String args[]) {
		control control = new control();
		control.setup();
		control.game();
		control.printGrid();
		
	}
}
