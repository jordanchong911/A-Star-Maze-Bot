import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeInput {
    private String fileName;
    private int mazeSize;
    private int[][] maze;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int[][] FileInput (String fileName) throws FileNotFoundException{
        File file = new File("src\\" + fileName);
        Scanner scan = new Scanner(file);

        mazeSize = scan.nextInt();
        maze = new int[mazeSize][mazeSize];
        scan.nextLine();

        for (int i = 0; i < mazeSize; i++) {
            String row = scan.nextLine();
            for (int j = 0 ; j < mazeSize; j++) {
                if (row.charAt(j) == 'S') {
                    maze[i][j] = 2;
                }
                else if (row.charAt(j) == 'G') {
                    maze[i][j] = 3;
                }
                else if (row.charAt(j) == '.') {
                    maze[i][j] = 0;
                }
                else if (row.charAt(j) == '#') {
                    maze[i][j] = 1;
                }
            }
        }
        scan.close();

        return maze;
    }
}
