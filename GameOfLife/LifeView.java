import java.awt.*;
import javax.swing.*;
import java.util.Random;

/** The view (graphical) component */
public class LifeView extends JPanel
{
    private static final long serialVersionUID = 1L;
    private static int SIZE = 60;

    /** store a reference to the current state of the grid */
    private LifeCell[][] grid;

    private boolean randomColor = false;

    public LifeView()
    {
        grid = new LifeCell[SIZE][SIZE];
    }

    /** entry point from the model, requests grid be redisplayed */
    public void updateView( LifeCell[][] mg )
    {
        grid = mg;
        repaint();
    }

    public void changeColor(){
        randomColor = !randomColor;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int testWidth = getWidth() / (SIZE+1);
        int testHeight = getHeight() / (SIZE+1);
        // keep each life cell square
        int boxSize = Math.min(testHeight, testWidth);
        Random rand = new Random();
        for ( int r = 0; r < SIZE; r++ )
        {
            for (int c = 0; c < SIZE; c++ )
            {
                if (grid[r][c] != null)
                {
                    if ( grid[r][c].isAliveNow())
                        if(randomColor){
                            int red = rand.nextInt(256);
                            int green = rand.nextInt(256) ;
                            int blue = rand.nextInt(256);
                            g.setColor(new Color(red, green, blue));
                        }
                        else
                            g.setColor( Color.BLUE );
                    else
                        g.setColor( new Color(235,235,255) );

                    g.fillRect( (c+1)*boxSize, (r+1)* boxSize, boxSize-2, boxSize-2);
                }
            }
        }
    }
}