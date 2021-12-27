public class GameInterface {
    int board[];

    GameInterface() {
        board = new int[16];
    }

    int[] stringToIntArray(String puzzle)
	{
		String[] values = puzzle.split("[ ]+");			//split string into array of strings(numbers)
		
		int[] intArray = new int[values.length];			//int array for converted strings
		
		for(int x = 0; x < intArray.length; x++)			//convert to int array
		{
			intArray[x] = Integer.parseInt(values[x]);
		}
		
		return intArray;
	}



}
