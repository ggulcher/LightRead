import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class LightReadMain {
	
	JFrame gui_frame;
	JPanel option_panel;
	JFileChooser reader_script;
	JLabel words_on_screen;
	
	static LightReadMain overlord = new LightReadMain();
	int word_speed = 265;
	String txt_path;
	
	public static void main(String args[]) throws FileNotFoundException, InterruptedException {
		
		//overlord.reader_logic();
		overlord.reader_gui();
		
	}
	
	public void reader_gui() {
		gui_frame = new JFrame();
		gui_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui_frame.setTitle("LightRead Speed Reader");
		gui_frame.setSize(800,500);
		gui_frame.setLocationRelativeTo(null);
		gui_frame.setLayout(new BorderLayout());
		
		reader_script = new JFileChooser();
		
		words_on_screen = new JLabel("Follow the buttons below.");
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		words_on_screen.setBorder(border);
		words_on_screen.setPreferredSize(new Dimension(400,200));
		Font font = new Font("Serif", Font.BOLD, 60);
		words_on_screen.setFont(font);
		words_on_screen.setHorizontalAlignment(JLabel.CENTER);
		words_on_screen.setVerticalAlignment(JLabel.CENTER);
		words_on_screen.setVisible(true);
		
		gui_frame.add(words_on_screen, BorderLayout.CENTER);
		
		option_panel = new JPanel();
		option_panel.setLayout(new GridLayout(1,3));
		
		gui_frame.add(option_panel,BorderLayout.SOUTH);
		
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
		
		JButton reader_execute = new JButton("Begin Reading");
		reader_execute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				try {
					each_word();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		option_panel.add(reader_execute);
		
		gui_frame.setVisible(true);;
	}
	
	private void open_file() throws Exception {

		int file_choice = reader_script.showOpenDialog(gui_frame);
		if(file_choice == JFileChooser.APPROVE_OPTION) {
			txt_path = reader_script.getSelectedFile().getPath();
		}
		
		Scanner scan = new Scanner(txt_path);
		
		
		// For debugging purposes
		System.out.println(txt_path);
	}
	
	private void set_wpm() {
		
		String[] wpm_speeds = {"200", "300", "500", "800"};
		JFrame frame = new JFrame("How fast would you like to read?");
		String selection = (String) JOptionPane.showInputDialog(frame,
				"Which speed would you like?",
				"WPM Selection",
				JOptionPane.QUESTION_MESSAGE,
				null,
				wpm_speeds,
				wpm_speeds[0]);
		if(selection.equals("200")) {
			word_speed = 265;
		} else if(selection.equals("300")) {
			word_speed = 160;
		} else if(selection.equals("500")) {
			word_speed = 75;
		} else if(selection.equals("800")) {
			word_speed = 28;
		}
		
		// For debugging purposes
		System.out.println(word_speed);
	}
	
	private void each_word() throws FileNotFoundException {
		List<String> words = new ArrayList<>();
		
		File file = new File(txt_path);
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
			Scanner each_word = new Scanner(scan.nextLine());
			while(each_word.hasNext()) {
				String next_word = each_word.next();
				words.add(next_word);
			}
		}
		
		ActionListener task_performer = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent ae) {
	        	words_on_screen.setText(words.get(0));
	        	words.remove(0);
	        }
	    };
	    Timer timer = new Timer(word_speed, task_performer);
	    timer.setRepeats(true);
	    timer.start();
	}
	
}