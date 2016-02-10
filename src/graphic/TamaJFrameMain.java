package graphic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import jeu.TamaGame;

public class TamaJFrameMain extends JFrame{
	
	private JMenuItem jmiJouer, jmiQuitter, jmiAide, jmiVersion;
	
	public TamaJFrameMain(){
		this.initElements();
		this.initEcouteurs();
		this.initFrame();
	}
	
	
	/**
	 * Méthode qui permet d'initialiser la fenetre.
	 */
	private void initFrame(){
		this.setTitle("Tamagoshi - Le jeu");
		this.setSize(400, 350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	
	/**
	 * Méthode qui initialise les éléments de toute la fenetre.
	 */
	private void initElements(){
		JMenuBar jmb = new JMenuBar();
		JMenu jmFichier = new JMenu("Fichier");
		JMenu jmAide = new JMenu("Aide");
		this.jmiJouer = new JMenuItem("Jouer");
		this.jmiQuitter = new JMenuItem("Quitter");
		this.jmiAide = new JMenuItem("Aide");
		this.jmiVersion = new JMenuItem("Version");
		
		jmFichier.add(jmiJouer);
		jmFichier.add(jmiQuitter);
		jmAide.add(jmiAide);
		jmAide.add(jmiVersion);
		
		jmb.add(jmFichier);
		jmb.add(jmAide);
		
		this.setJMenuBar(jmb);
		
		this.getContentPane().add(new JLabel(new ImageIcon(getClass().getResource("/images/logo.jpg"))), BorderLayout.CENTER);
	}
	
	
	/**
	 * Méthode qui initialise les écouteurs (de la barre des menus).
	 */
	private void initEcouteurs(){
		
		//btnJouer de la barre des menus
		this.jmiJouer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TamaGame tg = new TamaGame();
				tg.jouer();
			}
		});
		
		//Btn aide de la barre des menus
		this.jmiVersion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Version 1.0.2", "Aide", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//btn quitter de la barre des menus
		this.jmiQuitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	
	/**
	 * Méthode main, qui lance une fenetre et permet à l'utilisateur de jouer.
	 * @param args.
	 */
	public static void main(String args[]){
		new TamaJFrameMain();
	}

}
