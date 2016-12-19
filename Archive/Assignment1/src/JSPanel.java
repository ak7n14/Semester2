/*
 * Author @Anish Katariya
 * This Programme creates a Panel and draws the Julia set
 * On the panel using the Co-Ordinates of the Mandelbrot Set
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JSPanel extends JPanel {
	double xCoOrdinate;
	double yCoOrdinate;
	double reMax;
	double reMin;
	double imMax;
	double imMin;

	public JSPanel() {
		this.setSize(400, 400);
		reMax = 2;
		reMin = -2;
		imMax = 1.6;
		imMin = -1.6;
	}

	// Gets co-ordinates from the mouse motion listener function
	// And calls repaint
	public void setCoOrdinates(double x, double y, JLabel label) {
		xCoOrdinate = x;
		yCoOrdinate = y;
		// Calls function to get the color of the pixel for each pixel in the
		// set
		x = xCoOrdinate * (reMax - reMin) / this.getWidth() + reMin;
		y = yCoOrdinate * (imMax - imMin) / this.getHeight() + imMin;
		String labelText = String.format("X Co-Ordinate = %.2f , Y Co-Ordinate = %.2f, Complex Number : %.2f + %.2f i",
				x, y, x, y);
		label.setText(labelText);
		repaint();
	}

	public void setCoOrdinates(double x, double y) {
		xCoOrdinate = x;
		yCoOrdinate = y;
		// Calls function to get the color of the pixel for each pixel in the
		// set
		repaint();
	}

	// Calls the fractal generator function
	protected void paintComponent(Graphics g) {
		fractolGenerator(g);
	}

	// Generates the Julia set
	private void fractolGenerator(Graphics g) {

		for (int x = 0; x < this.getWidth(); x++) {
			for (int y = 0; y < this.getHeight(); y++) {
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
		ComplexNumbers z = new ComplexNumbers(a, b);
		ComplexNumbers c = new ComplexNumbers(xCoOrdinate * (reMax - reMin) / this.getWidth() + reMin,
				yCoOrdinate * (imMax - imMin) / this.getHeight() + imMin);
		while (i < 1000 && z.modularSquare() < 4) {
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
