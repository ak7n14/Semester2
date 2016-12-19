import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomIntegers {
	public static void main(String[] args) {
		
			// Produces file of size: 10.0 kB (10,001 bytes)
			// Used to perform 8-bit input output
	    	FileOutputStream binaryStreamOut;
			try {
				binaryStreamOut = new FileOutputStream("binaryStreamOutput.txt");
				for(int i = 0; i <= 10000; i++)
					binaryStreamOut.write(randInt(0, 100000));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Produces file of size: 24.6 kB (24,573 bytes)
			// Used to perform 16-bit unicode input output hence file is twice the size
			FileWriter characterStreamOut;
			try {
				characterStreamOut = new FileWriter("characterStreamOutput.txt");
				for(int i = 0; i <= 10000; i++)
					characterStreamOut.write(randInt(0, 100000));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}    
	
	public static int randInt(int min, int max) {
		Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
}

