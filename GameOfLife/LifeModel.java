import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

    /*
     *  This is the Model component.
     */

    private static int SIZE = 60;
    private LifeCell[][] grid;

    LifeView myView;
    Timer timer;

    /** Construct a new model using a particular file */
    public LifeModel(LifeView view, String fileName) throws IOException
    {
        int r, c;
        grid = new LifeCell[SIZE][SIZE];
        for ( r = 0; r < SIZE; r++ )
            for ( c = 0; c < SIZE; c++ )
                grid[r][c] = new LifeCell();

        if ( fileName == null ) //use random population
        {
            for ( r = 0; r < SIZE; r++ )
            {
                for ( c = 0; c < SIZE; c++ )
                {
                    if ( Math.random() > 0.85) //15% chance of a cell starting alive
                        grid[r][c].setAliveNow(true);
                }
            }
        }
        else
        {
            Scanner input = new Scanner(new File(fileName));
            int numInitialCells = input.nextInt();
            for (int count=0; count<numInitialCells; count++)
            {
                r = input.nextInt();
                c = input.nextInt();
                grid[r][c].setAliveNow(true);
            }
            input.close();
        }

        myView = view;
        myView.updateView(grid);

    }

    /** Constructor a randomized model */
    public LifeModel(LifeView view) throws IOException
    {
        this(view, null);
    }

    /** pause the simulation (the pause button in the GUI */
    public void pause()
    {
        timer.stop();
    }

    /** resume the simulation (the pause button in the GUI */
    public void resume()
    {
        timer.restart();
    }

    /** run the simulation (the pause button in the GUI */
    public void run()
    {
        timer = new Timer(50, this);
        timer.setCoalesce(true);
        timer.start();
    }

    /** called each time timer fires */
    public void actionPerformed(ActionEvent e)
    {
        oneGeneration();
        myView.updateView(grid);
    }

    /** main logic method for updating the state of the grid / simulation */
    private void oneGeneration() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                int neighbors = countNeighbors(r, c);
                if (grid[r][c].isAliveNow()) {
                    if (neighbors <= 1 || neighbors >= 4)
                        grid[r][c].setAliveNext(false);
                    if (neighbors == 2 || neighbors == 3)
                        grid[r][c].setAliveNext(true);
                } else if (neighbors == 3)
                    grid[r][c].setAliveNext(true);
            }
        }
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c].setAliveNow(grid[r][c].isAliveNext());
            }
        }
    }

    private int countNeighbors(int r, int c){
        int row[] = {-1,-1,-1,0,0,1,1,1};
        int col[] = {-1,0,1,1,-1, -1,0,1};
        int count = 0;
        for(int i = 0; i < row.length; i++)
            if(!isInGrid(r + row[i], c + col[i]))
                continue;
            else if(grid[r + row[i]][c + col[i]].isAliveNow())
                count++;
        return count;
    }

    private boolean isInGrid(int r, int c){
        return(r >= 0 && c >= 0 && c < SIZE && r < SIZE);
    }

    public void randColor(){
        myView.changeColor();
        myView.repaint();
    }
}
