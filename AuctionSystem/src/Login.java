import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Login extends JPanel  {

	ImageIcon image;
	JLabel login;
	JLabel username;
	JLabel password;
	JPasswordField passText;
	JTextField usrText;
	JButton enter;
	JButton signUp;
	MainPanel main;
	


	public Login(MainPanel main){ 
		this.main=main;
		init();}

	//initializing the gui
	public void init(){

		

		GridBagLayout gridbag = new GridBagLayout();
		this.setLayout(gridbag);
		GridBagConstraints constrains = new GridBagConstraints();

		login = new JLabel("Login");
		login.setForeground(Color.red);
		username = new JLabel("Username");
		usrText = new JTextField(10);
		password = new JLabel("Password");
		passText = new JPasswordField(10);
		passText.setEchoChar('*');
		signUp = new JButton("Sign Up");
		enter = new JButton("Log In");

		addComponent(login, gridbag, constrains, 0,0,0,0);
		addComponent(username, gridbag, constrains, 1,0,0,0);
		addComponent(usrText, gridbag, constrains, 1,1,0,0);
		addComponent(password, gridbag, constrains, 2,0,0,0);
		addComponent(passText, gridbag, constrains, 2,1,0,0);
		addComponent(signUp, gridbag, constrains, 3,0,2,2);
		addComponent(enter, gridbag, constrains, 3,1,2,2);	


		//now add listeners
		MainButtonListener listener = new MainButtonListener();
		signUp.addActionListener(listener);
		enter.addActionListener(listener);
	}
//Adding Components
	public void addComponent(Component components, GridBagLayout gridbag, GridBagConstraints constrains, int row, int colum, int width, int height){
		constrains.gridx=colum;
		constrains.gridy=row;
		gridbag.setConstraints(components, constrains);
		add(components);
	}

	///Listener for the main button
	class MainButtonListener implements ActionListener{



		public MainButtonListener() {}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Sign Up")){

				System.out.println(username.getText()+", "+password.getText());
				main.changePain(new SignUpGUI(main));


					System.out.println("Sign Up..pressed");

			} else if (e.getActionCommand().equals("Log In")){
				

				
					System.out.println("Log In..pressed");
					System.out.println("This data is sent to the server: "+usrText.getText()+"  "+passText.getText());
				
				
				//send login details to the server for authentication
				Message lm = new LoginMessage(usrText.getText(),passText.getText());
				Comms.sendMessage(lm);
				boolean authenticated = (Boolean) ((BooleanMessage)Comms.receiveMessage()).getBoolean();
			
				
				System.out.println(authenticated);
				
				//if user exists in a database on the server logs him in
				Client.isLoggedIn=authenticated;
				if(authenticated) {
					main.changePain(new AuctionsGUI(main));
					Client.setUserID(usrText.getText());		
				}

			}

		}



	}


}

@SuppressWarnings("serial")
class LogoImage extends JPanel {
//Logo
	public LogoImage() {


		init();
	}
	//Initiaslizes the logi
	public void init(){
		
		JLabel label = new JLabel();  
		label.setIcon(new ImageIcon("auction.jpg"));
		label.setBorder(new EmptyBorder(10, 10, 10, 10) );
		this.add(label);
	}

}