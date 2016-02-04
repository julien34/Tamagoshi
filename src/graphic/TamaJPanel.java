package graphic;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	private JLabel lblBulle = new JLabel("Jouons ensemble !");
	
	private HashMap<Integer, String> hmJouer = new HashMap<Integer, String>(20);
	private HashMap<Integer, String> hmManger = new HashMap<Integer, String>(20);
	private AudioClip clip = null;


	
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
		
		//On affiche la bulle au dessus des tamagoshis
		ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("../images/bulle.png")).getImage().getScaledInstance(400, 150, Image.SCALE_DEFAULT));
		this.lblBulle.setIcon(icon);
		
		//On affiche le texte dans la bulle
		this.lblBulle.setHorizontalTextPosition(SwingConstants.CENTER);
		this.lblBulle.setHorizontalAlignment(JLabel.CENTER);
		this.add(this.lblBulle, BorderLayout.NORTH);
		
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
		
		this.setSongs();//On initialise les sons qui seront lancés
	}
	
	
	
	/**
	 * Méthode qui initialise l'ensemble des écouteurs sur les boutons.
	 */
	private void initEcouteurs(){
		
		//Bouton manger
		btnManger.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TamaJPanel.this.tama.mange();//fais manger le tamagoshi
				TamaJPanel.this.jouerSons(1);//lance un son aléatoire de manger

				if(tourTermine()){//Si le tour est terminé (que l'utilisateur a cliqué sur 2 boutons)
					TamaGame.getJeu().play();//On relance un tour
					TamaJPanel.this.lblBulle.setText(TamaJPanel.this.tama.getEtatDeForme());
				}
			}
		});
		
		//Bouton jouer
		btnJouer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TamaJPanel.this.tama.joue();//fais jouer un tamagoshi
				TamaJPanel.this.jouerSons(2);//lance un son aléatoire de jouer
				
				if(tourTermine()){
					TamaGame.getJeu().play();
					TamaJPanel.this.lblBulle.setText(TamaJPanel.this.tama.getEtatDeForme());
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
	
	
	/**
	 * Méthode qui ajoute à la HashMap les liens vers les sons du jeu.
	 */
	private void setSongs(){
		
		//Sons pour jouer
		this.hmJouer.put(0,"../sounds/play/yallah.wav");
		this.hmJouer.put(1,"../sounds/play/merci-la-gueuse.wav");
		this.hmJouer.put(2,"../sounds/play/feel-like-this.wav");
		this.hmJouer.put(3,"../sounds/play/vous-ne-vous-reposer-jamais-vous.wav");
		this.hmJouer.put(4,"../sounds/play/tarzan.wav");
		
		//Sons pour manger
		this.hmManger.put(0,"../sounds/eat/ou-sont-les-poulardes.wav");
		this.hmManger.put(1,"../sounds/eat/cest-tres-bon.wav");
		this.hmManger.put(2,"../sounds/eat/cest-tres-fin.wav");
		this.hmManger.put(3,"../sounds/eat/cette-vinasse.wav");
		this.hmManger.put(4,"../sounds/eat/obelix-tombe-dedans.wav");
		this.hmManger.put(5,"../sounds/eat/on-va-se-goinfrer.wav");
		this.hmManger.put(6,"../sounds/eat/ou-sont-les-poulardes.wav");
	}
	
	
	/**
	 * Méthode qui joue un son aléatoirement dans le style 'manger' ou 'jouer'.
	 * @param n, un entier représentant 'jouer' ou 'manger'.
	 */
	private void jouerSons(int n){
		
		Random rnd = new Random();//On créer un random
		int index = 0; //On initialise l'index à 0
		
		switch (n) {
		
		//Pour le cas ou le tamagoshi joue
		case 1:
			index = rnd.nextInt(this.hmManger.size());//On génère un nombre aléatoire pour prendre un son au hasard dans la hashmap
			this.clip = Applet.newAudioClip(getClass().getResource(this.hmManger.get(index)));
			break;
		
		//Pour le cas ou le tamagoshi mange
		case 2:
			index = rnd.nextInt(this.hmJouer.size());//On génère un nombre aléatoire pour prendre un son au hasard dans la hashmap
			this.clip = Applet.newAudioClip(getClass().getResource(this.hmJouer.get(index)));
			break;
			
		default:
			break;
		}
		
		clip.play();//On lance l'audio
	}
	
	
	/**
	 * Setter du JLabel de l'état (dans l'image de la bulle). Affiche l'état de forme du tamagoshi.
	 */
	public void afficherEtatsDansBulles(){
		this.lblBulle.setText(this.tama.getEtatDeForme());
	}
}
