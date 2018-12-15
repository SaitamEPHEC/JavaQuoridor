package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	
	public void initEvents() {
		onlineButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouse) {
				//TODO lien vers la methode qui permet de se connecter
			}
		});
		
		upButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouse) {
				//TODO lien vers la methode qui fait monter le pion
			}
		});
		
		downButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouse) {
				//TODO lien vers la methode qui fait descendre le pion
			}
		});
		
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouse) {
				//TODO lien vers la methode qui fait aller a gauche le pion
			}
		});
		
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouse) {
				//TODO lien vers la methode qui fait aller a droite le pion
			}
		});
		
		chatTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode()== KeyEvent.VK_ENTER) {
					chatTextPane.setEditable(true);
					String message = chatTextField.getText();
					//lien vers la methode pour se connecter .echoChat(message);
					chatTextField.setText("");
					chatTextPane.setEditable(false);
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public BoardGUI() {
		setTitle("Quoridor - Java Project");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 937, 760);
		gameView = new JPanel();
		gameView.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(gameView);
		gameView.setLayout(null);
		
		boardView = new JPanel();
		boardView.setBounds(10, 10, 700, 600);
		boardView.setBackground(Color.WHITE);
		gameView.add(boardView);
		boardView.setLayout(new GridLayout(17, 17));
		
		
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