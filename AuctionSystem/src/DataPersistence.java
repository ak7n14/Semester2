import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

//Persisting Data
public class DataPersistence {
	//Initializing Buffered readers
	BufferedReader reader;
	String usersFile = "alausers.csv";
	String itemsFile = "allaitems.csv";
	String bidsFile = "allabids.csv";
	String logsFile = "alllogs.txt";
	String reportFile = "genreport.txt";
	//Saving UsersData
	public void SaveUserData(ArrayList<User> users){
		createFile(usersFile);

		try {
			FileOutputStream out = new FileOutputStream(usersFile); 
			PrintStream printstream = new PrintStream(out);
			for(User user : users){
				printstream.println(user.getFirstName()+","+user.getFamilyName()+","+user.getUsername()+","+user.getPassword());

			}
		} catch (FileNotFoundException e) {
			//TODO auto generated catch block
			e.printStackTrace();
		}
	}
	


	//Saving log file
	 public void SaveLog(String logsData){
		createFile(logsFile);
		
		try {
			FileOutputStream out = new FileOutputStream(logsFile); 
			PrintStream printstream = new PrintStream(out);
				printstream.println(logsData);
		} catch (FileNotFoundException e) {
			//TODO auto generated catch block
			e.printStackTrace();
		}
		
	}
	//Saving items
		public void SaveItems(ArrayList<Item> items){
			createFile(itemsFile);

			try {
				FileOutputStream out = new FileOutputStream(itemsFile); 
				PrintStream printstream = new PrintStream(out);
				for(Item item : items){
					printstream.println(item.getItemID()+","+item.getUserID()+","+item.getTitle()+","+item.getReservedPrice()+","+item.getkeyWordChosen()+","+item.getStartTime()+","+item.getEndTime()+","+item.getDescription());

				}
			} catch (FileNotFoundException e) {
				//TODO auto generated catch block
				e.printStackTrace();
			}
		}
		//generating a report
	 public void makeReport(String report){
			createFile(reportFile);
			
			try {
				FileOutputStream out = new FileOutputStream(reportFile); //this method does not append text...add true if you want it to
				PrintStream ps = new PrintStream(out);
					ps.println(report);
			} catch (FileNotFoundException e) {
				//TODO auto generated catch block
				e.printStackTrace();
			}
			
		}
	public void SaveBids(ArrayList<Bid>  bids){
		createFile(bidsFile);

		try {
			FileOutputStream out = new FileOutputStream(bidsFile); 
			PrintStream printstream = new PrintStream(out);
			for(Bid bid : bids){
				printstream.println(bid.getBidderID()+","+bid.getItemID()+","+bid.getBid());
				}
			} catch (FileNotFoundException e) {
				//TODO auto generated catch block
				e.printStackTrace();
			}

		}
	//ArrayList to get all users
	public ArrayList<User> getUsers(){
		ArrayList<User> users = new ArrayList<User>();
		createFile(usersFile);
		try {
			String[] data;
			reader = new BufferedReader(new FileReader(usersFile));

			String strLine;
			while((strLine = getLine()) != null){
				data = strLine.split(",");

				User user = new User(data[0], data[1],data[2],data[3]);
				users.add(user);

			}
	
		}catch (IOException e){
			//TODO auto generated catch block
			System.err.println(e);
		}

		return users;
	}

	//reads items from the csv file, stores them in array list and returns them
	public ArrayList<Item> getItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		createFile(itemsFile);
		try {
			String[] data;
			reader = new BufferedReader(new FileReader(itemsFile));

			String strLine;
			while((strLine = getLine()) != null){
				data = strLine.split(",");


				Item item = new Item(Integer.parseInt(data[0]), data[1],data[2],Integer.parseInt(data[3]), data[4], data[5], data[6],data[7]);
				items.add(item);

			}
		}catch (IOException e){
			//TODO auto generated catch block
			System.err.println(e);
		}

		return items;
	}

	//reads bids from the csv file and stores it in an arraylist
	public ArrayList<Bid> getBids(){
		ArrayList<Bid> bids = new ArrayList<Bid>();
		createFile(bidsFile);
		try {
			String[] data;
			reader = new BufferedReader(new FileReader(bidsFile));
			
			String strLine;
			while((strLine = getLine()) != null){
				data = strLine.split(",");

				Bid bid = new Bid(data[0], Integer.parseInt(data[1]),Integer.parseInt(data[2]));
				bids.add(bid);

			}
		}catch (IOException e){
			//TODO auto generated catch block
		System.err.println(e);
		}
		
		return bids;
	}


	public File createFile(String name){
		File file = new File(name);
		//creatsS a new file if it does not exist
			if(!file.exists()){ 
		try {
			file.createNewFile();
		} catch (IOException e) {
			//TODO auto generated catch block
			e.printStackTrace();
		}
			}
		return file;
	}



	// read the file
	public String getLine() { 
		try {
			return reader.readLine();
		} catch (IOException e) {
			//TODO auto generated catch block
			System.err.println("Line not found");
			return "false";
		}
	}



}
