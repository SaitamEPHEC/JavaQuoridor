package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JButton newGameButton = new JButton("New Game");
	private JTextPane chatTextPane = new JTextPane();
	private JScrollPane chatScrollPane = new JScrollPane(chatTextPane);
	private JTextPane displayInfoP1 = new JTextPane();
	private JTextPane displayInfoP2 = new JTextPane();
	private JPanel jPanel2;
	
    public BoardVueGui(Board model, BoardController controller) {
		super(model, controller);
		boardJFrame = new JFrame("Quoridor - Java Project");
    	boardJFrame.setResizable(false);
    	boardJFrame.setVisible(true);
        initComponents();
        String optionPane1 = JOptionPane.showInputDialog(null, "Quel est le pseudo du joueur 1 ?", null);
        while(optionPane1 == null) {
        	optionPane1 = JOptionPane.showInputDialog(null, "Quel est le pseudo du joueur 1 ?", null);
        }
        String optionPane2 = JOptionPane.showInputDialog(null, "Quel est le pseudo du joueur 2 ?", null);
        while(optionPane2 == null) {
        	optionPane2 = JOptionPane.showInputDialog(null, "Quel est le pseudo du joueur 2 ?", null);
        }
        model.setPlayerNicknames(optionPane1,optionPane2);
	}
    
    private void initComponents() {
        
        jPanel2 = new Panel2();
        
        
        jPanel2.setBackground(new Color(77, 0, 0));
        jPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jPanel2.setLayout(null);
        
        jPanel2.add(onlineButton);
        onlineButton.setBounds(720, 295, 191, 23);
        
        jPanel2.add(newGameButton);
        newGameButton.setBounds(720, 262, 191, 23);
        
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
		
		jPanel2.add(displayInfoP1);
		displayInfoP1.setBounds(720, 100, 191 ,23);
		displayInfoP1.setEditable(false);
		
		jPanel2.add(displayInfoP2);
		displayInfoP2.setBounds(720, 128, 191, 23);
		displayInfoP2.setEditable(false);
		
        
        // ajouter les composants au frame
        boardJFrame.setContentPane(jPanel2);
        boardJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardJFrame.pack();
        
        //Definition des actions sur les elements de la GUI
        upButton.addActionListener(this);
        downButton.addActionListener(this);
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        //chatTextField.addActionListener(this);
        onlineButton.addActionListener(this);
        //barrierTextField.addActionListener(this);
        setBarrierButton.addActionListener(this);
        
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
            
            g.setColor(Color.BLUE);
            g.fillRoundRect(700, 104, 15, 15, 15, 15);
            
            g.setColor(Color.RED);
            g.fillRoundRect(700, 130, 15, 15, 15, 15);
            
            g.setColor(Color.WHITE);
            
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
	public void actionPerformed(ActionEvent e) {
		if(!endOfGame) {
			Object source = e.getSource();
			if (source == upButton) {
				controller.moveUpAffichage();
			}
			if (source == downButton) {
				controller.moveDownAffichage();
			}
			if (source == leftButton) {
				controller.moveLeftAffichage();
			}
			if (source == rightButton) {
				controller.moveRightAffichage();
			}
			if (source == setBarrierButton) {
				char[] inputs = toCharArray(barrierTextField.getText());
				controller.putBarrier(inputs);
				barrierTextField.setText("");
			}
			if (source == newGameButton) {
				//TODO methode pour nouvelle partie
			}
		}
	}


	@Override
	public void affiche(String string) {
		chatTextPane.setEditable(true);
		chatTextPane.setText(string);
		chatTextPane.setEditable(false);
		
	}


	@Override
	public void update(Observable o, Object arg) {
		displayInfoP1.setEditable(true);
		displayInfoP1.setText((model.getPlayer1Nickname())+", barrieres restantes : " + model.getPlayer1BarrierLeft());
		displayInfoP1.setEditable(false);
		
		displayInfoP2.setEditable(false);
		displayInfoP2.setText(model.getPlayer2Nickname()+", barrieres restantes : " + model.getPlayer2BarrierLeft());
		displayInfoP2.setEditable(false);
		boardJFrame.repaint();
		
	}
	
	/**
	 * 
	 * @param textField la zone texte correspondant aux coordonnees d'une barriere que l'utilisateur veut placer
	 * @return un tableau caracteres (normalement 4) correspondant aux coordonnees d'une barriere entre par l'utilisateur 
	 */
	public char[] toCharArray(String textField) {
		char[] coordonnees = new char[4];
		int charCounter = 0;
		for(int i=0; i<textField.length();i++) {
			if(textField.charAt(i) != ' ') {
				coordonnees[charCounter] = Character.toUpperCase(textField.charAt(i));
				charCounter ++;
			}
		}
		
		return coordonnees;
	}
}