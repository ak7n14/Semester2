/*
 * Author @Anish Katariya
 * programme to create a GUI a textbox and an increment and reset button
 * increment button increments the value of the integer in the textbox by 1
 * resets sets the value ofthe textbox back to 0
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Class creates the GUI and peforms all the tasks
public class Increment_Reset {
	public static void main(String args[]) {
		IRFrame window = new IRFrame("Random Labels!");
		window.init();
			
	}
}
//Class to create the GUI and call listeners
class IRFrame extends JFrame{
	public IRFrame(String title){
		super(title);
	}
	//Initialises the GUI
	public  void init(){
		
		Container panel = this.getContentPane();
		panel.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500,100);
		JTextField textbox = new JTextField("0",10);
		
		JButton increment = new JButton("Increment");
		
		JButton submit = new JButton("Reset");
		
		panel.add(textbox);
		panel.add(increment);
		panel.add(submit);
		
		increment.addActionListener(new IncrementListener(textbox));
	    submit.addActionListener(new ResetListener(textbox));
	    this.setVisible(true);
	}
	
	/*
	 * Called when the increment button is called increments the integer
	 * value in the textbox by 1
	 */
	public class IncrementListener implements ActionListener{
		JTextField textbox;
		public IncrementListener(JTextField textbox){
			this.textbox = textbox;
		}
		public void actionPerformed(ActionEvent e) {
			textbox.setText(String.valueOf(Integer.valueOf(textbox.getText())+1));
			
		}
	}
	/*
	 * called when the reset button is called
	 * changes the value of the textbox to 0
	 */
	public class ResetListener implements ActionListener{
		JTextField textbox;
		public ResetListener(JTextField textbox){
			this.textbox = textbox;
		}
		public void actionPerformed(ActionEvent e) {
			textbox.setText("0");
			
		}
	}
		
}
