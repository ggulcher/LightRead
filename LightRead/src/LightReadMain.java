import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LightReadMain {
	
	String file_path;
	
	public static void main(String args[]) throws FileNotFoundException {
		
		File file = new File("C:\\Users\\Griff_Home_PC\\Desktop\\Java_Projects\\LightRead\\Sage.txt");
		Scanner scan = new Scanner(file);
		
		System.out.println(scan.nextLine());
	}
	
}
