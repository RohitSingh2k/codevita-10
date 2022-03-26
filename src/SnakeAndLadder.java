import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SnakeAndLadder {

    private static final int START = 1;
    private static final int LAST = 100;
    private static final int ROWS_OF_TABLE = 10;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] table = inputLadder(br);
        int[] steps = inputArray(br);

        solve(steps, table);

        br.close();
    }

    // Inputs a space separated integer line & returns an array of int.
    private static int[] inputArray(BufferedReader br) throws Exception{
        String[] array = br.readLine().split(" ");
        int[] intArray = new int[array.length];
        
        for(int i = 0 ; i < array.length ; i++) 
            intArray[i] = Integer.parseInt(array[i]);

        return intArray;
    }

    // Main logic goes here.
    private static void solve(int[] steps, int[] table) {

        int ladder = 0, snakes = 0, current = 0;

        for (int i = 0 ; i < steps.length ; i++) {

            int currIndex = steps[i] + current;
            if (currIndex <= 100) {
                if(table[currIndex] > currIndex) ladder++;
                if(table[currIndex] < currIndex) snakes++;
            } else 
                currIndex = current;
            
            current = table[currIndex];
        }
        
        if (current == 100) 
            System.out.println("Possible " + snakes + " " + ladder);
        else 
            System.out.println("Not possible " + snakes + " " + ladder + " " + current);
    }

    // Inputs the snake and ladder board.
    private static int[] inputLadder(BufferedReader br) throws Exception{

        int[] table = new int[LAST + 1];
        boolean rev = false;
        int i = 0, index = 100;

        while (i++ < ROWS_OF_TABLE) {

            String[] line = br.readLine().split(" ");

            if ( rev ) 
                reverseArray(line);

            for ( String val : line) 
                table[index--] = intValue(val);

            rev = !rev;
        }

        return table;
        
    }

    // Reverses a String array tooks O(log(n)).
    private static void reverseArray(String[] arr) {
        int len = arr.length;

        for(int i = 0, j = len - 1; i < len / 2 ;i++, j--) {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // Returns the int Value of the ladder string.
    private static int intValue(String point) {

        String val = "";

        if (point.startsWith("S(") || point.startsWith("L(")) {
            val = point.substring(2, point.length() - 1);
            if (val.equals("Start")) return START;
            if (val.equals("End")) return LAST;
        } else {
            if(point.equals("Start")) return START;
            if(point.equals("End")) return LAST;
            val = point;
        }
        
        return Integer.parseInt(val);
    }
}
