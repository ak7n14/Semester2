/*
 * @Author Anish Katariya
 * Program creates a simple GUI with text box and 2 buttons
 */

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class SubmitCancel {
    public static void main(String[] args){
    	
     	JFrame window = new JFrame("Simple submit cancel form");
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE) ;
        window.setSize(300,100);
    //Declaring a main container panel to contain text fields and buttons
	Container panel = window.getContentPane();
	panel.setLayout(new FlowLayout());

	//Creating Text fields and buttons
		JTextField t = new JTextField(20);
        JButton submit = new JButton("submit");
        JButton cancel = new JButton("cancel");
       
   //Adding Text fields and buttons to the container
        panel.add(t);
        panel.add(submit);
        panel.add(cancel);
	

       	window.setVisible(true);
   //Setting maximum and minimum sizes to maintain the current layout of the GUI
       	window.setMinimumSize(new Dimension(300,100));
       	window.setMaximumSize(new Dimension(300,100));
    }
}
