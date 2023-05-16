import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

class SnowFlakePanel extends JPanel {
	public SnowFlakePanel() {
		super.setPreferredSize(new Dimension(400, 400));
		super.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();

		super.paintComponent(g);

		drawStar(g, width / 2, height / 2, width / 4);

	}

	public void drawStar(Graphics g, int x, int y, int size) {
		if (size < 6)
			return;
		g.setColor(Color.BLUE);
		Point center = new Point(x, y);
		for (int i = 0; i < 6; i++) {
			int y2 = center.y + (int) (size * Math.sin(i * 2 * Math.PI / 6));
			int x2 =center.x + (int) (size * Math.cos(i * 2 * Math.PI / 6));
			g.drawLine(center.x, center.y, x2, y2);
			drawStar(g, x2, y2, size / 3);
		}
	}
}

public class Snowflake
{
	public static void main ( String[] args )
	{
		/*
		 * A frame is a container for a panel
		 * The panel is where the drawing will take place
		 */
		JFrame frame = new JFrame("Snowflake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SnowFlakePanel());
		frame.pack();
		frame.setVisible(true);
	}
}
