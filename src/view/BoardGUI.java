package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class BoardGUI extends JFrame {

	public JFrame frame;
	public JPanel gameView;
	public JTextField textFieldChat;
	public JTextField textFieldBarrier;
	public JButton onlineButton;
	public JButton upButton;
	public JButton downButton;
	public JButton leftButton;
	public JButton rightButton;
	public JButton setBarrierButton;
	public JTextArea chatTextArea;
	public JTextArea connectionTextArea;
	

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
		
		frame =  new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		gameView = new JPanel();
		gameView.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(gameView);
		gameView.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(5, 5, 171, 152);
		gameView.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(347, 5, 171, 152);
		gameView.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(15, 15, 700, 500);
		gameView.add(panel);
		
		textFieldChat = new JTextField();
		textFieldChat.setBounds(725, 580, 149, 20);
		gameView.add(textFieldChat);
		textFieldChat.setColumns(10);
		
		chatTextArea = new JTextArea();
		JScrollPane chatScrollPane = new JScrollPane(chatTextArea);
		chatScrollPane.setBounds(725, 367, 149, 202);
		gameView.add(chatScrollPane);
		
		
		onlineButton = new JButton("ONLINE");
		onlineButton.setBounds(725, 333, 149, 23);
		gameView.add(onlineButton);
		
		upButton = new JButton("UP");
		upButton.setBounds(515, 525, 90, 25);
		gameView.add(upButton);
		
		downButton = new JButton("DOWN");
		downButton.setBounds(515, 560, 90, 25);
		gameView.add(downButton);
		
		rightButton = new JButton("RIGHT");
		rightButton.setBounds(615, 560, 90, 25);
		gameView.add(rightButton);
		
		leftButton = new JButton("LEFT");
		leftButton.setBounds(415, 560, 90, 25);
		gameView.add(leftButton);
		
		textFieldBarrier = new JTextField();
		textFieldBarrier.setBounds(50, 560, 90, 25);
		gameView.add(textFieldBarrier);
		textFieldBarrier.setColumns(10);
		
		setBarrierButton = new JButton("SET");
		setBarrierButton.setBounds(50, 525, 90, 25);
		gameView.add(setBarrierButton);
	}
}
