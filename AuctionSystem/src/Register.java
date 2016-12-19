import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;



public class Register extends JPanel {
	//Components of the gui
	JLabel register;
	JLabel errorMsg;
	JLabel usrName;
	JTextField usrNameText;
	JLabel firstName;
	JTextField firstNameText;
	JLabel lastName;
	JTextField lastNameText;
	JLabel pass1;
	JPasswordField pass1Text;
	JLabel pass2;
	JPasswordField pass2Text;
	JButton back;
	JButton submit;
	MainPanel main;
	
	public Register(MainPanel main) {	
		this.main=main;//setting to the main panel
		init();//calling for initialization
	}
	//Initializing the gui
	public void init(){
		
	//Setting up grid bag layout
	GridBagLayout gridbag = new GridBagLayout();
	this.setLayout(gridbag);
	GridBagConstraints constrains = new GridBagConstraints();
	//creating lables and texts feilds
	register = new JLabel("Register");
	register.setForeground(Color.red);
	usrName = new JLabel("Username: ");
	usrNameText = new JTextField(10);	
	firstName = new JLabel("First Name: ");
	firstNameText = new JTextField(10);	
	lastName = new JLabel("Last Name: ");
	lastNameText = new JTextField(10);	
	pass1 = new JLabel("Password: ");
	pass1Text = new JPasswordField(10);	
	pass2 = new JLabel("Re-enter: ");
	pass2Text = new JPasswordField(10);	
	back = new JButton("Back");
	submit = new JButton("Submit");
	//Adding components to the gridbag layout
	addComponent(register, gridbag, constrains, 0,0,0,0);	
	addComponent(usrName, gridbag, constrains, 1,0,0,0);
	addComponent(usrNameText, gridbag, constrains, 1,2,0,0);	
	addComponent(firstName, gridbag, constrains, 2,0,0,0);
	addComponent(firstNameText, gridbag, constrains, 2,2,0,0);	
	addComponent(lastName, gridbag, constrains, 3,0,0,0);
	addComponent(lastNameText, gridbag, constrains, 3,2,0,0);	
	addComponent(pass1, gridbag, constrains, 4,0,0,0);
	addComponent(pass1Text, gridbag, constrains, 4,2,0,0);	
	addComponent(pass2, gridbag, constrains, 5,0,0,0);
	addComponent(pass2Text, gridbag, constrains, 5,2,0,0);
	addComponent(back, gridbag, constrains, 6,0,0,0);
	addComponent(submit, gridbag, constrains, 6,2,0,0);
	
	//now add action listenrs
	ButtonListener bl = new ButtonListener();
	back.addActionListener(bl);
	submit.addActionListener(bl);
	}
	//Function to add components in the gridbag
	public void addComponent(Component component, GridBagLayout gridbag, GridBagConstraints constrains, int row, int colum, int width, int height){
		constrains.gridx=colum;
		constrains.gridy=row;
		gridbag.setConstraints(component, constrains);
		add(component);
	}

	//Button listener for the register page
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Back")){
				
				
				main.changePain(new LoginGUI(main));
				
				
				System.out.println("Back..pressed");
	            
			} else if (e.getActionCommand().equals("Submit")){
		
			//Send server a registration request and see if it is sucessfull by passing back a boolean	
			RegisterMessage m = new RegisterMessage(new User(firstNameText.getText(), lastNameText.getText(), 
					usrNameText.getText(), pass1Text.getText()));
			try {
				
				Comms.sendMessage(m);
				Boolean registrationSuccesfull = (Boolean) ((BooleanMessage)Comms.receiveMessage()).getBoolean();
				
				System.out.println("The registration was: "+ registrationSuccesfull);
				
				if(registrationSuccesfull){
					JOptionPane.showMessageDialog(main, "Registration Successfull!");
					main.changePain(new LoginGUI(main));
				}
				
} catch (Exception e1) { 
	
	//TODO auto generates catch block
	
				e1.printStackTrace();
   }
			
			
			
			
			}
			
		}
	}
	

}
