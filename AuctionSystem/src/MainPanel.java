import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.AbstractTableModel;

import java.awt.event.*;
import java.awt.geom.Ellipse2D;


public class MainPanel extends JFrame {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3964442707127299417L;
	//panel for the main gui
	public MainPanel(String title){
		super(title);
	}
	//initializing the gui
	public void init(){
		
	

		LoginGUI panel = new LoginGUI(this);
		this.setContentPane(panel);
		
		JMenuBar menu = new MainMenuBar(this);
		this.setJMenuBar(menu);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 	
		setSize(900,400);
		setResizable(false);
		
		setVisible(true); 	
	}	
	//change the pane on screen
	public void changePain(JPanel newPanel){
	
		this.setContentPane(newPanel);
		this.revalidate();
		System.out.println("change Pane called");	
	}
	

	
} 

//Login GUI
class LoginGUI extends JPanel {
	
	MainPanel main;
	
	public LoginGUI(MainPanel main) {
		this.main=main;
		init();//call for initialization of the gui 
		}
	//initializing the gui
	public void init(){
		GridLayout gbl = new GridLayout(2,1);
		this.setLayout(gbl);
		
		this.add(new LogoImage());
		this.add(new Login(this.main));
	}
}

class SignUpGUI extends JPanel {
	
	MainPanel main;
	
	public SignUpGUI(MainPanel main) {
		this.main=main;
		init();}
	
	public void init(){
		//Sign Up Screen
		GridLayout gbl = new GridLayout(2,1);
		this.setLayout(gbl);
		
		this.add(new LogoImage());
		this.add(new Register(this.main));
	}
	
}

class ViewItemGUI extends JPanel {
	
	MainPanel main;
	Item item;
	ArrayList<Bid> relevantBids;
	
	public ViewItemGUI(MainPanel main, Item item, ArrayList<Bid> relevantBids) {
		this.main=main;
		this.item=item;
		this.relevantBids=relevantBids;
		init();}
	
	public void init(){
		FlowLayout gbl = new FlowLayout();
		this.setLayout(gbl);
		this.setLayout(new BorderLayout());
		this.add(new GetItem(main, item, relevantBids), BorderLayout.WEST);
		this.add(new BidsInfo(main, item), BorderLayout.SOUTH);
		this.add(new PlaceBids(main,relevantBids), BorderLayout.EAST);
		this.add(new ItemGreetingBar(main), BorderLayout.NORTH);
		
		//this.add(new SeeItem(main));
		//this.add(new BidsInfo(main));ItemGreetingBar
	}
}

class AddItemGUI extends JPanel {
	
	MainPanel main;
	
	public AddItemGUI(MainPanel main) {
	this.main=main;
	init();}
	
	public void init(){
		this.add(new AddItemInfo(this.main));
	}
}

class MyProfileGUI extends JPanel{
	
	MainPanel main;
	
	public MyProfileGUI(MainPanel main) {
		this.main=main;
		init();
		
	}
	
	public void init(){
		this.setLayout(new BorderLayout());
		
		this.add(new InfoBar(main), BorderLayout.NORTH);
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		JPanel profile = new Profile(main);
		profile.setBorder(new EmptyBorder(0, 20, 250, 0));
	this.add(profile);
		

		
	}
	
	
}

class AuctionsGUI extends JPanel {
	
	MainPanel main;
	
	public AuctionsGUI(MainPanel main) {
		this.main=main;
		init();}
	
	public void init(){
	//	 GridLayout gL = new GridLayout(2,1);
	//	this.setLayout(gL);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(new Search(main));
		this.add(new AuctionList(main));
	}
}

   
class MainMenuBar extends JMenuBar {
	
	
	
	//JMenuBar menuBar;
	JMenu home;
	JMenu myProfile;
	JMenu addItem;
	JMenu notifications;
	MainPanel main;
	
	
	public MainMenuBar(MainPanel main) {
		this.main=main;
		init();
	}
	
	public void init(){

		home = new JMenu("Home");
		myProfile = new JMenu("My Profile");
		addItem = new JMenu("Add Item");
		notifications = new JMenu("About");
	
		

		this.add(home);
		this.add(myProfile);
		this.add(addItem);
		this.add(notifications);
	

		//add Action Listeners
		ButtonListener bl = new ButtonListener();
		home.addMenuListener(bl);
		myProfile.addMenuListener(bl);
		addItem.addMenuListener(bl);
		notifications.addMenuListener(bl);
		
	}
	
	
	class ButtonListener implements MenuListener{


		public ButtonListener() {}
		
		@Override
		public void menuCanceled(MenuEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void menuDeselected(MenuEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		
		@Override
		public void menuSelected(MenuEvent e) {
		if(e.getSource().equals(home)){
			if (Client.isLoggedIn) main.changePain(new AuctionsGUI(main));
				
				
				System.out.println("Home..pressed");
	            
			} else if (e.getSource().equals(addItem)){
				if (Client.isLoggedIn)	main.changePain(new AddItemGUI(main));
				
				
					System.out.println("add Item... pressed");

				}
				
				if(true)
					if (Client.isLoggedIn)	main.changePain(new AddItemGUI(main));
				
			 else if(e.getSource().equals(notifications)){
				
					System.out.println("notifications... pressed");

				
				
				JOptionPane.showMessageDialog(main, "This is LightWeight Auction System developed by Anish");

			} else if(e.getSource().equals(myProfile)){
				
				if (Client.isLoggedIn)	main.changePain(new MyProfileGUI(main));
				
			}
		}
		
		
		
	}
	
}



