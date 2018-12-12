package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class BoardGUI extends JFrame {

	public JPanel gameView;
	public JPanel boardView;
	public JTextField chatTextField;
	public JScrollPane chatScrollPane;
	public JTextPane chatTextPane;
	public JButton onlineButton;
	public JButton upButton;
	public JButton downButton;
	public JButton leftButton;
	public JButton rightButton;
	public JTextField textFieldBarrier;
	public JButton setBarrierButton;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardGUI frame = new BoardGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 */
	public BoardGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 937, 760);
		gameView = new JPanel();
		gameView.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(gameView);
		gameView.setLayout(null);
		
		boardView = new JPanel();
		boardView.setBounds(10, 10, 700, 600);
		//boardView.setBackground(Color.WHITE);
		gameView.add(boardView);
		boardView.setLayout(new GridLayout(17, 17));
		
		
		for (int i = 1; i<=289; i+=1) {
			if ((i<=17)  || ((i>35)&&(i<=51)) || ((i>69)&&(i<=85)) || ((i>103)&&(i<=119)) || ((i>137)&&(i<=153))){
				if (i%2 == 0) {
					btnNewButton = new JButton("");
				} else {
					btnNewButton = new JButton("B");
				}
			} else {
				if (i%2==0) {
					btnNewButton = new JButton("B");
				} else {
					btnNewButton = new JButton("");
				}
			}
			
			btnNewButton.setPreferredSize(new Dimension(4, 4));
			boardView.add(btnNewButton);
		}
		
		chatTextField = new JTextField();
		chatTextField.setBounds(720, 690, 191, 20);
		gameView.add(chatTextField);
		chatTextField.setColumns(10);
		
		chatTextPane = new JTextPane();
		JScrollPane chatScrollPane = new JScrollPane(chatTextPane);
		chatScrollPane.setBounds(720, 279, 191, 400);
		gameView.add(chatScrollPane);
		chatTextPane.setEditable(false);
		
		onlineButton = new JButton("Online");
		onlineButton.setBounds(720, 245, 191, 23);
		gameView.add(onlineButton);
		
		upButton = new JButton("UP");
		upButton.setBounds(450, 620, 90, 25);
		gameView.add(upButton);
		
		downButton = new JButton("DOWN");
		downButton.setBounds(450, 655, 90, 25);
		gameView.add(downButton);
		
		rightButton = new JButton("RIGHT");
		rightButton.setBounds(550, 655, 90, 25);
		gameView.add(rightButton);
		
		leftButton = new JButton("LEFT");
		leftButton.setBounds(350, 655, 90, 25);
		gameView.add(leftButton);
		
		textFieldBarrier = new JTextField();
		textFieldBarrier.setBounds(50, 655, 90, 25);
		gameView.add(textFieldBarrier);
		textFieldBarrier.setColumns(10);
		
		setBarrierButton = new JButton("PLACE");
		setBarrierButton.setBounds(145, 655, 90, 25);
		gameView.add(setBarrierButton);
		
		
	}
}
