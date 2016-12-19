import java.util.ArrayList;


public class Client {
	
public static String userID=""; //store user id for identifications
public static boolean isLoggedIn=false;


//Setter and getter methods
public static void setUserID(String userId){
	Client.userID=userId;
}

public static String getUserID(){
	return Client.userID;
}


}