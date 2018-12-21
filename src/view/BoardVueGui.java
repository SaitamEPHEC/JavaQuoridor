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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

import controller.BoardController;
import model.Barrier;
import model.Board;

public class BoardVueGui extends BoardVue implements ActionListener {
	
	//on cree toutes nos variables d'instances 
	private JFrame boardJFrame;
	private JFrame rulesJFrame;
	private JTextField chatTextField = new JTextField();
	private JTextField barrierTextField = new JTextField();
	private JButton upButton = new JButton("HAUT");
	private JButton downButton = new JButton("BAS");
	private JButton leftButton = new JButton("GAUCHE");
	private JButton rightButton = new JButton("DROITE");
	private JButton setBarrierButton = new JButton("POSER");
	private JButton onlineButton = new JButton("EN LIGNE");
	private JButton newGameButton = new JButton("NOUVELLE PARTIE");
	private JButton rulesButton = new JButton("RÈGLES");
	private JButton rewindButton = new JButton("ANNULER COUP");
	private JTextPane chatTextPane = new JTextPane();
	private JScrollPane chatScrollPane = new JScrollPane(chatTextPane);
	private JTextPane displayInfoTurn = new JTextPane();
	private JTextPane displayInfoP2 = new JTextPane();
	private JTextPane displayInfoP1 = new JTextPane();
	private JTextPane displayInfoBarrier = new JTextPane();
	private JPanel jPanel2;
	
	
	/**
	 * Methode qui cree le JFrame de la GUI
	 * @param model
	 * @param controller
	 */
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
    	
        /*
         * on cree un jpanel qui contiendra tous nos composants, car le jframe ne permet pas 
         * l'utilisation de la methode Graphics
         */
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
		upButton.setBounds(480, 732, 90, 25);
		
		jPanel2.add(downButton);
		downButton.setBounds(480, 767, 90, 25);
		
		jPanel2.add(leftButton);
		leftButton.setBounds(380, 767, 90, 25);
		
		jPanel2.add(rightButton);
		rightButton.setBounds(580, 767, 90, 25);
		
		jPanel2.add(setBarrierButton);
		setBarrierButton.setBounds(177, 720, 90, 25);
		
		jPanel2.add(rulesButton);
		rulesButton.setBounds(770, 770, 90, 25);
		
		jPanel2.add(barrierTextField);
		barrierTextField.setBounds(82, 720, 90, 25);
		barrierTextField.setColumns(10);
		
		jPanel2.add(displayInfoP1);
		displayInfoP1.setBounds(720, 100, 191 ,23);
		displayInfoP1.setEditable(false);
		
		jPanel2.add(displayInfoP2);
		displayInfoP2.setBounds(720, 128, 191, 23);
		displayInfoP2.setEditable(false);
		
		jPanel2.add(displayInfoTurn);
		displayInfoTurn.setBounds(720, 184, 191 ,23);
		displayInfoTurn.setEditable(false);
		
		jPanel2.add(displayInfoBarrier);
		displayInfoBarrier.setBounds(10,755,330,85);
		displayInfoBarrier.setText("Si vous placez une barrière horizontale, elle sera placée au-dessus des coordonnées indiquées.\nSi vous"
				+ " placez une barrière verticale, elle sera placée à droite des coordonnées indiquées.\n"
				+ "Exemple : \"g8g9\" (horizontale) ou \"b3c3\" (verticale)\n");
		displayInfoBarrier.setEditable(false);
		
		jPanel2.add(rewindButton);
		rewindButton.setBounds(720, 217, 191, 23);
		
        
        // on ajoute les composants au frame
        boardJFrame.setContentPane(jPanel2);
        boardJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardJFrame.pack();
        
        //Definition des actions sur les elements de la GUI
        upButton.addActionListener(this);
        downButton.addActionListener(this);
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        onlineButton.addActionListener(this);
        setBarrierButton.addActionListener(this);
        newGameButton.addActionListener(this);
        rulesButton.addActionListener(this);
        rewindButton.addActionListener(this);
        
    }
    
    
    
        
    public class Panel2 extends JPanel {

		private static final long serialVersionUID = 1L;
		
		Panel2() {
            // mettre une taille pour le panel
            setPreferredSize(new Dimension(950,850));
        }


		
        @Override
        /**
         * Met a jour le board pour la GUI. Appele apres dans la methode update via repaint().
         * @param g un element Graphics
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(100, 100, 575, 575);
            
            g.setColor(model.getPlayer1().getPawnColor());
            g.fillRoundRect(700, 104, 15, 15, 15, 15);
            
            g.setColor(model.getPlayer2().getPawnColor());
            g.fillRoundRect(700, 132, 15, 15, 15, 15);
            
            g.setColor(model.getTurn().getPawnColor());
            g.fillRoundRect(700, 188, 15, 15, 15, 15);
            
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
            
            
            // on dessine notre plateau de jeu
            
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
    						if(model.isBarrierOnBoard(new Barrier(i+1,j,i-1,j))
    						|| model.isBarrierOnBoard(new Barrier(i,j-1,i,j+1))){
    							g.setColor(Color.BLACK);
    							g.fillRect(pixelX, pixelY, 10, 10);
    						}
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
	/**
	 * permet de set up nos action listeners
	 * @param e un actionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(!endOfGame) {
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
			if (source == rewindButton) {
				model.rewind();
			}
			
		}
		if (source == newGameButton) {
			model.resetBoard();
			chatTextPane.setText("");
		}
		if (source == rulesButton) {
			rulesJFrame = new JFrame("Règles de Quoridor");
			rulesJFrame.setResizable(true);
			JOptionPane.showMessageDialog(rulesJFrame, rulesString(),"Règles de Quoridor",JOptionPane.INFORMATION_MESSAGE);
			
		}
	}


	
	
	@Override
	/**
	 * Permet d'afficher un string dans le chat
	 * @param string un string a faire passer
	 */
	public void affiche(String string) {
		chatTextPane.setText(string);
	}


	
	
	@Override
	/**
	 * Permet de mettre a jour la GUI
	 * @param o un observable
	 * @param arg un object
	 */
	public void update(Observable o, Object arg) {
		displayInfoP1.setText(model.getPlayer1Nickname()+", barrières restantes : " + model.getPlayer1BarrierLeft());
		
		displayInfoP2.setText(model.getPlayer2Nickname()+", barrières restantes : " + model.getPlayer2BarrierLeft());
		
		displayInfoTurn.setText("Tour de : " + model.getTurn().getNickname());
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
	
	/**
	 * Renvoie les regles du jeu sous forme de String
	 * @return String representant les regles du jeu
	 */
	public String rulesString() {
		return "Chaque joueur possède un pion et 10 barrières. À tour de rôles, chaque joueur choisit de "
				+ "déplacer son pion ou de déposer une de ses barrières. Lorsqu'il n'y a plus de barrières, un joueur est obligé de déplacer son pion.\n\n"
				+ "Déplacements des pions :\nLes pions se déplacent d'une case, horizontalement ou verticalement, "
				+ "en avant ou en arrière ; les barrières doivent être contournées.\n\n"
				+ "Pose des barrières :\nUne barrière doit être posée exactement entre 2 blocs de 2 cases. La pose des barrières a pour "
				+ "but de se créer son propre chemin ou de ralentir l'adversaire, mais il est interdit de lui fermer totalement l'accès "
				+ "a sa ligne de but : il faut toujours lui laisser une solution.\n\n"
				+ "Face à face :\nQuand les 2 pions se retrouvent en vis-à-vis sur 2 cases voisines non séparées par une barrière, le joueur dont "
				+ "c'est le tour peut sauter son adversaire et se placer derrière lui. Si une barrière est située derrière le pion sauté, le joueur "
				+ "peut choisir de bifurquer à droite ou à gauche du pion sauté.\n\n"
				+ "Fin de la partie :\nLe premier joueur qui atteint une des 9 cases de la ligne opposée à sa ligne de départ gagne la partie.\n";
	}
}