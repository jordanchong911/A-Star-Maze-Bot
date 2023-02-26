import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Type in the file name: ");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        fileName = fileName + ".txt";

        MazeInput mazeInput = new MazeInput();

        try {
            mazeInput.FileInput(fileName);
        } catch (Exception e) {
            System.out.println("File not found: " + e.getMessage());
        }
        scan.close();

        int maze[][] = mazeInput.getMaze();
        int x1 = -1, x2 = -1, y1 = -1, y2 = -1;
        ArrayList<Integer> walls_x = new ArrayList<>();
        ArrayList<Integer> walls_y = new ArrayList<>();

        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[i].length; j++){
                if (maze[i][j] == 1) {
                    walls_x.add(j);
                    walls_y.add(i);
                } if (maze[i][j] == 2) {
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

        System.out.println();
        System.out.println("x");
        System.out.println(walls_x);
        System.out.println("-----------------------");
        System.out.println("y");
        System.out.println(walls_y);

        int blocked = 0;
        int startState = -1;
        // NOTE: DO THE CHECKING PER DIRECTION

        /* 
        for (int i = 0; i < walls_x.size(); i++) {    
            
            if (i == 0){
                startState = maze[walls_y.get(i)][walls_x.get(i)];
            }

            if (startState == maze[walls_y.get(i)][walls_x.get(i)+1]) { // check right move

                if (maze[walls_y.get(i)][walls_x.get(i)] == maze[walls_y.get(i)][walls_x.get(i)+1]) { // check right move
                    blocked = 1;
                } else if (maze[walls_y.get(i)][walls_x.get(i)] == maze[walls_y.get(i)][walls_x.get(i)-1]) { // check left  move (unnecessary)
                    blocked = 1;
                } else if (maze[walls_y.get(i)][walls_x.get(i)] == maze[walls_y.get(i)+1][walls_x.get(i)]) { // check down move
                    blocked = 1;
                } else if (maze[walls_y.get(i)][walls_x.get(i)] == maze[walls_y.get(i)-1][walls_x.get(i)]) { // check up move
                    blocked = 1;
                }
            }

            if (startState == maze[walls_y.get(i)][walls_x.get(i)-1]) { // check left  move
                continue;
            }

            if (startState == maze[walls_y.get(i)+1][walls_x.get(i)]) { // check down move
                continue;
            }

            if (startState == maze[walls_y.get(i)-1][walls_x.get(i)]) { // check up move
                continue;
            }
        }
        */

        Search search = new Search(maze, x1, y1, x2, y2);
        search.search();
       
        
        
    }
}