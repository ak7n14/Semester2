import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
class ClientWorker implements Runnable {
	private Socket client;//client side
	private JTextArea textArea;//Server GUI
	private ServerDatabase database;//Database for data persistance
	private DataPersistence persistance = new DataPersistence();//data persistance stream
	//Initialization
	ClientWorker(Socket client, JTextArea textArea, ServerDatabase database) {
		this.client = client;
		this.textArea = textArea;
		this.database=database;
	}
	//running thread
	public void run() {
		Message message;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		try {
			in = new ObjectInputStream(client.getInputStream());
			out = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			System.out.println("in or out failed");
			System.exit(-1);
		}
		while (true) {
			
			//Checking various messages verifying and sending to client side in form of objects
			try {
				message = (Message) in.readObject();

				textArea.append("CLIENT:"+message.getClass()+"\n");
				

				if(message instanceof LoginMessage){
					textArea.append("LoginMessage : "+((LoginMessage) message).getUsername()+"  "+ ((LoginMessage) message).getPassword()+"\n");
					persistance.SaveLog("LoginMessage: "+((LoginMessage) message).getUsername()+"  ");
					boolean authenticate = database.authenticateUser(((LoginMessage) message).getUsername(), ((LoginMessage) message).getPassword());
					 textArea.append(authenticate+""+"\n");
					message=new BooleanMessage(authenticate);
					
				}
				
				if(message instanceof RegisterMessage){
					textArea.append(((RegisterMessage) message).getUser().getUsername()+"\n");
					persistance.SaveLog(((RegisterMessage) message).getUser().getUsername());
					boolean registerUser = database.registerUser(((RegisterMessage) message).getUser());
					message= new BooleanMessage(registerUser);
				}
				
				if(message instanceof AddItemMessage ){
					textArea.append(((AddItemMessage) message).getItem().getItemID()+""+((AddItemMessage) message).getItem().getTitle()+""+((AddItemMessage) message).getItem().getDescription()+""+((AddItemMessage) message).getItem().getReservedPrice()+""+((AddItemMessage) message).getItem().getStartTime()+""+"\n");
					persistance.SaveLog("data received");
					boolean addItem = database.addItem(((AddItemMessage) message).getItem());
					message=new BooleanMessage(addItem);
				}
				
				if(message instanceof RequestAllAuctions){
					ArrayList<Item> items = database.getItems();
					message=new GetAllAuctions(items);
				}
				
				if(message instanceof RequestItemInfo){
					textArea.append(""+((RequestItemInfo) message).getItemID());
					Item item = database.getItemById(((RequestItemInfo) message).getItemID());
					ArrayList<Bid> bids = database.getItemBids(((RequestItemInfo) message).getItemID());
					textArea.append(""+item+bids);
					message= new SendItemInfo(item, bids);
				}
				
				if(message instanceof PlaceBidMessage){
					textArea.append(""+((PlaceBidMessage) message).getBid().getBidderID());
					Bid bid = ((PlaceBidMessage) message).getBid();
					boolean addedSuc = database.placeBid(bid);
					message=new BooleanMessage(addedSuc);
				
				}
				
				if(message instanceof RequestUserInfo){
					textArea.append(""+((RequestUserInfo) message).getObjectContent());
					String firstName = database.getFirstName(((RequestUserInfo) message).getUserId());
					String lastName = database.getLastName(((RequestUserInfo) message).getUserId());
					ArrayList<Bid> usersBids = database.getUsersBids(((RequestUserInfo) message).getUserId());
					ArrayList<Item> usersItems = database.getUsersItems(((RequestUserInfo) message).getUserId());
					message = new SendUserInfo(firstName, lastName, usersBids, usersItems);
								
			}
			
				out.writeObject(message);
				
				textArea.append(message.toString());
			} catch (Exception e) {
				System.out.println("Error:Unable to read");
				System.exit(-1);
			}
		}
	}
	

	
}
@SuppressWarnings("serial")
public class Server extends JFrame{
	JLabel label;
	JButton generateReport;
	JPanel panel;
	JTextArea textArea;
	ServerSocket server = null;
	ServerDatabase database = new ServerDatabase();
	//Constructor for GUI
	Server(String name){ 

		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 	
		setSize(1000,500);
		
		createJPanel();
	} 
	//Constructinf the JPanel
	public void createJPanel(){
		
		panel = new JPanel();
	
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.white);
		getContentPane().add(panel);
		
		
		label = new JLabel("Server Communication:");
		textArea = new JTextArea();
		textArea.setSize(400, 400);
		generateReport = new JButton("Get Report");
		generateReport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand().equals("Get Report"))
						database.generateReport();
					JOptionPane.showMessageDialog(panel, "Report generated in report.txt");
			}
			
		});
		
		panel.add("North", label);
		panel.add("Center", textArea);
		panel.add("South", generateReport);
	}
	
	public void listenSocket() {
		try {
			server = new ServerSocket(4444);
		} catch (IOException e) {
			System.out.println("port 4444 Unabalible");
			System.err.println("port 4444 Unabalible");
			System.exit(-1);
		}
		while (true) {
			ClientWorker worker;
			try{
				worker = new ClientWorker(server.accept(), textArea, database);
				Thread t = new Thread(worker);
				t.start();
			} catch (IOException e) {
				System.out.println("Accept failed: 4444");
				System.exit(-1);
			}
		}
	}
	
	//Finalize objects at exit time
	protected void finalize(){
		try {
			server.close();
		} catch (IOException e) {
			System.out.println("Error:Could not close socket");
			System.exit(-1);
		}
	}
	public static void main(String[] args) {
		Server frame = new Server("Server");
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		frame.addWindowListener(l);
		
		frame.setVisible(true);
		frame.listenSocket();
	}
}