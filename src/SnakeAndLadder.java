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




/* 

INPUTS : -
========
End S(Start) 98 S(7) 96 95 94 93 92 91
81 82 L(99) 84 85 86 87 88 89 90
80 79 78 77 76 75 74 73 72 71
61 S(22) 63 64 65 66 67 68 69 70
60 59 58 S(14) 56 55 54 51 52 51
41 42 43 44 45 46 L(80) 48 49 50
40 39 38 37 36 35 34 33 32 31
21 22 23 L(63) 25 26 27 28 29 30
20 19 S(2) 17 16 15 14 13 12 11
Start 2 3 4 5 6 7 8 9 10
6 6 6 5 4 3 6 6 6 5 6 4 3 1

*/