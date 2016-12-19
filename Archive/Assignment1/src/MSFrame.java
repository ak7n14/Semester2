/*
 * @Author Anish Katariya
 * This program creates the main frame and adds 
 * Mandelbrot , Julia and burning ship fractals to it
 */

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.io.BufferedReader;

public class MSFrame extends JFrame {
	private ArrayList favlist;

	public MSFrame() {
		super("Fractals");
	}

	// Initializing and setting up the GUI
	public void init() {
		JPanel panel = new JPanel();// Main Panel
		// Secondary panel to show complex number co-ordinates and combobox
		// for favorite co-ordinates
		JPanel panel2 = new JPanel();
		panel2.setSize(1200, 100);
		panel2.setLayout(new GridLayout(1, 3));
		// Label to show the co-ordinates
		JLabel label = new JLabel();
		favlist = new ArrayList<favourite>();
		JComboBox<String> list = new JComboBox<String>();
		JPanel panel3 = new JPanel();
		panel3.add(list);
		panel2.add(label);// adding label to panel 2
		panel2.add(panel3);
		// Setting background color of label as white
		label.setBackground(Color.WHITE);
		label.setOpaque(true);
		panel.setLayout(null);
		JSPanel jspanel = new JSPanel();// initializing Julia Set Panel
		MSPanel mspanel = new MSPanel(jspanel, label, list, favlist);// initializing
		BSPanel bspanel = new BSPanel();																// Mandelbrot
																		// set
																		// panel
		
		this.setSize(1200, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(panel);// Setting panel as the main panel

		// Adding panels to the main panel and setting their location
		panel.add(panel2);
		panel2.setLocation(0, 401);
		panel.add(jspanel);
		jspanel.setLocation(0, 0);
		panel.add(mspanel);
		mspanel.setLocation(400, 0);
		panel.add(bspanel);
		bspanel.setLocation(800,0);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		MSFrame window = new MSFrame();// creating new framw
		window.init();// initializing frame
	}

}









