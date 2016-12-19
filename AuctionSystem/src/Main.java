import java.io.IOException;


public class Main {
	
public static boolean debug=true;

	public static void main(String[] args){
		MainPanel LighWeight = new MainPanel("Lightweight Auction System");
		
		//Connect to server localhost
		try {
			Comms.connectToServer();
			Comms.establishConnection();
		} catch (IOException e) { 
			//TODO auto generated catch block
			e.printStackTrace();
		}
		LighWeight.init();
	}
	
	

}
