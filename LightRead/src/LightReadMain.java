import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class LightReadMain {
	
	JFrame gui_frame;
	JPanel option_panel;
	JFileChooser reader_script;
	JTextPane words_on_screen;
	
	static LightReadMain overlord = new LightReadMain();
	static Map<Integer, Integer> speed_map = new HashMap<>() {
		{
			put(200, 265);
			put(300, 165);
			put(500, 75);
			put(800, 28);
		}
	};
	static int word_speed;
	static int white = 1;
	static String txt_path;
	
	public static void main(String args[]) throws FileNotFoundException, InterruptedException {
		
		overlord.reader_logic();
		//overlord.reader_gui();
		
	}
	
	public void reader_logic() throws FileNotFoundException, InterruptedException {
		
		Scanner decide_speed = new Scanner(System.in);
		System.out.println("How quickly would you like to read?\n{200,300,500,800}");
		int wanted_speed = decide_speed.nextInt();
		
		if(speed_map.containsKey(wanted_speed)) {
			word_speed = speed_map.get(wanted_speed);
		}
		
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
	
	// CONSTRUCTION ZONE: GUI below in development. Not implemented.
	public void reader_gui() {
		gui_frame = new JFrame();
		gui_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui_frame.setTitle("LightRead Speed Reader");
		gui_frame.setSize(800,500);
		gui_frame.setLocationRelativeTo(null);
		gui_frame.setLayout(new BorderLayout());
		
		reader_script = new JFileChooser();
		
		words_on_screen = new JTextPane();
		words_on_screen.setEditable(false);
		Font font = new Font("Serif", Font.BOLD, 30);
		words_on_screen.setFont(font);
		StyledDocument doc = words_on_screen.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center,  StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		words_on_screen.setText("Welcome to LightRead.\nUpload your text and choose your speed.");
		words_on_screen.setVisible(true);
		gui_frame.add(words_on_screen, BorderLayout.CENTER);
		
		option_panel = new JPanel();
		option_panel.setLayout(new GridLayout(1,3));
		gui_frame.add(option_panel,BorderLayout.NORTH);
		
		JButton file_select = new JButton("Choose File");
		file_select.setActionCommand("Open File");
		file_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				try {
					open_file();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		option_panel.add(file_select);
		
		JButton wpm_select = new JButton("Choose WPM");
		wpm_select.setActionCommand("Apply WPM");
		wpm_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				try {
					set_wpm();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		option_panel.add(wpm_select);
		
		JButton color_select = new JButton("Invert Light/Dark Mode");
		file_select.setActionCommand("Apply New Mode");
		file_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				try {
					if(white == 1) {
						words_on_screen.setBackground(Color.black);
						words_on_screen.setForeground(Color.white);
						white = 0;
					} else {
						words_on_screen.setBackground(Color.white);
						words_on_screen.setForeground(Color.black);
						white = 1;
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		option_panel.add(color_select);
		
		gui_frame.setVisible(true);;
	}
	
	private void open_file() {
		// TODO Auto-generated method stub
		int file_choice = reader_script.showOpenDialog(gui_frame);
		if(file_choice == JFileChooser.APPROVE_OPTION) {
			txt_path = reader_script.getSelectedFile().getPath();
			
			//Define each_word()
			//(new Thread(new each_word())).start();
		}
	}
	
	protected void set_wpm() {
		// TODO Auto-generated method stub
		
	}
	
	//CONSTRUCTION ZONE END
	
	public void set_speed(int word_speed) {
		this.word_speed = word_speed;
	}
	
	public int get_speed(int word_speed) {
		return this.word_speed;
	}
	
}