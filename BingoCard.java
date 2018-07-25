import java.util.*;


/**
    This is my implementation of BingoCard. This problem was presented to me on July 18th, 2018
 during a google phone technical interview. This is my complete implementation (unoptimized), however, I was unable to
 finish this problem in the allotted time (~25-35 min) and my original solution did not account for:
    > Leaving the center of the bingo card blank.
    > Ensuring unique values for each column major element.
    (DON'T MAKE MY MISTAKES)

    Additionally, due to time constraints, I was not able to discuss time or space complexity of my original solution
 with the interviewer, partly due to a logistical error at the beginning of the interview which prevented me from
 accessing the necessary shared google doc for walking through code with the interviewer. It should be noted that phone interviewers strictly adhere to time limits (likely set by their organization), so any mistakes or delays will cost when it comes to closing the interview. Throughout the interview, you want to be able to have time to discuss and show:
    > Code testing/analysis & example evaluation/walk-through
    > Optimization of the problem / modularity / edge-case & unique case considerations
    > Time & Space Complexity in terms of Big-Oh Notation

    NOTE #1 (MUST-KNOWs):
        > This code is written in a proper class with a main function, however, I was only provided the problem
          statement in NOTE #3 below. You will be expected to simply write the function.
        > Although it has been created below, function randInt(int high, low) was ASSUMED to be provided.
        > You will not be allowed to use pseudo-code, do not do this. Since you choose the programming language,
          it is expected that you are well-versed on the syntax and common library functions.
          unless there is some kind of time limit and the interviewer wants you to be able to get your thoughts out.
        > It is my experience that these technical interviews are conducted while the interviewer is on speaker phone. It may
          be difficult to hear them speak, so try to get someone to practice with you over the phone so you can get accustomed
          to this as a potential barrier of communication.
        > If applicable, find a way to use HashSets/HashMaps/Dictionaries. These are fundamental data structures that
          save time and are highly efficient to scale.
        > If your solution has more than 30 lines of code, it is probably doing too much.

    NOTE #2: INTERVIEWEE HELPFUL MUST-DOs
        > Practice and code on a several interview problems. No text editor / no compiler. Get spun up on data
          structures and algorithms, and do not refer to outside resources during practice. You will invariably find complex solutions you won't know how to implement in real-time.
        > BEFORE beginning an attempt at the solution, make sure to ask appropriate questions about any parameters
          (i.e. Function randInt(A, B) was assumed to be provided. Do we know if B is inclusive or exclusive? Probably note
          so you want to ask).
        > Take a handwritten note of ALL conditions presented in the problem statement. DO NOT forget about things you
          were instructed to implement (i.e. Leaving the center grid element blank, ensuring unique values for each grid element). This shows attentiveness and hastiness to code will only penalize you at the end when/if you are asked why you ignored a key requirement.
        > Ask questions and remember to talk throughout the interview. Make sure the interviewer knows exactly what
          steps you plan to take so you don't code the wrong solution.
        > When you start the problem, think about what you were asked to return. So, if you are asked to return a 5x5
          object, you know that you either want to accept a grid (2D-Array) as a function parameter or create that
          so you can return that object as a result.
        > Go ahead and initialize temp values (i.e. start and end indeces, temporary primitive type values
          for in-place swaps or assignments, etc.). If you don't need them, make sure to go back and delete
          them so you don't confuse yourself a few lines later with a value you didn't intend to use.
*/

public class BingoCard{

     public static void main(String[] args){

        System.out.println("\n");

        /** ANOTHER WAY OF PRINTING (In-Line) */
        //System.out.print(Arrays.deepToString(createBingoCard()));

        printMatrix(createBingoCard());
        System.out.println("\n");

     }

     /**
      * NOTE #3: PROBLEM STATEMENT--Return a 5x5 bingo card where column 1 has random integer values with range 1 - 15,
      * column 2 has random integer range values between 16 - 30, column 3 has random integer
      * range values between 31 - 45, column 4 has random integer range values between 46 - 60,
      * and column 5 has random integer range values between 61 - 75. Leave the center value blank.
      * Ensure all elements are unique.
      *     This problem was not initially well-defined, but I had to ask quite a bit of questions to narrow the scope
      * of the problem. For anyone who may be reading this, I have provided the problem statement succinctly, but you
      * should not assume the problem will be stated so clearly. You might have to ask the right questions to get to
      * a proper problem statement. This is also a point of evaluation.
      */
     public static int[][] createBingoCard() {
         int[][] arr2d = new int[5][5];

         // Creating column histogram for ensuring column major elements are unique
         ArrayList<Integer> arrList1 = new ArrayList<>();
         ArrayList<Integer> arrList2 = new ArrayList<>();
         ArrayList<Integer> arrList3 = new ArrayList<>();
         ArrayList<Integer> arrList4 = new ArrayList<>();
         ArrayList<Integer> arrList5 = new ArrayList<>();


         for (int i = 0; i < arr2d.length; i++) {
             int first = 1, second = 15, j, temp;
             for (j = 0; j < arr2d.length; j++) {

                /**
                 * For each column major element, ensure unique values for each column.
                 * Compute a new element value while the column already contains that value.
                 * Add the newly computed value to the bingo card's column and the column histogram.
                 */
                switch (j) {
                    case 0:
                        do {
                            temp = randInt(first, second);
                        } while (arrList1.contains(temp));

                        arrList1.add(temp);
                        arr2d[i][j] = temp;
                        first += 15;
                        second += 15;
                        break;

                    case 1:
                        do {
                            temp = randInt(first, second);
                        } while (arrList2.contains(temp));

                        arrList2.add(temp);
                        arr2d[i][j] = temp;
                        first += 15;
                        second += 15;
                        break;

                    case 2:
                        // If center element, skip and leave blank.
                        if (i == 2) {
                            first += 15;
                            second += 15;
                            break;
                        }
                        do {
                            temp = randInt(first, second);
                        } while (arrList3.contains(temp));

                        arrList3.add(temp);
                        arr2d[i][j] = temp;
                        first += 15;
                        second += 15;
                        break;

                    case 3:
                        do {
                            temp = randInt(first, second);
                        } while (arrList4.contains(temp));

                        arrList4.add(temp);
                        arr2d[i][j] = temp;
                        first += 15;
                        second += 15;
                        break;

                    case 4:
                        do {
                            temp = randInt(first, second);
                        } while (arrList5.contains(temp));

                        arrList5.add(temp);
                        arr2d[i][j] = temp;
                        first += 15;
                        second += 15;
                        break;

                    default: break;
                }
             }
         }
         return arr2d;
     }

     public static int randInt(int low, int high) {
         Random rand = new Random();
         if (high >= low) {
             return rand.nextInt((high-low) + 1) + low;
         }
         else {
             return low;
         }

     }


     //Displays a 2d array in the console, one line per row.
     // SOURCE: https://stackoverflow.com/a/12845292/9718306
    static void printMatrix(int[][] grid) {
        for(int r = 0; r < grid.length; r++) {
           for(int c = 0; c < grid[r].length; c++) {
               System.out.print(grid[r][c] + "\t");
           }
           System.out.println();
        }
    }
}
