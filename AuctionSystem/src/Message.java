import java.io.Serializable;
import java.util.ArrayList;


public abstract class Message implements Serializable {


private static final long serialVersionUID = 7905158138813718470L;
protected Object message;//Mesage object to recieve different type of messages

//Constructor to assign message
public Message(Object message){
	this.message=message;
	
}
//Getter methode to get message
public Object getObjectContent() {
	return message;
}

}


//Class to send register request to the server
class RegisterMessage extends Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5101211567120584788L;
	User user;
	//Register
	public RegisterMessage(User usr) {
		super("Register Message");
		this.user=usr;
	}
	//return the user
	public User getUser(){
		return user;
		
	}

}
//Class to obtain boolean statuses of the message
class BooleanMessage extends Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 785033920085196125L;
	boolean bool;
	
	public BooleanMessage(boolean bool) {
		super("boolean message");
		this.bool=bool;
		
	}
	
	public boolean getBoolean(){
		return this.bool;
	}	
}
//login request sent to the server
class LoginMessage extends Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4160061675783399330L;
	String username, pass;//Username and password
	//Constructor to initialize usename and password
	public LoginMessage(String username, String pass) {
		super("Login Message");
		this.username=username;
		this.pass=pass;
	}
	//Getter methods for username and password
	
	
	public String getUsername() {
		return username;
	}

	
	public String getPassword() {
		return pass;
	}
}


//Send request to server to add an item to the auction list
class AddItemMessage extends Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3181054050056462259L;
	Item item;
	
	public AddItemMessage(Item item){
		super("Add Item Message");
		this.item=item;
		
	}
	
	public Item getItem(){
		return this.item;
	}	
	}

//request to get active auctions from the server
class RequestAllAuctions extends Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6253735881224174365L;

	public RequestAllAuctions() {
		super("Request all auctions");
	}
}	
//To get all auction
class GetAllAuctions extends Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4701214578332935631L;
	ArrayList<Item> item;//ArrayList with all items up for auction
	//Constructor to initialize an arraylist with all auctions
	public GetAllAuctions(ArrayList<Item> item) {
		super("Get All Auctions");
		this.item=item;	
	}
	
	//Getter method for arraylist containing items
	public ArrayList<Item> getItemsList(){
		return item;
	}

	
	
	
}
//Request for information page of an item
class RequestItemInfo extends Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1668621079647024746L;
	int ItemID;
	
	public int getItemID() {
		return ItemID;
	}

	public RequestItemInfo(int ItemID) {
		super("Request Item info");
		this.ItemID=ItemID;
		
	}
	

	
}
//To send information about the items
class SendItemInfo extends Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6369753114440450740L;
	Item Item;
	public Item getItem() {
		return Item;
	}

	public ArrayList<Bid> getRelevantBids() {
		return relevantBids;
	}

	ArrayList<Bid> relevantBids;
	
	public SendItemInfo(Item item,ArrayList<Bid> relevantBids ) {
		super("Request Item info");
		this.Item=item;
		this.relevantBids=relevantBids;
				
	}
	

	
}

//Placing a bid
class PlaceBidMessage extends Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8265543048292282118L;
	Bid bid;
	
	public Bid getBid() {
		return bid;
	}

	public PlaceBidMessage(Bid bid) {
		super("Place bid");
		this.bid=bid;
	}
	
	
	
}

//Request for users information from the server
class RequestUserInfo extends Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8235631260954486881L;
	String userId;
	
	public RequestUserInfo(String userId) {
		super("Request user Info");
		this.userId=userId;
	}

	public String getUserId() {
		return userId;
	}
}
//Server sends back userd information
class SendUserInfo extends Message implements Serializable{
	

	private static final long serialVersionUID = -587133931107013399L;
	//User info to send
	String name;
	String lastname;
	ArrayList<Bid> relevantBids;
	ArrayList<Item> usersItems;
	
	public  SendUserInfo(String name, String lastName, ArrayList<Bid> relevantBids, ArrayList<Item> usersItems) {
		super("Place bid");
		this.name=name;
		this.lastname=lastName;
		this.relevantBids=relevantBids;
		this.usersItems=usersItems;

	}
	//Getter methods
	
	
	public String getName() {
		return name;
	}

	public String getLastname() {
		return lastname;
	}

	public ArrayList<Bid> getRelevantBids() {
		return relevantBids;
	}
	
}



	
	
