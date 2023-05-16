import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {

    Square[][] maze;
    Square start;
    Square exit;

    public Maze() {
        maze = new Square[1][1];
    }

    boolean loadMaze(String fileName) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (Exception e){
            System.out.println("Your file input is not correct.");
            return false;
        }
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        Square[][] temp = new Square[rows][cols];
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++) {
                temp[r][c] = (new Square(r, c, scanner.nextInt()));
                if(temp[r][c].getType() == Square.EXIT)
                    exit = temp[r][c];
                if(temp[r][c].getType() == Square.START)
                    start = temp[r][c];
            }
        maze = temp;
        return true;
    }

    List<Square> getNeighbors(Square s){
        List<Square> temp = new ArrayList<>();
        int[] yPos = {s.getCol()-1, s.getCol(), s.getCol() +1, s.getCol()};
        int[] xPos = {s.getRow(), s.getRow() + 1, s.getRow(), s.getRow() -1};
        for(int i = 0; i < xPos.length; i++) {
            if (outOfBounds(xPos[i], yPos[i]))
                continue;
            temp.add(maze[xPos[i]][yPos[i]]);
        }
        return temp;
    }

    boolean outOfBounds(int row, int col){
        return row < 0 || col < 0 || row >= maze.length || col >= maze[row].length;
    }

    Square getStart(){
        return start;
    }

    Square getExit(){
        return exit;
    }

    void reset(){
        for(int i = 0; i < maze.length; i++)
            for(int n = 0; n < maze[i].length; n++)
                maze[i][n].reset();
    }

    public String toString(){
        String c = "";
        for(int i = 0; i < maze.length; i++){
            for(int n = 0; n < maze[i].length; n++) {
                c += maze[i][n] + " ";
            }
            c += ("\n");
        }
        return c;
    }

}
