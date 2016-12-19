/*
 * Author @Anish Katariya
 * This Programme creates a Panel and draws the Mandelbrot set
 * On the panel using the ComplexNumbers class
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Creates a panel and draws mandelbrot set in it
public class MSPanel extends JPanel {
	JSPanel panel;
	JLabel label;
	double reMax;
	double reMin;
	double imMax;
	double imMin;
	JComboBox<String> list;
	ArrayList<favourite> favlist;

	// initalizes and adds default values
	public MSPanel(JSPanel panel, JLabel label, JComboBox<String> list, ArrayList<favourite> favlist) {
		reMax = 2;
		reMin = -2;
		imMax = 1.6;
		imMin = -1.6;
		this.panel = panel;
		this.label = label;
		this.list = list;
		this.favlist = favlist;
		this.setSize(400, 400);
		this.addMouseMotionListener(new MSListener(panel, label));
		this.addMouseListener(new FavouriteListener(list, favlist, panel));
	}

	// Paint function called by the super class
	protected void paintComponent(Graphics g) {
		fractolGenerator(g);
	}

	// Generates the mandelbrot set
	void fractolGenerator(Graphics g) {
		for (int x = 0; x < this.getWidth(); x++) {
			for (int y = 0; y < this.getHeight(); y++) {
				// Calls function to get the color of the pixel for each pixel
				// in the set
				g.setColor(getPixalColour((x * (reMax - reMin) / this.getWidth()) + reMin,
						(y * (imMax - imMin) / this.getHeight()) + imMin));
				g.drawRect(x, y, 0, 0);
			}
		}
	}

	/*
	 * Function takes in the complex numbers Performs operations on the complex
	 * number and returns the color According the converging or diverging nature
	 * of the function
	 */
	private Color getPixalColour(double a, double b) {

		int i = 0;
		ComplexNumbers c = new ComplexNumbers(a, b);
		ComplexNumbers z = new ComplexNumbers(0.0, 0.0);
		while (i < 200 && z.modularSquare() < 4) {
			i++;
			z.square();// Squaring the number
			z.add(c);// Adding Zn-1 value
		}

		// Returning colors according to the nature of the Graph
		if (i < 256)
			return new Color(i, 0, 0);
		else if (i < 512)
			return new Color(0, i - 256, 0);
		else if (i < 765)
			return new Color(0, 0, i - 512);
		else
			return Color.WHITE;
	}
}