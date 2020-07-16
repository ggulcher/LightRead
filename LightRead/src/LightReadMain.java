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
			Scanner each_word = new Scanner(scan.nextLine());
			while(each_word.hasNext()) {
				String word_on_screen = each_word.next();
				System.out.println(word_on_screen);
				Thread.sleep(500);
			}
//			System.out.println(scan.nextLine());
//			Thread.sleep(500);
		}
	}
	
}
