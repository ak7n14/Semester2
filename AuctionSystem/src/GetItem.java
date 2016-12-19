import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import java.util.regex.*;


public class GetItem extends JPanel{

/**
	 * 
	 */
	private static final long serialVersionUID = 228596782198418905L;
	//Show active Auctions
	JLabel itemName;
	JLabel itemNameText;
	JLabel auctionStart;
	JLabel auctionStartText;
	JLabel auctionEnd;
	JLabel auctionEndText;
	JLabel description;
	JTextField descriptionText;
	JLabel price;
	JLabel priceText;
	JLabel seller;
	JLabel sellerText;

	//Active Bids Displayed

	MainPanel main;
	ArrayList<Bid> activeBids;
	Item item;
//Initiliazing
	public GetItem(MainPanel main,Item item, ArrayList<Bid> activeBids) {

		this.main=main;
		this.item=item;
		this.activeBids=activeBids;
		initContentContainer();
		initBidsContainer();

	}

	public void initContentContainer(){
		// Making GUI neater
		Border emptyBoader = new EmptyBorder(100,100,100,100);
		this.setBorder(emptyBoader);

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constrains = new GridBagConstraints();
		this.setLayout(gridbag);
		
		//Adding Information
		
		itemName = new JLabel("Item Name");
		itemNameText = new JLabel(this.item.getTitle());
		auctionStart=new JLabel("Start Date");
		auctionStartText=new JLabel(this.item.getStartTime());
		auctionEnd=new JLabel("End Date");
		auctionEndText=new JLabel(this.item.getEndTime());
		seller=new JLabel("seller");
		sellerText=new JLabel(this.item.getUserID());
		description=new JLabel("Description");
		descriptionText=new JTextField(40);
		descriptionText.setText(this.item.getDescription());
		descriptionText.setEditable(false);
		price = new JLabel("Price");
		priceText=new JLabel(Integer.toString(this.item.getReservedPrice()));
		
		//Adding Components to the panel
		
		addComponent(itemName, gridbag, constrains, 0,0,0,0);
		addComponent(itemNameText, gridbag, constrains, 0,1,0,0);
		addComponent(auctionStart, gridbag, constrains, 1,0,0,0);
		addComponent(auctionStartText, gridbag, constrains, 1,1,0,0);
		addComponent(auctionEnd, gridbag, constrains, 2,0,0,0);
		addComponent(auctionEndText, gridbag, constrains, 2,1,0,0);
		addComponent(seller, gridbag, constrains, 3,0,0,0);
		addComponent(sellerText, gridbag, constrains, 3,1,0,0);
		addComponent(price, gridbag, constrains, 4,0,0,0);
		addComponent(priceText, gridbag, constrains, 4,1,0,0);
		addComponent(description, gridbag, constrains, 5,0,0,0);
		constrains.gridwidth = 4;
		addComponent(descriptionText, gridbag, constrains, 6,0,0,0);

	}

	public void initBidsContainer(){

	}
//Function to add comonnents
	public void addComponent(Component com, GridBagLayout gridbag, GridBagConstraints c, int row, int colum, int width, int height){
		c.gridx=colum;
		c.gridy=row;
		gridbag.setConstraints(com, c);
		add(com);
	}


}
//Creats greatings bar
class ItemGreetingBar extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873943286957657444L;
	JLabel welcome;
	MainPanel main;

	public ItemGreetingBar(MainPanel main) {
		this.main=main;
		init();
	}

	public void init(){

		welcome = new JLabel(Client.getUserID() + "More Information below");
		this.add(welcome);
	}

}

class BidsInfo extends JPanel{


	/**
	 * 
	 */
	private static final long serialVersionUID = 8753736944571717208L;
	MainPanel main;
	JLabel bid;
	JTextField bidValue;
	JButton placeBid;
	Item item;

	public BidsInfo(MainPanel main, Item item) {
		this.main=main;
		this.item=item;
		init();
	}

	public void init(){

		bid = new JLabel("Your bid: ");
		bidValue = new JTextField(10);
		placeBid = new JButton("Place your Bid");

		this.add(bid);
		this.add(bidValue);
		this.add(placeBid);

		//Add listeners
		BidPlacingListener bpl = new BidPlacingListener();
		placeBid.addActionListener(bpl);

	}

	class BidPlacingListener implements ActionListener{


		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Place your Bid")){
				if(Main.debug)System.out.println("The value of placed bid is: "+bidValue.getText());
				
				boolean validation =true;
				//Validating BID
				boolean isInteger =  bidValue.getText().matches("[+]?\\d*?");
				if(!isInteger){
					JOptionPane.showMessageDialog(main, "Bid must be an integer");
					validation=false;
				}
				//Creating new Bid if valid or returning error message
				if(validation){
				Bid bid = new Bid(Client.userID, item.getItemID(), Integer.parseInt(bidValue.getText()));
				
				PlaceBidMessage pbm = new PlaceBidMessage(bid);
				Comms.sendMessage(pbm);
				boolean placed = (Boolean) ((BooleanMessage)Comms.receiveMessage()).getBoolean();
				
				if(placed){
					JOptionPane.showMessageDialog(main, "Bid placed Successfully!");
					main.changePain(new AuctionsGUI(main));
				} else{
					JOptionPane.showMessageDialog(main, "Error:Insufficient bid");
				}
				
			}
			}
			
		}

	}


}

class PlaceBids extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6287758355993890346L;
	//Creating Components
	JLabel title;
	JList bids;
	MainPanel main;
	ArrayList<Bid> activeBids;
	public PlaceBids(MainPanel main,ArrayList<Bid> activeBids) {
		this.main=main;
		this.activeBids=activeBids;
		init();
	}
	//Initializing GUI
	public void init(){

		String[] data = new String[activeBids.size()];
		for(int i=0; i<activeBids.size(); i++)
			data[i] = "User "+activeBids.get(i).getBidderID()+" bids "+ activeBids.get(i).getBid();

		title = new JLabel("Bids:");
		bids = new JList(data);
		bids.setLayoutOrientation(JList.VERTICAL);
		bids.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bids.setVisibleRowCount(-1);

		this.setBorder(new EmptyBorder(90, 0, 0, 100));

		this.add(bids);
	}

}