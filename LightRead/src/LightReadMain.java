import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LightReadMain {
	
	// Save for custom file destination
	// String file_path;
	static int word_speed;
	
	public static void main(String args[]) throws FileNotFoundException, InterruptedException {
		
		LightReadMain overlord = new LightReadMain();
		Scanner decide_speed = new Scanner(System.in);
		System.out.println("How quickly would you like to read?\n{200,300,500,800}");
		overlord.set_speed(decide_speed.nextInt());
		
		File file = new File("C:\\Users\\Griff_Home_PC\\Desktop\\Java_Projects\\LightRead\\Sage.txt");
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
			Scanner each_word = new Scanner(scan.nextLine());
			while(each_word.hasNext()) {
				String word_on_screen = each_word.next();
				System.out.println(word_on_screen);
				Thread.sleep(overlord.get_speed(word_speed)); // Work to develop user-designated speed
			}
		}
	}
	
	public void set_speed(int word_speed) {
		this.word_speed = word_speed;
	}
	
	public int get_speed(int word_speed) {
		return this.word_speed;
	}
	
}
