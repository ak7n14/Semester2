/*
 * @Author Anish Katariya
 * Program creates a simple GUI with text box and 2 buttons
 */

import java.awt.*;
import javax.swing.*;

public class FontChooser1 {
	public static void main(String args[]){
		
		//Creating a frame setting its size and exiting it when pressed close
		JFrame window = new JFrame("Font Chooser");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(500,100);
		
		//Making containers for font type ,font style, textbox and submit button
		Container panel = window.getContentPane();//Main container
		Container fontTypes = new Container();
		Container fontStyles = new Container();
		Container textboxSubmit = new Container();
		
		//Setting layouts for each of the panels
		panel.setLayout(new FlowLayout());
		fontTypes.setLayout(new GridLayout(2,1) );
		fontStyles.setLayout(new GridLayout(3,1));
		textboxSubmit.setLayout(new FlowLayout());
		
		//Creating checkboxes for bold and italics
		JCheckBox bold = new JCheckBox("bold");
		JCheckBox italic = new JCheckBox("italics");
		
		//Adding the created check boxes to the fontTypes
		fontTypes.add(bold);
		fontTypes.add(italic);
		
		//Adding panel to the main container panel
		panel.add(fontTypes);
		
		//Creating radio buttons with the type of font styles
		JRadioButton times = new JRadioButton("Times", true);
		JRadioButton hevlica = new JRadioButton("Hevlica", false);
		JRadioButton courier = new JRadioButton("Courier", false);
		
		//Creating a button group with the created radio buttons
		ButtonGroup fonts = new ButtonGroup();
		fonts.add(times);
		fonts.add(hevlica);
		fonts.add(courier);
		
		//Adding the created buttons to fontStyles
		fontStyles.add(times);
		fontStyles.add(hevlica);
		fontStyles.add(courier);
		
		//Adding fontStyles to the main panel
		panel.add(fontStyles);
		
		//Creating textbox and Submit Button
		JTextField text = new JTextField(10);
		JButton submit =new JButton("Submit");
		
		//Adding textbox and submit button to textboxSubmit	
		textboxSubmit.add(text);
		textboxSubmit.add(submit);
		//Adding textboxSubmit to main panel
		panel.add(textboxSubmit);
		
		window.setVisible(true);
		
		//Setting the minimum size to maintain current layout
		window.setMinimumSize(new Dimension(500,100));
		
	}
	
}
