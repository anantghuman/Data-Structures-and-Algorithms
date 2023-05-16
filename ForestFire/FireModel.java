public class FireModel
{
    public static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;

    public FireModel(FireView view)
    {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int r=0; r<SIZE; r++)
        {
            for (int c=0; c<SIZE; c++)
            {
                myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

    public void recursiveFireModel(){
        for(int i = 0; i < myGrid[0].length; i++)
            if(myGrid[SIZE - 1][i].getStatus() == FireCell.GREEN)
                recurFireHelper(SIZE - 1, i);
    }

    private void recurFireHelper(int x, int y){
        if(myGrid[x][y].getStatus() == FireCell.DIRT || myGrid[x][y].getStatus() == FireCell.BURNING)
            return;
        myGrid[x][y].setStatus(FireCell.BURNING);
        if(x < SIZE - 1)
            recurFireHelper(x + 1, y);
        if(x > 0)
            recurFireHelper(x - 1, y);
        if(y < SIZE - 1)
            recurFireHelper(x, y + 1);
        if(y > 0)
            recurFireHelper(x, y - 1);
    }

    public void solve() {
        recursiveFireModel();
        boolean onettDanger = false;
        for(int i = 0; i < myGrid[0].length; i++)
            if(myGrid[0][i].getStatus() == FireCell.BURNING)
                onettDanger = true;
        if(!onettDanger)
            System.out.println("Onett is safe from the fire.");
        else
            System.out.println("Onett is in danger because of the fire spreading.");
        myView.updateView(myGrid);
    }
}
