public class CrazyEights{
    public static void main(String[] args){
        boolean keepPlaying = true;

        while(keepPlaying){
            Game g = new Game();
            keepPlaying = g.play();
        }
    }
}
