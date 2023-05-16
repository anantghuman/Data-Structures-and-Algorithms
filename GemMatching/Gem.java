import java.awt.*;
import java.util.ArrayList;

enum GemType {
    GREEN, BLUE, ORANGE; //define the different types of Gems, comma delimited
}

public class Gem 
{
	private GemType type;
	private int points;
	public Gem() {
		type = GemType.values()[(int) (Math.random() * 3)];
		points =  5 * ((int)(Math.random() * 11));
	}

	public Gem(GemType type, int points){
		this.type = type;
		this.points = points;
	}

	public String toString(){
		return "" + getType() + " " + getPoints();
	}

	public int getPoints() {
		return points;
	}

	public GemType getType() {
		return type;
	}

	void draw(double x, double y){
		StdDraw.setFont(new Font("SansSerif", Font.BOLD, 14));
		StdDraw.setPenColor(Color.WHITE);
		if (type == GemType.GREEN) {
			StdDraw.picture(x,y,"gem_green.png");
		}
		else if(type == GemType.BLUE) {
			StdDraw.picture(x,y,"gem_blue.png");
		}
		else if(type == GemType.ORANGE){
			StdDraw.picture(x,y,"gem_orange.png");
		}
		StdDraw.text(x,y,"" + getPoints());
	}


	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;

		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);

		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
}
