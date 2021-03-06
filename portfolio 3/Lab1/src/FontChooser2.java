/*
 * @Author Anish Katariya
 * Program creates a simple GUI with text box and 2 buttons
 */

import java.awt.*;
import javax.swing.*;


public class FontChooser2 {
	public static void main(String args[]){
		//Creating a frame setting its size and exiting it when pressed close
		
		JFrame window = new JFrame("Font Chooser");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(500,100);
		
		//Making containers for font type ,font style, textbox and submit button
		Container panel = window.getContentPane();//Main container
		Container fontTypes = new Container();
		Container fontStyles = new Container();
		Container comboBox = new Container();
		
		//Setting layouts for each of the panels
		panel.setLayout(new FlowLayout());
		fontTypes.setLayout(new GridLayout(2,1) );
		fontStyles.setLayout(new BorderLayout(3,1));
		comboBox.setLayout(new FlowLayout());
		
		//Creating checkboxes for bold and italics
		JCheckBox bold = new JCheckBox("bold");
		JCheckBox italic = new JCheckBox("italics");
		
		//Adding the created check boxes to the fontTypes
		fontTypes.add(bold);
		fontTypes.add(italic);
		//Adding panel to the main container panel
		panel.add(fontTypes);
	
		String[] fontsStrings = { "Times", "Hevlica", "Courier" };
		//creating a Combo Box containing font sizes
		JComboBox<String> fontsList = new JComboBox<String>(fontsStrings);
		fontsList.setSelectedIndex(0);
		
		//Adding combo box to fontStyles and fontStyles to main container panel
		fontStyles.add(fontsList);
		panel.add(fontStyles);
		//Creating textbox and Submit Button
		JTextField text = new JTextField(10);
		JButton Submit = new JButton("Submit");
		
		//Adding textbox and submit button to comboBox	
		comboBox.add(text);
		comboBox.add(Submit);
		//Adding comboBox to main panel
		panel.add(comboBox);
		
		window.setVisible(true);
		
		//Setting the minimum size to maintain current layout
		window.setMinimumSize(new Dimension(500,100));
		
	}
	
}
