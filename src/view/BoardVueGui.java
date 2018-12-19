package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

import controller.BoardController;
import model.Board;

public class BoardVueGui extends BoardVue implements ActionListener {
	private JFrame boardJFrame;
	private JTextField chatTextField = new JTextField();
	private JTextField barrierTextField = new JTextField();
	private JButton upButton = new JButton("UP");
	private JButton downButton = new JButton("DOWN");
	private JButton leftButton = new JButton("LEFT");
	private JButton rightButton = new JButton("RIGHT");
	private JButton setBarrierButton = new JButton("PLACE");
	private JButton onlineButton = new JButton("Online");
	private JTextPane chatTextPane = new JTextPane();
	private JScrollPane chatScrollPane = new JScrollPane(chatTextPane);
	private JPanel jPanel2;
	
    public BoardVueGui(Board model, BoardController controller) {
		super(model, controller);
		boardJFrame = new JFrame("Quoridor - Java Project");
    	boardJFrame.setResizable(false);
    	boardJFrame.setVisible(true);
        initComponents();
        initEvents();
	}
    
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
        boardJFrame.setContentPane(jPanel2);
        boardJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardJFrame.pack();
    }
    
    //On initialise les listeners
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
    				//String message = chatTextField.getText();
    				//lien vers la methode pour se connecter .echoChat(message);
    				chatTextField.setText("");
    				chatTextPane.setEditable(false);
    			}
    		}
    	});
    	
    	barrierTextField.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent key) {
    			if (key.getKeyCode() == KeyEvent.VK_ENTER) {
    				//lien vers la methode pour placer une barriere
    				barrierTextField.setText("");
    			}
    		}
    	});
    }
        
    public class Panel2 extends JPanel {

		private static final long serialVersionUID = 1L;
		
		Panel2() {
            // mettre une taille pour le panel
            setPreferredSize(new Dimension(950,850));
        }


		/**
		 * Met a jour le board pour la GUI. Appele apres dans la methode update via repaint().
		 */
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
            }
            
            int pixelY = 100;
            int pixelX = 100;
            for(int i = 0; i < model.getLength(); i++){
            //for(int i = 100; i <= 675; i+=65){
                for(int j = 0; j < model.getLineLength(); j++){
                	if(i%2 == 0)  {
    					//Emplacement Pion
    					if(j%2==0) {
    						g.setColor(Color.WHITE);
    	                    g.fillRect(pixelX, pixelY, 55, 55);
    						if(model.getBoard()[i][j].equals("P1")) {
    							g.setColor(Color.BLUE);
    							g.fillRoundRect(pixelX,pixelY,55,55,55,55);
    						}
    						if(model.getBoard()[i][j].equals("P2")) {
    							g.setColor(Color.RED);
    							g.fillRoundRect(pixelX,pixelY,55,55,55,55);
    						}
    						pixelX += 55;
    					}
    					//Emplacement Barriere Verticale
    					else {
    						if(model.getBoard()[i][j].equals(" | ")) {
    							g.setColor(Color.BLACK);
    							g.fillRect(pixelX, pixelY, 10, 55);
    						}
    						pixelX += 10;
    					}
    					
    				}
    				else {
    					//Emplacement Barriere Horizontale
    					if(j%2==0) {
    						if(model.getBoard()[i][j].equals("――")) {
    							g.setColor(Color.BLACK);
    							g.fillRect(pixelX, pixelY, 55, 10);
    						}
    						pixelX += 55;
    					}
    					//Emplacement ni Barriere ni Pion
    					else {
    						pixelX += 10;
    					}
    				}
                }
                pixelX = 100;
                if(i%2==0) {
                	pixelY += 55;
                }
                else {
                	pixelY += 10;
                }
            }
        }
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void affiche(String string) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(Observable o, Object arg) {
		Panel2 drawPanel = new Panel2();
		drawPanel.repaint();
	}
}