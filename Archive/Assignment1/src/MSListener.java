/*
 * Author @Anish Katariya
 * This Programme creates a mouse listener for the MSPanel
 * to draw the julia set according to the co-ordinates of the mandelbrot set
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

public class MSListener implements MouseMotionListener {
	JSPanel panel;
	JLabel label;

	public MSListener(JSPanel panel, JLabel label) {
		this.panel = panel;
		this.label = label;
	}

	// Calls the set co-ordinates function to repaint the frame
	public void mouseDragged(MouseEvent e) {
		panel.setCoOrdinates(e.getX() * 1.0, e.getY() * 1.0, label);
	}

	// Calls the set co-ordinates function to repaint the frame
	public void mouseMoved(MouseEvent e) {
		panel.setCoOrdinates(e.getX() * 1.0, e.getY() * 1.0, label);

	}

}
