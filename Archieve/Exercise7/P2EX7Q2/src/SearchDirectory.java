import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SearchDirectory {
	public static void main(String[] args) {
		String dirName = args[0];
		String fileName = args[1];
		
		File thisDir = new File( dirName );
		if(thisDir.isDirectory()) {
			
			// Initialise FileOutputStream and PrintStream
			FileOutputStream characterStreamOut;
			PrintStream ps;
			try {
				// The path of the file storing the java file names is: [absolute location of dirName]/fileName
				characterStreamOut = new FileOutputStream(String.format("%s/%s", thisDir.getAbsoluteFile(), fileName), true);
				ps = new PrintStream(characterStreamOut);
				
				// Filters files so that only the desired files are stored in an array of files
				File[] files = thisDir.listFiles(new FileFilter() {
					public boolean accept(File file) {
						if(!file.isDirectory())
							if(file.getPath().contains(".java"))
								return true;
						return false;
					}
				});	
				for(File file: files)
					ps.println(file.getName());	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.printf("%s is not a directory!");
			System.err.println("Arguments should be: SearchDirectory <dirName> <fileName>");
		}
	}
}
