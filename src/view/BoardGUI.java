package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

class BoardGUI extends JFrame {
    
    public BoardGUI() {
    	setTitle("Quoridor - Java Project");
    	setResizable(false);
        initComponents();
    }

    JButton onlineButton = new JButton("Online");
    JTextField chatTextField = new JTextField();
    JTextPane chatTextPane = new JTextPane();
    JScrollPane chatScrollPane = new JScrollPane(chatTextPane);
    JButton upButton = new JButton("UP");
    JButton downButton = new JButton("DOWN");
    JButton leftButton = new JButton("LEFT");
    JButton rightButton = new JButton("RIGHT");
    JTextField barrierTextField = new JTextField();
    JButton setBarrierButton = new JButton("PLACE");
    
    private void initComponents() {
        
        jPanel2 = new Panel2();
        
        
        jPanel2.setBackground(new Color(77, 0, 0));
        jPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jPanel2.setLayout(null);
        
        jPanel2.add(onlineButton);
        onlineButton.setBounds(720, 295, 191, 23);
        
        jPanel2.add(chatTextField);
        chatTextField.setBounds(720, 740, 191, 20);
        chatTextField.setColumns(10);
        
		jPanel2.add(chatScrollPane);
		chatScrollPane.setBounds(720, 329, 191, 400);
		chatTextPane.setEditable(false);
		
		jPanel2.add(upButton);
		upButton.setBounds(450, 700, 90, 25);
		
		jPanel2.add(downButton);
		downButton.setBounds(450, 735, 90, 25);
		
		jPanel2.add(leftButton);
		leftButton.setBounds(350, 735, 90, 25);
		
		jPanel2.add(rightButton);
		rightButton.setBounds(550, 735, 90, 25);
		
		jPanel2.add(barrierTextField);
		barrierTextField.setBounds(50, 735, 90, 25);
		barrierTextField.setColumns(10);
		
		jPanel2.add(setBarrierButton);
		setBarrierButton.setBounds(145, 735, 90, 25);
        
        // ajouter les composants au frame
        this.setContentPane(jPanel2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
    

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoardGUI().setVisible(true);
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

    // declaration de variable
    private JPanel jPanel2;
        
    class Panel2 extends JPanel {

        Panel2() {
            // mettre une taille pour le panel
            setPreferredSize(new Dimension(950,850));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(100, 100, 575, 575);
            
            g.drawString("I", 80, 127);
            g.drawString("H", 80, 192);
            g.drawString("G", 80, 257);
            g.drawString("F", 80, 322);
            g.drawString("E", 80, 387);
            g.drawString("D", 80, 452);
            g.drawString("C", 80, 517);
            g.drawString("B", 80, 582);
            g.drawString("A", 80, 647);
            
            
            int k = 1;
            
            for(int i = 100; i <= 675; i+=65){
            	g.drawString(String.valueOf(k), (i + 27), 85);
            	k++;
                for(int j = 100; j <= 675; j+=65){
                	g.setColor(Color.WHITE);
                    g.fillRect(i, j, 55, 55);
                }
            }
        }
    }
}