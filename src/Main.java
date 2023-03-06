import java.util.*;
import java.lang.Math;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String fileName = null;
        //System.out.print("Type in the file name: ");
        //fileName = scan.nextLine();
        MazeInput mazeInput = new MazeInput();
        boolean valid = false;
        

        
        // MAZE 3 TEST filename: maze.txt
        while (!valid) {
            System.out.print("Type in the file name: ");
            fileName = scan.nextLine();
            
            if(fileName.equals("maze"))
                valid = true;
            else
                System.out.println("Wrong input. Try again");
        } 
        fileName += ".txt";
        mazeInput.FileInput(fileName);
        
        /* 
        MAZE 2 TEST
        try {
            mazeInput.FileInput(fileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        */

        int maze[][] = mazeInput.getMaze();
        int x1 = -1, x2 = -1, y1 = -1, y2 = -1;

        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[i].length; j++){
                if (maze[i][j] == 2) {
                    x1 = j;
                    y1 = i;
                } if (maze[i][j] == 3) {
                    x2 = j;
                    y2 = i;
                }

                System.out.print(maze[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println("Start: X = " + x1 + " Y = " + y1);
        System.out.println("End: X = " + x2 + " Y = " + y2);
        System.out.println("Manhattan distance: " + (Math.abs(x1 - x2) + Math.abs(y1 - y2)) + "\n");

        Search search = new Search(maze, x1, y1, x2, y2);

        if(search.search()==null)
            System.out.print("No path found.");

        search.finalExploredCount();

        System.out.println();
        System.out.print("Type any key to end program: ");
        String next = scan.nextLine();

        if (next != null)
            System.out.println("The program has ended.");

        scan.close();
    }
}