import java.util.Random;

public class RandomPermutation {

	private int [] grid;

	public RandomPermutation(int row, int column){
		grid = new int [row*column];
	}

	//Returns this permutation in an array, where the elements of the first row comes first, followed by the elements of the second row, etc.
	public int[] toArray(){
		return grid;
	}

	// Returns a permutation that is guaranteed to be solvable. Starting from the identity permutation, generate a large number of randomly generated moves. 
	public void shuffle(){
		//Initialise identity "matrix"
		for (int i = 0; i < grid.length; i++){
			if(i+1 != grid.length){
				grid[i] = i+1;
			}
			else{
				grid[i] = 0;
			}
		}

		//Shuffle 1000 times and puts blank space to corner
		int index = grid.length-1;
		grid = shuffle1000(grid, index);
		grid = shuffleToCorner(grid);
	}

	//Shuffles the array 1000 times
	private int [] shuffle1000(int [] grid, int index){
		for (int i = 0; i < 1001; i++){
			int randomPosition = nextMove(index);
			int temp = grid[index];
			grid[index] = grid[randomPosition];
			grid[randomPosition] = temp;
			index = temp;
		}
		return grid;
	}

	//Shuffles the array till the last position is a 0
	private int [] shuffleToCorner (int [] grid){
		boolean foundIndex = false;
		int index = 0;
		for (int i = 0; i < grid.length && !foundIndex; i++){
			if (grid[i] == 0){
				index = i;
				foundIndex = true;
			}
		}
		int i = 0;
		while (grid[grid.length-1] != 0){
			int randomPosition = nextMove(index);
			int temp = grid[index];
			grid[index] = grid[randomPosition];
			grid[randomPosition] = temp;
			index = temp;
			i++;
		}
		return grid;
	}

	//Decides what will be the next move, at the indicated position
	private int nextMove(int index){
		int move = 0;
		if (index == 0){
			move = choose2(1,3);
		}
		else if(index == 1){
			move = choose3(0,2,4);
		}
		else if (index == 2){
			move = choose2(1,5);
		}
		else if(index == 3){
			move = choose3(0,4,6);
		}
		else if (index == 4){
			move = choose4(1,3,5,7);
		}
		else if(index == 5){
			move = choose3(2,4,8);
		}
		else if (index == 6){
			move = choose2(3,7);
		}
		else if(index == 7){
			move = choose3(6,4,8);
		}
		else if (index == 8){
			move = choose2(5,7);
		}
		return move;
	}

	//Randomly chooses between two numbers
	private int choose2(int num1, int num2){
		Random roll = new Random();
		boolean choose = roll.nextBoolean();
		if(choose){
			return num1;
		}
		else{
			return num2;
		}
	}

	//Randomly chooses between 3 numbers
	private int choose3(int num1, int num2, int num3){
		Random roll = new Random();
		boolean dice1;
		boolean dice2;
		do{
			dice1 = roll.nextBoolean();
			dice2 = roll.nextBoolean();
		}
		while (dice1 && dice2);

		int result = 0;
		if (!dice1 && !dice2 ){
			result = num1;
		}
		else if (!dice1 && dice2){
			result = num2;
		}
		else if(dice1 && !dice2){
			result = num3;
		}
		return result;
	}

	//Randomly chooses between 4 numbers
	private int choose4(int num1, int num2, int num3, int num4){
		Random roll = new Random();
		boolean dice1 = roll.nextBoolean();
		boolean dice2 = roll.nextBoolean();

		int result = 0;
		if (!dice1 && !dice2 ){
			result = num1;
		}
		else if (!dice1 && dice2){
			result = num2;
		}
		else if(dice1 && !dice2){
			result = num3;
		}
		else if (dice1 && dice2){
			result = num4;
		}
		return result;
	}


	public String toString(){
		String result = new String();
		String newLine = System.getProperty("line.separator");
		for (int i = 0; i<grid.length; i++){
			result = result + "["+ grid[i] +  "] ";
			if (i == 2 || i == 5){
				result = result + newLine;
			}
		}
		return result;
	}

}
