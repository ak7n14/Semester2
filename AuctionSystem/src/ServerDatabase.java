import java.util.ArrayList;
import java.util.Random;


public class ServerDatabase {
	//Arraylist of all users
	ArrayList<User> users = new ArrayList<User>();


	
	//Adding new User
	public void addUser(User user){
		users.add(user);
		dataPersistance.SaveUserData(this.getUsers());
	}
	//Data persistance stream
	public void setDataPersistance(DataPersistence dataPersistance) {
		this.dataPersistance = dataPersistance;
	}

	//set user arraylist
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	//Setting bids arraylist
	public void setBids(ArrayList<Bid> bids) {
		this.bids = bids;
	}
	
	ArrayList<Bid> bids = new ArrayList<Bid>();
	ArrayList<Item> items = new ArrayList<Item>();
	DataPersistence dataPersistance = new DataPersistence();

	//getting user arraylist
	public ArrayList<User> getUsers() {
		return users;
	}
	//Return fist and last name of users from user ids
	
	
	
	public String getFirstName(String userId){
		for(User u : users){
			if(u.getUsername()==userId)
				return u.getFirstName();
		}
		return null;
	}
	
	public String getLastName(String userId){
		for(User u : users){
			if(u.getUsername()==userId)
				return u.getFamilyName();
		}
		return null;
	}

	//Getting relevant items
	public ArrayList<Item> getUsersItems(String userID){
		ArrayList<Item> relevant = new ArrayList<Item>();
		for(Item i : items){
			if(i.getUserID().equals(userID))
					relevant.add(i);
		}
		return relevant;
	}
	//getting acive items
	public synchronized ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	//Data persistance stream
		public DataPersistence getDataPersistance() {
			return dataPersistance;
		}


	//For data persistence
	public ServerDatabase() {

		setUsers(dataPersistance.getUsers());
		setItems(dataPersistance.getItems());
		setBids(dataPersistance.getBids());
	}
	//Authentication of user
	public boolean authenticateUser(String name, String password){	
		for(User u : users){
			System.out.println(u.getUsername()+u.getPassword()+u+"\n");
			if(u.getUsername().equals(name) && u.getPassword().equals(password))
				return true;
		}
		return false;
	}
	//Register new user
	public boolean registerUser(User u){

		this.users.add(u);

		System.out.println("List of users ");


		for(User ua : users)
			System.out.println(ua.getUsername()+ua.getPassword());

		dataPersistance.SaveUserData(this.getUsers());
		return true;

	}
	//Validating new item
	public boolean addItem(Item item){
		item.setItemID(generateItemId());
		this.items.add(item);
		System.out.println("The number of items in Auction: " + items.size());
		dataPersistance.SaveItems(this.getItems());
		return true;
	}

	//return user
	public User getUser(int index){
		return users.get(index);
	}

	//Return item
	public Item getItem(int index){
		return this.items.get(index);
	}
	//Generate an item id
	public int generateItemId(){
		Random rnd = new Random();
		String AB = "0123456789";
		int len = 8;
		{
			StringBuilder sb = new StringBuilder( len );
			for( int i = 0; i < len; i++ ) 
				sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );

			//check if non existant item
			for(Item i : items)
				if(i.getItemID() == Integer.parseInt(sb.toString()))
					generateItemId();

			return Integer.parseInt(sb.toString()) ;
		}
	}
	//Adding new bid
	public void addBid(Bid bid){
		this.bids.add(bid);
		dataPersistance.SaveBids(this.getBids());
	}
//Returning array of bids
	public ArrayList<Bid> getBids() {
		return bids;
	}
	//Returning items by item ids
	public Item getItemById(int itemId){
		for(Item i : items){
			System.out.println("Loking at item IDs: "+i.getItemID());
			if(i.getItemID()==itemId)
				return i;
		}

		return null;
	}
	//return arraylist of bids
	public ArrayList<Bid> getItemBids(int itemID){
		ArrayList<Bid> relevantBids = new ArrayList<Bid>();
		for(Bid rel : this.bids){
			System.out.println("Matching report IDs");
			if(rel.getItemID() == itemID){
				relevantBids.add(rel);
			}
		}
		return relevantBids;
	}
	//get specific bid
	public Bid getBid(int index){
		return this.bids.get(index);
	}
	//Getting items bid on
	public ArrayList<Bid> getItemBids(Item item){
		ArrayList<Bid> specificItem = new ArrayList<Bid>();
		for(Bid b : this.bids){
			if(b.getItemID() == item.getItemID()) 
				specificItem.add(b);
		}
		return specificItem;
	}
	//Checking validity ofbid
	public boolean placeBid(Bid bid){
			bids.add(bid);
			dataPersistance.SaveBids(this.getBids());
			return true;
	}
	

	

	//generating Report
	public String generateReport(){
		String report = "There are "+users.size()+ " registered in the system. \n Their names are: \n";
		for(User u : users)
		report +=" " +u.getFirstName()+ " "+u.getFamilyName();
		report +="There are  "+items.size() + "Items registered in the system";
		dataPersistance.makeReport(report);
		return report;
	}
	//Adding relevant bids
	public ArrayList<Bid> getUsersBids(String userID){
		ArrayList<Bid> relevant = new ArrayList<Bid>();
		for(Bid bids : bids){
			if(bids.getBidderID().equals(userID)){
					relevant.add(bids);
					
		}
		}
		return relevant;
	}
}
