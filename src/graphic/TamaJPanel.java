package graphic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jeu.TamaGame;
import tamagoshis.Tamagoshi;

public class TamaJPanel extends JPanel{
	private JButton btnJouer;
	private JButton btnManger;
	private Tamagoshi tama;
	private JLabel lblImg;
	private ImageIcon imgTamagoshi = new ImageIcon(getClass().getResource("../images/tamagoshi.png"));
	private ImageIcon imgGrosMangeur = new ImageIcon(getClass().getResource("../images/grosmangeur.png"));
	private ImageIcon imgGrosJoueur = new ImageIcon(getClass().getResource("../images/grosjoueur.png"));


	
	/**
	 * Constructeur de la classe. Créer un nouveau Panel en fonction du tamagoshi placé en paramètre.
	 */
	public TamaJPanel(Tamagoshi tam){
		this.tama = tam;
		this.initPanel();
		this.initEcouteurs();
	}
	
	/**
	 * Méthode qui initialise un Panel.
	 */
	private void initPanel(){
		this.setLayout(new BorderLayout());
		
		//On affiche une image différente selon les tamagoshis
		switch (this.tama.getClass().getSimpleName()) {
		case "Tamagoshi":
			this.lblImg = new JLabel(this.imgTamagoshi);
			this.add(this.lblImg, BorderLayout.CENTER);
			break;
			
		case "GrosMangeur":
			this.lblImg = new JLabel(this.imgGrosMangeur);
			this.add(this.lblImg, BorderLayout.CENTER);
			break;
			
		case "GrosJoueur":
			this.lblImg = new JLabel(this.imgGrosJoueur);
			this.add(this.lblImg, BorderLayout.CENTER);
			break;

		default:
			break;
		}
		
		//On créer les boutons et les ajoute au sud du panel
		btnJouer = new JButton("Jouer");
		btnManger = new JButton("Manger");
		
		JPanel panelBoutons = new JPanel();
		panelBoutons.add(btnJouer);
		panelBoutons.add(btnManger);

		this.add(panelBoutons, BorderLayout.SOUTH);
	}
	
	
	
	/**
	 * Méthode qui initialise l'ensemble des écouteurs sur les boutons.
	 */
	private void initEcouteurs(){
		
		//Bouton manger
		btnManger.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TamaJPanel.this.tama.mange();
				if(tourTermine()){//Si le tour est terminé (que l'utilisateur a cliqué sur 2 boutons)
					TamaGame.getJeu().play();//On relance un tour
				}
			}
		});
		
		//Bouton jouer
		btnJouer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TamaJPanel.this.tama.joue();
				if(tourTermine()){
					TamaGame.getJeu().play();
				}
			}
		});
	}
	
	
	/**
	 * Méthode qui grise ou dégrise les boutosn manger, jouer ou les deux.
	 * @param i, 1 le bouton "Manger", 2 le bouton "Jouer" et 3 les deux.
	 * @param b, un booléen pour mettre le bouton gris ou non.
	 */
	public void setBtn(int i, boolean b){
		switch (i) {
		case 1://Le bouton manger
			this.btnManger.setEnabled(b);
			break;

		case 2://le bouton jouer
			this.btnJouer.setEnabled(b);
			break;
			
		case 3://les deux boutons
			this.btnManger.setEnabled(b);
			this.btnJouer.setEnabled(b);
			break;
			
		default:
			break;
		}
	}
	
	
	/**
	 * Méthode qui permet de changer l'image du Tamagoshi lorsqu'il meurt.
	 * @param i, un entier (1 pour le tamagoshi, 2 pour le gros mangeur, et 3 pour le grosJoueur).
	 */
	public void setImg(int i){
		switch (i) {
		case 1:
			ImageIcon imgTamagoshiMort = new ImageIcon(getClass().getResource("../images/tamagoshiMort.png"));
			this.lblImg.setIcon(imgTamagoshiMort);
			break;
		case 2:
			ImageIcon imgGrosMangeurMort = new ImageIcon(getClass().getResource("../images/grosmangeurMort.png"));
			this.lblImg.setIcon(imgGrosMangeurMort);
			break;
		case 3:
			ImageIcon imgGrosJoueurMort = new ImageIcon(getClass().getResource("../images/grosjoueurMort.png"));
			this.lblImg.setIcon(imgGrosJoueurMort);
			break;

		default:
			break;
		}
	}
	
	
	/**
	 * Méthode qui retourne un booléen pour savoir si l'utilisateur a nourri et fait jouer un tamagoshi.
	 * @return, un booléen : true si le tour est terminé, false si non.
	 */
	public boolean tourTermine(){
		return !this.btnJouer.isEnabled() && !this.btnManger.isEnabled();
	}
}
