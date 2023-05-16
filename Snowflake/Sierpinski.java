import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
class SierpinskiPanel extends JPanel {

    public SierpinskiPanel() {
        super.setPreferredSize(new Dimension(400, 400));
        super.setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTriangle(g, 0, 0, getWidth());

    }

    public void drawTriangle(Graphics g, int x, int y, int size) {
        if (size < 6)
            return;
        g.setColor(Color.BLUE);
        Point origin = new Point(x, y);

        g.drawLine(origin.x, origin.y, origin.x + size, origin.y);
        g.drawLine(origin.x + size, origin.y, origin.x, origin.y + size);
        g.drawLine(origin.x, origin.y + size, origin.x, origin.y);

        drawTriangle(g, x, y, size / 2);
        drawTriangle(g, x, origin.y + (int)(.5 * size), size / 2);
        drawTriangle(g, origin.x + (int)(.5 * size), y, size / 2);
    }
}

public class Sierpinski
{
    public static void main ( String[] args )
    {
        /*
         * A frame is a container for a panel
         * The panel is where the drawing will take place
         */
        JFrame frame = new JFrame("Sierpinski");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SierpinskiPanel());
        frame.pack();
        frame.setVisible(true);
    }
}