import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.*;


public class Profile extends JPanel {
	//creating Information feilds
	MainPanel main;
	JLabel usrName;
	JLabel usrNameText;
	JLabel firstName;
	JLabel firstNameText;
	JLabel lastName;
	JLabel lastNameText;
	JLabel userBids;
	JList bids;
	SendUserInfo userInfo;
	//creating panel
	public Profile(MainPanel main) {	
		this.main=main;
		RequestUserInfo requestUserInfo = new RequestUserInfo(Client.getUserID());
		Comms.sendMessage(requestUserInfo);
		userInfo = (SendUserInfo) Comms.receiveMessage();
		go();
	}
	
	//Setting Up GUI
	public void go() {
		GridBagLayout gridbag = new GridBagLayout();
		this.setLayout(gridbag);
		GridBagConstraints constrains = new GridBagConstraints();
		
		usrName = new JLabel("User name: ");
		usrNameText = new JLabel(Client.getUserID());
		firstName = new JLabel("First Name: ");
		firstNameText = new JLabel(userInfo.getName());
		lastName = new JLabel("Last Name: ");
		lastNameText = new JLabel(userInfo.getLastname());
		userBids = new JLabel("List of Bids of This user");
		bids = new JList();
		//setting gridbag constrains
		addComponent(usrName, gridbag, constrains, 0,0,0,0);
		addComponent(usrNameText, gridbag, constrains, 0,1,0,0);
		addComponent(firstName, gridbag, constrains, 1,0,0,0);
		addComponent(firstNameText, gridbag, constrains, 1,1,0,0);
		addComponent(lastName, gridbag, constrains, 2,0,0,0);
		addComponent(lastNameText, gridbag, constrains, 2,1,0,0);
		addComponent(userBids, gridbag, constrains, 0,4,0,0);
		addComponent(bids, gridbag, constrains, 1,4,0,0);
		
	}
	//Adding Components
	public void addComponent(Component component, GridBagLayout gridbag, GridBagConstraints constrains, int row, int colum, int width, int height){
		constrains.gridx=colum;
		constrains.gridy=row;
		gridbag.setConstraints(component, constrains);
		add(component);
	}

}
//Creating InfoBar
class InfoBar extends JPanel{
	
	JLabel info;
	MainPanel main;
	
	public InfoBar(MainPanel main) {
		this.main=main;
		init();
	}
	//Initilizing
	public void init(){
			
		info = new JLabel(Client.getUserID() + "More information below.");
		this.add(info);
	}
	
}
