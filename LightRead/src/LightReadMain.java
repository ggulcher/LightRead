import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LightReadMain {
	
	// Save for custom file destination
	// String file_path;
	
	public static void main(String args[]) throws FileNotFoundException, InterruptedException {
		
		File file = new File("C:\\Users\\Griff_Home_PC\\Desktop\\Java_Projects\\LightRead\\Sage.txt");
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
			System.out.println(scan.nextLine());
			Thread.sleep(500);
		}
	}
	
}
